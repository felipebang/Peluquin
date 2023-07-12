package com.indra.cmoff.repository.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.google.common.base.Strings;
import com.indra.cmoff.dto.PorcentajeDTO;
import com.indra.cmoff.mapper.PorcentajeDTOMapper;
import com.indra.cmoff.model.QPorcentaje;
import com.indra.cmoff.repository.PorcentajeRepository;
import com.indra.cmoff.repository.custom.IPorcentajeDAO;
import com.querydsl.core.types.dsl.BooleanExpression;

@Repository
public class PorcentajeDAO implements IPorcentajeDAO{

	
	private final PorcentajeRepository repository;
	private final PorcentajeDTOMapper  mapper;
	
	public PorcentajeDAO(PorcentajeRepository repository, PorcentajeDTOMapper mapper ) {
		this.repository =repository;
		this.mapper = mapper;
	}


	@Override
	public Optional<PorcentajeDTO> findById(long id) {
		// TODO Auto-generated method stub
		return mapper.optionalEntityToDto(repository.findById(id));
	}


	@Override
	public List<PorcentajeDTO> findAll() {
		// TODO Auto-generated method stub
		return mapper.entitiesToDtos(repository.findAll());
	}


	@Override
	public Page<PorcentajeDTO> findAllPaginated(int page, int size) {
		PageRequest pagina = PageRequest.of(page, size);
		return mapper.entityPageToDtoPage(pagina, repository.findAll(pagina));
	}


	@Override
	public Page<PorcentajeDTO> filterPaginated(int page, int size, PorcentajeDTO entity, String column,
			String orderType) {
		PageRequest pagina = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(orderType), column));
		// TODO Auto-generated method stub
		return mapper.entityPageToDtoPage(pagina, repository.findAll(getWhere(entity), pagina));
	}

	
	public BooleanExpression getWhere(PorcentajeDTO filtre) {
		BooleanExpression where =QPorcentaje.porcentaje.isNotNull();
		if (!Strings.isNullOrEmpty(filtre.getPorcentajeEmpl())) {
			where = where
					.and(QPorcentaje.porcentaje.porcentajeEmpl.containsIgnoreCase(filtre.getPorcentajeEmpl()));
		}
		return where;
		
	}

	@Override
	public long countFilter(PorcentajeDTO entity) {
		// TODO Auto-generated method stub
		return repository.count(getWhere(entity));
	}


	@Override
	public PorcentajeDTO save(PorcentajeDTO entity) {
		// TODO Auto-generated method stub
		return mapper.entityToDto(repository.saveAndFlush(mapper.dtoToEntity(entity)));
	}


	@Override
	public List<PorcentajeDTO> saveList(List<PorcentajeDTO> entities) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void delete(PorcentajeDTO entity) {
		// TODO Auto-generated method stub
		repository.delete(mapper.dtoToEntity(entity));
	}


	@Override
	public void deleteById(long entityId) {
		// TODO Auto-generated method stub
		repository.deleteById(entityId);
	}
	

}
