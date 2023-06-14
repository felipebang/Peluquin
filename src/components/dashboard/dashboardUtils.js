import {
	DASHBOARD,
	CMOFF,
	ITI_FORMATIVOS_IMPORTACION,
	ADMINISTRACION,
	ADM_PERSONAS,
	ADM_USUARIOS,
	ADM_ROLES,
	ITI_FICHA_EMPLEADOS,
	ADM_GANANCIAS,
	ADM_REGISTROCORTES,
	ADM_HISTORIAGANANCIAS,
	ADM_PORCENTAJE,
	ADM_REGISTROPAGO,

	CMOFF_REGISTROPAGO,
	CMOFF_REGISTROCORTES,
	

	

} from '../../shared/constants/routesApp';
import { MODULOS } from '../../shared/constants/modulos';

/**
 * Constantes Dashboard
 * @type {string}
 */

import ICON_VOLVER from '../../img/volver.svg';
import ICON_ITINERARIOFORMATIVO from '../../img/itinerarioFormativo.svg';
import ICON_IMPORTACION from '../../img/importacion.svg';
import ICON_ADMINISTRACION from '../../img/administracion.svg';
import ICON_ADM_USUARIOS from '../../img/admUser.svg';
import ICON_ROLES_PERMISOS from '../../img/roles_permisos.svg';
import ICON_FICHAPERSONAS from '../../img/fichaPersonas.svg';
import HistoriaGanancias from '../../img/HistoriaGanancias.svg';
import porcentaje from '../../img/porcentaje.svg';
import RegistroCortes from '../../img/RegistroCortes.svg';
import RegistroPago from '../../img/RegistroPago.svg';
import ganancias from '../../img/ganancias.svg';




/**
 * Iconos NavBarLeft
 * @type {string}
 */
import ICON_VOLVER_BLANCO from '../../img/volver-blanco.svg';
import ICON_ITINERARIOFORMATIVO_BLANCO from '../../img/itinerarioFormativo-blanco.svg';
import ICON_IMPORTACION_BLANCO from '../../img/importacion-blanco.svg';
import ICON_ADMINISTRACION_BLANCO from '../../img/administracion-blanco.svg';
import ICON_ADM_USUARIOS_BLANCO from '../../img/admUser-blanco.svg';
import ICON_ROLES_PERMISOS_BLANCO from '../../img/roles_permisos-blanco.svg';

/* import ICON_CARGOS_BLANCO from '../../img/cargo-blanco.svg'; */



export const DASHBOARD_MODULE = {
	label: 'Volver',
	text: 'Volver',
	link: DASHBOARD,
	src: ICON_VOLVER,
	srcNavLeft: ICON_VOLVER_BLANCO

};



