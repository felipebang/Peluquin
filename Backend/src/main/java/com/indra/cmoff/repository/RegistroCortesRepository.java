package com.indra.cmoff.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.indra.cmoff.model.RegistroCortes;

public interface RegistroCortesRepository extends JpaRepository<RegistroCortes, Long> ,QuerydslPredicateExecutor<RegistroCortes> {

	

}
