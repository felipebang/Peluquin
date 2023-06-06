package com.indra.cmoff.mapper;

import org.mapstruct.Mapper;

import com.indra.cmoff.dto.PorcentajeDTO;
import com.indra.cmoff.model.Porcentaje;


@Mapper(componentModel = "spring")
public interface PorcentajeDTOMapper extends IModelDTOMapper<Porcentaje, PorcentajeDTO> {
	
	PorcentajeDTO entityToDto(Porcentaje entity);
	Porcentaje dtoToEntity(PorcentajeDTO dto);
}
