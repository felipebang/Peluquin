package com.indra.cmoff.repository.custom;

import java.util.List;

import com.indra.cmoff.dto.ModuloDTO;

public interface IModuloDAO extends ICrudDAO<ModuloDTO> {

	/**
	 * Metodo que permite obtener todos los modulos filtrados por el estado TRUE o FALSE
	 *
	 * @autor hiramirezc
	 * @param estado
	 * @return Lista de modulos filtrados por el estado
	 */
	List<ModuloDTO> findAllByEstadoOrderByModuloEnlazadoDesc(Boolean estado);
	
	ModuloDTO findByCodigo(String codigo);
	
	List<Long> findAllParentsByEstadoTrue();
}
