package com.indra.cmoff.service.impl;



import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.indra.cmoff.dto.ModuloDTO;
import com.indra.cmoff.repository.custom.IModuloDAO;
import com.indra.cmoff.service.IModuloService;

@Service
public class ModuloServiceImpl implements IModuloService {

	private final IModuloDAO moduloDAO;

	public ModuloServiceImpl(IModuloDAO moduloDAO) {
		this.moduloDAO = moduloDAO;
	}

	@Override
	public Optional<ModuloDTO> findById(long id) {
		return moduloDAO.findById(id);
	}

	@Override
	public List<ModuloDTO> findAll() {
		return moduloDAO.findAll();
	}

	@Override
	public List<ModuloDTO> findAllPaginated(int page, int size) {
		return  moduloDAO.findAllPaginated(page, size).toList();
	}

	@Override
	public Page<ModuloDTO> filterPaginated(int page, int size, ModuloDTO entity, String column, String orderType) {
		return moduloDAO.filterPaginated(page, size, entity, column, orderType);
	}

	@Override
	public long countFilter(ModuloDTO entity) {
		return moduloDAO.countFilter(entity);
	}

	@Override
	public ModuloDTO save(ModuloDTO entity) {
		return moduloDAO.save(entity);
	}

	@Override
	public List<ModuloDTO> saveList(List<ModuloDTO> entities) {
		List<ModuloDTO> resultEntities = new ArrayList<>();
		for (ModuloDTO modulo : entities) {
			resultEntities.add(moduloDAO.save(modulo));
		}
		return resultEntities;
	}

	@Override
	public void delete(ModuloDTO entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(long entityId) {
		moduloDAO.deleteById(entityId);
	}

	@Override
	public ModuloDTO findByCodigo(String codigo) {
		return moduloDAO.findByCodigo(codigo);
	}
	
	@Override
	public List<ModuloDTO> findAllByEstado(Boolean estado) {
		return moduloDAO.findAllByEstadoOrderByModuloEnlazadoDesc(estado);
	}

}
