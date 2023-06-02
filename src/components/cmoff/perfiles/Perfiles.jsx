import React, { useEffect, useState } from 'react';
import { withRouter } from 'react-router-dom';
import {
	PERFILES_BREADCRUMBS,
	VALIDATIONS_LABELS,
	MESSAGE_ERROR,
	MESSAGE_SUCCESS
} from '../../../shared/constants/client';
import { CMOFF } from '../../../shared/constants/routesApp';
import ToolBar from '../../../shared/toolbar/ToolBar';
import {
	faArrowLeft,
	faSave,
	faSearch
} from '@fortawesome/free-solid-svg-icons';
import FormGroup from 'reactstrap/lib/FormGroup';
import CustomSelect from '../../../shared/selects/CustomSelect';
import Datatable from '../../../shared/datatable/Datatable';
import {
	Button,
	Badge,
	ModalHeader,
	Modal,
	ModalBody,
	ModalFooter
} from 'reactstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { AvField, AvForm } from 'availity-reactstrap-validation';
import moment from 'moment';
import networkService from '../../../core/services/networkService';
import withReactContent from 'sweetalert2-react-content';
import Swal from 'sweetalert2';
import {
	notificationSuccess,
	notificationError
} from '../../../core/services/notificationService';
import { PERFILES_LABELS } from './PerfilesUtils';
import filterFactory, {
	textFilter,
	numberFilter,
	Comparator
} from 'react-bootstrap-table2-filter';
import BootstrapTable from 'react-bootstrap-table-next';
import { PROPIEDADES_PAGINATED } from '../../../shared/datatable/paginated';
import paginationFactory from 'react-bootstrap-table2-paginator';
let customSwal = withReactContent(Swal);

