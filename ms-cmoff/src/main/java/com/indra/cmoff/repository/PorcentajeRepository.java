package com.indra.cmoff.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.indra.cmoff.model.Porcentaje;

public interface PorcentajeRepository extends JpaRepository<Porcentaje, Long> ,QuerydslPredicateExecutor<Porcentaje>{

}
