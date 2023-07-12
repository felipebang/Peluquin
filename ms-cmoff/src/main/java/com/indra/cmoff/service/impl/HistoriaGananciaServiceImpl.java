package com.indra.cmoff.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.indra.cmoff.dto.HistoriaGananciasDTO;
import com.indra.cmoff.repository.custom.IHistoriaGananciasDAO;
import com.indra.cmoff.service.IHistoriaGananciaService;

@Service
public class HistoriaGananciaServiceImpl implements IHistoriaGananciaService {

	private final IHistoriaGananciasDAO historiaGananciasDAO;

	public  HistoriaGananciaServiceImpl(IHistoriaGananciasDAO historiaGananciasDAO) {
		this.historiaGananciasDAO =  historiaGananciasDAO;
		
	}

	@Override
	public Optional<HistoriaGananciasDTO> findById(long id) {
		// TODO Auto-generated method stub
		Optional<HistoriaGananciasDTO> p = historiaGananciasDAO.findById(id);
		return p;
	}

	@Override
	public List<HistoriaGananciasDTO> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<HistoriaGananciasDTO> findAllPaginated(int page, int size) {
		// TODO Auto-generated method stub
		return historiaGananciasDAO.findAll();
	}

	@Override
	public Page<HistoriaGananciasDTO> filterPaginated(int page, int size, HistoriaGananciasDTO entity, String column,
			String orderType) {
		// TODO Auto-generated method stub
		return 	historiaGananciasDAO.filterPaginated(page, size, entity, column, orderType);

	}

	@Override
	public long countFilter(HistoriaGananciasDTO entity) {
		// TODO Auto-generated method stub
		return historiaGananciasDAO.countFilter(entity);
	}

	@Override
	public HistoriaGananciasDTO save(HistoriaGananciasDTO entity) {
		// TODO Auto-generated method stub
		return historiaGananciasDAO.save(entity);
	}

	@Override
	public List<HistoriaGananciasDTO> saveList(List<HistoriaGananciasDTO> entities) {
		// TODO Auto-generated method stub
		return historiaGananciasDAO.saveList(entities);
	}

	@Override
	public void delete(HistoriaGananciasDTO entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(long entityId) {
		// TODO Auto-generated method stub
		historiaGananciasDAO.deleteById(entityId);
	}

	@Override
	public HistoriaGananciasDTO findByCodEmpleado(Long codigo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<HistoriaGananciasDTO> filtroPagPorNombre(Integer page, Integer sizePerPage, HistoriaGananciasDTO filter,
			String column, String order) {
		// TODO Auto-generated method stub
		return null;
	}

}
