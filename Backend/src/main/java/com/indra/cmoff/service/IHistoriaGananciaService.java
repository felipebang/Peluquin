package com.indra.cmoff.service;

import org.springframework.data.domain.Page;

import com.indra.cmoff.dto.HistoriaGananciasDTO;

public interface IHistoriaGananciaService  extends ICrudService<HistoriaGananciasDTO> {

	HistoriaGananciasDTO findByCodEmpleado(Long codigo);

	Page<HistoriaGananciasDTO> filtroPagPorNombre(Integer page, Integer sizePerPage, HistoriaGananciasDTO filter,
			String column, String order);

}
