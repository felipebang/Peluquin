import {
	ADM_ROLES,
	ADM_ROLES_DETAIL,
	ADM_ROLES_EDIT,
	ADM_ROLES_NEW,
	ADM_USUARIOS,
	ADM_USUARIOS_EDIT,
	ADM_USUARIOS_NEW,
	CMOFF_FICHA_EMPLEADOS,
	ITI_FORMATIVOS_PERFILES,
	ADMIN_PARAMETRO,
	ADM_GANANCIAS,
	ADM_REGISTROCORTES,
	ADM_REGISTROCORTES_DETAIL,
	ADM_REGISTROCORTES_EDIT,
	ADM_REGISTROCORTES_NEW,
	ADM_HISTORIAGANANCIAS,
	ADM_PORCENTAJE,
	ADM_REGISTROPAGO,
	ADMIN_PROYECTOS,
	ADMIN_PROYECTOS_NEW,
	ADMIN_PROYECTOS_DETAIL,
	ADMIN_PROYECTOS_EDIT,
	ADMIN_PRACTICAS,
	ADMIN_ARBOL_CONOCIMIENTO,
	ADMIN_ARBOL_CONOCIMIENTO_NEW,
	ADMIN_ARBOL_CONOCIMIENTO_DETAIL,
	ADMIN_ARBOL_CONOCIMIENTO_EDIT
} from './routesApp';
/**
 * Constantes comunes
 * @type {string}
 */
const NUEVO_LABEL = 'Nuevo';
const EDITAR_LABEL = 'Editar';
const DETALLE_LABEL = 'Detalle';
const PROFILE_LABEL = 'Perfil';

/**
 * Módulos o Componentes
 * @type {string}
 */
const DASHBOARD_LABEL = 'Inicio';
const PERFILES_FORMACION_LABEL = 'Perfiles';
const REPORTES_LABEL = 'Reportes';
const PERSONAS_LABEL = 'Personas';
const FICHA_PERSONAL_LABEL = 'Ficha personal';

const PRACTICAS_LABEL = 'Prácticas';
const IMPORTACION_LABEL = 'Importación';
const PARAMETRO_LABEL = 'Parametro';
const GANANCIAS_LABEL = 'Ganancias';
const REGISTROCORTES_LABEL = 'Registro de  Cortes';
const HISTORIAGANANCIAS_LABEL = 'Historial de Ganancias';
const PORCENTAJE_LABEL = 'Porcentaje';
const REGISTROPAGO_LABEL = 'Registro de pago';
const USUARIOS_LABEL = 'Usuarios';
const ROLES_LABEL = 'Roles';
const VALORES_LABEL = 'Valores';

const PROYECTOS_LABEL = 'Proyectos';
const ARBOL_CONOCIMIENTO_LABEL = 'Árbol de conocimiento';

export const VALIDATIONS_LABELS = {
	FIELD_REQUIRED: 'Este campo es obligatorio!',
	FIELD_INVALID: ' Inválido!',
	USER_INVALID: 'Ingrese un usuario válido',
	PASSWORD_INVALID: 'Ingrese una contraseña válida'
};

