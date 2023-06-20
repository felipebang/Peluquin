import React, { Component } from 'react';
import { BrowserRouter as Router, Redirect, Switch } from 'react-router-dom';
import Dashboard from '../../components/dashboard/Dashboard';
import {
	ADMINISTRACION,
	ADM_ROLES,
	ADM_ROLES_DETAIL,
	ADM_ROLES_EDIT,
	ADM_ROLES_NEW,
	ADM_USUARIOS,
	ADM_USUARIOS_EDIT,
	ADM_USUARIOS_NEW,
	DASHBOARD,
	CMOFF,
	CMOFF_FICHA_EMPLEADOS,
	CMOFF_FICHA_EMPLEADOS_PERSONAL,
	ITI_FORMATIVOS_IMPORTACION,
	LOGIN,
	RAIZ,
	CMOFF_REGISTROCORTES,
	CMOFF_REGISTROCORTESCE_NEW,
	CMOFF_REGISTROPAGO,
	ADM_EMPLEADOS_NEW,
	ADM_GANANCIAS,
	ADM_REGISTROCORTES,
	ADM_REGISTROCORTES_DETAIL,
	ADM_REGISTROCORTES_NEW,
	ADM_REGISTROCORTES_EDIT,
	ADM_HISTORIAGANANCIAS,
	ADM_PORCENTAJE,
	ADM_REGISTROPAGO
} from '../../shared/constants/routesApp';
import Login from '../auth/Login';
import LayoutBack from '../layout/LayoutBack';
import LayoutPublic from '../layout/LayouyPublic';
import RouteCustom from './RouteCustom';

import FichaPersonas from '../../components/cmoff/ficha_empleados/FichaPersonas';
import FichaPersonal from '../../components/cmoff/ficha_empleados/FichaPersonal';
import Importacion from '../../components/cmoff/importacion/Importacion';

import Usuario from '../../components/administracion/usuario/Usuario';
import UsuarioCE from '../../components/administracion/usuario/UsuarioCE';
import Rol from '../../components/administracion/rol/Rol';
import RolDetail from '../../components/administracion/rol/RolDetail';
import RolCE from '../../components/administracion/rol/RolCE';
import FichaPersonaCE from '../../components/cmoff/ficha_empleados/FichaPersonaCE';

import Ganancias from '../../components/administracion/ganancias/Ganancias';

import RegistroCortes from '../../components/administracion/RegistroCortes/RegistroCortes';
import RegistroCortesCE from '../../components/administracion/RegistroCortes/RegistroCortesCe';
import RegistroCortesDetail from '../../components/administracion/RegistroCortes/RegistroCortesDetail';

import HistoriaGanancias from '../../components/administracion/HistoriaGanancias/HistoriaGanancias';

import Porcentaje from '../../components/administracion/porcentaje/Porcentaje';

import registroPago from '../../components/administracion/RegistroPago/RegistroPago';

import CMOFFRegistroCortes from '../../components/cmoff/RegistroCortes/CMOFFRegistroCortes';
import CMOFFRegistroCortesCE from '../../components/cmoff/RegistroCortes/RegistroCortesCe';

import CMOFFRegistroPago from '../../components/cmoff/RegistroPago/CMOFFRegistroPago';

