import {
	faArrowLeft,
	faIdCardAlt,
	faSave,
	faSearch,
	faUserCircle
} from '@fortawesome/free-solid-svg-icons';
import { FormGroup } from 'reactstrap';
import CustomSelect from '../../../shared/selects/CustomSelect';
import CustomAsyncSelect from '../../../shared/selects/CustomAsyncSelect';
import React, { useEffect, useState } from 'react';
import { withRouter } from 'react-router-dom';
import ToolBar from '../../../shared/toolbar/ToolBar';
import {
	REGISTROCORTES_NEW_BREADCRUMBS,
	VALIDATIONS_LABELS,
	REGISTROCORTES_EDIT_BREADCRUMBS,
	ESTADO_INACTIVO,
	ESTADO_ACTIVO,
	MESSAGE_ERROR,
	MESSAGE_INFO
} from '../../../shared/constants/client';
import { AvField, AvForm } from 'availity-reactstrap-validation';
import networkService from '../../../core/services/networkService';
import Swal from 'sweetalert2';
import {
	notificationError,
	notificationSuccess,
	notificationInfo
} from '../../../core/services/notificationService';
import { FICHAPERSONAL_LABELS } from '../../cmoff/ficha_empleados/FichaEmpleadosUtils';
import { USUARIO_LABELS } from '../usuario/usuarioUtils';
import { REGISTROCORTES_LABELS } from '../RegistroCortes/RegistroCortesUtils';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { getLoggedUser, userLogOut } from '../../../core/auth/auth.service';
import { ADM_REGISTROCORTES } from '../../../shared/constants/routesApp';
import { PERMISOS } from '../../../shared/constants/permisos';
import { MODULOS } from '../../../shared/constants/modulos';
import { Button } from 'reactstrap';

