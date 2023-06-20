import { faArrowLeft, faPlus } from '@fortawesome/free-solid-svg-icons';
import React, { useEffect, useState } from 'react';
import { textFilter } from 'react-bootstrap-table2-filter';
import { withRouter } from 'react-router-dom';
import {
	PORCENTAJE_BREADCRUMBS
//	ROLES_BREADCRUMBS,
	//ESTADO_ACTIVO,
	//ESTADO_INACTIVO
} from '../../../shared/constants/client';
import Datatable, {
	getStoredFilter
} from '../../../shared/datatable/Datatable';
import ToolBar from '../../../shared/toolbar/ToolBar';
import networkService from '../../../core/services/networkService';
import { generatePath } from 'react-router-dom';
import {

	

	ADMINISTRACION
	//ADM_ROLES_NEW,
	//ADM_ROLES_DETAIL,

} from '../../../shared/constants/routesApp';
import store from '../../../store/redux.store';
import { receiveTableFilter } from '../../../store/redux.actions';
import { MODULOS } from '../../../shared/constants/modulos';
import { PERMISOS } from '../../../shared/constants/permisos';

const Porcentaje = props => {
	//State
	const [registrocortesList, setRegistroPagoList] = useState([]);
	const currentFilter = getStoredFilter('porcentaje');
	const [paginationRegistroPago, setPaginationRegistroPago] = useState(
		currentFilter
			? currentFilter
			: {
					filter: {},
					page: 1,
					sizePerPage: 10,
					totalSize: 0,
					paginationSize: 4,
					showTotal: true,
					column: 'numeroCortes',
					order: 'asc'
			  }
	);
	//api
	const registrocortesApi = {
		buscar: '/porcentaje/buscar'
	};

	// Datatable
	const columns = [
		{
			dataField: 'idPorcentaje',
			text: 'Código',
			sort: true,
			filter: textFilter({ placeholder: 'Código...' })
		},

		
		{
			dataField: 'porcentajeEmpl', 
			text: 'Empleado',
			sort: true,
			filter: textFilter({ placeholder: 'CodEmpleado...' })
		},


		{
			dataField: 'createdAt',
			text: 'Fecha de creación',
			sort: true,
			filter: textFilter({ placeholder: 'Fecha de creación...' })
		}


	];

	const rowEvents = {
		onClick: (e, row) => {
			store.dispatch(
				receiveTableFilter({
					...paginationRegistroPago,
					jsType: 'RegistroPago'
				})
			);
		//	props.history.push(generatePath(ADM_PORCENTAJE_DETAIL, { id: row.id }));
		}
	};

	const handleRegistroPagoFilter = paginate => {
		networkService.post(registrocortesApi.buscar, paginate).then(response => {
			setPaginationRegistroPago({
				...paginate,
				page: response.data.number + 1,
				sizePerPage: response.data.size,
				totalSize: response.data.totalElements
			});
			setRegistroPagoList(response.data.content);
		});
	};

	//own functions
	useEffect(() => {}, []);

	//Botones para el toolbar
	const toolbarButtons = [
		{
			module: MODULOS.ADM_PORCENTAJE.codigo,
			permissions: [PERMISOS.escritura],
			label: 'Volver',
			actions: { onClick: () => props.history.push(ADMINISTRACION) },
			icon: faArrowLeft
		},
	
	];

	//return
	return (
		<div className='flex-row'>
			<div className='col-12'>
				<ToolBar breadcrumbs={PORCENTAJE_BREADCRUMBS} buttons={toolbarButtons} />
				<div className='bg-white'>
					<Datatable
						keyField='idRegistroPago'
						data={registrocortesList}
						columns={columns}
						rowEvents={rowEvents}
						callbackCustomFilter={handleRegistroPagoFilter}
						customPagination={paginationRegistroPago}
					/>
				</div>
			</div>
		</div>
	);
};
export default withRouter(Porcentaje);
