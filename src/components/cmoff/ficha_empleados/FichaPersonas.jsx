import React, { useEffect, useState } from 'react';
import { faArrowLeft,faPlus } from '@fortawesome/free-solid-svg-icons';
import { textFilter } from 'react-bootstrap-table2-filter';
import { generatePath, withRouter } from 'react-router-dom';
import { FICHA_EMPLEADOS_BREADCRUMBS } from '../../../shared/constants/client';
import Datatable from '../../../shared/datatable/Datatable';
import ToolBar from '../../../shared/toolbar/ToolBar';
import {
	CMOFF,
	ADMINISTRACION,
	CMOFF_FICHA_EMPLEADOS_PERSONAL,
	ADM_EMPLEADOS_NEW
} from '../../../shared/constants/routesApp';
import networkService from '../../../core/services/networkService';
import store from '../../../store/redux.store';
import { receiveTableFilter } from '../../../store/redux.actions';
import '../styles.css';
import { MODULOS } from '../../../shared/constants/modulos';
import { PERMISOS } from '../../../shared/constants/permisos';



const FichaPersonas = props => {
	//State
	const [personasList, setPersonasList] = useState([]);
	const [paginationPersonas, setPaginationPersonas] = useState({
		filter: {},
		page: 1,
		sizePerPage: 10,
		totalSize: 0,
		paginationSize: 4,
		showTotal: true,
		column: 'nombres',
		order: 'asc'
	});

	//api
	const fichaPersonasApi = {
		buscar: '/persona/buscar'
	};

	// Datatable
	const columns = [
		{
			dataField: 'nombres',
			text: 'Persona',
			sort: true,
			filter: textFilter({ placeholder: 'Nombre o apellidos...' }),
			title: (cell, row) => {
				return (
					(row.nombres || '') +
					' ' +
					(row.apellido1 || '') +
					' ' +
					(row.apellido2 || '')
				);
			},
			formatter: (cellContent, row) => {
				return (
					(row.nombres || '') +
					' ' +
					(row.apellido1 || '') +
					' ' +
					(row.apellido2 || '')
				);
			}
		},
		{
			dataField: 'codigoEmpleado',
			text: 'Documento identidad',
			sort: true,
			filter: textFilter({ placeholder: 'Documento identidad...' })
		},
		{
			dataField: 'empresa',
			text: 'Empresa',
			sort: true,
			filter: textFilter({ placeholder: 'Empresa...' })
		},
		{
			dataField: 'funcionPrincipal',
			text: 'Función principal',
			sort: true,
			filter: textFilter({ placeholder: 'Función principal...' })
		}
	];

	const rowEvents = {
		onClick: (e, row) => {
			store.dispatch(
				receiveTableFilter({
					...paginationPersonas,
					jsType: 'Personas'
				})
			);
			props.history.push(
				generatePath(CMOFF_FICHA_EMPLEADOS_PERSONAL, {
					id: row.codigoEmpleado
				})
			);
		}
	};

	const handleFichaPersonasFilter = paginate => {
		networkService.post(fichaPersonasApi.buscar, paginate).then(response => {
			setPaginationPersonas({
				...paginate,
				page: response.data.number + 1,
				sizePerPage: response.data.size,
				totalSize: response.data.totalElements
			});
			setPersonasList(response.data.content);
		});
	};

	//own functions
	useEffect(() => {}, []);

	//Botones para el toolbar
	const toolbarButtons = [
		{
			label: 'Volver',
			actions: { onClick: () => props.history.push(ADMINISTRACION) },
			icon: faArrowLeft
		},
		{
			module: MODULOS.ADM_ROLES.codigo,
			permissions: [PERMISOS.escritura],
			label: 'Nuevo',
			actions: {
				onClick: () => props.history.push(ADM_EMPLEADOS_NEW)
			},
			icon: faPlus
		},
	];

	//return
	return (
		<div className='flex-row'>
			<div className='col-12'>
				<ToolBar
					breadcrumbs={FICHA_EMPLEADOS_BREADCRUMBS}
					buttons={toolbarButtons}
				/>
				<div className='bg-white'>
					<Datatable
						keyField='codigoEmpleado'
						data={personasList}
						columns={columns}
						rowEvents={rowEvents}
						callbackCustomFilter={handleFichaPersonasFilter}
						customPagination={paginationPersonas}
					/>
				</div>
			</div>
		</div>
	);
};
export default withRouter(FichaPersonas);
