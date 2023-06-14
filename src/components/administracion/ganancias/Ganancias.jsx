import { faArrowLeft, faPlus } from '@fortawesome/free-solid-svg-icons';
import React, { useEffect, useState } from 'react';
import { textFilter } from 'react-bootstrap-table2-filter';
import { withRouter } from 'react-router-dom';
import {
  GANANCIAS_BREADCRUMBS
} from '../../../shared/constants/client';
import Datatable, {
	getStoredFilter
} from '../../../shared/datatable/Datatable';
import ToolBar from '../../../shared/toolbar/ToolBar';
import networkService from '../../../core/services/networkService';
import { generatePath } from 'react-router-dom';
import {
	ADMINISTRACION
} from '../../../shared/constants/routesApp';
import store from '../../../store/redux.store';
import { receiveTableFilter } from '../../../store/redux.actions';
import { MODULOS } from '../../../shared/constants/modulos';
import { GANANCIAS } from '../../../shared/constants/ganancias';
import { PERMISOS } from '../../../shared/constants/permisos';
const Ganancias = props => {
	//State
	const [gananciasList, setGananciasList] = useState([]);
	const currentFilter = getStoredFilter('Ganancias');
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
					column: 'gananciaValor',
					order: 'asc'
			  }
	);
	//api
	const gananciasApi = {
		buscar: '/ganancias/buscar'
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
			dataField: 'gananciaValor',
			text: 'gananciaValor',
			sort: true,
			filter: textFilter({ placeholder: 'gananciasValor...' })
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
					jsType: 'Ganancias'
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
			label: 'Volver',
			actions: { onClick: () => props.history.push(ADMINISTRACION) },
			icon: faArrowLeft
		},
	
	];

	//return
	return (
		<div className='flex-row'>
			<div className='col-12'>
				<ToolBar breadcrumbs={GANANCIAS_BREADCRUMBS} buttons={toolbarButtons} />
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
export default withRouter(Ganancias);
