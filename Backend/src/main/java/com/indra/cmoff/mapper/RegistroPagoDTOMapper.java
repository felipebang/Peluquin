package com.indra.cmoff.mapper;

import org.mapstruct.Mapper;


import com.indra.cmoff.dto.RegistroPagoDTO;

import com.indra.cmoff.model.RegistroPago;

@Mapper(componentModel = "spring")
public interface RegistroPagoDTOMapper  extends IModelDTOMapper<RegistroPago, RegistroPagoDTO> {
	

	RegistroPagoDTO entityToDto(RegistroPago entity);
	RegistroPago dtoToEntity(RegistroPagoDTO dto);

}
