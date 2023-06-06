package com.indra.cmoff.model;

import com.indra.cmoff.utils.util.Constantes;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = Constantes.TABLE_REGISTRO_CORTES, schema = Constantes.SCHEMA_DB_IF)
public class RegistroCortes implements Serializable {

	private static final long serialVersionUID = 2721819147043330397L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "id_porcentaje")
	private Long idPorcentaje;

	@Column(name = "nu_cod_empleado", nullable = false, length = 240)
	private Long codigoEmpleado;

	@Column(name = "numero_cortes", nullable = false, length = 240)
	private String numeroCortes;

	@Column(name = "valor_corte", nullable = false, length = 240)
	private String valorCorte;

	@Column(name = "created_at", nullable = false, length = 240)
	private Date createdAt;

}
