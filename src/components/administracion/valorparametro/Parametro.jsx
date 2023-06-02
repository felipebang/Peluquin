import {
	ADMINISTRACION,
	ADMIN_VALORPARAMETRO
} from '../../../shared/constants/routesApp';
import networkService from '../../../core/services/networkService';
import React, { useEffect, useState } from 'react';
import { faArrowLeft } from '@fortawesome/free-solid-svg-icons';
import { textFilter } from 'react-bootstrap-table2-filter';
import { withRouter, generatePath } from 'react-router-dom';
import { ADM_PARAMETRO_BREADCRUMBS } from '../../../shared/constants/client';
import Datatable, {
	getStoredFilter
} from '../../../shared/datatable/Datatable';
import ToolBar from '../../../shared/toolbar/ToolBar';
import store from '../../../store/redux.store';
import { receiveTableFilter } from '../../../store/redux.actions';

const Parametro = props => {
	//State
	const [ParametrosList, setParametrosList] = useState([]);
	const currentFilter = getStoredFilter('Parametro');
	const [paginationParametro, setPaginationParametro] = useState(
		currentFilter
			? currentFilter
			: {
					filter: {},
					page: 1,
					sizePerPage: 10,
					totalSize: 0,
					paginationSize: 4,
					showTotal: true,
					column: 'codigo',
					order: 'asc'
			  }
	);
	//api
	const ParametrosApi = {
		buscar: '/parametro/buscarGrupos'
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
		}
	];

	const rowEvents = {
		onClick: (e, row) => {
			store.dispatch(
				receiveTableFilter({
					...paginationParametro,
					jsType: 'Parametro'
				})
			);
			props.history.push(
				generatePath(ADMIN_VALORPARAMETRO, { codigo: row.codigo })
			);
		}
	};

	const handleParametrosFilter = paginate => {
		networkService.post(ParametrosApi.buscar, paginate).then(response => {
			setPaginationParametro({
				...paginate,
				page: response.data.number + 1,
				sizePerPage: response.data.size,
				totalSize: response.data.totalElements
			});
			setParametrosList(response.data.content);
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
	];

	//return
	return (
		<div className='flex-row'>
			<div className='col-12'>
				<ToolBar
					breadcrumbs={ADM_PARAMETRO_BREADCRUMBS}
					buttons={toolbarButtons}
				/>
				<div className='bg-white'>
					<Datatable
						keyField='codigo'
						data={ParametrosList}
						columns={columns}
						rowEvents={rowEvents}
						callbackCustomFilter={handleParametrosFilter}
						customPagination={paginationParametro}
					/>
				</div>
			</div>
		</div>
	);
};
export default withRouter(Parametro);
