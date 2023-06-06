package com.indra.cmoff.repository.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.google.common.base.Strings;

import com.indra.cmoff.dto.HistoriaGananciasDTO;

import com.indra.cmoff.mapper.HistoriaGananciasDTOMapper;

import com.indra.cmoff.model.QHistoriaGanancias;

import com.indra.cmoff.repository.HistoriaGananciasRepository;
import com.indra.cmoff.repository.custom.IHistoriaGananciasDAO;
import com.querydsl.core.types.dsl.BooleanExpression;

@Repository
public class HistoriaGananciasDAO implements IHistoriaGananciasDAO {

	private final HistoriaGananciasRepository repository;
	private final HistoriaGananciasDTOMapper mapper;

	public HistoriaGananciasDAO(HistoriaGananciasRepository repository, HistoriaGananciasDTOMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}

	@Override
	public Optional<HistoriaGananciasDTO> findById(long id) {
		// TODO Auto-generated method stub
		return mapper.optionalEntityToDto(repository.findById(id));
	}

	@Override
	public List<HistoriaGananciasDTO> findAll() {
		// TODO Auto-generated method stub
		return mapper.entitiesToDtos(repository.findAll());
	}

	@Override
	public Page<HistoriaGananciasDTO> findAllPaginated(int page, int size) {
		PageRequest pagina = PageRequest.of(page, size);
		return mapper.entityPageToDtoPage(pagina, repository.findAll(pagina));
	}

	@Override
	public Page<HistoriaGananciasDTO> filterPaginated(int page, int size, HistoriaGananciasDTO entity, String column,
			String orderType) {
		PageRequest pagina = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(orderType), column));
		// TODO Auto-generated method stub
		return mapper.entityPageToDtoPage(pagina, repository.findAll(getWhere(entity), pagina));
	}

	public BooleanExpression getWhere(HistoriaGananciasDTO filtre) {
		BooleanExpression where = QHistoriaGanancias.historiaGanancias.isNotNull();
		if (!Strings.isNullOrEmpty(filtre.getGananciaValor())) {
			where = where
					.and(QHistoriaGanancias.historiaGanancias.gananciaValor.containsIgnoreCase(filtre.getGananciaValor()));
		}
		return where;

	}

	@Override
	public long countFilter(HistoriaGananciasDTO entity) {
		// TODO Auto-generated method stub
		return repository.count(getWhere(entity));
	}

	@Override
	public HistoriaGananciasDTO save(HistoriaGananciasDTO entity) {
		// TODO Auto-generated method stub
		return mapper.entityToDto(repository.saveAndFlush(mapper.dtoToEntity(entity)));
	}

	@Override
	public List<HistoriaGananciasDTO> saveList(List<HistoriaGananciasDTO> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(HistoriaGananciasDTO entity) {
		// TODO Auto-generated method stub
		repository.delete(mapper.dtoToEntity(entity));
	}

	@Override
	public void deleteById(long entityId) {
		// TODO Auto-generated method stub
		repository.deleteById(entityId);
	}
}
