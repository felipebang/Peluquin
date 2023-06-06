package com.indra.cmoff.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.indra.cmoff.dto.UsuarioRolDTO;
import com.indra.cmoff.model.UsuarioRol;

@Mapper(componentModel = "spring")
public interface UsuarioRolDTOMapper extends IModelDTOMapper<UsuarioRol, UsuarioRolDTO> {
	@Mappings({
		@Mapping(target = "rol", source = "id.rol"),
		@Mapping(target = "idUsuario", source = "id.idUsuario.id")
	})
	UsuarioRolDTO entityToDto(UsuarioRol entity);
	
	@Mappings({
		@Mapping(target="id.rol", source = "rol"),
		@Mapping(target="id.idUsuario.id", source = "idUsuario")
	})
	UsuarioRol dtoToEntity(UsuarioRolDTO dto);

}
