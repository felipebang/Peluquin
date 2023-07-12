package com.indra.cmoff.utils.util;

import java.io.Serializable;

public final class ConstantesRolesMP implements Serializable {

	private static final long serialVersionUID = 6686288433672190584L;
	
	/**
	 * CONSTANTES PARA PERMISOS
	 **/
	public static final String PRM_C = "-C"; 								//CREAR
	public static final String PRM_L = "-L";								//LEER
	public static final String PRM_A = "-A";								//ACTUALIZAR
	
	/**
	 * CONSTANTES PARA ROLES
	 **/
	public static final String ROLE_ADM = "ROLE_ADM"; 					//ADMINISTRADOR
	
	/**
	 * CONSTANTES PARA MODULOS PADRE
	 **/
	public static final String RRHH = "ROLE_CMOFF";						//CMOFF
	public static final String ADMIN = "ROLE_ADMIN";						//ADMINISTRACION
	
	/**
	 * CONSTANTES PARA MODULOS HIJO (SUB-MODULOS)
	 **/
	
	/* CMOFF */
	public static final String MPCPRY = "ROLE_MPCPRY";					//MAPA DE CONOCIMIENTOS POR PROYECTO
	public static final String MPCPRT = "ROLE_MPCPRT";					//MAPA DE CONOCIMIENTOS POR PRÁCTICA
	public static final String RPTS = "ROLE_RPTS";							//REPORTES
	public static final String INDTECN = "ROLE_INDTECN";				//INDICADORES TECNOLOGICOS
	public static final String INDFUNC = "ROLE_INDFUNC";				//INDICADORES FUNCIONALES
	public static final String FCHPERS = "ROLE_FCHPERS";				//FICHA PERSONAS
	public static final String CURS = "ROLE_CURS";							//CURSOS
	public static final String PRTS = "ROLE_PRTS";							//PRACTICAS
	public static final String IMPO = "ROLE_IMPFORM";						//IMPORTACIÓN DE ARCHIVOS
	
	
	/* ADMINISTRACION */
	public static final String ADMU = "ROLE_ADMU";							//ADMINISTRACION DE USUARIOS
	public static final String ROLPE = "ROLE_ROLPE";						//ADMINISTRACION DE PERMISOS (ROLES Y PERMISOS)


	
	
}