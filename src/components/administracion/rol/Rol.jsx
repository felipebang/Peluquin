import { faArrowLeft, faPlus } from '@fortawesome/free-solid-svg-icons';
import React, { useEffect, useState } from 'react';
import { textFilter } from 'react-bootstrap-table2-filter';
import { withRouter } from 'react-router-dom';
import {
	ROLES_BREADCRUMBS,
	ESTADO_ACTIVO,
	ESTADO_INACTIVO
} from '../../../shared/constants/client';
import Datatable, {
	getStoredFilter
} from '../../../shared/datatable/Datatable';
import ToolBar from '../../../shared/toolbar/ToolBar';
import networkService from '../../../core/services/networkService';
import { generatePath } from 'react-router-dom';
import {
	ADM_ROLES_NEW,
	ADM_ROLES_DETAIL,
	ADMINISTRACION
} from '../../../shared/constants/routesApp';
import store from '../../../store/redux.store';
import { receiveTableFilter } from '../../../store/redux.actions';
import { MODULOS } from '../../../shared/constants/modulos';
import { PERMISOS } from '../../../shared/constants/permisos';

const Rol = props => {
	//State
	const [rolesList, setRolesList] = useState([]);
	const currentFilter = getStoredFilter('Rol');
	const [paginationRoles, setPaginationRoles] = useState(
		currentFilter
			? currentFilter
			: {
					filter: {},
					page: 1,
					sizePerPage: 10,
					totalSize: 0,
					paginationSize: 4,
					showTotal: true,
					column: 'descripcion',
					order: 'asc'
			  }
	);
	//api
	const rolesApi = {
		buscar: '/roles/buscar'
	};
	// Datatable
	const columns = [
		{
			dataField: 'codigo',
			text: 'Código',
			sort: true,
			filter: textFilter({ placeholder: 'Código...' })
		},
		{
			dataField: 'descripcion',
			text: 'Descripción',
			sort: true,
			filter: textFilter({ placeholder: 'Descripción...' })
		},
		{
			dataField: 'estado',
			text: 'Estado',
			sort: true,
			formatter: (cellContent, row) =>
				row.estado ? ESTADO_ACTIVO : ESTADO_INACTIVO
		}
	];

	const rowEvents = {
		onClick: (e, row) => {
			store.dispatch(
				receiveTableFilter({
					...paginationRoles,
					jsType: 'Rol'
				})
			);
			props.history.push(generatePath(ADM_ROLES_DETAIL, { id: row.id }));
		}
	};

	const handleRolesFilter = paginate => {
		networkService.post(rolesApi.buscar, paginate).then(response => {
			setPaginationRoles({
				...paginate,
				page: response.data.number + 1,
				sizePerPage: response.data.size,
				totalSize: response.data.totalElements
			});
			setRolesList(response.data.content);
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
		}
		// {
		// 	module: MODULOS.ADM_ROLES.codigo,
		// 	permissions: [PERMISOS.escritura],
		// 	label: 'Nuevo',
		// 	actions: {
		// 		onClick: () => props.history.push(ADM_ROLES_NEW)
		// 	},
		// 	icon: faPlus
		// }
	];

	//return
	return (
		<div className='flex-row'>
			<div className='col-12'>
				<ToolBar breadcrumbs={ROLES_BREADCRUMBS} buttons={toolbarButtons} />
				<div className='bg-white'>
					<Datatable
						keyField='id'
						data={rolesList}
						columns={columns}
						rowEvents={rowEvents}
						callbackCustomFilter={handleRolesFilter}
						customPagination={paginationRoles}
					/>
				</div>
			</div>
		</div>
	);
};
export default withRouter(Rol);
