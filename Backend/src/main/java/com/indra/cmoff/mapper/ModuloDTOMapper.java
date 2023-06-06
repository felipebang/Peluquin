package com.indra.cmoff.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.indra.cmoff.dto.ModuloDTO;
import com.indra.cmoff.model.Modulo;


@Mapper(componentModel = "spring")
public interface ModuloDTOMapper extends IModelDTOMapper<Modulo, ModuloDTO>{
	
	@Mappings({
		@Mapping(target="moduloEnlazado.moduloEnlazado", ignore = false),
	})
	Modulo dtoToEntity(ModuloDTO dto);
	
	@Mappings({
		@Mapping(target="moduloEnlazado.moduloEnlazado", ignore = true),
	})
	ModuloDTO entityToDto(Modulo entity);

}
