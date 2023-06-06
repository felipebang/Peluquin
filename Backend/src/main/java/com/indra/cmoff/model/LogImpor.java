package com.indra.cmoff.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.indra.cmoff.utils.util.Constantes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = Constantes.TABLE_LOG, schema = Constantes.SCHEMA_DB_IF)
public class LogImpor implements Serializable {

	private static final long serialVersionUID = 2911997764984294147L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "nu_id_log")
	private Long id;

	@Column(nullable = false, length = 4000, name = "sb_nombre_archivo")
	private String nombreArchivo;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false, name = "dt_ultima_ejec")
	private Date fechaCarga;

	@Column(nullable = false, length = 10, name = "sb_estado_ejec")
	private String estado;

	@Column(nullable = false, length = 4000, name = "sb_detalle_ejec")
	private String descripcion;

}