export const MODULES = [
	{
		codigo: MODULOS.ITI_FORMATIVOS.codigo,
		label: MODULOS.ITI_FORMATIVOS.nombreCorto,
		text: MODULOS.ITI_FORMATIVOS.nombreLargo,
		link: CMOFF,
		src: ICON_ITINERARIOFORMATIVO,
		srcNavLeft: ICON_ITINERARIOFORMATIVO_BLANCO,
		modules: [
			{
				codigo: MODULOS.ITI_FORMATIVOS_IMPORTACION.codigo,
				label: MODULOS.ITI_FORMATIVOS_IMPORTACION.nombreCorto,
				text: MODULOS.ITI_FORMATIVOS_IMPORTACION.nombreLargo,
				link: ITI_FORMATIVOS_IMPORTACION,
				src: ICON_IMPORTACION,
				srcNavLeft: ICON_IMPORTACION_BLANCO
			},
	
			{
				codigo: MODULOS.CMOFF_REGISTROCORTES.codigo,
				label: MODULOS.CMOFF_REGISTROCORTES.nombreCorto,
				text: MODULOS.CMOFF_REGISTROCORTES.nombreLargo,
				link: CMOFF_REGISTROCORTES,
				src: RegistroCortes,
				srcNavLeft: ICON_ITINERARIOFORMATIVO_BLANCO
			},

				{
				codigo: MODULOS.CMOFF_REGISTROPAGO.codigo,
				label: MODULOS.CMOFF_REGISTROPAGO.nombreCorto,
				text: MODULOS.CMOFF_REGISTROPAGO.nombreLargo,
				link: CMOFF_REGISTROPAGO,
				src: RegistroPago,
				srcNavLeft:  ICON_ITINERARIOFORMATIVO_BLANCO
			}

		]
	},
	{
		codigo: MODULOS.ADMINISTRACION.codigo,
		label: MODULOS.ADMINISTRACION.nombreCorto,
		text: MODULOS.ADMINISTRACION.nombreLargo,
		link: ADMINISTRACION,
		src: ICON_ADMINISTRACION,
		srcNavLeft: ICON_ADMINISTRACION_BLANCO,
		modules: [
		
		
			{
				codigo: MODULOS.ADM_USUARIOS.codigo,
				label: MODULOS.ADM_USUARIOS.nombreCorto,
				text: MODULOS.ADM_USUARIOS.nombreLargo,
				link: ADM_USUARIOS,
				src: ICON_ADM_USUARIOS,
				srcNavLeft: ICON_ADM_USUARIOS_BLANCO
			},


//nose estan ultilizando //

			{
				codigo: MODULOS.ADM_ROLES.codigo,
				label: MODULOS.ADM_ROLES.nombreCorto,
				text: MODULOS.ADM_ROLES.nombreLargo,
				link: ADM_ROLES,
				src: ICON_ROLES_PERMISOS,
				srcNavLeft:  ICON_ROLES_PERMISOS_BLANCO
			},


		//----//




		{
			codigo: MODULOS.ADM_ROLES.codigo,
			label: MODULOS.ADMIN_PERSONAS.nombreCorto,
			text: MODULOS.ADMIN_PERSONAS.nombreLargo,
			link: ITI_FICHA_EMPLEADOS,
			src: ICON_FICHAPERSONAS,
			srcNavLeft: ICON_ADM_USUARIOS
		},
		




	

		
			{
				codigo: MODULOS.ADM_GANANCIAS.codigo,
				label: MODULOS.ADM_GANANCIAS.nombreCorto,
				text: MODULOS.ADM_GANANCIAS.nombreLargo,
				link: ADM_GANANCIAS,
				src: ganancias,
				srcNavLeft: ICON_ITINERARIOFORMATIVO_BLANCO
			},



			{
				codigo: MODULOS.ADM_REGISTROCORTES.codigo,
				label: MODULOS.ADM_REGISTROCORTES.nombreCorto,
				text: MODULOS.ADM_REGISTROCORTES.nombreLargo,
				link: ADM_REGISTROCORTES,
				src: RegistroCortes,
				srcNavLeft: ICON_ITINERARIOFORMATIVO_BLANCO
			},


			
			{
				codigo: MODULOS.ADM_HISTORIAGANANCIAS.codigo,
				label: MODULOS.ADM_HISTORIAGANANCIAS.nombreCorto,
				text: MODULOS.ADM_HISTORIAGANANCIAS.nombreLargo,
				link: ADM_HISTORIAGANANCIAS,
				src: HistoriaGanancias,
				srcNavLeft: ICON_ADM_USUARIOS
			}, 
		



			{
				codigo: MODULOS.ADM_PORCENTAJE.codigo,
				label: MODULOS.ADM_PORCENTAJE.nombreCorto,
				text: MODULOS.ADM_PORCENTAJE.nombreLargo,
				link: ADM_PORCENTAJE,
				src: porcentaje,
				srcNavLeft: ICON_ITINERARIOFORMATIVO_BLANCO
			}, 
		





			{
				codigo: MODULOS.ADM_REGISTROPAGO.codigo,
				label: MODULOS.ADM_REGISTROPAGO.nombreCorto,
				text: MODULOS.ADM_REGISTROPAGO.nombreLargo,
				link: ADM_REGISTROPAGO,
				src: RegistroPago,
				srcNavLeft: ICON_ITINERARIOFORMATIVO_BLANCO
			}

	
	

	
			
	
	

	
		]
	}
];
