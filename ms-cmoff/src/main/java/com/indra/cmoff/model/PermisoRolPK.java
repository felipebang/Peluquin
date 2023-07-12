package com.indra.cmoff.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class PermisoRolPK implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 735876885055009782L;
	
	@ManyToOne
	@JoinColumn(name = "id_permiso", nullable = false)
	private Permiso permiso;
	@ManyToOne
	@JoinColumn(name = "id_modulo", nullable = false)
	private Modulo modulo;
	@ManyToOne
	@JoinColumn(name = "id_rol", nullable = false)
	private Rol rol;

}
