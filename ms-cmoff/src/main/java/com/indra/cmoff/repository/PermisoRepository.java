package com.indra.cmoff.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.indra.cmoff.model.Permiso;

public interface PermisoRepository extends JpaRepository<Permiso, Long>, QuerydslPredicateExecutor<Permiso> {
}
