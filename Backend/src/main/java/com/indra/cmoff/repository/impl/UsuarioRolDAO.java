package com.indra.cmoff.repository.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.indra.cmoff.dto.UsuarioRolDTO;
import com.indra.cmoff.mapper.UsuarioRolDTOMapper;
import com.indra.cmoff.model.QUsuarioRol;
import com.indra.cmoff.repository.UsuarioRolRepository;
import com.indra.cmoff.repository.custom.IUsuarioRolDAO;
import com.querydsl.core.types.dsl.BooleanExpression;

@Repository
public class UsuarioRolDAO implements IUsuarioRolDAO {
	
	private final UsuarioRolRepository repository;
	private final UsuarioRolDTOMapper usuarioRolDTOMapper;
	
	public UsuarioRolDAO(UsuarioRolRepository repository,
			UsuarioRolDTOMapper usuarioRolDTOMapper) {
		this.repository = repository;
		this.usuarioRolDTOMapper = usuarioRolDTOMapper;
	}

	@Override
	public Optional<UsuarioRolDTO> findById(long id) {
		//return repository.findById(id);
		return null;
	}

	@Override
	public List<UsuarioRolDTO> findAll() {
		return usuarioRolDTOMapper.entitiesToDtos(repository.findAll());
	}

	@Override
	public Page<UsuarioRolDTO> findAllPaginated(int page, int size) {
		PageRequest pagina = PageRequest.of(page, size);
		return usuarioRolDTOMapper.entityPageToDtoPage(pagina, repository.findAll(pagina));
	}

	@Override
	public Page<UsuarioRolDTO> filterPaginated(int page, int size, UsuarioRolDTO entity, String column, String orderType) {
		PageRequest pagina = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(orderType), column));
		return usuarioRolDTOMapper.entityPageToDtoPage(pagina, repository.findAll(getWhere(entity, false), pagina));
	}

	@Override
	public long countFilter(UsuarioRolDTO entity) {
		return repository.count(getWhere(entity, false));
	}

	private BooleanExpression getWhere(UsuarioRolDTO filtro, Boolean isAvanced) {
		BooleanExpression where = QUsuarioRol.usuarioRol.isNotNull();
		return where;
	}

	@Override
	public UsuarioRolDTO save(UsuarioRolDTO entity) {
		return usuarioRolDTOMapper.entityToDto(repository.saveAndFlush(usuarioRolDTOMapper.dtoToEntity(entity)));
	}

	@Override
	public List<UsuarioRolDTO> saveList(List<UsuarioRolDTO> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(UsuarioRolDTO entity) {
		repository.delete(usuarioRolDTOMapper.dtoToEntity(entity));
		
	}

	@Override
	public void deleteById(long entityId) {
		//repository.deleteById(entityId);
	}

	@Override
	public List<UsuarioRolDTO> findByIdIdUsuario(Long idUsuario) {
		return usuarioRolDTOMapper.entitiesToDtos(repository.findByIdIdUsuarioId(idUsuario));
	}
	
	@Override
	@Transactional
	public void eliminarRolesPorIdUsuario (Long idUser) {
		repository.eliminarRolesPorIdUsuario(idUser);
	}

}
