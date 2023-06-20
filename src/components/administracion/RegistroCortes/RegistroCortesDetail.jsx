import {
	faArrowLeft,
	faIdCardAlt,
	faSave,
	faEdit
} from '@fortawesome/free-solid-svg-icons';
import { FormGroup } from 'reactstrap';
import { generatePath } from 'react-router-dom';
import React, { useEffect, useState } from 'react';
import { withRouter } from 'react-router-dom';
import ToolBar from '../../../shared/toolbar/ToolBar';
import {
	REGISTROCORTES_EDIT_BREADCRUMBS,
	MESSAGE_ERROR
} from '../../../shared/constants/client';
import { AvField, AvForm } from 'availity-reactstrap-validation';
import networkService from '../../../core/services/networkService';
import {
	notificationError,
	notificationSuccess
} from '../../../core/services/notificationService';
import { REGISTROCORTES_LABELS } from '../RegistroCortes/RegistroCortesUtils';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { MODULOS } from '../../../shared/constants/modulos';
import { Button } from 'reactstrap';
import {
	ADM_REGISTROCORTES,
	ADM_REGISTROCORTES_DETAIL
} from '../../../shared/constants/routesApp';
import { getLoggedUser, userLogOut } from '../../../core/auth/auth.service';
import axios from 'axios';
import { PERMISOS } from '../../../shared/constants/permisos';
const RegistroCortesDetail = props => {
	
	const [idParam, setIdParam] = useState(null);
	const [inputsUserDisabled, setInputsUserDisabled] = useState(false);
	const [rolesError, setRolesError] = useState(false);
	const userLogged = getLoggedUser();
	const [estadoError, setEstadoError] = useState(false);

	




	const empleadoInitialState = {
	
		numeroCortes: '',
		valorCorte: '',
		codigoEmpleado: '',
		registroCortesDTOList: {}
		
			
	};


	
	const [persona, setPersona] = useState({
		idRegistroCorte: '',
		numeroCortes: '',
		valorCorte: '',
		codigoEmpleado: '',
		registroCortesDTOList: {}
	
	});

	const [rolesUsuario, setRolesUsuario] = useState([]);
	const [personaService , setCodEmpleadoBuscar] = useState('');
	const [checked, setChecked] = useState([]);

	//api
	const empleadosApi = {
		registrocortes: '/registrocortes/',
		update: '/registrocortes/actualizar/',
		delete: '/registrocortes/delete/'
		
	};

	const toolbarButtons = [
		{
			module: MODULOS.ADM_REGISTROCORTES.codigo,
			permissions: [PERMISOS.lectura, PERMISOS.escritura, PERMISOS.actualizacion],
			label: 'Volver',
			actions: { onClick: () => props.history.goBack() },
			icon: faArrowLeft
		},

		{
			module: MODULOS.ADM_REGISTROCORTES.codigo,
			permissions: [PERMISOS.lectura, PERMISOS.escritura,  PERMISOS.actualizacion],
			label: 'Actualizar',
			form: inputsUserDisabled ? '' : 'usuarioForm',
			icon: faEdit,
			actions: {
				onClick: () => saveActualizar(props.match.params.id)
			}
		},

		{
			module: MODULOS.ADM_REGISTROCORTES.codigo,
			permissions: [PERMISOS .escritura, PERMISOS.actualizacion],
			label: 'Borrar Registro',
			form: inputsUserDisabled ? '' : 'usuarioForm',
			icon:DataView,
			actions: {
				onClick: () => deleteBook(props.match.params.id)
			}
		}
	];

	useEffect(() => {

		networkService
			.get(empleadosApi.registrocortes + props.match.params.id)
			.then(response => {
				setPersona(response.data);
			});
		const paginate = {
			filter: {
				registrocortes: { codigoEmpleado: props.match.params.id }
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

	//console.log(registroCortesApi)

	//return false;

	const saveActualizar = IdRegistroCorte => {
		const cortesRequest = { ...persona };
		networkService
			.put(empleadosApi.update + IdRegistroCorte, cortesRequest)
			.then(response => {
				notificationSuccess(response.data).then(r =>
					props.history.push(ADM_REGISTROCORTES)
				);
			});
	};



	const handleInputChange = event => {
		console.log(event.target.value);

		setPersona({
			...persona,
			[event.target.name]: event.target.value
		});
		//empleado.valorCorte=event.target.value;
	};
	const handleInputChangecortes = event => {
		console.log(event.target.value);
		setPersona({
			...persona,
			[event.target.name]: event.target.value
		});
	};

//delete
	const deleteBook = IdRegistroCorte =>{
		if(window.confirm("¿Realmente desea eliminar el registro?")) {
		networkService
			.delete(empleadosApi.delete + IdRegistroCorte )
			.then(response => {
				notificationSuccess(response.data).then(r =>
					props.history.push(ADM_REGISTROCORTES)
				
				);
			});


		}
	}










		/*
		{
		
		const cortesRequest = { ...persona };
		networkService
			 

		.delete("/registrocortes/delete/:id",(req,res)=>{
			const id = props.match.params.id;

			empleadosApi.query('DELETE FROM empleado WHERE id=?', id,
			(err,result)=>{
				if(err){
					console.log(err);
				}else{
					res.send("Emplaedo eliminado con exito!!");
				} if(console.log("error")); {
					alert("error");

				}
			}
	
	
	
	
		
			)

		}
		

		
		
		)
	

		

		};

		
	};

*/





				
		//	console.log(delete(pidRegistroCorte));

	//	return false;
					
	


	




	return (
		<div className='flex-row'>
			<ToolBar
				breadcrumbs={REGISTROCORTES_EDIT_BREADCRUMBS}
				buttons={toolbarButtons}
			/>

			<AvForm>
				<div className='col-12'>
					<div className='row pt-1'>
						<div className='border-bottom col-12 col-sm-12 col-md-12 col-lg-12'>
							<fieldset className=' col '>
								<br />

								<div className='form-group row'>
									<div className='col-1'></div>
									<label
										htmlFor='example-text-input'
										className='col-2 col-form-label'></label>

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
				</div>

				<div className='row pt-1'>
					<div className='border-right col-12 col-sm-12 col-md-12 col-lg-4'>
						<fieldset className=' col '>
							<legend className='h5 pt-2'>
								<FontAwesomeIcon
									icon={faIdCardAlt}
									className='text-secondary'
								/>
								{REGISTROCORTES_LABELS.REGISTRO}
							</legend>
							<br />
							<FormGroup className='row'>
								<label
									htmlFor='example-text-input'
									className='col-5 col-form-label'>
									{REGISTROCORTES_LABELS.NUMEROCORTES}
								
								</label>
								<div className='col-7'>
									<AvField
										name='numeroCortes'
										type='number'
										value={persona.numeroCortes}
										onChange={handleInputChange}
										autoComplete='numeroCortes'
									/>
								</div>
							</FormGroup>
							<FormGroup className='row'>
								<label
									htmlFor='example-text-input'
									className='col-5 col-form-label'>
									{' ' + REGISTROCORTES_LABELS.VALORCORTE}
								
								</label>
								<div className='col-7'>
									<AvField
										name='valorCorte'
										type='number'
										value={persona.valorCorte || ''}
										onChange={handleInputChangecortes}
										autoComplete='valorCorte'
										validate={{
											minLength: {
												value: 4
											},
											maxLength: {
												value: 16
											}
										}}
									/>
								</div>
							</FormGroup>
						</fieldset>
					</div>
				</div>
			</AvForm>
		</div>
	);
};
export default withRouter(RegistroCortesDetail);
