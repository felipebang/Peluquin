package com.indra.cmoff.mapper;

import org.mapstruct.Mapper;

import com.indra.cmoff.dto.RolDTO;
import com.indra.cmoff.model.Rol;

@Mapper(componentModel = "spring")
public interface RolDTOMapper extends IModelDTOMapper<Rol, RolDTO> {
	
	RolDTO entityToDto(Rol entity);
	
	Rol dtoToEntity(RolDTO dto);

}
