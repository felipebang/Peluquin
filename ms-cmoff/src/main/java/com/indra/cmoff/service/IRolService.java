package com.indra.cmoff.service;

import java.util.List;

import com.indra.cmoff.dto.PermisoRolDTO;
import com.indra.cmoff.dto.RolDTO;
import com.indra.cmoff.dto.UsuarioRolDTO;

public interface IRolService extends ICrudService<RolDTO> {

	//List<RolDTO> findRolDescripcionByIdUsuarioRol(Long idUsuario);
	List<UsuarioRolDTO> findUsuarioRolByIdIdUsuario(Long idUsuario);

	/**
	 * Metodo que obtiene el Rol apartir del identificador del rol
	 *
	 * @autor hiramirezc
	 * @param id
	 * @return rolDTO
	 */
	RolDTO obtenerRolPorId(long id);

	/**
	 * Metodo que permite guardar  el rol
	 * @param entity
	 * @return 
	 */
	Boolean saveRol(RolDTO entity);
	
	/**
	 * Metodo que permite obtener los Proyectos apartir de un estado
	 *
	 * @autor ddelgadoo
	 * @param estado
	 * @return List<Rol>
	 */
	List<RolDTO> findByEstado(Boolean estado);
	
	/**
	 * Metodo que permite actualizar la lista de roles asignada a un usuario 
	 *
	 * @autor ddelgadoo
	 * @param idUsuario
	 * @param List<UsuarioRolDTO>
	 * @return List<UsuarioRolDTO>
	 */
	List<UsuarioRolDTO> saveRolesUsuario(Long idUsuario, List<UsuarioRolDTO> rolesDTO);
	
	List<PermisoRolDTO> findPermisoRolByIdRolId(Long idRol);
	
	
	/**
	 * Metodo que permite obtener un rol a partir del codigo
	 * 
	 * @param codigo
	 * @return Rol
	 * @autor shenao
	 * */
	RolDTO findByCodigo (String codigo);

}
