package com.indra.cmoff.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.indra.cmoff.dto.PorcentajeDTO;
import com.indra.cmoff.repository.custom.IPorcentajeDAO;
import com.indra.cmoff.service.IPorcentajeService;
@Service
public class PorcentajeServiceImpl implements IPorcentajeService {

	private final IPorcentajeDAO porcentajeDAO;
	
	public PorcentajeServiceImpl(IPorcentajeDAO porcentajeDAO) {
		this.porcentajeDAO = porcentajeDAO;
		
	}
	
	@Override
	public Optional<PorcentajeDTO> findById(long id) {
		// TODO Auto-generated method stub
		Optional<PorcentajeDTO> p = porcentajeDAO.findById(id);
		return p;
	}

	@Override
	public List<PorcentajeDTO> findAll() {
		// TODO Auto-generated method stub
		return this.porcentajeDAO.findAll();
	}

	@Override
	public List<PorcentajeDTO> findAllPaginated(int page, int size) {
		// TODO Auto-generated method stub
		return porcentajeDAO.findAll();
	}

	@Override
	public Page<PorcentajeDTO> filterPaginated(int page, int size, PorcentajeDTO entity, String column,
			String orderType) {
		// TODO Auto-generated method stub
		return 	porcentajeDAO.filterPaginated(page, size, entity, column, orderType);

	}

	@Override
	public long countFilter(PorcentajeDTO entity) {
		// TODO Auto-generated method stub
		return porcentajeDAO.countFilter(entity);
	}

	@Override
	public PorcentajeDTO save(PorcentajeDTO entity) {
		// TODO Auto-generated method stub
		return porcentajeDAO.save(entity);
	}

	@Override
	public List<PorcentajeDTO> saveList(List<PorcentajeDTO> entities) {
		// TODO Auto-generated method stub
		return porcentajeDAO.saveList(entities);
	}

	@Override
	public void delete(PorcentajeDTO entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(long entityId) {
		// TODO Auto-generated method stub
		porcentajeDAO.deleteById(entityId);
	}

	@Override
	public PorcentajeDTO findByCodEmpleado(Long codigo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<PorcentajeDTO> filtroPagPorNombre(Integer page, Integer sizePerPage, PorcentajeDTO filter,
			String column, String order) {
		// TODO Auto-generated method stub
		return null;
	}

}
