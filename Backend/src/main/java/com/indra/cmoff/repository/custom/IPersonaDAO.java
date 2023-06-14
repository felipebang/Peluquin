package com.indra.cmoff.repository.custom;



import javax.validation.Valid;

import org.springframework.data.domain.Page;

import com.indra.cmoff.dto.PersonaDTO;

public interface IPersonaDAO extends ICrudDAO<PersonaDTO> {

	PersonaDTO findByCodEmpleado(Long codigo);
	
	Page<PersonaDTO> filterByNombre(Integer page, Integer sizePerPage, PersonaDTO filter, String column, String order);

	Object editar(@Valid PersonaDTO id);


}
