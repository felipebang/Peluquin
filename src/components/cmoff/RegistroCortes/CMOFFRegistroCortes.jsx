import { faArrowLeft, faPlus } from '@fortawesome/free-solid-svg-icons';
import React, { useEffect, useState } from 'react';
import { textFilter } from 'react-bootstrap-table2-filter';
import { withRouter } from 'react-router-dom';
import {
  REGISTROCORTES_BREADCRUMBS
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
	ADM_REGISTROCORTES_NEW,
	ADM_REGISTROCORTES_DETAIL,
	ADMINISTRACION,
	CMOFF
	//ADM_ROLES_NEW,
	//ADM_ROLES_DETAIL,

} from '../../../shared/constants/routesApp';
import store from '../../../store/redux.store';
import { receiveTableFilter } from '../../../store/redux.actions';
import { MODULOS } from '../../../shared/constants/modulos';
import { PERMISOS } from '../../../shared/constants/permisos';

const CMOFFRegistroCortes = props => {
	//State
	const [registrocortesList, setRegistroCortesList] = useState([]);
	const currentFilter = getStoredFilter('RegistroCortes');
	const [paginationRegistroCortes, setPaginationRegistroCortes] = useState(
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
		buscar: '/registrocortes/buscar'
	};

	// Datatable
	const columns = [
		{
			dataField: 'id',
			text: 'Código',
			sort: true,
			filter: textFilter({ placeholder: 'Código...' })
		},


		{
			dataField: 'codigoEmpleado',
			text: 'Documento identidad',
			sort: true,
			filter: textFilter({ placeholder: 'Documento identidad...' })
		},


		{
			dataField: 'idPorcentaje',
			text: 'Porcentaje',
			sort: true,
			filter: textFilter({ placeholder: 'Porcentaje...' })
		},

	


    {
			dataField: 'numeroCortes',
			text: 'Numero de Cortes',
			sort: true,
			filter: textFilter({ placeholder: 'Numero de Cortes...' })
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
					...paginationRegistroCortes,
					jsType: 'RegistroCortes'
				})
			);
		}
	};

	const handleRegistroCortesFilter = paginate => {
		networkService.post(registrocortesApi.buscar, paginate).then(response => {
			setPaginationRegistroCortes({
				...paginate,
				page: response.data.number + 1,
				sizePerPage: response.data.size,
				totalSize: response.data.totalElements
			});
			setRegistroCortesList(response.data.content);
		});
	};

	//own functions
	useEffect(() => {}, []);

	//Botones para el toolbar
	const toolbarButtons = [
		{
			label: 'Volver',
			actions: { onClick: () => props.history.push(CMOFF) },
			icon: faArrowLeft
		},
		{
			module: MODULOS.ADM_ROLES.codigo,
			permissions: [PERMISOS.escritura],
			label: 'Nuevo',
		actions: {
				onClick: () => props.history.push(ADM_REGISTROCORTES_NEW)
			},
			icon: faPlus
		}
	];

	//return
	return (
		<div className='flex-row'>
			<div className='col-12'>
				<ToolBar breadcrumbs={REGISTROCORTES_BREADCRUMBS} buttons={toolbarButtons} />
				<div className='bg-white'>
					<Datatable
						keyField='idRegistroCortes'
						data={registrocortesList}
						columns={columns}
						rowEvents={rowEvents}
						callbackCustomFilter={handleRegistroCortesFilter}
						customPagination={paginationRegistroCortes}
					/>
				</div>
			</div>
		</div>
	);
};
export default withRouter(CMOFFRegistroCortes);
