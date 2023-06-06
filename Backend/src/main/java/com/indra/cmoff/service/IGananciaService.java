package com.indra.cmoff.service;

import org.springframework.data.domain.Page;

import com.indra.cmoff.dto.GananciasDTO;

public interface IGananciaService  extends ICrudService<GananciasDTO>{

	Page<GananciasDTO> filtroPagPorNombre(Integer page, Integer sizePerPage, GananciasDTO filter, String column,
			String order);

	GananciasDTO findByCodEmpleado(Long codigo);


}
