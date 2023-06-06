package com.indra.cmoff.mapper;

import org.mapstruct.Mapper;

import com.indra.cmoff.dto.GananciasDTO;
import com.indra.cmoff.model.Ganancias;




@Mapper(componentModel = "spring")
public interface GananciasDTOMapper  extends IModelDTOMapper< Ganancias, GananciasDTO> {
	
	 GananciasDTO entityToDto(Ganancias entity);
	 Ganancias dtoToEntity( GananciasDTO dto);
}
