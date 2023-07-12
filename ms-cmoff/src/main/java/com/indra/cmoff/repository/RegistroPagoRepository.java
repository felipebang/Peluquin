package com.indra.cmoff.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;


import com.indra.cmoff.model.RegistroPago;

public interface RegistroPagoRepository  extends JpaRepository<RegistroPago, Long>, QuerydslPredicateExecutor<RegistroPago> {



}
