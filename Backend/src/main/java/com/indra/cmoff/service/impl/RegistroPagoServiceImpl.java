package com.indra.cmoff.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.indra.cmoff.dto.RegistroPagoDTO;

import com.indra.cmoff.repository.custom.IRegistroPagoDAO;
import com.indra.cmoff.service.IRegistroPagoService;

@Service
public class RegistroPagoServiceImpl implements IRegistroPagoService {

	private final IRegistroPagoDAO registroPagoDAO;

	public RegistroPagoServiceImpl(IRegistroPagoDAO registroPagoDAO) {
		this.registroPagoDAO = registroPagoDAO;

	}

	@Override
	public Optional<RegistroPagoDTO> findById(long id) {
		// TODO Auto-generated method stub
		Optional<RegistroPagoDTO> p = registroPagoDAO.findById(id);
		return p;
	}

	@Override
	public List<RegistroPagoDTO> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RegistroPagoDTO> findAllPaginated(int page, int size) {
		// TODO Auto-generated method stub
		return registroPagoDAO.findAll();
	}

	@Override
	public Page<RegistroPagoDTO> filterPaginated(int page, int size, RegistroPagoDTO entity, String column,
			String orderType) {
		// TODO Auto-generated method stub
		return registroPagoDAO.filterPaginated(page, size, entity, column, orderType);

	}

	@Override
	public long countFilter(RegistroPagoDTO entity) {
		// TODO Auto-generated method stub
		return registroPagoDAO.countFilter(entity);
	}

	@Override
	public RegistroPagoDTO save(RegistroPagoDTO entity) {
		// TODO Auto-generated method stub
		return registroPagoDAO.save(entity);
	}

	@Override
	public List<RegistroPagoDTO> saveList(List<RegistroPagoDTO> entities) {
		// TODO Auto-generated method stub
		return registroPagoDAO.saveList(entities);
	}

	@Override
	public void delete(RegistroPagoDTO entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(long entityId) {
		// TODO Auto-generated method stub
		registroPagoDAO.deleteById(entityId);
	}

	@Override
	public RegistroPagoDTO findByCodEmpleado(Long codigo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<RegistroPagoDTO> filtroPagPorNombre(Integer page, Integer sizePerPage, RegistroPagoDTO filter,
			String column, String order) {
		// TODO Auto-generated method stub
		return null;
	}

}
