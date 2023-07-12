package com.indra.cmoff.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;


import java.sql.SQLException;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.google.common.base.Strings;
import com.indra.cmoff.dto.PersonaDTO;
import com.indra.cmoff.mapper.PersonaDTOMapper;
import com.indra.cmoff.model.QPersona;
import com.indra.cmoff.repository.PersonaRepository;
import com.indra.cmoff.repository.custom.IPersonaDAO;
import com.querydsl.core.types.dsl.BooleanExpression;










@Repository
public class PersonaDAO implements IPersonaDAO{
	
	@Value("${spring.datasource.url}")
	private String url;
	
	@Value("${spring.datasource.username}")
	private String user;
	
	@Value("${spring.datasource.password}")
	private String password;
	
	private final PersonaRepository repository;
	private final PersonaDTOMapper mapper;
	
	public PersonaDAO(PersonaRepository repository, PersonaDTOMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}

	@Override
	public Optional<PersonaDTO> findById(long id) {
		return mapper.optionalEntityToDto(repository.findById(id));
	}

	@Override
	public List<PersonaDTO> findAll() {
		return mapper.entitiesToDtos(repository.findAll());
	}

	@Override
	public Page<PersonaDTO> findAllPaginated(int page, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<PersonaDTO> filterPaginated(int page, int size, PersonaDTO entity, String column, String orderType) {
		PageRequest pagina = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(orderType), column));
		return mapper.entityPageToDtoPage(pagina, repository.findAll(getWhere(entity), pagina));
	}

	private BooleanExpression getWhere(PersonaDTO entity) {
		BooleanExpression where = QPersona.persona.isNotNull();
		if (!Strings.isNullOrEmpty(entity.getNombres())) {
			where = where.and(QPersona.persona.nombres.append(" ").append(QPersona.persona.apellido1).append(" ")
					.append(QPersona.persona.apellido2.coalesce("").toString()).containsIgnoreCase(entity.getNombres()));
		}
		if (ObjectUtils.isNotEmpty(entity.getCodigoEmpleado())) {
			where = where.and(QPersona.persona.codigoEmpleado.eq(entity.getCodigoEmpleado()));
		}
		if (!Strings.isNullOrEmpty(entity.getEmail())) {
			where = where.and(QPersona.persona.empresa.containsIgnoreCase(entity.getEmail()));
		}
	
		return where;
	}

	@Override
	public long countFilter(PersonaDTO entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public PersonaDTO save(PersonaDTO entity) {
		// TODO Auto-generated method stub
		return 	mapper.entityToDto(repository.saveAndFlush(mapper.dtoToEntity(entity)));

	}

	@Override
	public List<PersonaDTO> saveList(List<PersonaDTO> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(PersonaDTO entity) {
		// TODO Auto-generated method stub
		repository.deleteAll();
	}

	@Override
	public void deleteById(long entityId) {
		// TODO Auto-generated method stub
		repository.deleteById(entityId);
		
	}

	@Override
	public PersonaDTO findByCodEmpleado(Long codigo) {
		return mapper.entityToDto(repository.findByCodigoEmpleado(codigo));
	}

	@Override
	public Page<PersonaDTO> filterByNombre(Integer page, Integer sizePerPage, PersonaDTO filter, String column,
			String order) {
		PageRequest pagina = PageRequest.of(page, sizePerPage, Sort.by(Sort.Direction.fromString(order), column));
		return mapper.entityPageToDtoPage(pagina, repository.findAll(getWherePagByNombre(filter), pagina));
	}
	
	/** Expresión para filtrar empleados por nombre **/
	public BooleanExpression getWherePagByNombre(PersonaDTO filtro) {
		BooleanExpression where = QPersona.persona.isNotNull();
		if (!Strings.isNullOrEmpty(filtro.getNombres())) {
			where = where.and(QPersona.persona.nombres.append(" ").append(QPersona.persona.apellido1).append(" ")
					.append(QPersona.persona.apellido2.coalesce("").toString()).containsIgnoreCase(filtro.getNombres()));
		}
		return where;
	}




	private Connection getConnection(String url, String user, String password) throws SQLException {
		Connection conn = DriverManager.getConnection(url, user, password);
		if (conn.getAutoCommit()) {
			conn.setAutoCommit(false);
		}
		return conn;
	}

	@Override
	public Object editar(@Valid PersonaDTO id) {
		// TODO Auto-generated method stub
		return ((IPersonaDAO) repository).editar(id);
	}


}
