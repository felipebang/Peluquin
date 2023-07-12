package com.indra.cmoff.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.indra.cmoff.dto.ModuloDTO;
import com.indra.cmoff.dto.PermisoDTO;
import com.indra.cmoff.dto.PermisoRolDTO;
import com.indra.cmoff.repository.custom.IModuloDAO;
import com.indra.cmoff.repository.custom.IPermisoDAO;
import com.indra.cmoff.repository.custom.IPermisoRolDAO;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import com.indra.cmoff.dto.RolDTO;
import com.indra.cmoff.dto.UsuarioRolDTO;
import com.indra.cmoff.repository.custom.IRolDAO;
import com.indra.cmoff.repository.custom.IUsuarioRolDAO;
import com.indra.cmoff.service.IRolService;

@Service
public class RolServiceImpl implements IRolService {

	private final IRolDAO rolDAO;
	private final IUsuarioRolDAO iusuarioRolDAO;
	private final IPermisoRolDAO iPermisoRolDAO;
	private final IModuloDAO iModuloDAO;
	private final IPermisoDAO iPermisoDAO;
	@Value("${cmoff.adm.rol}")
	private String adminRole;

	public RolServiceImpl(IRolDAO rolDAO, IUsuarioRolDAO iusuarioRolDAO, IPermisoRolDAO iPermisoRolDAO,
			IModuloDAO iModuloDAO, IPermisoDAO iPermisoDAO) {
		super();
		this.rolDAO = rolDAO;
		this.iusuarioRolDAO = iusuarioRolDAO;
		this.iPermisoRolDAO = iPermisoRolDAO;
		this.iModuloDAO = iModuloDAO;
		this.iPermisoDAO = iPermisoDAO;
	}

	@Override
	public Optional<RolDTO> findById(long id) {
		return rolDAO.findById(id);
	}

	@Override
	public List<RolDTO> findAll() {
		return rolDAO.findAll();
	}

	@Override
	public List<RolDTO> findAllPaginated(int page, int size) {
		return rolDAO.findAllPaginated(page, size).toList();
	}

	@Override
	public Page<RolDTO> filterPaginated(int page, int size, RolDTO entity, String column, String orderType) {
		return rolDAO.filterPaginated(page, size, entity, column, orderType);
	}

	@Override
	public long countFilter(RolDTO entity) {
		return rolDAO.countFilter(entity);
	}

	@Override
	public RolDTO save(RolDTO entity) {
		return rolDAO.save(entity);
	}

	@Override
	public List<RolDTO> saveList(List<RolDTO> entities) {
		return null;
	}

