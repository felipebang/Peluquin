package com.indra.cmoff.service;

import org.springframework.data.domain.Page;


import com.indra.cmoff.dto.RegistroCortesDTO;
import com.indra.cmoff.model.RegistroCortes;

public interface IRegistroCorteService  extends ICrudService<RegistroCortesDTO> {

	RegistroCortesDTO findByCodEmpleado(Long codigo);

	Page<RegistroCortesDTO> filtroPagPorNombre(Integer page, Integer sizePerPage, RegistroCortesDTO filter,
			String column, String order);

	RegistroCortes deleteregistroCorte(int id);







}
