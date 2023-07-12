package com.indra.cmoff.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.indra.cmoff.model.HistoriaGanancias;

public interface HistoriaGananciasRepository  extends JpaRepository<HistoriaGanancias, Long>,  QuerydslPredicateExecutor<HistoriaGanancias> {

}
