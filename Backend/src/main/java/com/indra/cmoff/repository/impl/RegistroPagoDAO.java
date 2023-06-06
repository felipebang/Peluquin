package com.indra.cmoff.repository.impl;


import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.google.common.base.Strings;

import com.indra.cmoff.dto.RegistroPagoDTO;

import com.indra.cmoff.mapper.RegistroPagoDTOMapper;
import com.indra.cmoff.model.QRegistroPago;
import com.indra.cmoff.repository.RegistroPagoRepository;
import com.indra.cmoff.repository.custom.IRegistroPagoDAO;
import com.querydsl.core.types.dsl.BooleanExpression;


@Repository
public class RegistroPagoDAO implements IRegistroPagoDAO{


	private final RegistroPagoRepository repository;
	private final RegistroPagoDTOMapper  mapper;
	
	public RegistroPagoDAO(RegistroPagoRepository repository, RegistroPagoDTOMapper mapper ) {
		this.repository =repository;
		this.mapper = mapper;
	}


	@Override
	public Optional<RegistroPagoDTO> findById(long id) {
		// TODO Auto-generated method stub
		return mapper.optionalEntityToDto(repository.findById(id));
	}


	@Override
	public List<RegistroPagoDTO> findAll() {
		// TODO Auto-generated method stub
		return mapper.entitiesToDtos(repository.findAll());
	}


	@Override
	public Page<RegistroPagoDTO> findAllPaginated(int page, int size) {
		PageRequest pagina = PageRequest.of(page, size);
		return mapper.entityPageToDtoPage(pagina, repository.findAll(pagina));
	}


	@Override
	public Page<RegistroPagoDTO> filterPaginated(int page, int size, RegistroPagoDTO entity, String column,
			String orderType) {
		PageRequest pagina = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(orderType), column));
		// TODO Auto-generated method stub
		return mapper.entityPageToDtoPage(pagina, repository.findAll(getWhere(entity), pagina));
	}

	
	public BooleanExpression getWhere(RegistroPagoDTO filtre) {
		BooleanExpression where =QRegistroPago.registroPago.isNotNull();
		if (!Strings.isNullOrEmpty(filtre.getValorPago())) {
			where = where
					.and(QRegistroPago.registroPago.valorPago .containsIgnoreCase(filtre.getValorPago()));
		}
		return where;
		
	}

	@Override
	public long countFilter(RegistroPagoDTO entity) {
		// TODO Auto-generated method stub
		return repository.count(getWhere(entity));
	}


	@Override
	public RegistroPagoDTO save(RegistroPagoDTO entity) {
		// TODO Auto-generated method stub
		return mapper.entityToDto(repository.saveAndFlush(mapper.dtoToEntity(entity)));
	}


	@Override
	public List<RegistroPagoDTO> saveList(List<RegistroPagoDTO> entities) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void delete(RegistroPagoDTO entity) {
		// TODO Auto-generated method stub
		repository.delete(mapper.dtoToEntity(entity));
	}


	@Override
	public void deleteById(long entityId) {
		// TODO Auto-generated method stub
		repository.deleteById(entityId);
	}
	


}
