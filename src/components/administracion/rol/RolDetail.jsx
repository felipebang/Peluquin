import {
	faArrowLeft,
	faKey,
	faInfoCircle,
	faEdit
} from '@fortawesome/free-solid-svg-icons';
import React, { useEffect, useState } from 'react';
import { withRouter, generatePath } from 'react-router-dom';
import ToolBar from '../../../shared/toolbar/ToolBar';
import {
	ESTADO_ACTIVO,
	ESTADO_INACTIVO,
	ROLES_DETAIL_BREADCRUMBS,
	ROL_ADM_CODIGO
} from '../../../shared/constants/client';
import networkService from '../../../core/services/networkService';
import { ROL_LABELS } from './RolUtils';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import CustomCheckTree from '../../../shared/checkboxtree/CustomCheckTree';
import { MODULOS } from '../../../shared/constants/modulos';
import { PERMISOS } from '../../../shared/constants/permisos';
import { ADM_ROLES_EDIT } from '../../../shared/constants/routesApp';

const RolDetail = props => {
	const [modulosPermisos, setModulosPermisos] = useState([]);
	const [checked, setChecked] = useState([]);
	const [expanded, setExpanded] = useState([]);
	const [rol, setRol] = useState({
		codigo: '',
		descripcion: '',
		estado: '',
		modulosDtoList: {}
	});

	//api
	const rolesApi = {
		edit: '/roles/',
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
		if (props.match.params.id) {
			networkService
				.get(rolesApi.edit + props.match.params.id)
				.then(response => {
					const rolResponse = response.data;
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
				});
		} else {
			setRol({ rol });
		}
	}, []);

	//Botones para el toolbar
	const toolbarButtons = [
		{
			label: 'Volver',
			actions: { onClick: () => props.history.goBack() },
			icon: faArrowLeft
		},
		{
			module: MODULOS.ADM_ROLES.codigo,
			permissions: [PERMISOS.actualizacion],
			label: 'Editar',
			actions: {
				onClick: () =>
					props.history.push(generatePath(ADM_ROLES_EDIT, { id: rol.id })),
				disabled: rol.codigo === ROL_ADM_CODIGO
			},
			icon: faEdit
		}
	];

	const onExpand = expandedItems => {
		setExpanded(expandedItems);
	};

	return (
		<div className='flex-row'>
			<div className='col-12'>
				<ToolBar
					breadcrumbs={ROLES_DETAIL_BREADCRUMBS}
					buttons={toolbarButtons}
				/>
				<br />
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
								</label>
								<div className='col-8'>
									<input
										readOnly={true}
										className='form-control'
										type='text'
										value={rol.codigo}
									/>
								</div>
							</div>
							<div className='form-group row'>
								<label
									htmlFor='example-text-input'
									className='col-4 col-form-label'>
									{ROL_LABELS.descripcion}
								</label>
								<div className='col-8'>
									<input
										readOnly={true}
										className='form-control'
										type='text'
										value={rol.descripcion}
									/>
								</div>
							</div>
							<div className='form-group row'>
								<label
									htmlFor='example-text-input'
									className='col-4 col-form-label'>
									{ROL_LABELS.estado}
								</label>
								<div className='col-8'>
									<input
										readOnly={true}
										className='form-control'
										type='text'
										value={rol.estado ? ESTADO_ACTIVO : ESTADO_INACTIVO}
									/>
								</div>
							</div>
						</fieldset>
					</div>
					<div className='col-12 col-sm-12 col-md-6 col-lg-6'>
						<legend className='h5 pt-2'>
							<FontAwesomeIcon icon={faKey} />
							{' ' + ROL_LABELS.modulosAsociados}
						</legend>
						<br />
						<div className='card'>
							<CustomCheckTree
								checked={checked}
								expanded={expanded}
								nodes={modulosPermisos}
								showExpandAll={true}
								disabled={true}
								onExpand={onExpand}
							/>
						</div>
					</div>
				</div>
			</div>
		</div>
	);
};
export default withRouter(RolDetail);