export const MESSAGE_ERROR = {
	ERROR: 'ERROR.',
	ERROR_UPLOAD_FILE_FAILED: 'Se presento una falla al subir los archivos',
	ERROR_CAMPOS: 'Por favor revisar los campos del formulario',
	ERROR_UPLOAD_FILE_IMPORT: 'debe seleccionar al menos un archivo a  subir',
	ERROR_PERSONA_NO_EXISTE: 'La persona para el usuario consultado no existe.',
	ERROR_FECHAFIN_SINFECHAINICIO_PLFRM: 'Debe ingresar una fecha inicio.',
	ERROR_FECHAFIN_PLFRM:
		'La fecha fin debe ser igual o mayor a la fecha inicio.',
	ERROR_CURSOS_PLFRM:
		'Debe seleccionar al menos un curso para el plan de formación.',
	ERROR_EMPLEADO_PLFRM:
		'Debe asociar un empleado para asignar un plan de formación.',

	ERROR_CURSO_ACTIVO_PLFRM:
		'El curso seleccionado ya se encuentra en plan de formación para el empleado.',

	ERROR_ROL_EXISTENTE_PROYECTO: 'Ya existe el rol diligenciado.',
	ERROR_GRADOMADUREZ_CONOCIMIENTOS:
		'Debe diligenciar grado de madurez para los conocimientos: ',
	ERROR_CONOCIMIENTOS_PRACTICA: 'Debe asociar al menos un conocimiento.',

	ERROR_FILTRO_PLFRM_MASIVO: 'Debe realizar algún filtro de búsqueda',
	ERROR_NO_AUTORIZADO: 'No esta autorizado.',
	ERROR_ROL_MODULOS: 'El rol ingresado no tiene módulos y permisos asociados.',
	ERROR_ARCHIVO_TAMAÑO_MAXIMO: 'Archivo supera tamaño máximo 50 MB.',
	ERROR_CODIGO_PERSONA_NOEXISTE:
		'No existe ningún empleado con el código ingresado.',
	ERROR_CODIGO_EMPLEADO: 'Debe ingresar el código de empleado.',
	ERROR_NORELACION_EMPLEADO: 'No se ha relacionado ningún empleado.',
	ERROR_NO_CONTRASEÑA: 'No se ha ingresado una contraseña.',
	ERROR_CONTRASEÑAS: 'Las contraseñas no coinciden.',

	ERROR_UPLOAD_FILE_IMPORT_ARBOL: 'Se deben seleccionar el archivo a subir'
};

export const MESSAGE_SUCCESS = {
	CURSO_ADD_CORRECTAMENTE: 'Curso agregado temporalmente.',
	IMPORTACION_EXITOSA: 'Importación ejecutada correctamente.'
};

export const MESSAGE_INFO = {
	INFO_REGISTROS_EXPORTAR: 'Aún no hay registros para exportar.',
	INFO_PERSONA_NO_EXISTE: 'La persona para el usuario consultado no existe.',
	INFO_USUARIO_YAEXITE: 'Ya existe un usuario para el empleado seleccionado.',
	INFO_PERSONAS_FILTRO_NOEXISTE:
		'No hay personas que correspondan con el filtro de búsqueda.'
};

export const ROL_ADM_CODIGO = 'ADM';
export const ESTADO_ACTIVO = 'ACTIVO';
export const ESTADO_INACTIVO = 'INACTIVO';

/**
 * Raiz
 * @type {{home: string, home2: string}}
 */
export const RAIZ = {
	home: '/cmoff',
	home2: '/dashboard'
};

/**
 * CONST ROUTES
 * @type {index: {path: string, label: string}}}
 */

export const PERFILES_ROUTE = {
	index: {
		path: ITI_FORMATIVOS_PERFILES,
		label: PERFILES_FORMACION_LABEL
	}
};

export const FICHA_PERSONAL_ROUTE = {
	index: {
		path: CMOFF_FICHA_EMPLEADOS,
		label: PERSONAS_LABEL
	}
};

export const PRACTICA_ROUTE = {
	index: {
		path: ADMIN_PRACTICAS,
		label: PRACTICAS_LABEL
	}
};

export const PARAMETRO_ROUTE = {
	index: {
		path: ADMIN_PARAMETRO,
		label: PARAMETRO_LABEL
	}
};

export const USUARIO_ROUTE = {
	index: {
		path: ADM_USUARIOS,
		label: USUARIOS_LABEL
	},
	nuevo: {
		path: ADM_USUARIOS_NEW,
		label: NUEVO_LABEL
	},
	editar: {
		path: ADM_USUARIOS_EDIT,
		label: EDITAR_LABEL
	}
};

export const ROL_ROUTE = {
	index: {
		path: ADM_ROLES,
		label: ROLES_LABEL
	},
	nuevo: {
		path: ADM_ROLES_NEW,
		label: NUEVO_LABEL
	},
	detalle: {
		path: ADM_ROLES_DETAIL,
		label: DETALLE_LABEL
	},
	editar: {
		path: ADM_ROLES_EDIT,
		label: EDITAR_LABEL
	}
};

//-ganacias-

export const GANANCIAS_ROUTE = {
	index: {
		path: ADM_GANANCIAS,
		label: GANANCIAS_LABEL
	}
};

//----

//-registrocortes-

