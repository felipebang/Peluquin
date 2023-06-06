package com.indra.cmoff.repository.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.google.common.base.Strings;
import com.indra.cmoff.dto.GananciasDTO;

import com.indra.cmoff.mapper.GananciasDTOMapper;

import com.indra.cmoff.model.QGanancias;

import com.indra.cmoff.repository.GananciasRepository;

import com.indra.cmoff.repository.custom.IGananciasDAO;
import com.querydsl.core.types.dsl.BooleanExpression;

@Repository
public class GananciasDAO implements IGananciasDAO {

	private final GananciasRepository repository;
	private final GananciasDTOMapper mapper;

	public GananciasDAO(GananciasRepository repository, GananciasDTOMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}

	@Override
	public Optional<GananciasDTO> findById(long id) {
		// TODO Auto-generated method stub
		return mapper.optionalEntityToDto(repository.findById(id));
	}

	@Override
	public List<GananciasDTO> findAll() {
		// TODO Auto-generated method stub
		return mapper.entitiesToDtos(repository.findAll());
	}

	@Override
	public Page<GananciasDTO> findAllPaginated(int page, int size) {
		PageRequest pagina = PageRequest.of(page, size);
		return mapper.entityPageToDtoPage(pagina, repository.findAll(pagina));
	}

	@Override
	public Page<GananciasDTO> filterPaginated(int page, int size, GananciasDTO entity, String column,
			String orderType) {
		PageRequest pagina = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(orderType), column));
		// TODO Auto-generated method stub
		return mapper.entityPageToDtoPage(pagina, repository.findAll(getWhere(entity), pagina));
	}

	public BooleanExpression getWhere(GananciasDTO filtre) {
		BooleanExpression where = QGanancias.ganancias.isNotNull();
		if (!Strings.isNullOrEmpty(filtre.getGananciaValor())) {
			where = where.and(QGanancias.ganancias.gananciaValor.containsIgnoreCase(filtre.getGananciaValor()));
		}
		return where;

	}

	@Override
	public long countFilter(GananciasDTO entity) {
		// TODO Auto-generated method stub
		return repository.count(getWhere(entity));
	}

	@Override
	public GananciasDTO save(GananciasDTO entity) {
		// TODO Auto-generated method stub
		return mapper.entityToDto(repository.saveAndFlush(mapper.dtoToEntity(entity)));
	}

	@Override
	public List<GananciasDTO> saveList(List<GananciasDTO> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(GananciasDTO entity) {
		// TODO Auto-generated method stub
		repository.delete(mapper.dtoToEntity(entity));
	}

	@Override
	public void deleteById(long entityId) {
		// TODO Auto-generated method stub
		repository.deleteById(entityId);
	}

}
