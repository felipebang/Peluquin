package com.indra.cmoff.service;

import org.springframework.data.domain.Page;

import com.indra.cmoff.dto.PorcentajeDTO;

public interface IPorcentajeService extends ICrudService<PorcentajeDTO> {

	PorcentajeDTO findByCodEmpleado(Long codigo);

	Page<PorcentajeDTO> filtroPagPorNombre(Integer page, Integer sizePerPage, PorcentajeDTO filter, String column,
			String order);

}
