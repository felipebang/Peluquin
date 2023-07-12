package com.indra.cmoff.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Rol implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8596300939188475321L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotEmpty
	@Column(unique = true, length = 15, nullable = false)
	private String codigo;
	@Column(nullable = false, length = 100)
	private String descripcion;
	@Column(nullable = false)
	private Boolean estado;

}