const Perfiles = props => {
	const [modalComparativo, setModalComparativo] = useState(false);
	const [persona, setPersona] = useState({});
	const [crearFormacion, setCrearFormacion] = useState(false);
	const [modalAsig, setModalAsig] = useState(false);
	const [conoc, setConoc] = useState('');
	const [curso, setCurso] = useState({
		nombre: '',
		fechaInicio: ''
	});
	const [proyectoRol, setProyectoRol] = useState({});
	let [errors, setErrors] = useState({});
	let [selects, setSelects] = useState({});
	const [cursosPlanTable, setCursosPlanTable] = useState([]);
	const [perfiles, setPerfiles] = useState([]);
	const [cursosList, setCursosList] = useState([]);
	const [cursoSeleccionado, setCursoSeleccionado] = useState({});
	const [conocimientosRol, setConocimientosRol] = useState([]);
	const [conocimientosMatriz, setConocimientosMatriz] = useState([]);
	const [paginationConocimientos, setPaginationConocimientos] = useState({
		filter: {
			proyecto: {
				codigo: ''
			},
			rolProyecto: ''
		},
		page: 1,
		sizePerPage: 10,
		totalSize: 0,
		paginationSize: 4,
		showTotal: true,
		column: 'conocimiento',
		order: 'asc'
	});
	const [listConoc, setListConoc] = useState([]);
	const [nivelesMadurez, setNivelesMadurez] = useState([]);

	const perfilesApi = {
		proyectos: '/perfil/proyectos/all',
		roles: '/perfil/rolesByProyecto/',
		cursosList: '/curso/ByConocimiento/',
		conocimientos: '/proyecto/findConocimientosPaginatedByProyectoAndRol',
		conocimientosPersona: '/perfil/conocimientosByPersona/',
		conocimientosPract: '/arbolconocimiento/listarOrdenPractica',
		parametrosNivelMadurez: '/parametro/buscarPorGrupo/NIVEL_MADUREZ',
		createFormacion: '/planformacion/create'
	};

	useEffect(() => {
		networkService.get(perfilesApi.conocimientosPract).then(response => {
			setListConoc(response.data);
			networkService.get(perfilesApi.proyectos).then(response => {
				const parsedData = formatSelectData(response.data, 'codigo', 'nombre');
				handleSelects('proyectos', parsedData);
			});
			networkService.get(perfilesApi.parametrosNivelMadurez).then(resp => {
				setNivelesMadurez(resp.data);
			});
		});
	}, []);

	const toolbarButtons = [
		{
			label: 'Volver',
			actions: { onClick: () => props.history.push(CMOFF) },
			icon: faArrowLeft
		}
	];

	const columns = [
		{
			dataField: 'codigo',
			text: ' Código empleado',
		//	filter: textFilter({ placeholder: 'Código empleado...' })
		},
		{
			dataField: 'nombre',
			text: 'Nombre',
			title: true,
		//	filter: textFilter({ placeholder: 'Nombre...' })
		},
		{
			dataField: 'apel1',
			text: 'Primer apellido',
			title: true,
			//filter: textFilter({ placeholder: 'Primer apellido...' })
		},
		{
			dataField: 'apel2',
			text: 'Segundo apellido',
			title: true,
		//	filter: textFilter({ placeholder: 'Segundo apellido...' })
		},
		{
			dataField: 'nivel',
			text: 'Nivel de cumplimiento',
			align: 'center',
			//sort: true,
			/*
			filter: numberFilter({
				placeholder: 'Nivel...',
				comparators: Comparator.EQ,
				withoutEmptyComparatorOption: true
			}),
			*/
			formatter: (cell, row) => {
				if (row.nivel === 100) {
					return <Badge color='success'>{row.nivel}</Badge>;
				} else if (row.nivel >= 75) {
					return <Badge color='warning'>{row.nivel}</Badge>;
				} else if (row.nivel >= 50) {
					return <Badge color='secondary'>{row.nivel}</Badge>;
				} else {
					return <Badge color='danger'>{row.nivel}</Badge>;
				}
			}
		},
		{
			dataField: '',
			text: 'Detalle',
			align: 'center',
			formatExtraData: { conocimientosRol }, //Cargar data para el manejo en columna
			formatter: (cell, row, rowIndex, formatExtraData) => {
				return (
					<Button
						outline
						color='danger'
						size='sm'
						onClick={() => {
							networkService
								.get(perfilesApi.conocimientosPersona + row.codigo)
								.then(response => {
									const conocMatriz = [];
									conocimientosRol.forEach(element => {
										const ret = { ...element };
										let conocPerson = response.data.find(
											el =>
												el.conocimiento.codConocimiento ===
												element.conocimiento.codConocimiento
										);
										ret.gradoMadurezValue = nivelesMadurez.find(
											el => el.valorOrigen === element.gradoMadurez
										).valorDestino;
										if (conocPerson) {
											ret.gradoMadurezPersona = conocPerson.nivelMadurez;
											ret.gradoMadurezPersonaValue = nivelesMadurez.find(
												el => el.valorOrigen === conocPerson.nivelMadurez
											).valorDestino;
										} else {
											ret.gradoMadurezPersona = 'B';
											ret.gradoMadurezPersonaValue = '1';
										}
										conocMatriz.push(ret);
									});
									setConocimientosMatriz(conocMatriz);
									setPersona(row);
									toggleModalComparativo();
								});
						}}>
						Ver
					</Button>
				);
			}
		}
	];

	const columnMatriz = [
		{
			dataField: 'conocimiento.nombre',
			text: 'Conocimiento rol'
		},
		{
			dataField: 'gradoMadurez',
			text: 'Nivel de madurez rol'
		},
		{
			dataField: 'gradoMadurezPersona',
			text: 'Nivel de madurez empleado'
		}
	];

	const columnsConocimientos = [
		{
			dataField: 'conocimiento.nombre',
			text: 'Conocimiento'
		},
		{
			dataField: 'gradoMadurez',
			text: 'Grado madurez'
		},
		{
			dataField: 'tipo',
			text: 'Tipo'
		}
	];

	const formatSelectData = (data, value, label) => {
		let dataFormat = [];
		let dataFormateo = data.map(element => {
			if (element.hasOwnProperty(value)) {
				element.value = element[value];
				delete element[value];
			}
			if (element.hasOwnProperty(label)) {
				element.label = element[label];
				delete element[label];
			}
			return element;
		});
		dataFormateo.forEach(({ value, label }) =>
			dataFormat.push({ value, label })
		);
		return dataFormat;
	};

	//manejador de data for selects
	const handleSelects = (type, data) => {
		selects = { ...selects, [type]: data };
		setSelects(selects);
	};

	const accionFormatterPlan = (cell, row, rowIndex, formatExtraData) => {
		if (row.gradoMadurezValue > row.gradoMadurezPersonaValue) {
			return (
				<Button
					outline
					color='warning'
					size='sm'
					onClick={() => {
						networkService
							.get(perfilesApi.cursosList + row.conocimiento.codConocimiento)
							.then(response => {
								const data = response.data.map(el => {
									return el.curso;
								});
								setCursosList(data);
								const parsedData = formatSelectData(data, 'id', 'nombre');
								handleSelects('cursos', parsedData);
							});
						setConoc(row.conocimiento.nombre);
						toggleModalAsig();
					}}>
					Crear
				</Button>
			);
		}
	};

	const columnsFormacion = [
		...columnMatriz,
		{
			dataField: '',
			text: 'Acción',
			align: 'center',
			formatter: accionFormatterPlan
		}
	];

	const accionFormatter = (cell, row, rowIndex, formatExtraData) => {
		return (
			<span onClick={e => e.stopPropagation()}>
				<Button
					outline
					color='danger'
					size='sm'
					onClick={() => {
						customSwal
							.fire({
								title: 'Confirmar',
								text: `¿Esta seguro de eliminar el curso ${row.curso.nombre}?`,
								showCancelButton: true,
								confirmButtonColor: '#004254',
								cancelButtonColor: '#03657c',
								confirmButtonText: `Si, Eliminar`,
								cancelButtonText: 'Cancelar'
							})
							.then(result => {
								if (result.value) {
									const newCursosPlanList = cursosPlanTable.filter(
										(element, index) => {
											return index !== rowIndex;
										}
									);
									setCursosPlanTable(newCursosPlanList);
								}
							});
					}}>
					Eliminar
				</Button>
			</span>
		);
	};

	const columnsPlan = [
		{
			dataField: 'cc',
			text: 'Conocimiento'
		},
		{
			dataField: 'curso.nombre',
			text: 'Curso'
		},
		{
			dataField: 'fechaInicio',
			text: 'Fecha inicio',
			formatter: cell => (cell ? moment(cell).format('DD/MM/YYYY') : '')
		},
		{
			dataField: '',
			text: 'Acción',
			align: 'center',
			formatter: accionFormatter,
			formatExtraData: { cursosPlanTable } //Cargar data para el manejo en columna
		}
	];

	const toggleModalComparativo = () => {
		setModalComparativo(!modalComparativo);
	};

	const toggleModalAsig = () => {
		setModalAsig(!modalAsig);
	};

	//Manejador de event Component CustomSelectCurso
	const handleChangeSelectCurso = (event, type) => {
		setCurso({ ...curso, [type.name]: event });
		if (event) {
			cursosList.forEach(element => {
				if (element.value === event.value) setCursoSeleccionado(element);
			});
		} else setCursoSeleccionado({});
	};

	// Manejo de filtro para table de conocimientos
	const handleConocimientosFilter = paginate => {
		paginate['filter'] = {
			...paginate['filter'],
			proyecto: { codigo: proyectoRol.proyecto.value },
			rolProyecto:
				paginate.sizePerPage === 10
					? paginate.filter.rolProyecto
						? paginate.filter.rolProyecto
						: proyectoRol.rol.value
					: proyectoRol.rol.value
		};
		networkService.post(perfilesApi.conocimientos, paginate).then(response => {
			const data = response.data.content;
			const req = data.map(ele => {
				const val = { ...ele };
				val.conocimiento = listConoc.find(
					el => el.codConocimiento === ele.conocimiento
				);
				return val;
			});
			setPaginationConocimientos({
				...paginate,
				page: response.data.number + 1,
				sizePerPage: response.data.size,
				totalSize: response.data.totalElements
			});
			setConocimientosRol(req);
		});
	};

	//Manejador de event Component CustomSelect
	const handleChangeSelectRol = (event, type) => {
		if (event) {
			handleConocimientosFilter({
				...paginationConocimientos,
				filter: {
					proyecto: {
						codigo: proyectoRol.proyecto.value
					},
					rolProyecto: event.value
				},
				page: paginationConocimientos.page - 1,
				sizePerPage: 10
			});
		} else {
			setConocimientosRol([]);
		}
		setProyectoRol({ ...proyectoRol, [type.name]: event });
		if (perfiles.length !== 0) setPerfiles([]);
	};

	const handleChangeSelectProyecto = (event, type) => {
		if (event) {
			networkService.get(perfilesApi.roles + event.value).then(response => {
				const parsedData = response.data.map(ele => {
					const resp = { value: ele, label: ele };
					return resp;
				});
				handleSelects('roles', parsedData);
			});
			setConocimientosRol([]);
		} else {
			handleSelects('roles', []);
			setConocimientosRol([]);
		}
		setProyectoRol({ ...proyectoRol, [type.name]: event, rol: '' });
		if (perfiles.length !== 0) setPerfiles([]);
	};

	//manejador de errores selects
	const handleErrors = (type, data) => {
		errors = { ...errors, [type]: data };
		setErrors(errors);
	};

	//Manejador de event Component AvField
	const handleChange = event => {
		const value = event.target.value;
		const name = event.target.name;
		setCurso({ ...curso, [name]: value });
	};

	const validateSelects = () => {
		!curso.nombre
			? handleErrors('cursoError', true)
			: handleErrors('cursoError', false);
	};

	const validateSelectsPerfiles = () => {
		!proyectoRol.proyecto
			? handleErrors('proyectoError', true)
			: handleErrors('proyectoError', false);
		!proyectoRol.rol
			? handleErrors('rolError', true)
			: handleErrors('rolError', false);
	};

	const handleValidSubmitBuscar = () => {
		if (!proyectoRol.proyecto || !proyectoRol.rol) validateSelectsPerfiles();
		else {
			networkService
				.get(
					`/perfil/proyecto/${proyectoRol.proyecto.value}/rol/${proyectoRol.rol.value}`
				)
				.then(response => {
					setPerfiles(response.data);
				});
		}
	};

	const handleValidSubmit = () => {
		if (!curso.nombre) validateSelects();
		else {
			let exist = false;
			cursosPlanTable.forEach(e => {
				if (e.curso.id === curso.nombre.value) {
					exist = true;
					notificationError({
						message: MESSAGE_ERROR.ERROR_CURSO_EXISTENTE_PLFRM
					});
				}
			});
			if (!exist) {
				/* Si el curso no esta en tabla temporal de plan de formacion se agrega correctamente*/
				const cursoTable = {};
				cursoTable.id = curso.nombre.value;
				cursoTable.cc = conoc;
				cursoTable.curso = {};
				cursoTable.curso.id = curso.nombre.value;
				cursoTable.curso.nombre = curso.nombre.label;
				cursoTable.fechaInicio = curso.fechaInicio;
				cursoTable.ente = cursoSeleccionado.enteInstructor;
				cursoTable.modalidad = cursoSeleccionado.modalidad;
				cursoTable.horas = cursoSeleccionado.intensidadHoras;
				cursoTable.coste = cursoSeleccionado.coste;
				setCursosPlanTable([...cursosPlanTable, cursoTable]);
				toggleModalAsig();
				notificationSuccess({
					message: MESSAGE_SUCCESS.CURSO_ADD_CORRECTAMENTE
				}).then(r => {
					setCurso({});
					setCursoSeleccionado({});
				});
			}
		}
	};

	const handleValidSubmitPlan = () => {
		if (cursosPlanTable.length === 0)
			notificationError({ message: MESSAGE_ERROR.ERROR_CURSOS_PLFRM });
		else {
			const planFormacionRequest = cursosPlanTable.map(e => {
				const cursoPlan = {};
				cursoPlan.id = null;
				cursoPlan.curso = e.curso;
				cursoPlan.codigoEmpleado = persona.codigo;
				cursoPlan.fechaInicio = e.fechaInicio;
				cursoPlan.estado = 'PROGRAMADO';
				cursoPlan.conocimientos = e.cc;
				return cursoPlan;
			});
			networkService
				.post(perfilesApi.createFormacion, planFormacionRequest)
				.then(response => {
					notificationSuccess(response.data).then(r => {
						setCursosPlanTable([]);
						toggleModalComparativo();
						setCrearFormacion(!crearFormacion);
					});
				});
		}
	};

	const rowStyle2 = (row, rowIndex) => {
		const style = {};
		if (
			row.gradoMadurezValue === row.gradoMadurezPersonaValue ||
			row.gradoMadurezValue <= row.gradoMadurezPersonaValue
		) {
			style.backgroundColor = '#fbbb21';
		} else {
			style.backgroundColor = '#ffffff';
		}
		return style;
	};

	const FragmentModalComparativo = (
		<Modal id='mComparativa' isOpen={modalComparativo} size='lg'>
			<ModalHeader cssModule={{ 'modal-title': 'w-100 text-center' }}>
				{PERFILES_LABELS.COMPARATIVA}
			</ModalHeader>
			<ModalBody>
				<div className='row pt-1'>
					<div className='col-12'>
						<h5 className='titleGeneral'>{'Filtro'}</h5>
						<div className='form-group row'>
							<label
								htmlFor='example-text-input'
								className='col-2 col-form-label'>
								{PERFILES_LABELS.PROYECTO}
							</label>
							<div className='col-4'>
								<input
									readOnly={true}
									className='form-control'
									type='text'
									value={proyectoRol.proyecto ? proyectoRol.proyecto.label : ''}
								/>
							</div>
							<label
								htmlFor='example-text-input'
								className='col-2 col-form-label'>
								{PERFILES_LABELS.ROL}
							</label>
							<div className='col-4'>
								<input
									readOnly={true}
									className='form-control'
									type='text'
									value={proyectoRol.rol ? proyectoRol.rol.label : ''}
								/>
							</div>
						</div>
						<br />
						<div className='form-group row'>
							<label
								htmlFor='example-text-input'
								className='col-2 col-form-label'>
								{PERFILES_LABELS.NOMBRE_EMPLEADO}
							</label>
							<div className='col-4'>
								<input
									readOnly={true}
									className='form-control'
									type='text'
									value={
										persona.nombre +
										' ' +
										persona.apel1 +
										' ' +
										(persona.apel2 != null ? persona.apel2 : '')
									}
								/>
							</div>
						</div>
						<div>
							<Button
								disabled={crearFormacion}
								outline
								color='warning'
								className='botonesGenerales float-right'
								onClick={() => {
									setCrearFormacion(!crearFormacion);
								}}>
								Crear plan formación
							</Button>
						</div>
					</div>
					<div className='col-12'>
						<br />
						<h5 className='titleGeneral'>{'Conocimientos'}</h5>
						<div className='bg-white'>
							<Datatable
								keyField='id'
								data={conocimientosMatriz}
								columns={
									crearFormacion === false ? columnMatriz : columnsFormacion
								}
								callbackCustomFilter={null}
								customPagination={null}
								rowStyle={rowStyle2}
							/>
						</div>
					</div>
					{crearFormacion ? (
						<div className='col-12'>
							<br />
							<h5 className='titleGeneral'>{'Plan de formación'}</h5>
							<AvForm id='cierreForm' onValidSubmit={handleValidSubmitPlan}>
								<div className='bg-white'>
									<Datatable
										keyField='id'
										data={cursosPlanTable}
										columns={columnsPlan}
										callbackCustomFilter={null}
										customPagination={null}
									/>
								</div>
							</AvForm>
						</div>
					) : (
						''
					)}
				</div>
			</ModalBody>
			<ModalFooter>
				<Button
					color='secondary'
					onClick={() => {
						toggleModalComparativo();
						setCrearFormacion(false);
						if (cursosPlanTable.length !== 0) setCursosPlanTable([]);
					}}>
					<FontAwesomeIcon icon={faArrowLeft} /> {'Volver'}
				</Button>
				{crearFormacion ? (
					<button
						className='btn btn-primary'
						form='cierreForm'
						/* disabled={!resultadoInputs} */
					>
						<FontAwesomeIcon icon={faSave} /> Guardar
					</button>
				) : (
					''
				)}
			</ModalFooter>
		</Modal>
	);

	const FragmentModalAsig = (
		<Modal id='mAsign' isOpen={modalAsig} size='lg'>
			<ModalHeader cssModule={{ 'modal-title': 'w-100 text-center' }}>
				{PERFILES_LABELS.ASIGNAR_CURSO_FORMACION}
			</ModalHeader>
			<ModalBody>
				<div className='row pt-1'>
					<div className='col-12'>
						<div className='form-group row'>
							<label
								htmlFor='example-text-input'
								className='col-2 col-form-label'>
								{PERFILES_LABELS.CONOCIMIENTO}
							</label>
							<div className='col-6'>
								<input
									readOnly={true}
									className='form-control'
									type='text'
									value={conoc}
								/>
							</div>
						</div>
						<AvForm
							id='asigForm'
							onValidSubmit={handleValidSubmit}
							onSubmit={validateSelects}>
							<div className='form-group row'>
								<label
									htmlFor='example-text-input'
									className='col-2 col-form-label'>
									{PERFILES_LABELS.CURSO}
									<span
										style={{ color: 'red' }}
										title={VALIDATIONS_LABELS.FIELD_REQUIRED}>
										*
									</span>
								</label>
								<div className='col-6'>
									<CustomSelect
										name='nombre'
										options={selects.cursos}
										value={curso.nombre || ''}
										onChange={handleChangeSelectCurso}
										error={errors.cursoError}
									/>
								</div>
							</div>
							<div className='form-group row'>
								<label
									htmlFor='example-text-input'
									className='col-2 col-form-label'>
									{PERFILES_LABELS.FECHAINICIO}
									<span
										style={{ color: 'red' }}
										title={VALIDATIONS_LABELS.FIELD_REQUIRED}>
										*
									</span>
								</label>
								<div className='col-4'>
									<AvField
										name='fechaInicio'
										type='date'
										errorMessage={
											'Fecha inicio ' + VALIDATIONS_LABELS.FIELD_INVALID
										}
										validate={{
											required: { value: true },
											dateRange: {
												start: {
													value: moment(moment().add(0, 'day')).format(
														'MM/DD/YYYY'
													)
												},
												end: {
													value: moment(moment().add(1, 'years')).format(
														'MM/DD/YYYY'
													)
												},
												errorMessage:
													'La fecha inicio debe ser igual o mayor a la fecha actual: ' +
													moment(moment().add(0, 'day')).format('DD/MM/YYYY')
											}
										}}
										value={curso.fechaInicio || ''}
										onChange={handleChange}
										autoComplete='fechaInicio'
									/>
								</div>
							</div>
							<div className='titulo'>
								<h5>{PERFILES_LABELS.DETALLES_CURSO_SELECCIONADO}</h5>
							</div>
							<br />
							<div className='form-group row'>
								<label
									htmlFor='example-text-input'
									className='col-1 col-form-label'>
									{PERFILES_LABELS.ENTE}
								</label>
								<div className='col-2'>
									<input
										readOnly={true}
										className='form-control'
										type='text'
										value={cursoSeleccionado.enteInstructor || ''}
									/>
								</div>
								<label
									htmlFor='example-text-input'
									className='col-2 col-form-label'>
									{PERFILES_LABELS.MODALIDAD}
								</label>
								<div className='col-2'>
									<input
										readOnly={true}
										className='form-control'
										type='text'
										value={cursoSeleccionado.modalidad || ''}
									/>
								</div>
								<label
									htmlFor='example-text-input'
									className='col-1 col-form-label'>
									{PERFILES_LABELS.HORAS}
								</label>
								<div className='col-1'>
									<input
										readOnly={true}
										className='form-control'
										type='text'
										value={cursoSeleccionado.intensidadHoras || ''}
									/>
								</div>
								<label
									htmlFor='example-text-input'
									className='col-1 col-form-label'>
									{PERFILES_LABELS.COSTE}
								</label>
								<div className='col-2'>
									<input
										readOnly={true}
										className='form-control'
										type='text'
										value={cursoSeleccionado.coste || ''}
									/>
								</div>
							</div>
						</AvForm>
					</div>
				</div>
			</ModalBody>
			<ModalFooter>
				<Button
					color='secondary'
					onClick={() => {
						toggleModalAsig();
						setCurso({});
						setCursoSeleccionado({});
						handleErrors('cursoError', false);
					}}>
					<FontAwesomeIcon icon={faArrowLeft} /> {'Volver'}
				</Button>
				<button
					className='btn btn-primary'
					form='asigForm'
					/* disabled={!resultadoInputs} */
				>
					<FontAwesomeIcon icon={faSave} /> Guardar
				</button>
			</ModalFooter>
		</Modal>
	);

	const customTotal = (from, to, size) => (
		<span className='react-bootstrap-table-pagination-total'>
			{' '}
			Mostrando las filas {from} a {to} de {size}
		</span>
	);
	const options = { ...PROPIEDADES_PAGINATED };
	options.paginationTotalRenderer = customTotal;

	return (
		<div className='flex-row'>
			<div className='col-12'>
				<ToolBar breadcrumbs={PERFILES_BREADCRUMBS} buttons={toolbarButtons} />
				<br />
				<div className='titulo'>
					<h3>{PERFILES_LABELS.FILTROS_DE_BUSQUEDA}</h3>
				</div>
				<br />
				<AvForm
					id='buscar'
					onValidSubmit={handleValidSubmitBuscar}
					onSubmit={validateSelectsPerfiles}>
					<div className='row pt-1'>
						<div className='col-12 col-sm-12 col-md-12 col-lg-12'>
							<FormGroup className='col-12 row'>
								<label
									htmlFor='example-text-input'
									className='col-1 col-form-label'>
									{PERFILES_LABELS.PROYECTO}
									<span
										style={{ color: 'red' }}
										title={VALIDATIONS_LABELS.FIELD_REQUIRED}>
										*
									</span>
								</label>
								<div className='col-3'>
									<CustomSelect
										name='proyecto'
										options={selects.proyectos}
										value={proyectoRol.proyecto || ''}
										onChange={handleChangeSelectProyecto}
										error={errors.proyectoError}
									/>
								</div>
								<label
									htmlFor='example-text-input'
									className='col-1 col-form-label'>
									{PERFILES_LABELS.ROL}
									<span
										style={{ color: 'red' }}
										title={VALIDATIONS_LABELS.FIELD_REQUIRED}>
										*
									</span>
								</label>
								<div className='col-3'>
									<CustomSelect
										name='rol'
										options={selects.roles}
										value={proyectoRol.rol || ''}
										onChange={handleChangeSelectRol}
										error={errors.rolError}
									/>
								</div>
								<div className='col-3'>
									<Button
										form='buscar'
										outline
										color='warning'
										className='botonesGenerales float-right'>
										<FontAwesomeIcon icon={faSearch} className='card-img-top' />
										{' Realizar búsqueda'}
									</Button>
								</div>
							</FormGroup>
						</div>
					</div>
				</AvForm>
				{conocimientosRol.length !== 0 ? (
					<div>
						<br />
						<br />
						<FormGroup className='col-12 row' tag='fieldset'>
							<label
								htmlFor='example-text-input'
								className='col-2 col-form-label'>
								{PERFILES_LABELS.CONOCIMIENTO_ROL}
							</label>
							<div className='col-7'>
								<Datatable
									keyField='id'
									data={conocimientosRol}
									columns={columnsConocimientos}
									callbackCustomFilter={handleConocimientosFilter}
									customPagination={paginationConocimientos}
								/>
							</div>
						</FormGroup>
					</div>
				) : (
					''
				)}
				<br />
				<br />
				<h3 className='titleGeneral'>{PERFILES_LABELS.PERFILES}</h3>
				<br />
				<div className='bg-white'>
					<Datatable
						striped
						hover
						keyField='codigo'
						data={perfiles}
						columns={columns}
						callbackCustomFilter={false}
						customPagination={false}
						noDataIndication={'Tabla sin registros'}
						//filter={filterFactory()}
						pagination={paginationFactory(options)}
					/>
				</div>
				{FragmentModalComparativo}
				{FragmentModalAsig}
			</div>
		</div>
	);
};
export default withRouter(Perfiles);
