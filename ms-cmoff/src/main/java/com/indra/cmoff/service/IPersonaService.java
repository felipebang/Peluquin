package com.indra.cmoff.service;

import javax.validation.Valid;

import org.springframework.data.domain.Page;

import com.indra.cmoff.dto.PersonaDTO;

public interface IPersonaService extends ICrudService<PersonaDTO> {

	PersonaDTO findByCodEmpleado(Long codigo);

	Page<PersonaDTO> filtroPagPorNombre(Integer page, Integer sizePerPage, PersonaDTO filter, String column,
			String order);

	PersonaDTO save(PersonaDTO p);

	Object editar(@Valid PersonaDTO personaDto);
}
