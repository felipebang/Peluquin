package com.indra.cmoff.mapper;

import org.mapstruct.Mapper;

import com.indra.cmoff.dto.HistoriaGananciasDTO;

import com.indra.cmoff.model.HistoriaGanancias;


@Mapper(componentModel = "spring")
public interface HistoriaGananciasDTOMapper extends IModelDTOMapper<HistoriaGanancias, HistoriaGananciasDTO> {

	HistoriaGananciasDTO entityToDto(HistoriaGanancias entity);
	HistoriaGanancias dtoToEntity(HistoriaGananciasDTO dto);
}
