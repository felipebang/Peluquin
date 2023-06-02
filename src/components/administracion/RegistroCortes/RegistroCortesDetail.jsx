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

const RegistroCortesDetail = props => {
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
		valorCorte: '',
		codigoEmpleado: ''
	};
	const [empleado, setEmpleado] = useState(empleadoInitialState);
	const [empleados, setEmpleados] = useState({});
	const [roles, setRoles] = useState([]);
	const [registroCortes, setgistroCortes] = useState(registroCortesInitialState);
	const [rolesUsuario, setRolesUsuario] = useState([]);
	const [codEmpleadoBuscar, setCodEmpleadoBuscar] = useState('');
	const userLogged = getLoggedUser();
	const [modalDownload, setModalDownload] = useState(false);
	const [persona, setPersona] = useState({});
	//api
	const empleadosApi = {
		persona: '/persona/',
		buscarEmpleado: '/persona/buscarporcodigo/',
		filterSelect: '/persona/filternombres'
	
	};

	const usuariosApi = {
		edit: '/usuarios/',
	
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


	useEffect(() => {
		networkService
			.get(empleadosApi.persona + props.match.params.id)
			.then(response => {
				setPersona(response.data);
			});
		const paginate = {
			filter: {
				persona: { codigoEmpleado: props.match.params.id }
			},
			page: 0,
			sizePerPage: 10,
			totalSize: 0,
			paginationSize: 4,
			showTotal: true,
			column: 'id',
			order: 'asc'
		};

	}, []);

	
	



	const toggleDownload = () => {
		setModalDownload(!modalDownload);
	
	};


//console.log(registroCortesApi)

	//return false;






















	


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
					/*{onValidSubmit={handleValidUserSubmit}*/>
					<Button color='primary'  /*onClick={handleValidUserSubmit}*/>
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
										
									</label>
								
									<FormGroup className='row'>
									<label
										htmlFor='example-text-input'
										className='col-5 col-form-label'>
											{' ' + REGISTROCORTES_LABELS.NUCODEMPLEADO}
									</label>
									<div className='col-7'>
										<input
											readOnly={true}
											className='form-control'
											type='text'
											name='codigoEmpleado'
											value={persona.codigoEmpleado}
										/>
									</div>
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

								
							</fieldset>
						</div>
					</div>
				</AvForm>
			</div>
		</div>
	);
};
export default withRouter(RegistroCortesDetail);
