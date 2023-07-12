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
public class UsuarioRolPK implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3541166844730626603L;
	
	@ManyToOne
	@JoinColumn(name = "id_usuario", nullable = false)
	private Usuario idUsuario;
	@ManyToOne
	@JoinColumn(name = "id_rol", nullable = false)
	private Rol rol;

}