class Routes extends Component {
	render() {
		return (
			<Router>
				<Switch>
					<Redirect exact from={RAIZ} to={DASHBOARD} />

					{/**
					 *  modulos principales
					 */}
					<RouteCustom
						exact={true}
						path={LOGIN}
						layout={LayoutPublic}
						component={Login}
						privateRoute={false}
					/>

					<RouteCustom
						exact={true}
						path={DASHBOARD}
						layout={LayoutBack}
						component={Dashboard}
						privateRoute={true}
					/>
					<RouteCustom
						exact={true}
						path={CMOFF}
						layout={LayoutBack}
						component={Dashboard}
						privateRoute={true}
					/>
					<RouteCustom
						exact={true}
						path={ADMINISTRACION}
						layout={LayoutBack}
						component={Dashboard}
						privateRoute={true}
					/>

					{/**
					 *  submodulos cmoff
					 */}

					<RouteCustom
						exact={true}
						path={CMOFF_FICHA_EMPLEADOS}
						layout={LayoutBack}
						component={FichaPersonas}
						privateRoute={true}
					/>
					<RouteCustom
						exact={true}
						path={CMOFF_FICHA_EMPLEADOS_PERSONAL}
						layout={LayoutBack}
						component={FichaPersonal}
						privateRoute={true}
					/>

					<RouteCustom
						exact={true}
						path={ITI_FORMATIVOS_IMPORTACION}
						layout={LayoutBack}
						component={Importacion}
						privateRoute={true}
					/>

					<RouteCustom
						exact={true}
						path={CMOFF_REGISTROCORTES}
						layout={LayoutBack}
						component={CMOFFRegistroCortes}
						privateRoute={true}
					/>
					<RouteCustom
						exact={true}
						path={CMOFF_REGISTROCORTESCE_NEW}
						layout={LayoutBack}
						component={CMOFFRegistroCortesCE}
						privateRoute={true}
					/>

					<RouteCustom
						exact={true}
						path={CMOFF_REGISTROPAGO}
						layout={LayoutBack}
						component={CMOFFRegistroPago}
						privateRoute={true}
					/>

					{/**
					 *  modulos de administracion
					 */}
					<RouteCustom
						exact={true}
						path={ADM_USUARIOS}
						layout={LayoutBack}
						component={Usuario}
						privateRoute={true}
					/>
					<RouteCustom
						exact={true}
						path={ADM_USUARIOS_NEW}
						layout={LayoutBack}
						component={UsuarioCE}
						privateRoute={true}
					/>
					<RouteCustom
						exact={true}
						path={ADM_USUARIOS_EDIT}
						layout={LayoutBack}
						component={UsuarioCE}
						privateRoute={true}
					/>

					<RouteCustom
						exact={true}
						path={ADM_ROLES}
						layout={LayoutBack}
						component={Rol}
						privateRoute={true}
					/>
					<RouteCustom
						exact={true}
						path={ADM_ROLES_NEW}
						layout={LayoutBack}
						component={RolCE}
						privateRoute={true}
					/>
					<RouteCustom
						exact={true}
						path={ADM_ROLES_DETAIL}
						layout={LayoutBack}
						component={RolDetail}
						privateRoute={true}
					/>
					<RouteCustom
						exact={true}
						path={ADM_ROLES_EDIT}
						layout={LayoutBack}
						component={RolCE}
						privateRoute={true}
					/>

					<RouteCustom
						exact={true}
						path={ADM_GANANCIAS}
						layout={LayoutBack}
						component={Ganancias}
						privateRoute={true}
					/>

					<RouteCustom
						exact={true}
						path={ADM_HISTORIAGANANCIAS}
						layout={LayoutBack}
						component={HistoriaGanancias}
						privateRoute={true}
					/>

					<RouteCustom
						exact={true}
						path={ADM_REGISTROCORTES}
						layout={LayoutBack}
						component={RegistroCortes}
						privateRoute={true}
					/>

					<RouteCustom
						exact={true}
						path={ADM_REGISTROCORTES_NEW}
						layout={LayoutBack}
						component={RegistroCortesCE}
						privateRoute={true}
					/>
					<RouteCustom
						exact={true}
						path={ADM_REGISTROCORTES_DETAIL}
						layout={LayoutBack}
						component={RegistroCortesDetail}
						privateRoute={true}
					/>
					<RouteCustom
						exact={true}
						path={ADM_REGISTROCORTES_EDIT}
						layout={LayoutBack}
						component={RegistroCortesDetail}
						privateRoute={true}
					/>

					<RouteCustom
						exact={true}
						path={ADM_PORCENTAJE}
						layout={LayoutBack}
						component={Porcentaje}
						privateRoute={true}
					/>

					<RouteCustom
						exact={true}
						path={ADM_REGISTROPAGO}
						layout={LayoutBack}
						component={registroPago}
						privateRoute={true}
					/>

					<RouteCustom
						exact={true}
						path={ADM_EMPLEADOS_NEW}
						layout={LayoutBack}
						component={FichaPersonaCE}
						privateRoute={true}
					/>

					<Redirect exact to={DASHBOARD} />
				</Switch>
			</Router>
		);
	}
}

export default Routes;
