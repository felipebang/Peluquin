package com.indra.cmoff.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



import com.indra.cmoff.utils.util.Constantes;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = Constantes.TABLE_GANANCIAS, schema = Constantes.SCHEMA_DB_IF)
public class Ganancias implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_ganancias")
	private Long idGanancias;

	@Column(name = "id")
	private Long id;

	@Column(name = "ganancia_valor", nullable = false, length = 240)
	private String gananciaValor;

	@Column(name = "created_at", nullable = false, length = 240)
	private Date createdAt;

}