const RegistroCortesCe = props => {
	const [idParam, setIdParam] = useState(null);
	const [inputsUserDisabled, setInputsUserDisabled] = useState(false);
	const [rolesError, setRolesError] = useState(false);
	const usuarioInitialState = {
		usuario: '',
		clave: '',
		verificacionClave: ''
	};
	const [usuario, setUsuario] = useState(usuarioInitialState);
	const empleadoInitialState = {
		codigoEmpleado: ''
	};

	const registroCortesInitialState = {
		numeroCortes: '',
		valorCorte: ''
	};
	const [empleado, setEmpleado] = useState(empleadoInitialState);
	const [empleados, setEmpleados] = useState({});
	const [roles, setRoles] = useState([]);
	const [registroCortes, setregistroCortes] = useState(
		registroCortesInitialState
	);
	const [rolesUsuario, setRolesUsuario] = useState([]);
	const [codEmpleadoBuscar, setCodEmpleadoBuscar] = useState('');
	const userLogged = getLoggedUser();
	//api
	const empleadosApi = {
		edit: '/persona/',
		buscarEmpleado: '/persona/buscarporcodigo/',
		filterSelect: '/persona/filternombres'
	};

	const usuariosApi = {
		edit: '/usuarios/',
		create: '/usuarios/crear',
		update: '/usuarios/actualizar/',
		obtenerUsuarioIdEmpl: '/usuarios/buscarPorCodEmpleado/',
		obtenerrolesusuario: '/usuarios/obtenerrolesusuario/',
		saveUsuarioRoles: '/usuarios/actualizarrolesusuario/'
	};

	const rolesApi = {
		obtenerroles: '/roles/listar'
	};

	const registroCortesApi = {
		edit: '/registrocortes/',
		buscarEmpleado: '/registrocortes/buscarporcodigo/',
		filterSelect: '/registrocortes/filternombres',
		update: '/registrocortes/actualizar/',
		create: '/registrocortes/crear'
	};

	const toolbarButtons = [
		{
			label: 'Volver',
			actions: { onClick: () => props.history.goBack() },
			icon: faArrowLeft
		}
		/*{
			
			
			module: MODULOS.ADM_REGISTROCORTES .codigo,
			permissions: [PERMISOS.escritura, PERMISOS.actualizacion],
			label: 'Guardar',
			form: inputsUserDisabled ? '' : 'usuarioForm',
			icon: faSave,
			
			
		}*/
	];

	const handleSelectNombresChange = event => {
		if (event) {
			const empleadoResponse = empleados.find(
				empleado => empleado.value === event.value
			);
			if (empleadoResponse) {
				empleadoResponse.codigoEmpleado = empleadoResponse.value;
				empleadoResponse.nombreCompuesto = event;

				setEmpleado(empleadoResponse);
				setregistroCortes({
					...registroCortes,
					codigoEmpleado: empleadoResponse.codigoEmpleado
				});

				//obtenerUsuario(event.value, empleadoResponse.email);
			} else {
				/*
				networkService.get(empleadosApi.edit + event.value).then(response => {
					const empleadoResponse2 = response.data;
					empleadoResponse2.nombreCompuesto = event;
					empleadoResponse2.estado = empleadoResponse2.estado
						? ESTADO_ACTIVO
						: ESTADO_INACTIVO;
					setRolesError(false);
					setEmpleado(empleadoResponse2);
					obtenerUsuario(event.value, empleadoResponse2.email);
				
				
				});
			*/
			}
		} else {
			//setEmpleado(empleadoInitialState);
			setUsuario(usuarioInitialState);
			setRolesUsuario([]);
			setInputsUserDisabled(false);
		}
		setCodEmpleadoBuscar('');
	};

	const handleValidUserSubmit = event => {
		event.preventDefault();

		console.log(registroCortes);
		//return false;
	
	
	
	
		if (registroCortes.numeroCortes != '' && registroCortes.valorCorte != '') {
			const usuarioRequest = { ...registroCortes };

			networkService
				.post(registroCortesApi.create, usuarioRequest)
				.then(response => {
					notificationSuccess(response.data).then(r =>
						props.history.push(ADM_REGISTROCORTES)
					);
				});
		} else {
			//console.log(empleado.nuCodEmpleado);

			const params = {};
			params.message = MESSAGE_ERROR.ERROR_CAMPOS;
			notificationError(params);
		}
	};

	const handleSelectNombresFilter = (inputValue, callback) => {
		if (inputValue.length >= 3 || inputValue.length === 0) {
			const paginacion = {
				filter: inputValue
					? {
							nombres: inputValue
					  }
					: {},
				page: 0,
				sizePerPage: 10,
				totalSize: 0,
				column: 'nombres',
				order: 'asc'
			};
			networkService
				.post(empleadosApi.filterSelect, paginacion)
				.then(response => {
					const parsedData = formatSelect(
						'responsables',
						response.data.content
					);
					setEmpleados(response.data.content);
					callback(parsedData);
				});
		}
	};

	const formatSelect = (type, data, value, label) => {
		let dataFormat = [];
		let dataFormateo = data.map(element => {
			if (type === 'responsables') {
				if (element.hasOwnProperty('codigoEmpleado')) {
					element.value = element['codigoEmpleado'];
					delete element['codigoEmpleado'];
				}
				if (
					element.hasOwnProperty('nombres') &&
					element.hasOwnProperty('apellido1')
				) {
					element.label =
						element['nombres'] +
						' ' +
						element['apellido1'] +
						' ' +
						(element['apellido2'] ? element['apellido2'] : '');
				}
			} else if (type === 'roles') {
				if (element.hasOwnProperty(value)) {
					element.value = element[value];
					delete element[value];
				}
				if (element.hasOwnProperty(label)) {
					element.label = element[label];
					delete element[label];
				}
				if (element.hasOwnProperty('estado')) {
					element.isDisabled = !element['estado'];
					delete element['estado'];
				}
			} else if (type === 'rolesUsuario') {
				if (element.hasOwnProperty('rol')) {
					const rol = element['rol'];
					if (rol.hasOwnProperty(value)) {
						element.value = rol[value];
						delete rol[value];
					}
					if (rol.hasOwnProperty(label)) {
						element.label = rol[label];
						delete rol[label];
					}
					if (rol.hasOwnProperty('estado')) {
						element.isDisabled = !rol['estado'];
						delete rol['estado'];
					}
				}
			}
			return element;
		});
		if (type === 'responsables') {
			dataFormateo.forEach(({ value, label }) => {
				if (value && label) {
					dataFormat.push({ value, label });
				}
			});
		} else if (type === 'roles' || type === 'rolesUsuario') {
			dataFormateo.forEach(({ value, label, isDisabled }) =>
				dataFormat.push({ value, label, isDisabled })
			);
		}

		return dataFormat;
	};

	useEffect(() => {
		setIdParam(props.match.params.id);
		if (props.match.params.id) {
			networkService
				.get(usuariosApi.edit + props.match.params.id)
				.then(response => {
					const usuarioResponse = response.data;
					setUsuario(response.data);
					if (usuarioResponse) {
						networkService
							.get(empleadosApi.edit + usuarioResponse.persona)
							.then(response => {
								setEmpleado(response.data);
								obtenerRolesUsuario(props.match.params.id);
							})
							.catch(error => {
								notificationInfo({
									message: MESSAGE_INFO.INFO_PERSONA_NO_EXISTE
								}).then(res => {
									props.history.goBack();
								});
							});
					}
				});
		} else {
			obtenerRoles();
		}
	}, []);

	const handleValidSearch = () => {
		if (codEmpleadoBuscar) {
			networkService
				.get(registroCortesApi.valorCorte + codEmpleadoBuscar)
				.then(response => {
					if (response.data) {
						response.data.numeroCortes = '';
						response.data.valorCorte = '';
						setRolesError(false);
						setEmpleado(response.data);
						//obtenerUsuario(response.data.codigoEmpleado, response.data.num);
					} else {
						notificationError({
							message: MESSAGE_ERROR.ERROR_CODIGO_PERSONA_NOEXISTE
						});
						setEmpleado(empleadoInitialState);
					}
				});
		} else {
			notificationError({
				message: MESSAGE_ERROR.ERROR_CODIGO_EMPLEADO
			});
			setEmpleado(empleadoInitialState);
		}
	};

	const handleInputChange = event => {
		console.log(event.target.value);

		setregistroCortes({
			...registroCortes,
			[event.target.name]: event.target.value
		});
		//empleado.valorCorte=event.target.value;
	};
	const handleInputChangecortes = event => {
		setregistroCortes({
			...registroCortes,
			[event.target.name]: event.target.value
		});
	};

	const obtenerRoles = () => {
		networkService.get(rolesApi.obtenerroles).then(response => {
			const parsedData = formatSelect(
				'roles',
				response.data,
				'id',
				'descripcion'
			);
			setRoles(parsedData);
		});
	};

	const obtenerRolesUsuario = usuarioId => {
		networkService.get(rolesApi.obtenerroles).then(response => {
			const parsedDataRoles = formatSelect(
				'roles',
				response.data,
				'id',
				'descripcion'
			);
			setRoles(parsedDataRoles);
			networkService
				.get(usuariosApi.obtenerrolesusuario + usuarioId)
				.then(response => {
					const parsedDataRolesUsuario = formatSelect(
						'rolesUsuario',
						response.data,
						'id',
						'descripcion'
					);
					setRolesUsuario(parsedDataRolesUsuario);
				});
		});
	};

	

	return (
		<div className='flex-row'>
			<div className='col-12'>
				<ToolBar
					breadcrumbs={
						props.match.params.id
							? REGISTROCORTES_EDIT_BREADCRUMBS
							: REGISTROCORTES_NEW_BREADCRUMBS
					}
					buttons={toolbarButtons}
				/>
				<AvForm
					id='usuarioForm'
					model={empleado}
					onValidSubmit={handleValidUserSubmit}>
					<Button color='primary' onClick={handleValidUserSubmit}>
						<FontAwesomeIcon icon={faSave} /> Guardar
					</Button>

					<div className='row pt-1'>
						<div className='border-bottom col-12 col-sm-12 col-md-12 col-lg-12'>
							<fieldset className=' col '>
								<legend className='h5 pt-2'>
									<FontAwesomeIcon icon={faSearch} className='text-secondary' />
									{' ' + USUARIO_LABELS.groupBusquedaEmpleado}
								</legend>
								<br />

								<div className='form-group row'>
									<div className='col-1'></div>
									<label
										htmlFor='example-text-input'
										className='col-2 col-form-label'>
										{FICHAPERSONAL_LABELS.NOMBRES}
									</label>
									<FormGroup className='col-3'>
										<CustomAsyncSelect
											name='codigoEmpleado'
											value={empleado.nombreCompuesto}
											onChange={handleSelectNombresChange}
											loadOptions={handleSelectNombresFilter}
										/>
									</FormGroup>
								</div>
							</fieldset>
						</div>
					</div>

					<div className='row pt-1'>
						<div className='border-right col-12 col-sm-12 col-md-12 col-lg-4'>
							<fieldset className=' col '>
								<legend className='h5 pt-2'>
									<FontAwesomeIcon
										icon={faIdCardAlt}
										className='text-secondary'
									/>
									{' ' + REGISTROCORTES_LABELS.REGISTRO}
								</legend>
								<br />

								<FormGroup className='row'>
									<label
										htmlFor='example-text-input'
										className='col-5 col-form-label'>
										{REGISTROCORTES_LABELS.NUMEROCORTES}
										{props.match.params.id ? (
											''
										) : (
											<span
												style={{ color: 'red' }}
												title='Este campo es obligatorio.'>
												*
											</span>
										)}
									</label>
									<div className='col-7'>
										<FormGroup>
											<AvField
												name='numeroCortes'
												type='text'
												value={empleado.numeroCortes}
												model={empleado.numeroCortes}
												onChange={handleInputChange}
											/>
										</FormGroup>
									</div>
								</FormGroup>
								<FormGroup className='row'>
									<label
										htmlFor='example-text-input'
										className='col-5 col-form-label'>
										{REGISTROCORTES_LABELS.VALORCORTE}
										{props.match.params.id ? (
											''
										) : (
											<span
												style={{ color: 'red' }}
												title='Este campo es obligatorio.'>
												*
											</span>
										)}
									</label>
									<div className='col-7'>
										<FormGroup>
											<AvField
												name='valorCorte'
												type='text'
												value={empleado.valorCorte}
												model={empleado.valorCorte}
												onChange={handleInputChangecortes}
											/>
										</FormGroup>
									</div>
								</FormGroup>
							</fieldset>
						</div>
					</div>
				</AvForm>
			</div>
		</div>
	);
};
export default withRouter(RegistroCortesCe);
