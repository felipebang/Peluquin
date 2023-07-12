package com.indra.cmoff.service;

import org.springframework.data.domain.Page;


import com.indra.cmoff.dto.RegistroPagoDTO;

public interface IRegistroPagoService  extends ICrudService<RegistroPagoDTO>{

	RegistroPagoDTO findByCodEmpleado(Long codigo);

	Page<RegistroPagoDTO> filtroPagPorNombre(Integer page, Integer sizePerPage, RegistroPagoDTO filter, String column,
			String order);




}
