package com.indra.itiformativos.repository.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.indra.itiformativos.dto.PermisoRolDTO;
import com.indra.itiformativos.mapper.PermisoRolDTOMapper;
import com.indra.itiformativos.repository.PermisoRolRepository;
import com.indra.itiformativos.repository.custom.IPermisoRolDAO;

@Repository
public class PermisoRolDAO implements IPermisoRolDAO {

	private final PermisoRolRepository repository;
	private final PermisoRolDTOMapper permisoRolDTOMapper;

	public PermisoRolDAO(PermisoRolRepository repository,PermisoRolDTOMapper permisoRolDTOMapper) {
		this.repository = repository;
		this.permisoRolDTOMapper = permisoRolDTOMapper;
	}

	@Override
	public Optional<PermisoRolDTO> findById(long id) {
		return permisoRolDTOMapper.optionalEntityToDto(repository.findById(id));
	}

	@Override
	public List<PermisoRolDTO> findAll() {
		return permisoRolDTOMapper.entitiesToDtos(repository.findAll());
	}

	@Override
	public Page<PermisoRolDTO> findAllPaginated(int page, int size) {
		PageRequest pagina = PageRequest.of(page, size);
		return permisoRolDTOMapper.entityPageToDtoPage(pagina, repository.findAll(pagina));
	}

	@Override
	public Page<PermisoRolDTO> filterPaginated(int page, int size, PermisoRolDTO entity, String column, String orderType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long countFilter(PermisoRolDTO entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public PermisoRolDTO save(PermisoRolDTO entity) {
		return permisoRolDTOMapper.entityToDto(repository.saveAndFlush(permisoRolDTOMapper.dtoToEntity(entity)));
	}

	@Override
	public List<PermisoRolDTO> saveList(List<PermisoRolDTO> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(PermisoRolDTO entity) {
		repository.delete(permisoRolDTOMapper.dtoToEntity(entity));

	}

	@Override
	public void deleteById(long entityId) {
		repository.deleteById(entityId);
	}

	@Override
	public List<PermisoRolDTO> findByIdModulo_IdAndIdRol_Id(Long idModulo,Long idRol) {
		return permisoRolDTOMapper.entitiesToDtos(repository.findByIdModuloIdAndIdRolId(idModulo,idRol));
	}

	@Override
	public List<PermisoRolDTO> findByIdRolId(Long idRol) {
		return permisoRolDTOMapper.entitiesToDtos(repository.findByIdRolId(idRol));
	}
	
	@Override
	@Transactional
	public void eliminarPermisosRolPorIdRol (Long idRol) {
		repository.eliminarPermisosRolPorIdRol(idRol);
	}

}
