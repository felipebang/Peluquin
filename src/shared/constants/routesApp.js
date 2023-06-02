/**
 * Constantes rutas de la App Front
 * @type {string}
 */

/* Constantes rutas principales */
export const RAIZ = '/'; //finalizar con '/'
export const LOGIN = RAIZ + 'login';
export const DASHBOARD = RAIZ + 'dashboard';
export const CMOFF = RAIZ + 'cmoff';
export const ADMINISTRACION = RAIZ + 'administracion';

/* Constantes modulo itinerarios formativos */
export const ITI_FORMATIVOS_PERFILES = CMOFF + '/perfiles';
export const ITI_FORMATIVOS_MAPAC_PROYECTOS =
	ITI_FORMATIVOS_PERFILES + '/proyectos';
export const ITI_FORMATIVOS_MAPAC_PROYECTOS_DETAIL =
	ITI_FORMATIVOS_MAPAC_PROYECTOS + '/:id';
export const ITI_FORMATIVOS_MAPAC_PROYECTOS_HISTORICO =
	ITI_FORMATIVOS_MAPAC_PROYECTOS_DETAIL + '/historico';
export const ITI_FORMATIVOS_MAPAC_PRACTICAS =
	ITI_FORMATIVOS_PERFILES + '/practicas';

export const ITI_FORMATIVOS_REPORTES = CMOFF + '/reportes';


export const CMOFF_FICHA_EMPLEADOS =
	CMOFF + '/ficha-personas';
export const CMOFF_FICHA_EMPLEADOS_PERSONAL =
CMOFF_FICHA_EMPLEADOS + '/:id/ficha-personal';



export const ITI_FORMATIVOS_IMPORTACION = CMOFF + '/importacion';
export const CMOFF_REGISTROCORTES= CMOFF  + '/registrocortes';
export const CMOFF_REGISTROPAGO = CMOFF + '/registropago';



/**
 * Constantes modulo de administracion
 */
export const ADM_USUARIOS = ADMINISTRACION + '/usuarios';
export const ADM_USUARIOS_NEW = ADM_USUARIOS + '/crear';
export const ADM_USUARIOS_EDIT = ADM_USUARIOS + '/:id/editar';



export const ADM_PERSONAS = ADMINISTRACION + '/personas';

export const ADM_ROLES = ADMINISTRACION + '/roles-permisos';
export const ADM_ROLES_NEW = ADM_ROLES + '/crear';
export const ADM_ROLES_DETAIL = ADM_ROLES + '/:id';
export const ADM_ROLES_EDIT = ADM_ROLES + '/:id/editar';

export const ADMIN_PARAMETRO = ADMINISTRACION + '/parametro';
export const ADMIN_VALORPARAMETRO = ADMIN_PARAMETRO + '/:codigo/valorparametro';

export const ADMIN_PROYECTOS = ADMINISTRACION + '/proyectos';
export const ADMIN_PROYECTOS_NEW = ADMIN_PROYECTOS + '/crear';
export const ADMIN_PROYECTOS_DETAIL = ADMIN_PROYECTOS + '/:id';
export const ADMIN_PROYECTOS_EDIT = ADMIN_PROYECTOS + '/:id/editar';
export const ADMIN_CARGOS = ADMINISTRACION + '/roles-empleado';




export const ADMIN_PRACTICAS = ADMINISTRACION + '/practicas';
export const ADMIN_PRACTICAS_NEW = ADMIN_PRACTICAS + '/crear';
export const ADMIN_PRACTICAS_DETAIL =
	ADMIN_PRACTICAS + '/:idp/subpractica/:ids/especializacion/:ide';
export const ADMIN_PRACTICAS_EDIT = ADMIN_PRACTICAS_DETAIL + '/editar';

export const ADMIN_ARBOL_CONOCIMIENTO = ADMINISTRACION + '/arbolconocimiento';
export const ADMIN_ARBOL_CONOCIMIENTO_NEW = ADMIN_ARBOL_CONOCIMIENTO + '/crear';
export const ADMIN_ARBOL_CONOCIMIENTO_DETAIL = ADMIN_ARBOL_CONOCIMIENTO + '/:id';
export const ADMIN_ARBOL_CONOCIMIENTO_EDIT = ADMIN_ARBOL_CONOCIMIENTO + '/:id/editar';




export const ITI_FICHA_EMPLEADOS =
CMOFF + '/ficha-personas';
export const ITI_FORMATIVOS_FICHA_EMPLEADOS_PERSONAL =
ITI_FICHA_EMPLEADOS + '/:id/ficha-personal';

export const ADM_EMPLEADOS_NEW = ITI_FICHA_EMPLEADOS + '/crear';





//---ganancias/-//


export const ADM_GANANCIAS= ADMINISTRACION + '/ganancias';



//--//



//---RegistroCortes/-//


export const ADM_REGISTROCORTES= ADMINISTRACION + '/registrocortes';
export const ADM_REGISTROCORTES_NEW = ADM_REGISTROCORTES + '/crear';
export const ADM_REGISTROCORTES_DETAIL = ADM_REGISTROCORTES + '/:id';
export const ADM_REGISTROCORTES_EDIT = ADM_REGISTROCORTES + '/:id/editar';



//-HISTORIAGANANCIAS-//



export const ADM_HISTORIAGANANCIAS = ADMINISTRACION + '/historiaganancias';









//-PORCENTAJES-//



export const ADM_PORCENTAJE = ADMINISTRACION + '/porcentaje';











//-REGISTROPAGO--//

export const ADM_REGISTROPAGO = ADMINISTRACION + '/registropago';





