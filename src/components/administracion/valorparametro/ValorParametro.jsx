import networkService from '../../../core/services/networkService';
import React, { useEffect, useState } from 'react';
import {
	faArrowLeft,
	faEdit,
	faPlus,
	faSave
} from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { textFilter } from 'react-bootstrap-table2-filter';
import { withRouter } from 'react-router-dom';
import {
	VALIDATIONS_LABELS,
	ADM_VALORESPARAMETRO_BREADCRUMBS
} from '../../../shared/constants/client';
import Datatable from '../../../shared/datatable/Datatable';
import ToolBar from '../../../shared/toolbar/ToolBar';
import {
	Button,
	FormGroup,
	Modal,
	ModalBody,
	ModalFooter,
	ModalHeader
} from 'reactstrap';
import { notificationSuccess } from '../../../core/services/notificationService';
import { MODULOS } from '../../../shared/constants/modulos';
import { PERMISOS } from '../../../shared/constants/permisos';
import { VALORPARAMETRO_LABELS } from './valorParametroUtils';
import { AvField, AvForm } from 'availity-reactstrap-validation';
import ability from '../../../core/auth/ability';

const ValorParametro = props => {
	//State
	const [modalEdit, setModalEdit] = useState(false);
	const [esEdicion, setEsEdicion] = useState(false);
	const [valoresParametrosList, setValoresParametrosList] = useState([]);
	const valorParametroInitialState = {
		parametro: { codigo: props.match.params.codigo },
		vorigen: '',
		vdestino: ''
	};
	const [valorParametro, setValorParametro] = useState(
		valorParametroInitialState
	);
	const [paginationValorParametro, setPaginationValorParametro] = useState({
		filter: {},
		page: 1,
		sizePerPage: 10,
		totalSize: 0,
		paginationSize: 4,
		showTotal: true,
		column: 'valorOrigen',
		order: 'asc'
	});

	//api
	const valoresParametrosApi = {
		buscar: '/parametro/buscar',
		update: '/parametro/actualizar/',
		create: '/parametro/crear'
	};

	// Datatable
	const columns = [
		{
			dataField: 'valorOrigen',
			text: 'Valor origen',
			sort: true,
			filter: textFilter({ placeholder: 'Valor Origen...' })
		},
		{
			dataField: 'valorDestino',
			text: 'Valor destino',
			sort: true,
			filter: textFilter({ placeholder: 'Valor Destino...' })
		}
	];

	const rowEvents = {
		onClick: (e, row) => {
			setEsEdicion(true);
			setValorParametro({
				...row,
				vorigen: row.valorOrigen,
				vdestino: row.valorDestino
			});
			toggle();
		}
	};

	const toggle = () => {
		setModalEdit(!modalEdit);
	};

	const handleValoresParametrosFilter = paginate => {
		paginate['filter'] = {
			...paginate['filter'],
			grupoParametro: { codigo: props.match.params.codigo }
		};

		networkService
			.post(valoresParametrosApi.buscar, paginate)
			.then(response => {
				setPaginationValorParametro({
					...paginate,
					page: response.data.number + 1,
					sizePerPage: response.data.size,
					totalSize: response.data.totalElements
				});
				setValoresParametrosList(response.data.content);
			});
	};

	//own functions
	useEffect(() => {}, []);

	//Botones para el toolbar
	const toolbarButtons = [
		{
			label: 'Volver',
			actions: { onClick: () => props.history.goBack() },
			icon: faArrowLeft
		},
		{
			module: MODULOS.ADMIN_PARAMETRO.codigo,
			permissions: [PERMISOS.escritura],
			label: 'Nuevo',
			actions: {
				onClick: () => {
					toggle();
					setEsEdicion(false);
					setValorParametro(valorParametroInitialState);
				}
			},
			icon: faPlus
		}
	];

	const handleChange = event => {
		const value = event.target.value;
		const name = event.target.name;
		setValorParametro({ ...valorParametro, [name]: value });
	};

	const handleValidSubmit = () => {
		const valorParametroRequest = {};
		valorParametroRequest.id = valorParametro.id;
		valorParametroRequest.grupoParametro = {};
		valorParametroRequest.grupoParametro.codigo = props.match.params.codigo;
		valorParametroRequest.valorOrigen = valorParametro.vorigen.trim();
		valorParametroRequest.valorDestino = valorParametro.vdestino.trim();

		if (esEdicion) {
			networkService
				.put(
					valoresParametrosApi.update + valorParametroRequest.id,
					valorParametroRequest
				)
				.then(response => {
					const responsedValorParametro = { ...valorParametroRequest };
					const index = valoresParametrosList.findIndex(
						element => element.id === responsedValorParametro.id
					);
					if (index !== -1) {
						const newValoresParametroList = [...valoresParametrosList];
						newValoresParametroList[index] = responsedValorParametro;
						setValoresParametrosList(newValoresParametroList);
					}
					notificationSuccess(response.data).then(r => toggle());
				});
		} else {
			networkService
				.post(valoresParametrosApi.create, valorParametroRequest)
				.then(response => {
					notificationSuccess(response.data).then(r => {
						handleValoresParametrosFilter({
							...paginationValorParametro,
							page: paginationValorParametro.page - 1
						});
						toggle();
					});
				});
		}
	};

	const FragmentModalEdit = (
		<Modal isOpen={modalEdit} size='lg'>
			<ModalHeader>
				<FontAwesomeIcon icon={faEdit} />{' '}
				{esEdicion
					? VALORPARAMETRO_LABELS.EDITAR_VALORPARAMETRO
					: VALORPARAMETRO_LABELS.NUEVO_VALORPARAMETRO}
			</ModalHeader>
			<ModalBody>
				<FormGroup tag='fieldset'>
					<AvForm id='valorParametroForm' onValidSubmit={handleValidSubmit}>
						<div className='row pt-1'>
							<div className='col-12'>
								<div className='form-group row'>
									<label
										htmlFor='example-text-input'
										className='col-3 col-form-label'>
										{VALORPARAMETRO_LABELS.codigoParametro}
									</label>
									<div className='col-3'>
										<AvField
											name='codigoParametro'
											type='text'
											readOnly={true}
											value={props.match.params.codigo || ''}
											autoComplete='codigoParametro'
										/>
									</div>
									<label
										htmlFor='example-text-input'
										className='col-3 col-form-label'>
										{VALORPARAMETRO_LABELS.vorigen}
										<span
											style={{ color: 'red' }}
											title='Este campo es obligatorio.'>
											*
										</span>
									</label>
									<div className='col-3'>
										<AvField
											disabled={esEdicion ? true : false}
											name='vorigen'
											type='text'
											errorMessage={
												VALORPARAMETRO_LABELS.vorigen +
												VALIDATIONS_LABELS.FIELD_INVALID
											}
											validate={{
												required: { value: true },
												pattern: {
													value: "^[a-zA-Z0-9-_']+$"
												},
												minLength: { value: 1 },
												maxLength: { value: 20 }
											}}
											value={valorParametro.vorigen || ''}
											onChange={handleChange}
											autoComplete='vorigen'
										/>
									</div>
								</div>
								<div className='form-group row'>
									<label
										htmlFor='example-text-input'
										className='col-3 col-form-label'>
										{VALORPARAMETRO_LABELS.vdestino}
										<span
											style={{ color: 'red' }}
											title='Este campo es obligatorio.'>
											*
										</span>
									</label>
									<div className='col-3'>
										<AvField
											name='vdestino'
											type='text'
											errorMessage={
												VALORPARAMETRO_LABELS.vdestino +
												VALIDATIONS_LABELS.FIELD_INVALID
											}
											validate={{
												required: { value: true },
												minLength: { value: 1 },
												maxLength: { value: 20 }
											}}
											value={valorParametro.vdestino || ''}
											onChange={handleChange}
											autoComplete='vdestino'
										/>
									</div>
								</div>
							</div>
						</div>
					</AvForm>
				</FormGroup>
			</ModalBody>

			<ModalFooter>
				<Button color='secondary' onClick={toggle}>
					<FontAwesomeIcon icon={faArrowLeft} /> Cancelar
				</Button>
				{esEdicion ? (
					ability.can(
						PERMISOS.actualizacion,
						MODULOS.ADMIN_PARAMETRO.codigo
					) ? (
						<button className='btn btn-primary' form='valorParametroForm'>
							<FontAwesomeIcon icon={faSave} /> Guardar
						</button>
					) : (
						''
					)
				) : (
					<button className='btn btn-primary' form='valorParametroForm'>
						<FontAwesomeIcon icon={faSave} /> Guardar
					</button>
				)}
			</ModalFooter>
		</Modal>
	);

	//return
	return (
		<div className='flex-row'>
			<div className='col-12'>
				<ToolBar
					breadcrumbs={ADM_VALORESPARAMETRO_BREADCRUMBS}
					buttons={toolbarButtons}
				/>
				<div className='bg-white'>
					<Datatable
						keyField='id'
						data={valoresParametrosList}
						columns={columns}
						rowEvents={rowEvents}
						callbackCustomFilter={handleValoresParametrosFilter}
						customPagination={paginationValorParametro}
					/>
					{FragmentModalEdit}
				</div>
			</div>
		</div>
	);
};
export default withRouter(ValorParametro);
