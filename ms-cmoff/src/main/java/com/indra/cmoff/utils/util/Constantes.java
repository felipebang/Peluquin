package com.indra.cmoff.utils.util;

import java.io.Serializable;

public final class Constantes implements Serializable {

	private static final long serialVersionUID = 2590005579326465347L;
	
	/**
	 * Constantes para el manejo de nombres de schemas y tablas de la base de datos del sistema itinerarios
	 * */
	public static final String SCHEMA_DB_IF = "\"public\"";
//	public static final String SCHEMA_DB_IF = "it_format_uat";
	
	public static final String TABLE_LOG = "tb_if_log_carga_arch";
	public static final String TABLE_PARAMETRO = "tb_if_parametros";
	public static final String TABLE_PERSONA = "tb_if_personas";
	public static final String TABLE_TITULO_PERSONA = "tb_if_titulo_persona";
	public static final String TABLE_USUARIO_ROL = "usuario_rol";
	public static final String TABLE_PERMISO_ROL = "permiso_rol";
	public static final String TABLE_PORCENTAJE = "porcentaje";
	public static final String TABLE_GANANCIAS = "ganancias";
	public static final String TABLE_REGISTRO_PAGO = "registro_pago";
	public static final String TABLE_REGISTRO_CORTES = "registro_cortes";       
	public static final String TABLE_HISTORIA_GANANCIAS = "registro_cortes";  

	
	/**
	 * Constantes generales
	 * */
	public static final String SEPARADOR_IDS_GUION = "-";
	public static final String SEPARADOR_DOS_PUNTOS = ":";
	public static final String SEPARADOR_PUNTO_Y_COMA = ";";
	public static final String SEPARADOR_IGUAL = "=";
	public static final String SIMBOLO_ARROBA = "@";
	public static final String PUNTO = ".";
	public static final String COMA = ",";
	public static final String MENSAJE_CAMPO_REQUERIDO = "Campo requerido";
	public static final String MENSAJE_EXCEDE_TAMANO_MAXIMO = "Campo excede tamaño máximo";
	public static final String MENSAJE_VALOR_INVALIDO = "valor inválido";
	public static final String MENSAJE_LETRA_INVALIDA = "Letra no permitida";
	public static final String MENSAJE_CAMPO_VACIO = "Campo no puede contener cadena vacía";
	public static final String MENSAJE_CLAVE_INVALIDA = "Clave inválida! Debe ser entre 4 y 16 dígitos.";
	public static final String MENSAJE_DESCRIPCION_INVALIDA = "Descripción inválida! Debe ser entre 5 y 255 caracteres.";
	public static final String FORMATO_FECHA = "dd/MM/yyyy";
	public static final String FORMATO_FECHA_YYYY_MM_DD = "yyyy-MM-dd";
	public static final String FORMATO_FECHA_YYYYMMDD = "yyyyMMdd";
	public static final String FORMATO_HORA = "%02d:%02d";
	public static final String FORMATO_HORA_12H_AMPM = "hh:mm a"; // h-> hour in am-pm (1-12) a-> PM-AM
	public static final String FORMATO_HORA_24H = "kk:mm"; // k-> hour in day (1-24)
	public static final String FORMATO_FECHA_DD_MM_YYYY_HH_MM_SS = "dd/MM/yyyy HH:mm:ss";
	public static final String FORMATO_FECHA_LIMITE_EVALUACIONES = "ddMMyyyy";
	public static final String FORMATO_FECHA_LETRAS_COMPLETO = "EEEE dd 'de' MMMM 'de' yyyy";
	public static final String FORMATO_FECHA_YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";
	public static final String INDICADOR_AM = " a.m.";
	public static final String INDICADOR_PM = " p.m.";
	public static final String ALGORITHM_HASH_MD5 = "MD5";
	public static final String LINEA = "Línea";
	public static final String CODE = "code";
	public static final String MESSAGE = "message";
	public static final String CAMPO = "Campo ";
	public static final String ACTIVO = "Activo";
	public static final String INACTIVO = "Inactivo";
	public static final String EMAIL_INVALIDO = "Email incorrecto";
	public static final String ENVIO_ARCHIVO_S = "s";
	public static final String ENVIO_ARCHIVO_N = "n";
	
