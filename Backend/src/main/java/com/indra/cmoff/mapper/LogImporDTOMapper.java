package com.indra.cmoff.mapper;

import org.mapstruct.Mapper;

import com.indra.cmoff.dto.LogImporDTO;
import com.indra.cmoff.model.LogImpor;

@Mapper(componentModel = "spring")
public interface LogImporDTOMapper extends IModelDTOMapper<LogImpor, LogImporDTO>{

}
