package com.indra.cmoff.repository.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.google.common.base.Strings;

import com.indra.cmoff.dto.RegistroCortesDTO;

import com.indra.cmoff.mapper.RegistroCortesDTOMapper;

import com.indra.cmoff.model.QRegistroCortes;
import com.indra.cmoff.repository.PorcentajeRepository;
import com.indra.cmoff.repository.RegistroCortesRepository;
import com.indra.cmoff.repository.custom.IRegistroCortesDAO;
import com.querydsl.core.types.dsl.BooleanExpression;

@Repository
public class RegistroCortesDAO implements IRegistroCortesDAO {

	private final RegistroCortesRepository repository;
	private final RegistroCortesDTOMapper mapper;

	public RegistroCortesDAO(RegistroCortesRepository repository, RegistroCortesDTOMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}

	@Override
	public Optional<RegistroCortesDTO> findById(long id) {
		// TODO Auto-generated method stub
		return mapper.optionalEntityToDto(repository.findById(id));
	}

	@Override
	public List<RegistroCortesDTO> findAll() {
		// TODO Auto-generated method stub
		return mapper.entitiesToDtos(repository.findAll());
	}

	@Override
	public Page<RegistroCortesDTO> findAllPaginated(int page, int size) {
		PageRequest pagina = PageRequest.of(page, size);
		return mapper.entityPageToDtoPage(pagina, repository.findAll(pagina));
	}

	@Override
	public Page<RegistroCortesDTO> filterPaginated(int page, int size, RegistroCortesDTO entity, String column,
			String orderType) {
		PageRequest pagina = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(orderType), column));
		// TODO Auto-generated method stub
		return mapper.entityPageToDtoPage(pagina, repository.findAll(getWhere(entity), pagina));
	}

	public BooleanExpression getWhere(RegistroCortesDTO filtre) {
		BooleanExpression where = QRegistroCortes.registroCortes.isNotNull();
		if (!Strings.isNullOrEmpty(filtre.getNumeroCortes())) {
			where = where
					.and(QRegistroCortes.registroCortes.numeroCortes.containsIgnoreCase(filtre.getNumeroCortes()));
		}
		return where;

	}

	@Override
	public long countFilter(RegistroCortesDTO entity) {
		// TODO Auto-generated method stub
		return repository.count(getWhere(entity));
	}

	@Override
	public RegistroCortesDTO save(RegistroCortesDTO entity) {
		// TODO Auto-generated method stub
		return mapper.entityToDto(repository.saveAndFlush(mapper.dtoToEntity(entity)));
	}

	@Override
	public List<RegistroCortesDTO> saveList(List<RegistroCortesDTO> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(RegistroCortesDTO entity) {
		// TODO Auto-generated method stub
		repository.delete(mapper.dtoToEntity(entity));
	}

	@Override
	public void deleteById(long entityId) {
		// TODO Auto-generated method stub
		repository.deleteById(entityId);
	}

}
