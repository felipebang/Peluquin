package com.indra.cmoff.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.indra.cmoff.model.Ganancias;

public interface GananciasRepository  extends JpaRepository <Ganancias, Long>, QuerydslPredicateExecutor<Ganancias> {

}
