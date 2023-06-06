package com.indra.cmoff.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Modulo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5987266813225582253L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotEmpty
	@Column(unique = true, nullable = false, length = 15)
	private String codigo;
	@Column(nullable = false, length = 255)
	private String nombre;
	@Column(nullable = false)
	private Boolean estado;
	@ManyToOne
	@JoinColumn(name = "id_padre", nullable = true)
	protected Modulo moduloEnlazado;
	@OneToMany(mappedBy = "moduloEnlazado")
	protected Set<Modulo> modulos;

}
