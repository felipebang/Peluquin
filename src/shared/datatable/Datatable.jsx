import React from 'react';
import BootstrapTable from 'react-bootstrap-table-next';
import filterFactory from 'react-bootstrap-table2-filter';
import cellEditFactory from 'react-bootstrap-table2-editor';
import paginationFactory, {
	PaginationTotalStandalone,
	PaginationListStandalone,
	PaginationProvider,
	SizePerPageDropdownStandalone
} from 'react-bootstrap-table2-paginator';
import '../../shared/datatable/Datatable.css';
import store from '../../store/redux.store';

export const getStoredFilter = jsType => {
	const currentFilter = store.getState().generalReducer.currentFilter;
	if (currentFilter && currentFilter.jsType === jsType) {
		return currentFilter;
	}
	return null;
};

const Datatable = props => {
	const customTotal = (from, to, size) => (
		<span className='react-bootstrap-table-pagination-total'>
			{' '}
			Mostrando las filas {from} a {to} de {size}
		</span>
	);

	const datatableRemoto = {
		filter: true,
		pagination: true,
		sort: true,
		cellEdit: Boolean(props.cellEdit)
	};

	const datatablePagination = {
		...props.customPagination
	};

	const customPagination = {
		...props.customPagination,
		custom: true,
		paginationTotalRenderer: customTotal
	};

	const defaultSorted = [
		{
			dataField: customPagination.column,
			order: customPagination.order
		}
	];

	const onTableChange = (type, newState) => {
		// El filtro permite max 3 niveles de filtracion ej: obj.value.subvalue
		let filter = {};
		const paginate = datatablePagination;
		if (newState.filters) {
			for (const key in newState.filters) {
				let value = newState.filters[key];
				if (newState.filters.hasOwnProperty(key)) {
					let keyList = key.split('.');
					if (keyList.length > 1) {
						let valor;
						keyList.map((key, index) => {
							if (index === 0) {
								if (!filter.hasOwnProperty(key)) {
									filter[key] = {};
								}
								valor = filter[key];
							} else if (index !== keyList.length - 1) {
								let obj = valor;
								if (!obj.hasOwnProperty(key)) {
									obj[key] = {};
								}
								valor = obj[key];
							}
							if (index === keyList.length - 1) {
								if (value.filterType === 'NUMBER')
									valor[key] = value.filterVal.number;
								else valor[key] = value.filterVal;
							}
						});
					} else {
						if (value.filterType === 'NUMBER')
							filter[key] = value.filterVal.number;
						else filter[key] = value.filterVal;
					}
				}
			}
		}
		paginate.filter = filter;
		paginate.page = newState.page - 1;
		paginate.sizePerPage = newState.sizePerPage;
		paginate.column =
			newState.sortField != null ? newState.sortField : paginate.column;
		paginate.order =
			newState.sortOrder != null ? newState.sortOrder : paginate.order;

		if (type === 'filter' || type === 'sort' || type === 'pagination') {
			props.callbackCustomFilter(paginate);
		} else if (type === 'cellEdit' && newState.cellEdit) {
			props.callbackCustomCellEdit(newState.cellEdit);
		}
	};

	return (
		<PaginationProvider pagination={paginationFactory(customPagination)}>
			{({ paginationProps, paginationTableProps }) => (
				<div>
					<BootstrapTable
						selectRow={props.selectRow}
						rowEvents={props.rowEvents}
						striped
						hover
						keyField={props.keyField}
						data={props.data}
						columns={props.columns}
						filter={filterFactory()}
						remote={datatableRemoto}
						onTableChange={onTableChange}
						defaultSorted={defaultSorted}
						noDataIndication={props.noDataIndication}
						cellEdit={cellEditFactory(props.cellEdit)}
						rowStyle={props.rowStyle}
						{...paginationTableProps}
					/>
					<div className='row react-bootstrap-table-pagination'>
						<div className='col-md-6 col-xs-6 col-sm-6 col-lg-6'>
							{props.customPagination && props.data && props.data.length > 0 ? (
								<SizePerPageDropdownStandalone {...paginationProps} />
							) : (
								''
							)}
							{props.customPagination ? (
								<PaginationTotalStandalone {...paginationProps} />
							) : (
								''
							)}
						</div>
						<div className='react-bootstrap-table-pagination-list col-md-6 col-xs-6 col-sm-6 col-lg-6'>
							{props.customPagination ? (
								<PaginationListStandalone
									className='react-bootstrap-table-pagination-list col-md-6 col-xs-6 col-sm-6 col-lg-6'
									{...paginationProps}
								/>
							) : (
								''
							)}
						</div>
					</div>
				</div>
			)}
		</PaginationProvider>
	);
};
export default Datatable;
