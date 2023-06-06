package com.indra.cmoff.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Permiso implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2911997764984294147L;
	
	@Id
	private Long id;
	@NotEmpty
	@Column(nullable = false, length = 15)
	private String codigo;
	@Column(length = 255)
	private String descripcion;

}
