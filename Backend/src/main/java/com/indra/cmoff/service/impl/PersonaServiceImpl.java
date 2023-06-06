package com.indra.cmoff.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.indra.cmoff.dto.PersonaDTO;
import com.indra.cmoff.repository.custom.IPersonaDAO;
import com.indra.cmoff.service.IPersonaService;

@Service
public class PersonaServiceImpl implements IPersonaService {

	private final IPersonaDAO personaDAO;

	public PersonaServiceImpl(IPersonaDAO personaDAO) {
		this.personaDAO = personaDAO;

	}

	@Override
	public Optional<PersonaDTO> findById(long id) {
		return personaDAO.findById(id);
	}

	@Override
	public List<PersonaDTO> findAll() {
		return personaDAO.findAll();
	}

	@Override
	public List<PersonaDTO> findAllPaginated(int page, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<PersonaDTO> filterPaginated(int page, int size, PersonaDTO entity, String column, String orderType) {
		return personaDAO.filterPaginated(page, size, entity, column, orderType);
	}

	@Override
	public long countFilter(PersonaDTO entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public PersonaDTO save(PersonaDTO entity) {
		// TODO Auto-generated method stub
		return personaDAO.save(entity);
	}

	@Override
	public List<PersonaDTO> saveList(List<PersonaDTO> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(PersonaDTO entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(long entityId) {
		// TODO Auto-generated method stub

	}

	@Override
	public PersonaDTO findByCodEmpleado(Long codigo) {
		return personaDAO.findByCodEmpleado(codigo);
	}

	@Override
	public Page<PersonaDTO> filtroPagPorNombre(Integer page, Integer sizePerPage, PersonaDTO filter, String column,
			String order) {
		return personaDAO.filterByNombre(page, sizePerPage, filter, column, order);
	}



}
