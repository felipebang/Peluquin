package com.indra.cmoff.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.indra.cmoff.dto.PermisoDTO;
import com.indra.cmoff.repository.custom.IPermisoDAO;
import com.indra.cmoff.service.IPermisoService;

@Service
public class PermisoServiceImpl implements IPermisoService {
	
	private final IPermisoDAO permisoDAO;
	
	public PermisoServiceImpl(IPermisoDAO permisoDAO) {
		this.permisoDAO = permisoDAO;
	}

	@Override
	public Optional<PermisoDTO> findById(long id) {
		return permisoDAO.findById(id);
	}

	@Override
	public List<PermisoDTO> findAll() {
		return permisoDAO.findAll();
	}

	@Override
	public List<PermisoDTO> findAllPaginated(int page, int size) {
		return permisoDAO.findAllPaginated(page, size).toList();
	}

	@Override
	public Page<PermisoDTO> filterPaginated(int page, int size, PermisoDTO entity, String column, String orderType) {
		return permisoDAO.filterPaginated(page, size, entity,
				column, orderType);
	}

	@Override
	public long countFilter(PermisoDTO entity) {
		return permisoDAO.countFilter(entity);
	}

	@Override
	public PermisoDTO save(PermisoDTO entity) {
		return permisoDAO.save(entity);
	}

	@Override
	public List<PermisoDTO> saveList(List<PermisoDTO> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(PermisoDTO entity) {
		permisoDAO.delete(entity);
		
	}

	@Override
	public void deleteById(long entityId) {
		permisoDAO.deleteById(entityId);
		
	}

}
