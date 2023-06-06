package com.indra.cmoff.service;

import org.springframework.data.domain.Page;

import com.indra.cmoff.dto.PersonaDTO;

public interface IPersonaService extends ICrudService<PersonaDTO> {

	PersonaDTO findByCodEmpleado(Long codigo);

	Page<PersonaDTO> filtroPagPorNombre(Integer page, Integer sizePerPage, PersonaDTO filter, String column,
			String order);

	PersonaDTO save(PersonaDTO p);
}