export const REGISTROCORTES_ROUTE = {
	index: {
		path: ADM_REGISTROCORTES,
		label: REGISTROCORTES_LABEL
	},
	nuevo: {
		path: ADM_REGISTROCORTES_NEW,
		label: NUEVO_LABEL
	},
	detalle: {
		path: ADM_REGISTROCORTES_DETAIL,
		label: DETALLE_LABEL
	},
	editar: {
		path: ADM_REGISTROCORTES_EDIT,
		label: EDITAR_LABEL
	}
};

//-HISTORIAGANANCIA-

export const HISTORIAGANANCIAS_ROUTE = {
	index: {
		path: ADM_HISTORIAGANANCIAS,
		label: HISTORIAGANANCIAS_LABEL
	}
};

//----

//-PORCETAJE-

export const PORCENTAJE_ROUTE = {
	index: {
		path: ADM_PORCENTAJE,
		label: PORCENTAJE_LABEL
	}
};

//----

//-PORCETAJE-

export const REGISTROPAGO_ROUTE = {
	index: {
		path: ADM_REGISTROPAGO,
		label: REGISTROPAGO_LABEL
	}
};

//----

export const PROYECTO_ROUTE = {
	index: {
		path: ADMIN_PROYECTOS,
		label: PROYECTOS_LABEL
	},
	nuevo: {
		path: ADMIN_PROYECTOS_NEW,
		label: NUEVO_LABEL
	},
	detalle: {
		path: ADMIN_PROYECTOS_DETAIL,
		label: DETALLE_LABEL
	},
	editar: {
		path: ADMIN_PROYECTOS_EDIT,
		label: EDITAR_LABEL
	}
};

/**
 * CONST BREADCRUMBS
 */

/**
 * BREADCRUMBS PARA ITINERARIOS MAPA DE CONOCIMIENTOS
 */
export const PERFILES_BREADCRUMBS = [
	{ label: DASHBOARD_LABEL, path: RAIZ.home2 },
	{ label: PERFILES_FORMACION_LABEL }
];

/**
 * BREADCRUMBS PARA ITINERARIOS REPORTES
 */
export const REPORTES_BREADCRUMBS = [
	{ label: DASHBOARD_LABEL, path: RAIZ.home2 },
	{ label: REPORTES_LABEL }
];

export const FICHA_EMPLEADOS_BREADCRUMBS = [
	{ label: DASHBOARD_LABEL, path: RAIZ.home2 },
	{ label: PERSONAS_LABEL }
];
export const FICHA_EMPLEADOS_PERSONAL_BREADCRUMBS = [
	{ label: DASHBOARD_LABEL, path: RAIZ.home2 },
	{ label: PERSONAS_LABEL, path: FICHA_PERSONAL_ROUTE.index.path },
	{ label: FICHA_PERSONAL_LABEL }
];

/**
 * BREADCRUMBS PARA ITINERARIOS IMPORTACION
 */
export const IMPORTACION_BREADCRUMBS = [
	{ label: DASHBOARD_LABEL, path: RAIZ.home },
	{ label: IMPORTACION_LABEL }
];

/**
 * BREADCRUMBS PARA ADMINISTRACION PARAMETROS
 */
export const ADM_PARAMETRO_BREADCRUMBS = [
	{ label: DASHBOARD_LABEL, path: RAIZ.home2 },
	{ label: PARAMETRO_LABEL }
];
export const ADM_VALORESPARAMETRO_BREADCRUMBS = [
	{ label: DASHBOARD_LABEL, path: RAIZ.home2 },
	{ label: PARAMETRO_LABEL, path: PARAMETRO_ROUTE.index.path },
	{ label: VALORES_LABEL }
];

/**
 * BREADCRUMBS PARA ADMINISTRACION GANANCIAS
 */

/**
 * BREADCRUMBS PARA ADMINISTRACION USUARIOS
 */
export const USUARIOS_BREADCRUMBS = [
	{ label: DASHBOARD_LABEL, path: RAIZ.home2 },
	{ label: USUARIOS_LABEL }
];
export const USUARIOS_DETAIL_BREADCRUMBS = [
	{ label: DASHBOARD_LABEL, path: RAIZ.home2 },
	{ label: USUARIOS_LABEL, path: USUARIO_ROUTE.index.path },
	{ label: DETALLE_LABEL }
];
export const USUARIOS_EDIT_BREADCRUMBS = [
	{ label: DASHBOARD_LABEL, path: RAIZ.home2 },
	{ label: USUARIOS_LABEL, path: USUARIO_ROUTE.index.path },
	{ label: EDITAR_LABEL }
];
export const USUARIOS_NEW_BREADCRUMBS = [
	{ label: DASHBOARD_LABEL, path: RAIZ.home2 },
	{ label: USUARIOS_LABEL, path: USUARIO_ROUTE.index.path },
	{ label: NUEVO_LABEL }
];
export const USUARIOS_PROFILE_BREADCRUMBS = [
	{ label: DASHBOARD_LABEL, path: RAIZ.home2 },
	{ label: USUARIOS_LABEL, path: USUARIO_ROUTE.index.path },
	{ label: PROFILE_LABEL }
];

