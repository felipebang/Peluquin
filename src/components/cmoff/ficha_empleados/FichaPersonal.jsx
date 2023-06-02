import React, { useEffect, useState } from 'react';
import { withRouter } from 'react-router-dom';
import { FICHA_EMPLEADOS_PERSONAL_BREADCRUMBS } from '../../../shared/constants/client';
import ToolBar from '../../../shared/toolbar/ToolBar';
import {
	faArrowLeft,
	faIdCardAlt,
	faUserCircle
} from '@fortawesome/free-solid-svg-icons';
import {
	Nav,
	NavItem,
	NavLink,
	FormGroup,
	
} from 'reactstrap';
import { FICHAPERSONAL_LABELS } from './FichaEmpleadosUtils';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import Datatable from '../../../shared/datatable/Datatable';
import { Button } from 'reactstrap';
import networkService from '../../../core/services/networkService';
import moment from 'moment';

import '../styles.css';
const FichaPersonal = props => {
	const [informacionState, setInformacionState] = useState(true);

	const [persona, setPersona] = useState({});




	const [modalDownload, setModalDownload] = useState(false);


	const toolbarButtons = [
		{
			label: 'Volver',
			actions: { onClick: () => props.history.goBack() },
			icon: faArrowLeft
		}
		
		
		
	];

	const fichaPersonalApi = {
		persona: '/persona/',
	
	};




	useEffect(() => {
		networkService
			.get(fichaPersonalApi.persona + props.match.params.id)
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


	return (
		<div className='flex-row'>
			<div className='col-12'>
				<ToolBar
					breadcrumbs={FICHA_EMPLEADOS_PERSONAL_BREADCRUMBS}
					buttons={toolbarButtons}
				/>
				<div className='col-12'>
					<Nav tabs>
						<NavItem>
							<NavLink
								active={informacionState}
								onClick={() => {
									setInformacionState(true);
								
								}}
								href='#'>
								{FICHAPERSONAL_LABELS.INFORMACION}
							</NavLink>
						</NavItem>
				
					</Nav>
				</div>
				<br />
				{informacionState ? (
					<div className='row pt-1'>
						<div className='border-right col-12 col-sm-12 col-md-12 col-lg-6'>
							<fieldset className=' col '>
								<h5 className='titleGeneral'>
									<FontAwesomeIcon icon={faIdCardAlt} />
									{' ' + FICHAPERSONAL_LABELS.INFORMACION_PERSONA}
								</h5>
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
											value={persona.nombres || ''}
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
												persona.apellido1 +
												' ' +
												(persona.apellido2 ? persona.apellido2 : '')
											}
										/>
									</div>
								</FormGroup>
								<FormGroup className='row'>
									<label
										htmlFor='example-text-input'
										className='col-5 col-form-label'>
										{FICHAPERSONAL_LABELS.PAIS}
									</label>
									<div className='col-7'>
										<input
											readOnly={true}
											className='form-control'
											type='text'
											value={persona.pais || ''}
										/>
									</div>
								</FormGroup>
								<FormGroup className='row'>
									<label
										htmlFor='example-text-input'
										className='col-5 col-form-label'>
										{FICHAPERSONAL_LABELS.PROVINCIA}
									</label>
									<div className='col-7'>
										<input
											readOnly={true}
											className='form-control'
											type='text'
											value={persona.provincia || ''}
										/>
									</div>
								</FormGroup>
								<FormGroup className='row'>
									<label
										htmlFor='example-text-input'
										className='col-5 col-form-label'>
										{FICHAPERSONAL_LABELS.IDIOMA_NATIVO}
									</label>
									<div className='col-7'>
										<input
											readOnly={true}
											className='form-control'
											type='text'
											value={persona.idiomaNativo || ''}
										/>
									</div>
								</FormGroup>
							</fieldset>
						</div>
						<div className='border-right col-12 col-sm-12 col-md-12 col-lg-6'>
							<h5 className='titleGeneral'>
								<FontAwesomeIcon icon={faUserCircle} />
								{' ' + FICHAPERSONAL_LABELS.INFORMACION_CUENTA}
							</h5>
							<div className='row'>
								<div className='col-md-12 col-sm-12 rounded'>
									<fieldset className='col'>
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
													value={persona.codigoEmpleado || ''}
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
													value={persona.empresa || ''}
												/>
											</div>
										</FormGroup>
										<FormGroup className='row'>
											<label
												htmlFor='example-text-input'
												className='col-5 col-form-label'>
												{FICHAPERSONAL_LABELS.FUNCION_PRINCIPAL}
											</label>
											<div className='col-7'>
												<input
													readOnly={true}
													className='form-control'
													type='text'
													value={persona.funcionPrincipal || ''}
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
													value={persona.email || ''}
												/>
											</div>
										</FormGroup>
									</fieldset>
								</div>
							</div>
						</div>
					</div>
				) : (
					''
				)}
			
			
			</div>
		</div>
	);
};
export default withRouter(FichaPersonal);
