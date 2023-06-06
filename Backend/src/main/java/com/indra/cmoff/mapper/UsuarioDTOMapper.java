package com.indra.cmoff.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.indra.cmoff.dto.UsuarioDTO;
import com.indra.cmoff.model.Usuario;

@Mapper(componentModel = "spring")
public interface UsuarioDTOMapper extends IModelDTOMapper<Usuario, UsuarioDTO> {
	
	@Mappings({
		@Mapping(target="clave", ignore=true)
	})
	UsuarioDTO entityToDto(Usuario entity);
	
	Usuario dtoToEntity(UsuarioDTO dto);
}
