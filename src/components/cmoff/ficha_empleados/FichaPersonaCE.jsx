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
	USUARIOS_NEW_BREADCRUMBS,
	VALIDATIONS_LABELS,
	USUARIOS_EDIT_BREADCRUMBS,
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
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { getLoggedUser, userLogOut } from '../../../core/auth/auth.service';
import { ITI_FICHA_EMPLEADOS } from '../../../shared/constants/routesApp';
import { PERMISOS } from '../../../shared/constants/permisos';
import { MODULOS } from '../../../shared/constants/modulos';
import { FICHA_EMPLEADOS_BREADCRUMBS } from '../../../shared/constants/client';
import { Button } from 'reactstrap';
const FichaPersonaCE = props => {
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
		identificacion: String,
		tipoDoc: '',
		codigoEmpleado: '',
		nombres: '',
		apellido: '',
		email: '',
		direccion: '',
		celular: ''
	
	};
	const [empleado, setEmpleado] = useState(empleadoInitialState);
	const [empleados, setEmpleados] = useState({});
	const [roles, setRoles] = useState([]);
	const [rolesUsuario, setRolesUsuario] = useState([]);
	const [codEmpleadoBuscar, setCodEmpleadoBuscar] = useState('');
	const userLogged = getLoggedUser();
	//api
	const empleadosApi = {
		edit: '/persona/',
		buscarEmpleado: '/persona/buscarporcodigo/',
		filterSelect: '/persona/filternombres',
		create: '/persona/crear'
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

	const toolbarButtons = [
		{
			label: 'Volver',
			actions: { onClick: () => props.history.goBack() },
			icon: faArrowLeft
		}
	];

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
					element.hasOwnProperty('apellido')
				) {
					element.label =
						element['nombres'] +
						' ' +
						element['apellido'] 
						
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

	const handleInputChange = event => {
		// console.log(event.target.name)
		// console.log(event.target.value)
		setEmpleado({
			...empleado,
			[event.target.name]: event.target.value
		});
	};

	const handleValidUserSubmit = event => {
		event.preventDefault();
		if (
			empleado.nombres != '' &&
			empleado.empresa != '' &&
			empleado.apellido1 != '' &&
			empleado.codigoEmpleado != '' &&
			empleado.email != ''
		) {
			const usuarioRequest = { ...empleado };
			networkService
				.post(empleadosApi.create, usuarioRequest)
				.then(response => {
					notificationSuccess(response.data).then(r =>
						props.history.push(ITI_FICHA_EMPLEADOS)
					);
				});
		} else {
			console.log(empleado.empresa);

			const params = {};
			params.message = MESSAGE_ERROR.ERROR_CAMPOS;
			notificationError(params);
		}
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
					breadcrumbs={FICHA_EMPLEADOS_BREADCRUMBS}
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
						<div className='border-right col-12 col-sm-12 col-md-12 col-lg-4'>
							<fieldset className=' col '>
								<legend className='h5 pt-2'>
									<FontAwesomeIcon
										icon={faIdCardAlt}
										className='text-secondary'
									/>

									{' ' + FICHAPERSONAL_LABELS.INFORMACION_PERSONA}
								</legend>
								<br />
								<FormGroup className='row'>
									<label
										htmlFor='example-text-input'
										className='col-5 col-form-label'>
										{FICHAPERSONAL_LABELS.NOMBRES}
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
												name='nombres'
												type='text'
												value={empleado.nombres}
												model={empleado.nombres}
												onChange={handleInputChange}
											/>
										</FormGroup>
									</div>
								</FormGroup>
								<FormGroup className='row'>
									<label
										htmlFor='example-text-input'
										className='col-5 col-form-label'>
										{FICHAPERSONAL_LABELS.APELLIDOS}
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
												name='apellido'
												type='text'
												value={empleado.apellido}
												model={empleado.apellido}
												onChange={handleInputChange}
											/>
										</FormGroup>
									</div>
								</FormGroup>
								<FormGroup className='row'>
									<label
										htmlFor='example-text-input'
										className='col-5 col-form-label'>
										{FICHAPERSONAL_LABELS.DIRECCION}
									</label>
									<div className='col-7'>
										<FormGroup>
											<AvField
												name='direccion'
												type='text'
												value={empleado.direccion}
												model={empleado.direccion}
												onChange={handleInputChange}
											/>
										</FormGroup>
									</div>
								</FormGroup>
						
							</fieldset>
						</div>
						<div className='border-right col-12 col-sm-12 col-md-12 col-lg-4'>
							<fieldset className=' col '>
								<legend className='h5 pt-2'>
									<FontAwesomeIcon
										icon={faUserCircle}
										className='text-secondary'
									/>
								</legend>
								<br />
								<FormGroup className='row'>
									<label
										htmlFor='example-text-input'
										className='col-5 col-form-label'>
										{FICHAPERSONAL_LABELS.COD_EMPLEADO}
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
												name='codigoEmpleado'
												type='number'
												errorMessage='Código Inválido'
												validate={{
													pattern: { value: '^[0-9]+$' },
													minLength: { value: 1 },
													maxLength: { value: 15 }
												}}
												value={empleado.codigoEmpleado}
												model={empleado.codigoEmpleado}
												onChange={handleInputChange}
											/>
										</FormGroup>
									</div>
								</FormGroup>
								<FormGroup className='row'>
									<label
										htmlFor='example-text-input'
										className='col-5 col-form-label'>
										{FICHAPERSONAL_LABELS.COMPANIA}
									</label>
									<div className='col-7'>
										<AvField
											name='celular'
											type='text'
											value={empleado.celular}
											model={empleado.celular}
											onChange={handleInputChange}
										/>
									</div>
								</FormGroup>
								<FormGroup className='row'>
									<label
										htmlFor='example-text-input'
										className='col-5 col-form-label'>
										{FICHAPERSONAL_LABELS.EMAIL}
									</label>
									<div className='col-7'>
										<AvField
											name='email'
											type='text'
											value={empleado.email}
											onChange={handleInputChange}
											validate={{
											pattern: {value: '/^w+([.-_+]?w+)*@w+([.-]?w+)*(.w{2,10})+$/ '}
											}}
										/>
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
export default withRouter(FichaPersonaCE);
