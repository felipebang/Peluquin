package com.indra.cmoff.repository.impl;

import com.google.common.base.Strings;
import com.indra.cmoff.dto.UsuarioDTO;
import com.indra.cmoff.mapper.UsuarioDTOMapper;
import com.indra.cmoff.model.QUsuario;
import com.indra.cmoff.repository.UsuarioRepository;
import com.indra.cmoff.repository.custom.IUsuarioDAO;
import com.querydsl.core.types.dsl.BooleanExpression;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UsuarioDAO implements IUsuarioDAO {
	
	private final UsuarioRepository repository;
	private final UsuarioDTOMapper usuarioMapper;
	
	public UsuarioDAO(UsuarioRepository repository, UsuarioDTOMapper usuarioMapper) {
		this.repository = repository;
		this.usuarioMapper = usuarioMapper;
	}

	@Override
	public Optional<UsuarioDTO> findById(long id) {
		return usuarioMapper.optionalEntityToDto(repository.findById(id));
	}

	@Override
	public List<UsuarioDTO> findAll() {
		return usuarioMapper.entitiesToDtos(repository.findAll());
	}

	@Override
	public Page<UsuarioDTO> findAllPaginated(int page, int size) {
		PageRequest pagina = PageRequest.of(page, size);
		return usuarioMapper.entityPageToDtoPage(pagina, repository.findAll(pagina));
	}

	@Override
	public Page<UsuarioDTO> filterPaginated(int page, int size, UsuarioDTO entity, String column, String orderType) {
		PageRequest pagina = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(orderType), column));
		return usuarioMapper.entityPageToDtoPage(pagina, repository.findAll(getWhere(entity), pagina));
	}

	@Override
	public long countFilter(UsuarioDTO entity) {
		return repository.count(getWhere(entity));
	}

	@Override
	public UsuarioDTO save(UsuarioDTO entity) {
		return usuarioMapper.entityToDto(repository.saveAndFlush(usuarioMapper.dtoToEntity(entity)));
	}

	@Override
	public void delete(UsuarioDTO entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(long entityId) {
		repository.deleteById(entityId);
	}
	
	public BooleanExpression getWhere(UsuarioDTO filtro) {
		BooleanExpression where = QUsuario.usuario1.isNotNull();
		if (!Strings.isNullOrEmpty(filtro.getUsuario())) {
			where = where.and(QUsuario.usuario1.usuario.containsIgnoreCase(filtro.getUsuario()));
		}
		if (ObjectUtils.isNotEmpty(filtro.getPersona())) {
			where = where.and(QUsuario.usuario1.persona.eq(filtro.getPersona()));
		}
		return where;
	}

	@Override
	public UsuarioDTO findByUsuarioAndClave(String usuario, String clave) {
		return usuarioMapper.entityToDto(repository.findByUsuarioAndClave(usuario, clave));
	}

	@Override
	public List<UsuarioDTO> saveList(List<UsuarioDTO> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<UsuarioDTO> findByUsuario(String usuario) {
		return usuarioMapper.optionalEntityToDto(repository.findByUsuario(usuario));
	}

	@Override
	public String getPasswordByUsuarioId(Long usuarioId) {
		return repository.getPasswordByUsuarioId(usuarioId);
	}

	@Override
	public UsuarioDTO findByPersonaCodigoEmpleado(Long codEmpleado) {
		return usuarioMapper.entityToDto(repository.findByPersona(codEmpleado));
	}
	
}