/**
 * BREADCRUMBS PARA ADMINISTRACION ROLES Y PERMISOS
 */

export const ROLES_BREADCRUMBS = [
	{ label: DASHBOARD_LABEL, path: RAIZ.home2 },
	{ label: ROLES_LABEL }
];

export const ROLES_EDIT_BREADCRUMBS = [
	{ label: DASHBOARD_LABEL, path: RAIZ.home2 },
	{ label: ROLES_LABEL, path: ROL_ROUTE.index.path },
	{ label: EDITAR_LABEL }
];
export const ROLES_DETAIL_BREADCRUMBS = [
	{ label: DASHBOARD_LABEL, path: RAIZ.home2 },
	{ label: ROLES_LABEL, path: ROL_ROUTE.index.path },
	{ label: DETALLE_LABEL }
];
export const ROLES_NEW_BREADCRUMBS = [
	{ label: DASHBOARD_LABEL, path: RAIZ.home2 },
	{ label: ROLES_LABEL, path: ROL_ROUTE.index.path },
	{ label: NUEVO_LABEL }
];

/**
 * BREADCRUMBS PARA ADMINISTRACION PROYECTOS
 */

export const PROYECTOS_BREADCRUMBS = [
	{ label: DASHBOARD_LABEL, path: RAIZ.home2 },
	{ label: PROYECTOS_LABEL }
];
export const PROYECTOS_EDIT_BREADCRUMBS = [
	{ label: DASHBOARD_LABEL, path: RAIZ.home2 },
	{ label: PROYECTOS_LABEL, path: PROYECTO_ROUTE.index.path },
	{ label: EDITAR_LABEL }
];

export const PROYECTOS_DETAIL_BREADCRUMBS = [
	{ label: DASHBOARD_LABEL, path: RAIZ.home2 },
	{ label: PROYECTOS_LABEL, path: PROYECTO_ROUTE.index.path },
	{ label: DETALLE_LABEL }
];
export const PROYECTOS_NEW_BREADCRUMBS = [
	{ label: DASHBOARD_LABEL, path: RAIZ.home2 },
	{ label: PROYECTOS_LABEL, path: PROYECTO_ROUTE.index.path },
	{ label: NUEVO_LABEL }
];

/**
 * BREADCRUMBS PARA ADMINISTRACION PRACTICAS
 */
export const PRACTICAS_BREADCRUMBS = [
	{ label: DASHBOARD_LABEL, path: RAIZ.home2 },
	{ label: PRACTICAS_LABEL }
];
export const PRACTICAS_EDIT_BREADCRUMBS = [
	{ label: DASHBOARD_LABEL, path: RAIZ.home2 },
	{ label: PRACTICAS_LABEL, path: PRACTICA_ROUTE.index.path },
	{ label: EDITAR_LABEL }
];
export const PRACTICAS_DETAIL_BREADCRUMBS = [
	{ label: DASHBOARD_LABEL, path: RAIZ.home2 },
	{ label: PRACTICAS_LABEL, path: PRACTICA_ROUTE.index.path },
	{ label: DETALLE_LABEL }
];
export const PRACTICAS_NEW_BREADCRUMBS = [
	{ label: DASHBOARD_LABEL, path: RAIZ.home2 },
	{ label: PRACTICAS_LABEL, path: PRACTICA_ROUTE.index.path },
	{ label: NUEVO_LABEL }
];
/**
 * ROUTER PARA ADMINISTRACION ARBOL DE CONOCIMIENTO
 */
