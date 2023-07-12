package com.indra.cmoff.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.indra.cmoff.dto.UsuarioRolDTO;
import com.indra.cmoff.repository.custom.IUsuarioRolDAO;
import com.indra.cmoff.service.IUsuarioRolService;

@Service
public class UsuarioRolServiceImpl implements IUsuarioRolService {
	
	private final IUsuarioRolDAO usuarioRolDAO;
	
	public UsuarioRolServiceImpl(IUsuarioRolDAO usuarioRolDAO) {
		this.usuarioRolDAO = usuarioRolDAO;
	}

	@Override
	public Optional<UsuarioRolDTO> findById(long id) {
		return usuarioRolDAO.findById(id);
	}

	@Override
	public List<UsuarioRolDTO> findAll() {
		return usuarioRolDAO.findAll();
	}

	@Override
	public List<UsuarioRolDTO> findAllPaginated(int page, int size) {
		return usuarioRolDAO.findAllPaginated(page, size).toList();
	}

	@Override
	public Page<UsuarioRolDTO> filterPaginated(int page, int size, UsuarioRolDTO entity, String column,
			String orderType) {
		return usuarioRolDAO.filterPaginated(page, size, entity,
				column, orderType);
	}

	@Override
	public long countFilter(UsuarioRolDTO entity) {
		return usuarioRolDAO.countFilter(entity);
	}

	@Override
	public UsuarioRolDTO save(UsuarioRolDTO entity) {
		return usuarioRolDAO.save(entity);
	}

	@Override
	public List<UsuarioRolDTO> saveList(List<UsuarioRolDTO> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(UsuarioRolDTO entity) {
		usuarioRolDAO.delete(entity);
		
	}

	@Override
	public void deleteById(long entityId) {
		usuarioRolDAO.deleteById(entityId);
		
	}

	@Override
	public List<UsuarioRolDTO> findByIdIdUsuario(Long idUsuario) {
		return usuarioRolDAO.findByIdIdUsuario(idUsuario);
	}

}
