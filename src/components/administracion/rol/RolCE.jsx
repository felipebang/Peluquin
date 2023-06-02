import {
	faArrowLeft,
	faKey,
	faInfoCircle,
	faSave
} from '@fortawesome/free-solid-svg-icons';
import React, { useEffect, useState } from 'react';
import { withRouter } from 'react-router-dom';
import ToolBar from '../../../shared/toolbar/ToolBar';
import {
	ROLES_EDIT_BREADCRUMBS,
	ROLES_NEW_BREADCRUMBS,
	ESTADO_ACTIVO,
	ESTADO_INACTIVO,
	VALIDATIONS_LABELS,
	ROL_ADM_CODIGO,
	MESSAGE_ERROR
} from '../../../shared/constants/client';
import networkService from '../../../core/services/networkService';
import { ROL_LABELS } from './RolUtils';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { ADM_ROLES, DASHBOARD } from '../../../shared/constants/routesApp';
import {
	notificationSuccess,
	notificationError
} from '../../../core/services/notificationService';
import CustomSelect from '../../../shared/selects/CustomSelect';
import { AvField, AvForm } from 'availity-reactstrap-validation';
import 'react-checkbox-tree/lib/react-checkbox-tree.css';
import CustomCheckTree from '../../../shared/checkboxtree/CustomCheckTree';
import { MODULOS } from '../../../shared/constants/modulos';
import { PERMISOS } from '../../../shared/constants/permisos';

