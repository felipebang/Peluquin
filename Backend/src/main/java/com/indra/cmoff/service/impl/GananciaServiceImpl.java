package com.indra.cmoff.service.impl;


import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import com.indra.cmoff.dto.GananciasDTO;
import com.indra.cmoff.repository.custom.IGananciasDAO;
import com.indra.cmoff.service.IGananciaService;



@Service
public class GananciaServiceImpl implements IGananciaService {
	
private final IGananciasDAO gananciasDAO;

public GananciaServiceImpl(IGananciasDAO gananciasDAO) {
	this.gananciasDAO = gananciasDAO;
	
}

@Override
public Optional<GananciasDTO> findById(long id) {
	// TODO Auto-generated method stub
	Optional<GananciasDTO> p = gananciasDAO.findById(id);
	return p;
}

@Override
public List<GananciasDTO> findAll() {
	// TODO Auto-generated method stub
	return null;
}

@Override
public List<GananciasDTO> findAllPaginated(int page, int size) {
	// TODO Auto-generated method stub
	return gananciasDAO.findAll();
}

@Override
public Page<GananciasDTO> filterPaginated(int page, int size, GananciasDTO entity, String column,
		String orderType) {
	// TODO Auto-generated method stub
	return 	gananciasDAO.filterPaginated(page, size, entity, column, orderType);

}

@Override
public long countFilter(GananciasDTO entity) {
	// TODO Auto-generated method stub
	return gananciasDAO.countFilter(entity);
}

@Override
public GananciasDTO save(GananciasDTO entity) {
	// TODO Auto-generated method stub
	return gananciasDAO.save(entity);
}

@Override
public List<GananciasDTO> saveList(List<GananciasDTO> entities) {
	// TODO Auto-generated method stub
	return gananciasDAO.saveList(entities);
}

@Override
public void delete(GananciasDTO entity) {
	// TODO Auto-generated method stub
	
}

@Override
public void deleteById(long entityId) {
	// TODO Auto-generated method stub
	gananciasDAO.deleteById(entityId);
}

@Override
public Page<GananciasDTO> filtroPagPorNombre(Integer page, Integer sizePerPage, GananciasDTO filter, String column,
		String order) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public GananciasDTO findByCodEmpleado(Long codigo) {
	// TODO Auto-generated method stub
	return null;
}


}
