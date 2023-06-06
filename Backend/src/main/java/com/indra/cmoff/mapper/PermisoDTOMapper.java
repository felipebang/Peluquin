package com.indra.cmoff.mapper;

import org.mapstruct.Mapper;

import com.indra.cmoff.dto.PermisoDTO;
import com.indra.cmoff.model.Permiso;

@Mapper(componentModel = "spring")
public interface PermisoDTOMapper extends IModelDTOMapper<Permiso, PermisoDTO> {
	
	PermisoDTO entityToDto(Permiso entity);
	
	Permiso dtoToEntity(PermisoDTO dto);

}