const RolCE = props => {
	const [modulosPermisos, setModulosPermisos] = useState([]);
	const [checked, setChecked] = useState([]);
	const [expanded, setExpanded] = useState([]);
	const [rol, setRol] = useState({
		codigo: '',
		descripcion: '',
		estado: '',
		modulosDtoList: {}
	});
	const [estados, setEstados] = useState([]);
	const [estadoError, setEstadoError] = useState(false);
	const states = [
		{ value: true, label: ESTADO_ACTIVO },
		{ value: false, label: ESTADO_INACTIVO }
	];

	//api
	const rolesApi = {
		edit: '/roles/',
		update: '/roles/actualizar/',
		create: '/roles/crear',
		modulosActivos: '/roles/listarModulosActivos',
		permisos: '/roles/permisosListar'
	};

	const formatCheckTree = (
		modulos,
		valueMod,
		labelMod,
		padre,
		permisos,
		valuePer,
		labelPer
	) => {
		let dataFormat = [];
		modulos.map((modulo, index, array) => {
			if (modulo.hasOwnProperty(padre) && modulo[padre]) {
				if (
					modulo.hasOwnProperty(valueMod) &&
					modulo.hasOwnProperty(labelMod)
				) {
					const subModulo = {};
					const permisosToReturn = [];
					/*Se busca el modulo padre para agregar su hijo*/
					const moduloPadre = array.find(mod => mod.id === modulo[padre].id);
					subModulo.value = modulo[padre].id + '_' + modulo[valueMod];
					subModulo.label = modulo[labelMod];
					permisos.map(permiso => {
						const permisoToReturn = {};
						if (permiso.hasOwnProperty(valuePer)) {
							permisoToReturn.value =
								modulo[padre].id +
								'_' +
								modulo[valueMod] +
								'_' +
								permiso[valuePer];
						}
						if (permiso.hasOwnProperty(labelPer)) {
							permisoToReturn.label = permiso[labelPer];
						}
						permisosToReturn.push(permisoToReturn);
					});
					/*Se agregan los permisos al submodulo*/
					subModulo.children = permisosToReturn;
					if (moduloPadre.hasOwnProperty('children')) {
						moduloPadre.children.push(subModulo);
					} else {
						moduloPadre.children = [];
						moduloPadre.children.push(subModulo);
					}
					/*Se retorna toda la estructura creada del Padre*/
					const idx = dataFormat.findIndex(mod => mod.value === moduloPadre.id);
					const moduloToReturn = {};
					moduloToReturn.value = moduloPadre[valueMod];
					moduloToReturn.label = moduloPadre[labelMod];
					moduloToReturn.children = moduloPadre.children;
					dataFormat[idx] = moduloToReturn;
				}
			} else {
				const moduloToReturn = {};
				if (
					modulo.hasOwnProperty(valueMod) &&
					modulo.hasOwnProperty(labelMod)
				) {
					const permisosToReturn = [];
					moduloToReturn.value = modulo[valueMod];
					moduloToReturn.label = modulo[labelMod];
					permisos.map(permiso => {
						const permisoToReturn = {};
						if (permiso.hasOwnProperty(valuePer)) {
							permisoToReturn.value =
								modulo[valueMod] + '_' + permiso[valuePer];
						}
						if (permiso.hasOwnProperty(labelPer)) {
							permisoToReturn.label = permiso[labelPer];
						}
						permisosToReturn.push(permisoToReturn);
					});
					moduloToReturn.children = permisosToReturn;
				}
				dataFormat.push(moduloToReturn);
			}
		});
		return dataFormat;
	};

	useEffect(() => {
		networkService.get(rolesApi.modulosActivos).then(response => {
			const modulos = response.data;
			networkService.get(rolesApi.permisos).then(response2 => {
				const permisos = response2.data;
				const modulosPermisos = formatCheckTree(
					modulos,
					'id',
					'nombre',
					'moduloEnlazado',
					permisos,
					'id',
					'descripcion'
				);
				setModulosPermisos(modulosPermisos);
			});
		});

		setEstados(states);
		if (props.match.params.id) {
			networkService
				.get(rolesApi.edit + props.match.params.id)
				.then(response => {
					const rolResponse = response.data;
					if (rolResponse.codigo !== ROL_ADM_CODIGO) {
						if (rolResponse.estado)
							rolResponse.estado = { value: true, label: ESTADO_ACTIVO };
						else rolResponse.estado = { value: false, label: ESTADO_INACTIVO };
						const checks = [];
						rolResponse.modulosDtoList.map(modulo => {
							if (
								modulo.hasOwnProperty('permisoDTOList') &&
								modulo.permisoDTOList.length > 0
							) {
								if (
									modulo.hasOwnProperty('moduloEnlazado') &&
									modulo.moduloEnlazado
								) {
									modulo.permisoDTOList.map(permiso => {
										const check =
											modulo.moduloEnlazado.id +
											'_' +
											modulo.id +
											'_' +
											permiso.id;
										checks.push(check);
									});
								} else {
									modulo.permisoDTOList.map(permiso => {
										const check = modulo.id + '_' + permiso.id;
										checks.push(check);
									});
								}
							}
						});
						setChecked(checks);
						setRol(rolResponse);
					} else {
						notificationError({
							message: MESSAGE_ERROR.ERROR_NO_AUTORIZADO
						}).then(r => (window.location.href = DASHBOARD));
					}
				});
		} else {
			setRol({ rol });
		}
	}, []);

	const handleChange = event => {
		const target = event.target;
		let value = target.value;
		const name = target.name;
		setRol({ ...rol, [name]: value });
	};

	const handleSelectEstadoChange = event => {
		setRol({ ...rol, estado: event });
	};

	const handleValidSubmit = () => {
		if (!rol.estado || checked.length === 0) {
			validateFields();
		} else {
			const rolRequest = {};
			rolRequest.id = rol.id;
			rolRequest.codigo = rol.codigo.toUpperCase().trim();
			rolRequest.descripcion = rol.descripcion;
			rolRequest.estado = rol.estado.value;
			let currentModulo, currentSubModulo;
			let prevModuloId, prevSubModuloId, prevLength;
			const modulosDtoList = [];
			checked.map((check, index) => {
				const moduloPermiso = check.split('_');
				const moduloId = moduloPermiso[0];
				if (index === 0) prevLength = moduloPermiso.length;
				if (moduloPermiso.length === 2 || moduloPermiso.length === 3) {
					const permisoId =
						moduloPermiso.length === 2 ? moduloPermiso[1] : moduloPermiso[2];
					if (moduloId !== prevModuloId) {
						if (index !== 0) modulosDtoList.push(currentModulo);
						currentModulo = {};
						currentModulo.id = moduloId;
						currentModulo.permisoDTOList = [];
						currentModulo.permisoDTOList.push({ id: permisoId });
					} else if (
						!currentModulo.permisoDTOList.find(
							permiso => permiso.id === permisoId
						)
					) {
						currentModulo.permisoDTOList.push({ id: permisoId });
					}
					if (moduloPermiso.length === 3) {
						const subModuloId = moduloPermiso[1];
						if (subModuloId !== prevSubModuloId) {
							if (index !== 0) modulosDtoList.push(currentSubModulo);
							currentSubModulo = {};
							currentSubModulo.id = subModuloId;
							currentSubModulo.permisoDTOList = [];
							currentSubModulo.permisoDTOList.push({ id: permisoId });
						} else {
							currentSubModulo.permisoDTOList.push({ id: permisoId });
						}
						if (index === checked.length - 1) {
							modulosDtoList.push(currentSubModulo);
						}
						prevSubModuloId = subModuloId;
					}
					if (index === checked.length - 1) modulosDtoList.push(currentModulo);
					if (prevLength !== moduloPermiso.length) {
						modulosDtoList.push(currentSubModulo);
					}
				}
				prevModuloId = moduloId;
				prevLength = moduloPermiso.length;
			});
			rolRequest.modulosDtoList = modulosDtoList;
			if (rol.id) {
				networkService
					.put(rolesApi.update + rol.id, rolRequest)
					.then(response => {
						notificationSuccess(response.data).then(r =>
							props.history.push(ADM_ROLES)
						);
					});
			} else {
				networkService.post(rolesApi.create, rolRequest).then(response => {
					notificationSuccess(response.data).then(r =>
						props.history.push(ADM_ROLES)
					);
				});
			}
		}
	};

	const validateFields = () => {
		!rol.estado ? setEstadoError(true) : setEstadoError(false);
		if (checked.length === 0) {
			notificationError({
				message: MESSAGE_ERROR.ERROR_ROL_MODULOS
			});
		}
	};

	//Botones para el toolbar
	const toolbarButtons = [
		{
			label: 'Volver',
			actions: { onClick: () => props.history.goBack() },
			icon: faArrowLeft
		},
		{
			module: MODULOS.ADM_ROLES.codigo,
			permissions: [PERMISOS.escritura, PERMISOS.actualizacion],
			label: 'Guardar',
			form: 'rolForm',
			icon: faSave
		}
	];

	const onCheck = checkedItems => {
		const filtervalores = checkedItems.filter(filter => {
			let el = filter.split('_').length - 1;
			let n = filter.split('_')[el];
			if (n !== 1) {
				return filter;
			} else if (checkedItems.length === 0) {
				return checkedItems;
			}
		});
		const newValues = filtervalores.map(element => {
			let ind = element.split('_').length - 1;
			let value = element.split('_');
			value[ind] = '1';
			return value.join('_');
		});
		let values = filtervalores.concat(newValues); // Convierte los dos array en uno solo
		let unique = [...new Set(values)]; // Devuelve el array sin valores duplicados
		const sortValue = unique.sort((a, b) => {
			return a.localeCompare(b);
		});
		setChecked(sortValue);
	};

	const onExpand = expandedItems => {
		setExpanded(expandedItems);
	};

	return (
		<div className='flex-row'>
			<div className='col-12'>
				<ToolBar
					breadcrumbs={
						props.match.params.id
							? ROLES_EDIT_BREADCRUMBS
							: ROLES_NEW_BREADCRUMBS
					}
					buttons={toolbarButtons}
				/>
				<br />
				<AvForm
					id='rolForm'
					onValidSubmit={handleValidSubmit}
					onSubmit={validateFields}>
					<div className='row pt-1'>
						<div className='border-right col-12 col-sm-12 col-md-6 col-lg-6'>
							<fieldset className=' col '>
								<legend className='h5 pt-2'>
									<FontAwesomeIcon icon={faInfoCircle} />
									{' ' + ROL_LABELS.informacionRol}
								</legend>
								<br />
								<div className='form-group row'>
									<label
										htmlFor='example-text-input'
										className='col-4 col-form-label'>
										{ROL_LABELS.codigo}
										<span
											style={{ color: 'red' }}
											title='Este campo es obligatorio.'>
											*
										</span>
									</label>
									<div className='col-8'>
										<AvField
											name='codigo'
											type='text'
											errorMessage={
												ROL_LABELS.codigo + VALIDATIONS_LABELS.FIELD_INVALID
											}
											validate={{
												required: { value: true },
												pattern: {
													value: "^[a-zA-Z0-9-_']+$"
												},
												minLength: { value: 1 },
												maxLength: { value: 15 }
											}}
											value={rol.codigo || ''}
											onChange={handleChange}
											autoComplete='codigo'
										/>
									</div>
								</div>
								<div className='form-group row'>
									<label
										htmlFor='example-text-input'
										className='col-4 col-form-label'>
										{ROL_LABELS.descripcion}
										<span
											style={{ color: 'red' }}
											title='Este campo es obligatorio.'>
											*
										</span>
									</label>
									<div className='col-8'>
										<AvField
											name='descripcion'
											type='text'
											errorMessage={
												ROL_LABELS.descripcion +
												VALIDATIONS_LABELS.FIELD_INVALID
											}
											validate={{
												required: { value: true },
												pattern: {
													value:
														"^[a-zA-ZÀ-ÿ\u00f1\u00d1']+(s*[a-zA-ZÀ-ÿ\u00f1\u00d1']* )*[a-zA-ZÀ-ÿ\u00f1\u00d1']+$"
												},
												minLength: { value: 1 },
												maxLength: { value: 100 }
											}}
											value={rol.descripcion || ''}
											onChange={handleChange}
											autoComplete='descripcion'
										/>
									</div>
								</div>
								<div className='form-group row'>
									<label
										htmlFor='example-text-input'
										className='col-4 col-form-label'>
										{ROL_LABELS.estado}
										<span
											style={{ color: 'red' }}
											title='Este campo es obligatorio.'>
											*
										</span>
									</label>
									<div className='col-8'>
										<CustomSelect
											name='estado'
											options={estados}
											value={rol.estado}
											onChange={handleSelectEstadoChange}
											error={estadoError}
										/>
									</div>
								</div>
							</fieldset>
						</div>
						<div className='col-12 col-sm-12 col-md-6 col-lg-6'>
							<legend className='h5 pt-2'>
								<FontAwesomeIcon icon={faKey} />
								{' ' + ROL_LABELS.modulosAsociados}
								<span
									style={{ color: 'red' }}
									title='Este campo es obligatorio.'>
									*
								</span>
							</legend>
							<br />
							<div className='card'>
								<CustomCheckTree
									checked={checked}
									expanded={expanded}
									nodes={modulosPermisos}
									showExpandAll={true}
									disabled={false}
									onCheck={onCheck}
									onExpand={onExpand}
								/>
							</div>
						</div>
					</div>
				</AvForm>
			</div>
		</div>
	);
};
export default withRouter(RolCE);
