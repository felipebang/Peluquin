package com.indra.cmoff.repository.custom;

import java.util.List;
import com.indra.cmoff.dto.PermisoRolDTO;

public interface IPermisoRolDAO extends ICrudDAO<PermisoRolDTO> {
	/**
	 * Metodo que permite consultar los permisosRol apartir del Identificador del modulo y el identificador del rol
	 *
	 * @autor hiramirezc
	 * @param idModulo
	 * @param idRol
	 * @return lista de permisoRol
	 */
	List<PermisoRolDTO> findByIdModulo_IdAndIdRol_Id(Long idModulo, Long idRol);
	
	List<PermisoRolDTO> findByIdRolId(Long idRol);

	void eliminarPermisosRolPorIdRol(Long idRol);
}