	@Override
	public void delete(RolDTO entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(long entityId) {
		rolDAO.deleteById(entityId);
	}

	@Override
	public RolDTO obtenerRolPorId(long id) {
		Optional<RolDTO> rolOptional = rolDAO.findById(id);
		RolDTO rolDto = new RolDTO();
		if (rolOptional.isPresent()) {
			rolDto = obtenerModuloAndPermisos(rolOptional.get(), id);
		}
		return rolDto;
	}

	/**
	 * Metodo que permite obtener el RolDTO con sus modulos y sus permisos
	 *
	 * @param rolDto
	 * @param idRol
	 * @return RolDTO
	 * @autor ddelgadoo
	 */
	private RolDTO obtenerModuloAndPermisos(RolDTO rolDto, Long idRol) {
		List<Long> idsModulosPadres = iModuloDAO.findAllParentsByEstadoTrue();
		List<ModuloDTO> modulos = iModuloDAO.findAllByEstadoOrderByModuloEnlazadoDesc(Boolean.TRUE);
		List<ModuloDTO> listModulos = new ArrayList<>();
		//Si es rol Administrador se agregan los modulos y permisos existentes
		if(!StringUtils.equals(rolDto.getCodigo(), adminRole)) {
			
			for (ModuloDTO moduloDto : modulos) {
				List<PermisoDTO> permisos = new ArrayList<>();
				List<PermisoRolDTO> permisosRol = iPermisoRolDAO.findByIdModulo_IdAndIdRol_Id(moduloDto.getId(), idRol);
				List<PermisoRolDTO> permisosRol2 = iPermisoRolDAO.findByIdRolId(idRol);
				//si es un modulo padre no se agrega
				if(idsModulosPadres.indexOf(moduloDto.getId()) == -1) {
					for(PermisoRolDTO permisoRolDTO : permisosRol) {
						//si tiene modulo enlazado se evalua que exista el permiso tanto en el padre como en el hijo
						if(permisoRolDTO.getModulo().getModuloEnlazado() != null) {
							PermisoRolDTO permisoRolDTOEnlazado = null;
							for (PermisoRolDTO permisoRolDTO2 : permisosRol2) {
								//si es el modulo padre (enlazado->null) y si (hijo, padre) tienen el mismo modulo
								if(permisoRolDTO2.getModulo().getModuloEnlazado() == null 
										&& StringUtils.equals(permisoRolDTO2.getModulo().getCodigo(), permisoRolDTO.getModulo().getModuloEnlazado().getCodigo()) 
										&& StringUtils.equals(permisoRolDTO2.getPermiso().getCodigo(), permisoRolDTO.getPermiso().getCodigo())) {
									permisoRolDTOEnlazado = permisoRolDTO2;
									break;
								}
							}
							if(permisoRolDTOEnlazado != null && 
									StringUtils.equals(permisoRolDTOEnlazado.getPermiso().getCodigo(), 
											permisoRolDTO.getPermiso().getCodigo())) {
								permisos.add(permisoRolDTO.getPermiso());
							}
						}else {
							permisos.add(permisoRolDTO.getPermiso());
						}
					}
					moduloDto.setPermisoDTOList(permisos);
					if(permisos != null && !permisos.isEmpty()) {
						listModulos.add(moduloDto);
					}
				}
			}
		}else {
			List<PermisoDTO> permisos = iPermisoDAO.findAll();
			for (ModuloDTO moduloDto : modulos) {
				//si es un modulo padre no se agrega
				if(idsModulosPadres.indexOf(moduloDto.getId()) == -1) {
					moduloDto.setPermisoDTOList(permisos);
					listModulos.add(moduloDto);
				}
			}
		}
		rolDto.setModulosDtoList(listModulos);
		return rolDto;
	}

	@Override
	public Boolean saveRol(RolDTO entity) {
		Boolean eject = true;
		//Si no es rol Administrador se agregan los modulos y permisos asociados
		if(!StringUtils.equals(entity.getCodigo(), adminRole) || 
				(StringUtils.equals(entity.getCodigo(), adminRole) && 
				Objects.isNull(entity.getId()))) {
			//Si es rol administrador se fija como activo
			if(StringUtils.equals(entity.getCodigo(), adminRole))
					entity.setEstado(Boolean.TRUE);
			RolDTO nuevoRol = rolDAO.save(entity);
			if(!StringUtils.equals(entity.getCodigo(), adminRole) && 
					entity.getModulosDtoList().size() > 0) {
				iPermisoRolDAO.eliminarPermisosRolPorIdRol(nuevoRol.getId());
				for (ModuloDTO moduloDto : entity.getModulosDtoList()) {
					if(moduloDto.getPermisoDTOList() != null && !moduloDto.getPermisoDTOList().isEmpty()) {
						for (PermisoDTO permisoDTO : moduloDto.getPermisoDTOList()) {
							PermisoRolDTO permisoRolDto = new PermisoRolDTO(permisoDTO, moduloDto, nuevoRol);
							iPermisoRolDAO.save(permisoRolDto);
						}
					}
				}
			}
			return eject;
		} else {
			eject = false;
			return eject;
		}
	}
	
	@Override
	public List<UsuarioRolDTO> findUsuarioRolByIdIdUsuario(Long idUsuario) {
		return iusuarioRolDAO.findByIdIdUsuario(idUsuario);
	}

	@Override
	public List<RolDTO> findByEstado(Boolean estado) {
		return rolDAO.findByEstado(estado);
	}

	@Override
	public List<UsuarioRolDTO> saveRolesUsuario(Long idUsuario, List<UsuarioRolDTO> usuarioRolesDTO) {
		UsuarioRolDTO newUsuarioRolDTO;
		List<UsuarioRolDTO> newUsuarioRolesDTO = new ArrayList<UsuarioRolDTO>();
		iusuarioRolDAO.eliminarRolesPorIdUsuario(idUsuario);
		for (UsuarioRolDTO usuarioRol : usuarioRolesDTO) {
			Optional<RolDTO> rolDTO = rolDAO.findById(usuarioRol.getRol().getId());
			if(rolDTO.isPresent()) {
				if(StringUtils.equals(rolDTO.get().getCodigo(), adminRole)) {
					newUsuarioRolesDTO = new ArrayList<UsuarioRolDTO>();
					newUsuarioRolDTO = new UsuarioRolDTO(idUsuario, rolDTO.get());
					newUsuarioRolesDTO.add(newUsuarioRolDTO);
					break;
				}else {
					newUsuarioRolDTO = new UsuarioRolDTO(idUsuario, rolDTO.get());
					newUsuarioRolesDTO.add(newUsuarioRolDTO);
				}
			}
		}
		for (UsuarioRolDTO newUsuarioRol : newUsuarioRolesDTO) {
			iusuarioRolDAO.save(newUsuarioRol);
		}
		return usuarioRolesDTO;
	}

	@Override
	public List<PermisoRolDTO> findPermisoRolByIdRolId(Long idRol) {
		return iPermisoRolDAO.findByIdRolId(idRol);
	}

	@Override
	public RolDTO findByCodigo(String codigo) {
		return rolDAO.findByCodigo(codigo);
	}

}