	public static final String PARAMETRO_CREADO = "Valor creado correctamente.";
	public static final String PARAMETRO_ACTUALIZADO = "Valor actualizado correctamente.";
	public static final String PARAMETRO_ELIMINADO = "Valor eliminado correctamente.";
	
	public static final String USUARIO_CREADO = "Usuario creado correctamente.";
	public static final String USUARIO_ACTUALIZADO = "Usuario actualizado correctamente.";
	public static final String ROL_CREADO = "Rol creado correctamente.";
	public static final String PER_CREADO = "Registro creado correctamente.";
	public static final String ROL_ACTUALIZADO = "Rol actualizado correctamente.";
	public static final String ROLES_ASIGNADOS = "Roles asignados correctamente.";
	public static final String PLAN_DE_FORMACION_ASIGNADO = "Plan de formación asignado correctamente.";
	public static final String ROLPROYECTO_CREADO = "Cargo creado correctamente.";
	public static final String ROLPROYECTO_ACTUALIZADO = "Cargo actualizado correctamente.";
	public static final String DOMINIO_TECNOLOGICO_CREADO = "Dominio tecnológico creado correctamente.";
	public static final String DOMINIO_TECNOLOGICO_ACTUALIZADO = "Dominio tecnológico actualizado correctamente.";
	/**
	 * CONSTANTES PARA EL PDF DE FICHA PERSONAL
	 */
	public static final String TITULO_CV = "Ficha personal";
	public static final String SUB_INFORMACION_PERSONAL = "Información personal";
	public static final String SUB_CONOCIMIENTOS = "Conocimientos";
	public static final String SUB_TITULOS = "Títulos";
	public static final String SUB_CERTIFICACIONES = "Certificaciones";
	public static final String SUB_IDIOMAS = "Idiomas";
	
	/**
	 * CONSTANTES PARA EL XLSX DE FICHA PERSONAL
	 */
	public static final String CODIGO_EMPLEADO = "Código empleado";
	public static final String NOMBRE_COMPLETO = "Nombre completo";
	public static final String COL_PAIS_EMPLEADO = "País";
	public static final String COL_EMPRESA_EMPLEADO = "Empresa";
	public static final String COL_DELIVERY_EMPLEADO = "Delivery";
	


	
	/**Constantes Hoja XLSX de idiomas ficha personal*/
	public static final String IDIOMAS = "Idiomas";
	public static final String COL_IDIOMA = "Idioma";
	public static final String COL_IDIOMA_HABLA = "Nivel habla";
	public static final String COL_IDIOMA_COMPRENSION = "Nivel comprensión";
	public static final String COL_IDIOMA_ESCRITURA = "Nivel escritura";
	public static final String COL_IDIOMA_NATIVO = "Nativo";

	/**
	 * CONSTANTES PARA EL MANEJO DE ERRORES
	 */
	public static final String ERROR_SOLICITUD = "No se ha podido procesar su solicitud.";
	public static final String ERROR_TIPO_DATO = "Tipo de dato incorrecto";
	public static final String ERROR_USER_BAD_CRED = "Bad credentials";
	public static final String ERROR_USER_EMPTY_AUTH = "User does not have permissions";
	public static final String ERROR_EMAIL_BAD_CRED = "usuario y/o contraseña genérica incorrecta.";
	public static final String ERROR_USUARIO_EXISTENTE = "El usuario ingresado ya existe";
	public static final String ERROR_ROL_ACTUALIZADO = "El código ADM ya existe";
	public static final String ERROR_PROYECTO_CREADO = "El proyecto no se creo correctamente";
	public static final String ERROR_PROYECTO_NUEVO_YA_EXISTENTE = "Ya existe un proyecto con el código ingresado.";
	public static final String ERROR_DOMINIO_CREADO = "El dominio no se guardo correctamente.";
	public static final String ERROR_PRACTICA_CREADO = "Ya existen conocimientos para la práctica, subpráctica y especialización en sistema.";
	public static final String ERROR_SOLICITUD_CORTE = "El corte que desea borrar tiene registros asociados a ganancia";
}
