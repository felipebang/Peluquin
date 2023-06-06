package com.indra.cmoff.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table
public class Usuario implements Serializable {
	
	private static final long serialVersionUID = -3440740631517777044L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true, name = "cod_persona", nullable = false)
	private Long persona;
	
	@NotEmpty
	@Column(unique = true, nullable = false, length = 50)
	private String usuario;
	
	@Column(nullable = false, length = 60)
	private String clave;

}
