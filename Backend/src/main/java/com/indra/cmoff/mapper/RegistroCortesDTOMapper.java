package com.indra.cmoff.mapper;

import org.mapstruct.Mapper;


import com.indra.cmoff.dto.RegistroCortesDTO;

import com.indra.cmoff.model.RegistroCortes;

@Mapper(componentModel = "spring")
public interface RegistroCortesDTOMapper   extends IModelDTOMapper<RegistroCortes, RegistroCortesDTO> {
	
	
	RegistroCortesDTO entityToDto(RegistroCortes entity);
	RegistroCortes dtoToEntity(RegistroCortesDTO dto);

}
