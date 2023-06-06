package com.indra.cmoff.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;


import com.indra.cmoff.dto.RegistroCortesDTO;
import com.indra.cmoff.model.RegistroCortes;
import com.indra.cmoff.repository.custom.IRegistroCortesDAO;
import com.indra.cmoff.service.IRegistroCorteService;





@Service
public class RegistroCorteServiceImpl   implements IRegistroCorteService{
	
	

private final IRegistroCortesDAO  registroCortesDAO;
	
	public RegistroCorteServiceImpl(IRegistroCortesDAO registroCortesDAO) {
		this.registroCortesDAO = registroCortesDAO;
		
	}
	
	@Override
	public Optional<RegistroCortesDTO> findById(long id) {
		// TODO Auto-generated method stub
		Optional<RegistroCortesDTO> p = registroCortesDAO.findById(id);
		return p;
	}

	@Override
	public List<RegistroCortesDTO> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RegistroCortesDTO> findAllPaginated(int page, int size) {
		// TODO Auto-generated method stub
		return registroCortesDAO.findAll();
	}

	@Override
	public Page<RegistroCortesDTO> filterPaginated(int page, int size, RegistroCortesDTO entity, String column,
			String orderType) {
		// TODO Auto-generated method stub
		return 	registroCortesDAO.filterPaginated(page, size, entity, column, orderType);

	}

	@Override
	public long countFilter(RegistroCortesDTO entity) {
		// TODO Auto-generated method stub
		return registroCortesDAO.countFilter(entity);
	}

	@Override
	public RegistroCortesDTO save(RegistroCortesDTO entity) {
		// TODO Auto-generated method stub
		return registroCortesDAO.save(entity);
	}

	@Override
	public List<RegistroCortesDTO> saveList(List<RegistroCortesDTO> entities) {
		// TODO Auto-generated method stub
		return registroCortesDAO.saveList(entities);
	}

	@Override
	public void delete(RegistroCortesDTO entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(long entityId) {
		// TODO Auto-generated method stub
		registroCortesDAO.deleteById(entityId);
	}

	@Override
	public Page<RegistroCortesDTO> filtroPagPorNombre(Integer page, Integer sizePerPage, RegistroCortesDTO filter,
			String column, String order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RegistroCortesDTO findByCodEmpleado(Long codigo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RegistroCortes deleteregistroCorte(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
