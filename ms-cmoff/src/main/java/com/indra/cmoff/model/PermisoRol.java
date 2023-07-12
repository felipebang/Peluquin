package com.indra.cmoff.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.indra.cmoff.utils.util.Constantes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = Constantes.TABLE_PERMISO_ROL)
public class PermisoRol implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4173595857041962076L;
	
	@EmbeddedId
	private PermisoRolPK id;

}