export const ARBOL_CONOCIMIENTO_ROUTE = {
	index: {
		path: ADMIN_ARBOL_CONOCIMIENTO,
		label: ARBOL_CONOCIMIENTO_LABEL
	},
	nuevo: {
		path: ADMIN_ARBOL_CONOCIMIENTO_NEW,
		label: NUEVO_LABEL
	},
	detalle: {
		path: ADMIN_ARBOL_CONOCIMIENTO_DETAIL,
		label: DETALLE_LABEL
	},
	editar: {
		path: ADMIN_ARBOL_CONOCIMIENTO_EDIT,
		label: EDITAR_LABEL
	}
};

/**
 * BREADCRUMBS PARA ADMINISTRACION PROYECTOS
 */

export const ARBOL_CONOCIMIENTO_BREADCRUMBS = [
	{ label: DASHBOARD_LABEL, path: RAIZ.home2 },
	{ label: ARBOL_CONOCIMIENTO_LABEL }
];
export const ARBOL_CONOCIMIENTO_EDIT_BREADCRUMBS = [
	{ label: DASHBOARD_LABEL, path: RAIZ.home2 },
	{
		label: ARBOL_CONOCIMIENTO_LABEL,
		path: ARBOL_CONOCIMIENTO_ROUTE.index.path
	},
	{ label: EDITAR_LABEL }
];
export const ARBOL_CONOCIMIENTO_DETAIL_BREADCRUMBS = [
	{ label: DASHBOARD_LABEL, path: RAIZ.home2 },
	{
		label: ARBOL_CONOCIMIENTO_LABEL,
		path: ARBOL_CONOCIMIENTO_ROUTE.index.path
	},
	{ label: DETALLE_LABEL }
];
export const ARBOL_CONOCIMIENTO_NEW_BREADCRUMBS = [
	{ label: DASHBOARD_LABEL, path: RAIZ.home2 },
	{
		label: ARBOL_CONOCIMIENTO_LABEL,
		path: ARBOL_CONOCIMIENTO_ROUTE.index.path
	},
	{ label: NUEVO_LABEL }
];

/**
 * BREADCRUMBS PARA ADMINISTRACION GANANCIAS
 */

export const GANANCIAS_BREADCRUMBS = [
	{ label: DASHBOARD_LABEL, path: RAIZ.home2 },
	{ label: GANANCIAS_LABEL }
];

/**
 * BREADCRUMBS PARA ADMINISTRACION REGISTROCORTES
 */

export const REGISTROCORTES_BREADCRUMBS = [
	{ label: DASHBOARD_LABEL, path: RAIZ.home2 },
	{ label: REGISTROCORTES_LABEL }
];
export const REGISTROCORTES_EDIT_BREADCRUMBS = [
	{ label: DASHBOARD_LABEL, path: RAIZ.home2 },
	{ label: REGISTROCORTES_LABEL, path: REGISTROCORTES_ROUTE.index.path },
	{ label: EDITAR_LABEL }
];
export const REGISTROCORTES_DETAIL_BREADCRUMBS = [
	{ label: DASHBOARD_LABEL, path: RAIZ.home2 },
	{ label: REGISTROCORTES_LABEL, path: REGISTROCORTES_ROUTE.index.path },
	{ label: DETALLE_LABEL }
];
export const REGISTROCORTES_NEW_BREADCRUMBS = [
	{ label: DASHBOARD_LABEL, path: RAIZ.home2 },
	{ label: REGISTROCORTES_LABEL, path: REGISTROCORTES_ROUTE.index.path },
	{ label: NUEVO_LABEL }
];

/**
 * BREADCRUMBS PARA ADMINISTRACION HISTORIAGANANCIAS
 */

export const HISTORIAGANANCIAS_BREADCRUMBS = [
	{ label: DASHBOARD_LABEL, path: RAIZ.home2 },
	{ label: HISTORIAGANANCIAS_LABEL }
];

/**
 * BREADCRUMBS PARA ADMINISTRACION PORCETAJE
 */

export const PORCENTAJE_BREADCRUMBS = [
	{ label: DASHBOARD_LABEL, path: RAIZ.home2 },
	{ label: PORCENTAJE_LABEL }
];

/**
 * BREADCRUMBS PARA ADMINISTRACION REGISTROPAGO
 */

export const REGISTROPAGO_BREADCRUMBS = [
	{ label: DASHBOARD_LABEL, path: RAIZ.home2 },
	{ label: REGISTROPAGO_LABEL }
];
