package com.indra.cmoff.service;

import java.util.List;

import com.indra.cmoff.dto.ModuloDTO;

public interface IModuloService extends ICrudService<ModuloDTO> {
	
	ModuloDTO findByCodigo(String codigo);

	List<ModuloDTO> findAllByEstado(Boolean estado);
	
}
