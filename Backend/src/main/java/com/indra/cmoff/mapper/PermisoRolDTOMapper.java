package com.indra.cmoff.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.indra.cmoff.dto.PermisoRolDTO;
import com.indra.cmoff.model.PermisoRol;

@Mapper(componentModel = "spring")
public interface PermisoRolDTOMapper extends IModelDTOMapper<PermisoRol, PermisoRolDTO> {
	
	@Mappings({
		@Mapping(target = "permiso", source = "id.permiso"),
		@Mapping(target = "modulo", source = "id.modulo"),
		@Mapping(target = "rol", source = "id.rol")
	})
	PermisoRolDTO entityToDto(PermisoRol entity);
	
	@Mappings({
		@Mapping(target="id.permiso", source = "permiso"),
		@Mapping(target="id.modulo", source = "modulo"),
		@Mapping(target="id.rol", source = "rol")
	})
	PermisoRol dtoToEntity(PermisoRolDTO dto);

}
