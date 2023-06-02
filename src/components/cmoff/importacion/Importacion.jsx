import React, { useEffect, useState } from 'react';
import { Button, Input, Container, Row, Col } from 'reactstrap';
import Datatable from '../../../shared/datatable/Datatable';
import { IMPORTACION_LABELS } from './ImportacionUtils';
import { IMPORTACION_BREADCRUMBS } from '../../../shared/constants/client';
import networkService from '../../../core/services/networkService';
import { textFilter } from 'react-bootstrap-table2-filter';
import { withRouter } from 'react-router-dom';
import './estilo.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import {
	notificationSuccess,
	notificationError
} from '../../../core/services/notificationService';
import {
	MESSAGE_ERROR,
	MESSAGE_SUCCESS
} from '../../../shared/constants/client';
import {
	faArrowLeft,
	faFileImport,
	faSyncAlt
} from '@fortawesome/free-solid-svg-icons';
import { CMOFF, ADM_ROLES_NEW } from '../../../shared/constants/routesApp';
import ToolBar from '../../../shared/toolbar/ToolBar';
import moment from 'moment';
import Swal from 'sweetalert2';
import withReactContent from 'sweetalert2-react-content';

let customSwal = withReactContent(Swal);

const Importacion = props => {
	//State
	const [gestion, setGestion] = useState(false);
	const [archivo, setArchivo] = useState({});
	const [archivoProyecto, setArchivoProyecto] = useState({});
	const [archivoSeguimiento, setArchivoSeguimiento] = useState({});
	const [archivoPlanytillaPtp, setArchivoPlantillaPtp] = useState({});
	const [importCSV, setimportCSV] = useState(true);
	const [importCSVP, setimportCSVP] = useState(true);
	const [importCSVCambios, setimportCSVPCambios] = useState(true);
	const [logList, setLogList] = useState([]);
	const [stateRol, setStateRol] = useState(true);
	const [valorArchivo, setValorArchivo] = useState(0);
	const [valorArchivoProyecto, setValorArchivoProyecto] = useState(0);
	const [tmpArchivo, setTmpArchivo] = useState({});
	const [tmpArchivoProyecto, setTmpArchivoProyecto] = useState({});
	const [tmpArchivoPalntilla, setTmpArchivoPlantilla] = useState({});
	const [tmpArchivoPalntillaPtp, setTmpArchivoPlantillaPtp] = useState({});
	const [tmpArchivoGestion, setTmpArchivoGestion] = useState({});
	const [tmpArchivoMovimientos, setTmpMovimientos] = useState({});
	const formData = new FormData();
	const formDataf = new FormData();
	const [paginationLog, setPaginationLog] = useState({
		filter: {},
		page: 1,
		sizePerPage: 10,
		totalSize: 0,
		paginationSize: 4,
		showTotal: true,
		column: 'fechaCarga',
		order: 'desc'
	});
	const [selectSeguimiento, setselectSeguimiento] = useState(0);
	const [selectGestion, setSelectGestion] = useState(0);
	const [selectPlantilla, setSelectPlantilla] = useState(0);
	const [selectPlantillaPtp, setSelectPlantillaPtp] = useState(0);
	const [selectMovimientos, setSelectMovimientos] = useState(0);
	const logApi = {
		buscarl: '/importacion/uploads',
		uploads: '/importacion/uploads',
		uploadsSeguimiento: '/importacion/Seguimiento',
		uploadsproyectosPlantilla: '/importacion/uploads/proyectosPlantilla',
		uploadsDedicaciones: '/importacion/Dedicaciones',
		uploadsPlantillaPtp: '/importacion/DedicacionPlantillaPTP',
		uploadsCambios: '/importacion/cambiosMovimientos',
	};

	// Datatable
	const columns = [
		{
			dataField: 'nombreArchivo',
			text: 'Nombre archivo',
			sort: true,
			filter: textFilter({ placeholder: 'Nombre archivo...' }),
			title: true
		},
		{
			dataField: 'estado',
			text: 'Estado',
			sort: true,
			filter: textFilter({ placeholder: 'Estado...' })
		},
		{
			dataField: 'descripcion',
			text: 'Descripción estado',
			sort: true,
			filter: textFilter({ placeholder: 'Descripción estado...' })
		},
		{
			dataField: 'fechaCarga',
			text: 'Fecha ejecución',
			sort: true

		}
	];

	const handleLogFilter = paginate => {

		networkService.post(logApi.buscarl, paginate).then(response => {
			setPaginationLog({
				...paginate,
				page: response.data.number + 1,
				sizePerPage: response.data.size,
				totalSize: response.data.totalElements
			});
			setLogList(response.data.content);
		});
	};

	useEffect(() => { }, []);

	const toolbarButtons = [
		{
			label: 'Volver',
			actions: { onClick: () => props.history.push(CMOFF) },
			icon: faArrowLeft
		}

	];

	const onFileChangeHandler = e => {
		e.preventDefault();
		if (e.target.files.length !== 0) {
			setValorArchivo(1);
			console.log("valorCandidato" + valorArchivo);
			formData.append('candidatos', e.target.files[0]);
			formData.append('enviocandidato', 's');
			setArchivo(formData);
			setTmpArchivo(e.target.files[0]);
			var num = e.target.files[0].name.split('.').length - 1;
			e.target.files[0].name.split('.')[num] === 'csv' || e.target.files[0].name.split('.')[num] === 'CSV'
				? setimportCSV(false)
				: setimportCSV(true);


			if (selectGestion == 1) {
				archivo.append('necesidades', setTmpArchivoGestion);
			}

		} else {

			setimportCSV(true);
			archivo.has('candidatos') && archivo.delete('candidatos'); //Si encuentra el form arbol en la const archivo lo elimina
			archivo.has('necesidades') && archivo.delete('necesidades'); //Si encuentra el form gestion en la const archivo lo elimina
		}
		setGestion(e.target.files[0] ? false : true);

	};

	const onFileChangeHandler2 = e => {
		e.preventDefault();
		if (e.target.files.length !== 0) {

			setSelectGestion(1);
			setTmpArchivoGestion(e.target.files[0]);

			console.log("valorArbol" + valorArchivo);
			if (valorArchivo == 1) {
				console.log("selec.." + valorArchivo);
				archivo.append('necesidades', e.target.files[0]);
			} else {

				formDataf.append('necesidades', e.target.files[0]);
				//si solo se selecciono un archivo en gestion, entonces procedemos
				// hacer que la peticion reciba los dos archivo pero solo procesa el que se leecciono
				formDataf.append('candidatos', e.target.files[0]);
				setArchivo(formDataf);
				formDataf.append('enviocandidato', 'n');
				setTmpArchivoGestion(e.target.files[0]);

			}
			var num = e.target.files[0].name.split('.').length - 1;
			e.target.files[0].name.split('.')[num] === 'csv' || e.target.files[0].name.split('.')[num] === 'CSV'
				? setimportCSV(false)
				: setimportCSV(true);


		} else {
			archivo.has('necesidades') && archivo.delete('necesidades');
			setimportCSV(true);
		}
	};

	const onFileChangeHandler3 = e => {
		e.preventDefault();
		if (e.target.files.length !== 0) {

			setselectSeguimiento(1);

			formDataf.append('seguimiento', e.target.files[0]);
			setArchivoSeguimiento(formDataf);
			if (valorArchivo != 1 && selectGestion != 1) {
				formDataf.append('candidatos', null);
				formDataf.append('necesidades', null);
				setArchivo(formDataf);
			}
			var num = e.target.files[0].name.split('.').length - 1;
			e.target.files[0].name.split('.')[num] === 'csv' || e.target.files[0].name.split('.')[num] === 'CSV'
				? setimportCSV(false)
				: setimportCSV(true);


		} else {
			archivoSeguimiento.has('seguimiento') && archivoSeguimiento.delete('seguimiento');
			setimportCSV(true);
		}
	};

	const onFileChangeHandlerPlantillaPtp = e => {


		e.preventDefault();
		if (e.target.files.length !== 0) {
			setSelectPlantillaPtp(1);

			formDataf.append('PlantillaPTP', e.target.files[0]);
			setTmpArchivoPlantillaPtp(formDataf);
			if (valorArchivoProyecto != 1 && selectPlantilla != 1) {
				formDataf.append('proyectos', null);
				formDataf.append('plantillaoffshore', null);
				setArchivoProyecto(formDataf);
			}

			var num = e.target.files[0].name.split('.').length - 1;
			e.target.files[0].name.split('.')[num] === 'csv' || e.target.files[0].name.split('.')[num] === 'CSV'
				? setimportCSVP(false)
				: setimportCSVP(true);


		} else {
			archivoPlanytillaPtp.has('PlantillaPTP') && archivoPlanytillaPtp.delete('PlantillaPTP');
			setimportCSVP(true);
		}



	};

	const onFileChangeHandlerProyecto = e => {
		if (e.target.files.length !== 0) {
			setValorArchivoProyecto(1);
			formData.append('proyectos', e.target.files[0]);
			formData.append('envioproyecto', 's');
			setArchivoProyecto(formData);
			setTmpArchivoProyecto(e.target.files[0]);
			var num = e.target.files[0].name.split('.').length - 1;
			e.target.files[0].name.split('.')[num] === 'csv' || e.target.files[0].name.split('.')[num] === 'CSV'
				? setimportCSVP(false)
				: setimportCSVP(true);


			if (selectPlantilla == 1) {
				archivoProyecto.append('plantillaoffshore', setTmpArchivoPlantilla);
			}

		} else {

			setimportCSVP(true);
			archivoProyecto.has('proyectos') && archivoProyecto.delete('proyectos'); //Si encuentra el form proyectos en la const archivo lo elimina
			archivoProyecto.has('plantillaoffshore') && archivoProyecto.delete('plantillaoffshore'); //Si encuentra el form plantillaoffshore en la const archivo lo elimina
		}
		setGestion(e.target.files[0] ? false : true);

	};

	const onFileChangeHandlePLantilla = e => {

		if (e.target.files.length !== 0) {

			setSelectPlantilla(1);
			setTmpArchivoPlantilla(e.target.files[0]);
			if (valorArchivoProyecto == 1) {
				archivoProyecto.append('plantillaoffshore', e.target.files[0]);
			} else {
				formDataf.append('plantillaoffshore', e.target.files[0]);
				//si solo se selecciono un archivo en gestion, entonces procedemos
				// hacer que la peticion reciba los dos archivo pero solo procesa el que se leecciono
				formDataf.append('proyectos', e.target.files[0]);
				setArchivoProyecto(formDataf);
				formDataf.append('envioproyecto', 'n');
				setTmpArchivoPlantilla(e.target.files[0]);

			}
			var num = e.target.files[0].name.split('.').length - 1;
			e.target.files[0].name.split('.')[num] === 'csv' || e.target.files[0].name.split('.')[num] === 'CSV'
				? setimportCSVP(false)
				: setimportCSVP(true);


		} else {
			archivoProyecto.has('plantillaoffshore') && archivoProyecto.delete('plantillaoffshore');
			setimportCSVP(true);
		}


	};



	const handleSubirArchivo = () => {

		if (archivo.has('candidatos') != false || archivo.has('necesidades') != false) {
			console.log(".." + archivo.has('candidatos') + " necesidades.." + archivo.has('necesidades'));

			// si solo se selecciono el archibo candidatos hacemos creer que se selecciono ambos pero slo se procesa candidatos
			if (archivo.has('necesidades') == false && archivo.has('candidatos') == true && selectGestion == 0) {
				archivo.append('necesidades', tmpArchivo);
				archivo.append('envionecesidad', 'n');
			}
			if (selectGestion == 1) {
				console.log("tmpArchivo..." + tmpArchivoGestion.name + "  ");
				archivo.append('necesidades', tmpArchivoGestion);
				archivo.append('envionecesidad', 's');
			}


			customSwal
				.fire({
					title: 'Confirmar',
					text: `¿Este proceso realiza carga de datos en las tabla temporal correspondondiente, desea continuar?`,
					showCancelButton: true,
					confirmButtonColor: '#004254',
					cancelButtonColor: '#03657c',
					confirmButtonText: `Si, Continuar`,
					cancelButtonText: 'Cancelar'
				})
				.then(result => {

					if (result.value) {



						networkService
							.post(logApi.uploads, archivo)
							.then(() => {
								notificationSuccess({
									message: MESSAGE_SUCCESS.IMPORTACION_EXITOSA
								}).then(
									selectSeguimiento == 0 ?
										window.location.reload() : ""

								);
							})
							.catch(error => {
								const params = {};
								params.message = MESSAGE_ERROR.ERROR_UPLOAD_FILE_FAILED;
								/*
								notificationError(
									error.response ? error.response.data : params
								);
								*/
							});


					}
					if (selectSeguimiento == 1) {
						networkService
							.post(logApi.uploadsSeguimiento, archivoSeguimiento)
							.then(() => {
								notificationSuccess({
									message: MESSAGE_SUCCESS.IMPORTACION_EXITOSA
								}).then(window.location.reload());
							})
							.catch(error => {
								const params = {};
								params.message = MESSAGE_ERROR.ERROR_UPLOAD_FILE_FAILED;
								notificationError(
									error.response ? error.response.data : params
								);
							});

					}


				});

		}

		//	notificationError({ message: MESSAGE_ERROR.ERROR_UPLOAD_FILE_IMPORT });


	};

	const handleSubirArchivoProyecto = () => {

		if (archivoProyecto.has('proyectos') != false || archivoProyecto.has('plantillaoffshore') != false) {
			console.log(".." + archivoProyecto.has('proyectos') + " plantillaoffshore.." + archivoProyecto.has('plantillaoffshore'));

			// si solo se selecciono el archibo proyectos hacemos creer que se selecciono ambos pero slo se procesa proyectos
			if (archivoProyecto.has('plantillaoffshore') == false && archivoProyecto.has('proyectos') == true && selectPlantilla == 0) {
				archivoProyecto.append('plantillaoffshore', tmpArchivoProyecto);
				archivoProyecto.append('envioplantillaoffshore', 'n');
			}
			if (selectPlantilla == 1) {
				console.log("tmpArchivo..." + tmpArchivoPalntilla.name + "  ");
				archivoProyecto.append('plantillaoffshore', tmpArchivoPalntilla);
				archivoProyecto.append('envioplantillaoffshore', 's');
			}

			customSwal
				.fire({
					title: 'Confirmar',
					text: `¿Este proceso realiza carga de datos en las tabla temporal correspondondiente, desea continuar?`,
					showCancelButton: true,
					confirmButtonColor: '#004254',
					cancelButtonColor: '#03657c',
					confirmButtonText: `Si, Continuar`,
					cancelButtonText: 'Cancelar'
				})
				.then(result => {
					if (result.value) {
						networkService
							.post(logApi.uploadsproyectosPlantilla, archivoProyecto)
							.then(() => {
								notificationSuccess({
									message: MESSAGE_SUCCESS.IMPORTACION_EXITOSA
								}).then(selectPlantillaPtp == 0 ?
									window.location.reload() : "");
							})
							.catch(error => {
								const params = {};
								params.message = MESSAGE_ERROR.ERROR_UPLOAD_FILE_FAILED;
								/*
								notificationError(
									error.response ? error.response.data : params
								);
								*/
							});
						setimportCSVP(true);
						setStateRol(true);
					}
					if (selectPlantillaPtp == 1) {


						networkService
							.post(logApi.uploadsPlantillaPtp, tmpArchivoPalntillaPtp)
							.then(() => {
								notificationSuccess({
									message: MESSAGE_SUCCESS.IMPORTACION_EXITOSA
								}).then(

									window.location.reload()

								);
							})
							.catch(error => {
								const params = {};
								params.message = MESSAGE_ERROR.ERROR_UPLOAD_FILE_FAILED;

							});

					}

				});


		}
		//	notificationError({ message: MESSAGE_ERROR.ERROR_UPLOAD_FILE_IMPORT });



	};

	const onFileChangeHandlerCambios = e => {
		e.preventDefault();
		if (e.target.files.length !== 0) {
			setValorArchivo(1);
			formData.append('Hcambios', e.target.files[0]);
			formData.append('enviocambio', 's');
			setArchivo(formData);
			setTmpArchivo(e.target.files[0]);
			var num = e.target.files[0].name.split('.').length - 1;
			e.target.files[0].name.split('.')[num] === 'csv' || e.target.files[0].name.split('.')[num] === 'CSV'
				? setimportCSVPCambios(false)
				: setimportCSVPCambios(true);

				if(selectMovimientos ==1){
					archivo.append('movimientos', setSelectMovimientos);
				}
		

		} else {

			setimportCSVPCambios(true);
			archivo.has('Hcambios') && archivo.delete('Hcambios');
			archivo.has('movimientos') && archivo.delete('movimientos');
		}
		setSelectMovimientos(e.target.files[0] ? false : true);

	};


	
	const onFileChangeHandlerMoviento = e => {
		e.preventDefault();
		if (e.target.files.length !== 0) {
		
			setSelectMovimientos(1);
			setTmpMovimientos(e.target.files[0]);
		
			if(valorArchivo ==1 ){
				console.log("selec.."+ valorArchivo);
				archivo.append('movimientos', e.target.files[0]);
			}else{
			
				formDataf.append('movimientos', e.target.files[0]);
				//si solo se selecciono un archivo en gestion, entonces procedemos
				// hacer que la peticion reciba los dos archivo pero solo procesa el que se leecciono
				formDataf.append('Hcambios', e.target.files[0]);
				setArchivo(formDataf);
				formDataf.append('enviocambio', 'n');
				setTmpMovimientos(e.target.files[0]);
			
			}
			var num = e.target.files[0].name.split('.').length - 1;
			e.target.files[0].name.split('.')[num] === 'csv'
				? setimportCSVPCambios(false)
				: setimportCSVPCambios(true);


		} else {
			archivo.has('movimientos') && archivo.delete('movimientos');
			setimportCSVPCambios(true);
		}
	};



	const handleSubirArchivoCambios = () => {

		if (archivo.has('Hcambios') != false || archivo.has('movimientos') != false) {
			console.log(".." + archivo.has('Hcambios'));

			// si solo se selecciono el archibo Hcanbios hacemos creer que se selecciono ambos pero slo se procesa Hcambios
			if (archivo.has('movimientos') == false && archivo.has('Hcambios') == true && selectMovimientos == 0) {
				archivo.append('movimientos', tmpArchivo);
				archivo.append('enviomovimientos', 'n');
			} 
			if (selectMovimientos == 1) {
				console.log("tmpArchivo..." + tmpArchivoMovimientos.name + "  ");
				archivo.append('movimientos', tmpArchivoMovimientos);
				archivo.append('enviomovimientos', 's');
			}


			customSwal
				.fire({
					title: 'Confirmar',
					text: `¿Este proceso realiza carga de datos en las tabla temporal correspondondiente, desea continuar?`,
					showCancelButton: true,
					confirmButtonColor: '#004254',
					cancelButtonColor: '#03657c',
					confirmButtonText: `Si, Continuar`,
					cancelButtonText: 'Cancelar'
				})
				.then(result => {

					if (result.value) {
						networkService
							.post(logApi.uploadsCambios, archivo)
							.then(() => {
								notificationSuccess({
									message: MESSAGE_SUCCESS.IMPORTACION_EXITOSA
								}).then(window.location.reload());
							})
							.catch(error => {
								const params = {};
								params.message = MESSAGE_ERROR.ERROR_UPLOAD_FILE_FAILED;
								notificationError(
									error.response ? error.response.data : params
								);

							});


					}



				});

		}else
		notificationError({ message: MESSAGE_ERROR.ERROR_UPLOAD_FILE_IMPORT });

	};
	const actualizar = () => {
		handleLogFilter({ ...paginationLog, page: paginationLog.page - 1 });
	};

	return (
		<div className='flex-row'>
			<div className='col-12'>
				<ToolBar
					breadcrumbs={IMPORTACION_BREADCRUMBS}
					buttons={toolbarButtons}

				/>
				<br></br>

				<a href="/archivosCargue/archivos.zip">
					<button type="button" class="btn btn-primary btn-sm">Ver archivos ejemplo <span class="bi bi-file-image"></span></button> </a>

				<div className='titulo'>
					<h2>{IMPORTACION_LABELS.IMPORTACION_LABEL}</h2>
				</div>
				<br />

				<div className='row pt-1'>

					<div className='col-6 col-md-4'>
					

					</div>

					<div className='col-md-4'>
				
					</div>


					<div className='col-md-4'>
						{/* 	<div className='border-right col-12 col-sm-12 col-md-12 col-lg-5'> */}
			


					</div>






				</div>

				<div className='bg-withReactContent  ' >
					<br />
					<Button color='primary' onClick={actualizar} className="btn-sm">
						<FontAwesomeIcon icon={faSyncAlt} />{' '}
						{IMPORTACION_LABELS.ACTUALIZART_LABEL}
					</Button>

			

				</div>
			</div>
		</div>
	);
};
export default withRouter(Importacion);
