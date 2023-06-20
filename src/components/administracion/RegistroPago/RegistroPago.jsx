import { faArrowLeft, faPlus } from '@fortawesome/free-solid-svg-icons';
import React, { useEffect, useState } from 'react';
import { textFilter } from 'react-bootstrap-table2-filter';
import { withRouter } from 'react-router-dom';
import { REGISTROPAGO_BREADCRUMBS } from '../../../shared/constants/client';
import Datatable, {
	getStoredFilter
} from '../../../shared/datatable/Datatable';
import ToolBar from '../../../shared/toolbar/ToolBar';
import networkService from '../../../core/services/networkService';
import { ADMINISTRACION } from '../../../shared/constants/routesApp';
import store from '../../../store/redux.store';
import { receiveTableFilter } from '../../../store/redux.actions';
import { MODULOS } from '../../../shared/constants/modulos';
import { PERMISOS } from '../../../shared/constants/permisos';

const RegistroPago = props => {
	//State
	const [gananciasList, setGananciasList] = useState([]);
	const currentFilter = getStoredFilter('registroPago');
	const [paginationGanancias, setPaginationGanancias] = useState(
		currentFilter
			? currentFilter
			: {
					filter: {},
					page: 1,
					sizePerPage: 10,
					totalSize: 0,
					paginationSize: 4,
					showTotal: true,
					column: 'valorPago',
					order: 'asc'
			  }
	);
	//api
	const gananciasApi = {
		buscar: '/registropago/buscar'
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
			dataField: 'valorPago',
			text: 'Valor de Pago',
			sort: true,
			filter: textFilter({ placeholder: 'Valor de Pago...' })
		},
		{
			dataField: 'createdAt',
			text: 'fecha de creación',
			sort: true,
			filter: textFilter({ placeholder: 'fecha de creación...' })
		}
	];

	const rowEvents = {
		onClick: (e, row) => {
			store.dispatch(
				receiveTableFilter({
					...paginationGanancias,
					jsType: 'registroPago'
				})
			);
			//props.history.push(generatePath(ADM_GANANCIAS_DETAIL, { id: row.id }));
		}
	};

	const handleGananciasFilter = paginate => {
		networkService.post(gananciasApi.buscar, paginate).then(response => {
			setPaginationGanancias({
				...paginate,
				page: response.data.number + 1,
				sizePerPage: response.data.size,
				totalSize: response.data.totalElements
			});
			setGananciasList(response.data.content);
		});
	};

	//own functions
	useEffect(() => {}, []);

	//Botones para el toolbar
	const toolbarButtons = [
		{
			module: MODULOS.ADM_REGISTROPAGO.codigo,
			permissions: [PERMISOS.escritura],
			label: 'Volver',
			actions: { onClick: () => props.history.push(ADMINISTRACION) },
			icon: faArrowLeft
		}
	];

	//return
	return (
		<div className='flex-row'>
			<div className='col-12'>
				<ToolBar
					breadcrumbs={REGISTROPAGO_BREADCRUMBS}
					buttons={toolbarButtons}
				/>
				<div className='bg-white'>
					<Datatable
						keyField='id'
						data={gananciasList}
						columns={columns}
						rowEvents={rowEvents}
						callbackCustomFilter={handleGananciasFilter}
						customPagination={paginationGanancias}
					/>
				</div>
			</div>
		</div>
	);
};
export default withRouter(RegistroPago);
