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
import { USUARIO_LABELS } from './usuarioUtils';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { getLoggedUser, userLogOut } from '../../../core/auth/auth.service';
import { ADM_USUARIOS } from '../../../shared/constants/routesApp';
import { PERMISOS } from '../../../shared/constants/permisos';
import { MODULOS } from '../../../shared/constants/modulos';

const UsuarioCE = props => {
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
		identificacion: '',
		tipoDoc: '',
		codEmpleado: '',
		nombres: '',
		apellido1: '',
		apellido2: '',
		nombreCompuesto: '',
		email: '',
		estado: ''
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
		filterSelect: '/persona/filternombres'
	};

	const usuariosApi = {
		edit: '/usuarios/',
		create: '/usuarios/crear',
		update: '/usuarios/actualizar/',
		obtenerUsuarioIdEmpl: '/usuarios/buscarPorCodEmpleado/',
		obtenerrolesusuario: '/usuarios/obtenerrolesusuario/',
		saveUsuarioRoles: '/usuarios/actualizarrolesusuario/',
		delete: '/usuarios/delete/'
	};

	const rolesApi = {
		obtenerroles: '/roles/listar'
	};

	const toolbarButtons = [
		{
			label: 'Volver',
			actions: { onClick: () => props.history.goBack() },
			icon: faArrowLeft
		},
		{
			module: MODULOS.ADM_USUARIOS.codigo,
			permissions: [PERMISOS.escritura, PERMISOS.actualizacion],
			label: 'Guardar',
			form: inputsUserDisabled ? '' : 'usuarioForm',
			icon: faSave,
			actions: {
				disabled: inputsUserDisabled
			}
		}



		// {
		// 	module: MODULOS.ADM_REGISTROCORTES.codigo,
		// 	permissions: [PERMISOS .escritura, PERMISOS.actualizacion],
		// 	label: 'Borrar Registro',
		// 	form: inputsUserDisabled ? '' : 'usuarioForm',
		// 	icon:DataView,
		// 	actions: {
		// 		onClick: () => deleteBook(props.match.params.id)
		// 	}
		// }
		


	];

	const handleCodEmpleadoBuscarChange = event => {
		const target = event.target;
		const value = target.value;
		setCodEmpleadoBuscar(value);
	};

	const handleUsuarioChange = event => {
		const target = event.target;
		const value = target.value;
		const name = target.name;
		setUsuario({ ...usuario, [name]: value });
	};

	const handleSelectNombresChange = event => {
		if (event) {
			const empleadoResponse = empleados.find(
				empleado => empleado.value === event.value
			);
			if (empleadoResponse) {
				empleadoResponse.codigoEmpleado = empleadoResponse.value;
				empleadoResponse.nombreCompuesto = event;
				empleadoResponse.estado = empleadoResponse.estado
					? ESTADO_ACTIVO
					: ESTADO_INACTIVO;
				setRolesError(false);
				setEmpleado(empleadoResponse);
				obtenerUsuario(event.value, empleadoResponse.email);
			} else {
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
			}
		} else {
			setEmpleado(empleadoInitialState);
			setUsuario(usuarioInitialState);
			setRolesUsuario([]);
			setInputsUserDisabled(false);
		}
		setCodEmpleadoBuscar('');
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
					element.hasOwnProperty('apellido')
				) {
					element.label =
						element['nombres'] +
						' ' +
						element['apellido'] +
						' ' +
						(element['apellido'] ? element['apellido'] : '');
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
				.get(empleadosApi.buscarEmpleado + codEmpleadoBuscar)
				.then(response => {
					if (response.data) {
						response.data.nombreCompuesto = '';
						setRolesError(false);
						setEmpleado(response.data);
						obtenerUsuario(response.data.codigoEmpleado, response.data.email);
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

	const handleValidUserSubmit = () => {
		if (idParam && !usuario.clave && !usuario.verificacionClave) {
			saveUsuarioRoles(idParam);
		} else {
			if (!empleado || !empleado.codigoEmpleado) {
				notificationError({
					message: MESSAGE_ERROR.ERROR_NORELACION_EMPLEADO
				});
			} else if (
				(!usuario.clave && !usuario.verificacionClave) ||
				!usuario.clave.trim()
			) {
				notificationError({
					message: MESSAGE_ERROR.ERROR_NO_CONTRASEÑA
				});
			} else if (usuario.clave !== usuario.verificacionClave) {
				notificationError({
					message: MESSAGE_ERROR.ERROR_CONTRASEÑAS
				});
			} else {
				const usuarioRequest = { ...usuario };
				usuarioRequest.persona = empleado.codigoEmpleado;
				usuarioRequest.clave = usuario.clave;
				delete usuarioRequest['verificacionClave'];

				if (rolesUsuario && rolesUsuario.length > 0) {
					const rolesUsuarioRequest = [];
					rolesUsuario.map(rolUsuario => {
						rolesUsuarioRequest.push(rolUsuario.value);
					});
					usuarioRequest.roles = rolesUsuarioRequest;
					setRolesError(false);
					if (idParam) {
						networkService
							.put(usuariosApi.update + usuarioRequest.id, usuarioRequest)
							.then(response => {
								if (userLogged && userLogged.id === usuarioRequest.id) {
									Swal.fire({
										icon: 'info',
										title: 'Contraseña actualizada',
										text: 'Debe volver a iniciar sesión.'
									}).then(r => window.location.reload(), userLogOut());
								} else {
									notificationSuccess(response.data).then(r =>
										props.history.push(ADM_USUARIOS)
									);
								}
							});
					} else {
						networkService
							.post(usuariosApi.create, usuarioRequest)
							.then(response => {
								notificationSuccess(response.data).then(r =>
									props.history.push(ADM_USUARIOS)
								);
							});
					}
				} else {
					setRolesError(true);
				}
			}
		}
	};

	const onUserSubmit = () => {
		rolesUsuario
			? rolesUsuario.length === 0
				? setRolesError(true)
				: setRolesError(false)
			: setRolesError(true);
	};

	const saveUsuarioRoles = usuarioId => {
		if (rolesUsuario && rolesUsuario.length > 0) {
			const rolesUsuarioRequest = rolesUsuario.map(rolUsuario => {
				return { idUsuario: usuarioId, rol: { id: rolUsuario.value } };
			});
			setRolesError(false);
			networkService
				.put(usuariosApi.saveUsuarioRoles + usuarioId, rolesUsuarioRequest)
				.then(response => {
					if (userLogged && userLogged.id == usuarioId) {
						Swal.fire({
							icon: 'info',
							title: 'Roles asignados',
							text: 'Debe volver a iniciar sesión.'
						}).then(r => window.location.reload(), userLogOut());
					} else {
						notificationSuccess(response.data).then(r =>
							props.history.push(ADM_USUARIOS)
						);
					}
				});
		} else {
			setRolesError(true);
		}
	};

	const obtenerUsuario = (empleadoId, email) => {
		networkService
			.get(usuariosApi.obtenerUsuarioIdEmpl + empleadoId)
			.then(response => {
				if (response.data) {
					notificationInfo({
						message: MESSAGE_INFO.INFO_USUARIO_YAEXITE
					}).then(() => {
						setUsuario(response.data);
						obtenerRolesUsuario(response.data.id);
						setInputsUserDisabled(true);
					});
				} else {
					const usuario = email.split('@')[0].toLowerCase();
					setUsuario({ ...usuarioInitialState, usuario: usuario });
					setRolesUsuario([]);
					setInputsUserDisabled(false);
				}
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

	const handleSelectRolesUsuarioChange = event => {
		setRolesUsuario(event);
	};





		// // delete
		// const deleteBook = IdRegistroCorte =>{
		// 	if(window.confirm("¿Realmente desea eliminar el registro?")) {
		// 	networkService
		// 		.delete(usuariosApi.delete + IdRegistroCorte )
		// 		.then(response => {
		// 			notificationSuccess(response.data).then(r =>
		// 				props.history.push(ADM_USUARIOS)
					
		// 			);
		// 		});
	
	
		// 	}
		// }






	return (
		<div className='flex-row'>
			<div className='col-12'>
				<ToolBar
					breadcrumbs={
						props.match.params.id
							? USUARIOS_EDIT_BREADCRUMBS
							: USUARIOS_NEW_BREADCRUMBS
					}
					buttons={toolbarButtons}
				/>
				{idParam ? (
					''
				) : (
					<AvForm onValidSubmit={handleValidSearch}>
						<div className='row pt-1'>
							<div className='border-bottom col-12 col-sm-12 col-md-12 col-lg-12'>
								<fieldset className=' col '>
									<legend className='h5 pt-2'>
										<FontAwesomeIcon
											icon={faSearch}
											className='text-secondary'
										/>
										{' ' + USUARIO_LABELS.groupBusquedaEmpleado}
									</legend>
									<br />

									<div className='form-group row'>
										<label
											htmlFor='example-text-input'
											className='col-2 col-form-label'>
											{FICHAPERSONAL_LABELS.COD_EMPLEADO}
										</label>
										<FormGroup className='col-3'>
											<AvField
												name='codigoEmpleado'
												type='number'
												errorMessage='Código Inválido'
												validate={{
													pattern: { value: '^[0-9]+$' },
													minLength: { value: 1 },
													maxLength: { value: 15 }
												}}
												value={codEmpleadoBuscar || ''}
												onChange={handleCodEmpleadoBuscarChange}
												autoComplete='codigoEmpleado'
											/>
										</FormGroup>
										<FormGroup>
											<button
												type='submit'
												className='btn btn-primary border border-secondary'>
												<FontAwesomeIcon
													icon={faSearch}
													className='card-img-top'
												/>
											</button>
										</FormGroup>
										<div className='col-1'></div>
										<label
											htmlFor='example-text-input'
											className='col-2 col-form-label'>
											{FICHAPERSONAL_LABELS.NOMBRES}
										</label>
										<FormGroup className='col-3'>
											<CustomAsyncSelect
												name='nombreCompuesto'
												value={empleado.nombreCompuesto}
												onChange={handleSelectNombresChange}
												loadOptions={handleSelectNombresFilter}
											/>
										</FormGroup>
									</div>
								</fieldset>
							</div>
						</div>
					</AvForm>
				)}
				<AvForm
					id='usuarioForm'
					onSubmit={onUserSubmit}
					onValidSubmit={handleValidUserSubmit}>
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
									</label>
									<div className='col-7'>
										<input
											readOnly={true}
											className='form-control'
											type='text'
											value={empleado.nombres || ''}
										/>
									</div>
								</FormGroup>
								<FormGroup className='row'>
									<label
										htmlFor='example-text-input'
										className='col-5 col-form-label'>
										{FICHAPERSONAL_LABELS.APELLIDOS}
									</label>
									<div className='col-7'>
										<input
											readOnly={true}
											className='form-control'
											type='text'
											value={
												empleado.apellido +
												' ' +
												(empleado.apellido2 ? empleado.apellido2 : '')
											}
										/>
									</div>
								</FormGroup>
								<FormGroup className='row'>
									<label
										htmlFor='example-text-input'
										className='col-5 col-form-label'>
										{FICHAPERSONAL_LABELS.DIRECCION}
									</label>
									<div className='col-7'>
										<input
											readOnly={true}
											className='form-control'
											type='text'
											value={empleado.direccion || ''}
										/>
									</div>
								</FormGroup>
								<FormGroup className='row'>
									<label
										htmlFor='example-text-input'
										className='col-5 col-form-label'>
										{FICHAPERSONAL_LABELS.COMPANIA}
									</label>
									<div className='col-7'>
										<input
											readOnly={true}
											className='form-control'
											type='text'
											value={empleado.celular || ''}
										/>
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
									{' ' + USUARIO_LABELS.groupInformacionCuenta}
								</legend>
								<br />
								<FormGroup className='row'>
									<label
										htmlFor='example-text-input'
										className='col-5 col-form-label'>
										{FICHAPERSONAL_LABELS.COD_EMPLEADO}
									</label>
									<div className='col-7'>
										<input
											readOnly={true}
											className='form-control'
											type='text'
											value={empleado.codigoEmpleado || ''}
										/>
									</div>
								</FormGroup>
								<FormGroup className='row'>
									<label
										htmlFor='example-text-input'
										className='col-5 col-form-label'>
										{USUARIO_LABELS.usuario}
									</label>
									<div className='col-7'>
										<AvField
											name='usuario'
											type='text'
										
											errorMessage={VALIDATIONS_LABELS.USER_INVALID}
											value={usuario.usuario || ''}
											autoComplete='usuario'
											onChange={handleUsuarioChange}
										/>
									</div>
								</FormGroup>
								<FormGroup className='row'>
									<label
										htmlFor='example-text-input'
										className='col-5 col-form-label'>
										{USUARIO_LABELS.asignarPassword}
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
										<AvField
											name='clave'
											type='password'
											readOnly={inputsUserDisabled}
											errorMessage={VALIDATIONS_LABELS.PASSWORD_INVALID}
											validate={{
												minLength: {
													value: 4
												},
												maxLength: {
													value: 16
												}
											}}
											value={usuario.clave || ''}
											onChange={handleUsuarioChange}
											autoComplete='clave'
										/>
									</div>
								</FormGroup>
								<FormGroup className='row'>
									<label
										htmlFor='example-text-input'
										className='col-5 col-form-label'>
										{USUARIO_LABELS.repetirPassword}
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
										<AvField
											name='verificacionClave'
											type='password'
											readOnly={inputsUserDisabled}
											errorMessage={VALIDATIONS_LABELS.PASSWORD_INVALID}
											validate={{
												minLength: {
													value: 4
												},
												maxLength: {
													value: 16
												}
											}}
											value={usuario.verificacionClave || ''}
											onChange={handleUsuarioChange}
											autoComplete='verificacionClave'
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
										<input
											readOnly={true}
											className='form-control'
											type='text'
											value={empleado.email || ''}
										/>
									</div>
								</FormGroup>
							</fieldset>
						</div>
						<div className='col-12 col-sm-12 col-md-12 col-lg-4'>
							<fieldset className=' col '>
								<legend className='h5 pt-2'>
									<FontAwesomeIcon
										icon={faUserCircle}
										className='text-secondary'
									/>
									{' ' + USUARIO_LABELS.groupConfiguracionPerfil}
									<span
										style={{ color: 'red' }}
										title='Este campo es obligatorio.'>
										*
									</span>
								</legend>
								<br />
								<CustomSelect
									name='rolesUsuario'
									isMulti
									options={roles}
									value={rolesUsuario}
									isDisabled={inputsUserDisabled}
									onChange={handleSelectRolesUsuarioChange}
									error={rolesError}
								/>
							</fieldset>
						</div>
					</div>
				</AvForm>
			</div>
		</div>
	);
};
export default withRouter(UsuarioCE);
