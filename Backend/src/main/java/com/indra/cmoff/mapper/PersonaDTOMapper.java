package com.indra.cmoff.mapper;

import org.mapstruct.Mapper;

import com.indra.cmoff.dto.PersonaDTO;
import com.indra.cmoff.model.Persona;

@Mapper(componentModel = "spring")
public interface PersonaDTOMapper extends IModelDTOMapper<Persona, PersonaDTO> {

}
