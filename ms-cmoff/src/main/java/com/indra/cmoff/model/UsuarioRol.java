package com.indra.cmoff.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.indra.cmoff.utils.util.Constantes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = Constantes.TABLE_USUARIO_ROL)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class UsuarioRol implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4332003420213595354L;
	
	@EmbeddedId
	private UsuarioRolPK id;

}
