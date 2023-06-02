import {
	ADM_USUARIOS_NEW,
	ADM_USUARIOS_EDIT,
	ADMINISTRACION
} from '../../../shared/constants/routesApp';

import networkService from '../../../core/services/networkService';
import React, { useEffect, useState } from 'react';
import {
	faArrowLeft,
	faUserPlus,
	faFileImport,
	faCloudUploadAlt,
	faSave
} from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import {
	textFilter,
	numberFilter,
	Comparator
} from 'react-bootstrap-table2-filter';
import { withRouter, generatePath } from 'react-router-dom';
import {
	USUARIOS_BREADCRUMBS,
	MESSAGE_ERROR,
	MESSAGE_INFO
} from '../../../shared/constants/client';
import Datatable, {
	getStoredFilter
} from '../../../shared/datatable/Datatable';
import ToolBar from '../../../shared/toolbar/ToolBar';
import {
	Button,
	FormGroup,
	Input,
	Label,
	Modal,
	ModalBody,
	ModalFooter,
	ModalHeader,
	CustomInput
} from 'reactstrap';
import {
	notificationSuccess,
	notificationInfo,
	notificationError
} from '../../../core/services/notificationService';
import { MODULOS } from '../../../shared/constants/modulos';
import { PERMISOS } from '../../../shared/constants/permisos';
import store from '../../../store/redux.store';
import { receiveTableFilter } from '../../../store/redux.actions';




const Usuario = props => {
	//State
	const [modal, setModal] = useState(false);
	const [modalDownload, setModalDownload] = useState(false);
	const [importCSV, setimportCSV] = useState(true);
	const [filterDataExport, setFilterDataExport] = useState({});
	const [plantillaUrl, setPlantillaUrl] = useState('');
	const [archivo, setArchivo] = useState({});
	const [usuariosList,  setUGananciasList] = useState([]);
	const currentFilter = getStoredFilter('Usuario');
	const [paginationUsuarios, setPaginationUsuarios] = useState(
		currentFilter
			? currentFilter
			: {
					filter: {},
					page: 1,
					sizePerPage: 10,
					totalSize: 0,
					paginationSize: 4,
					showTotal: true,
					column: 'usuario',
					order: 'asc'
			  }
	);








	//api
	const usuariosApi = {
		buscar: '/usuarios/buscar',
		upload: '/usuarios/upload',
		plantillaVacia: '/usuarios/download/0',
		plantilla: '/usuarios/download/1'
	};
	// Datatable
	const columns = [
		{
			dataField: 'persona',
			text: 'Código empleado',
			sort: true,
			filter: numberFilter({
				placeholder: 'Código empleado...',
				comparators: Comparator.EQ,
				withoutEmptyComparatorOption: true
			})
		},
		{
			dataField: 'usuario',
			text: 'Usuario',
			sort: true,
			filter: textFilter({ placeholder: 'Usuario...' })
		}
	];

	const onFileChangeHandler = e => {
		e.preventDefault();
		const formData = new FormData();
		formData.append('file', e.target.files[0]);
		setArchivo(formData);
		if (e.target.files.length !== 0) {
			var num = e.target.files[0].name.split('.').length - 1;
			if (e.target.files[0].name.split('.')[num] === 'csv') {
				if (e.target.files[0].size > 52428800) {
					setimportCSV(true);
					notificationError({
						message: MESSAGE_ERROR.ERROR_ARCHIVO_TAMAÑO_MAXIMO
					});
				} else {
					setimportCSV(false);
				}
			} else {
				setimportCSV(true);
			}
		} else {
			setimportCSV(true);
		}
	};

	const handleSubirArchivo = () => {
		networkService
			.post(usuariosApi.upload, archivo)
			.then(response => {
				toggle();
				notificationSuccess(response.data);
				setimportCSV(true);
			})
			.catch(error => {
				const params = {};
				params.message = MESSAGE_ERROR.ERROR_UPLOAD_FILE_IMPORT;
				notificationError(error.response ? error.response.data : params);
			});
	};

	const toggle = () => {
		setModal(!modal);
		setimportCSV(true);
	};

	const toggleDownload = () => {
		setModalDownload(!modalDownload);
		setPlantillaUrl('');
	};

	const rowEvents = {
		onClick: (e, row, rowIndex) => {
			store.dispatch(
				receiveTableFilter({
					...paginationUsuarios,
					jsType: 'Usuario'
				})
			);
			props.history.push(generatePath(ADM_USUARIOS_EDIT, { id: row.id }));
		}
	};

	const handleUsuariosFilter = paginate => {
		networkService.post(usuariosApi.buscar, paginate).then(response => {
			setPaginationUsuarios({
				...paginate,
				page: response.data.number + 1,
				sizePerPage: response.data.size,
				totalSize: response.data.totalElements
			});
			 setUGananciasList(response.data.content);
			setFilterDataExport({
				...paginate,
				page: 0,
				sizePerPage: response.data.totalElements
			});
		});
	};

	//own functions
	useEffect(() => {}, []);

	//Botones para el toolbar
	const toolbarButtons = [
		{
			label: 'Volver',
			actions: { onClick: () => props.history.push(ADMINISTRACION) },
			icon: faArrowLeft
		},
		{
			module: MODULOS.ADM_USUARIOS.codigo,
			permissions: [PERMISOS.escritura],
			label: 'Nuevo',
			actions: {
				onClick: () => props.history.push(ADM_USUARIOS_NEW)
			},
			icon: faUserPlus
		} /* ,
		{
			label: 'Acciones masivas',
			children: [
				{
					module: MODULOS.ADM_USUARIOS.codigo,
					permissions: [PERMISOS.escritura, PERMISOS.actualizacion],
					label: 'Importar registros',
					icon: faFileImport,
					actions: { onClick: () => toggle() }
				},
				{
					module: MODULOS.ADM_USUARIOS.codigo,
					permissions: [PERMISOS.lectura],
					label: 'Exportar registros',
					actions: { onClick: () => toggleDownload() },
					icon: faFileExport
				}
			]
		} */
	];

	const FragmentModalUploadCsv = (
		<Modal isOpen={modal}>
			<ModalHeader>
				<FontAwesomeIcon icon={faCloudUploadAlt} /> Importar Información de CSV
			</ModalHeader>
			<ModalBody>
				<ol>
					<li>Extensión de archivo .CSV</li>

					<li>Separación de Campos (punto y coma) ;</li>

					<li>Terminación de Linea (enter)</li>
				</ol>
				<FormGroup>
					<Label for='upload'>Seleccione un archivo</Label>
					<Input
						type='file'
						id='example'
						name='file'
						accept='.csv	'
						onChange={onFileChangeHandler}
					/>
				</FormGroup>
			</ModalBody>
			<ModalFooter>
				<Button color='secondary' onClick={toggle}>
					<FontAwesomeIcon icon={faArrowLeft} /> Cancelar
				</Button>
				<Button
					color='primary'
					onClick={handleSubirArchivo}
					disabled={importCSV}>
					<FontAwesomeIcon icon={faFileImport} /> Importar
				</Button>{' '}
			</ModalFooter>
		</Modal>
	);

	const FragmentModalDownloadCsv = (
		<Modal id='mDownload' isOpen={modalDownload}>
			<ModalHeader>
				<FontAwesomeIcon icon={faCloudUploadAlt} /> Exportar Información a CSV
			</ModalHeader>
			<ModalBody>
				<FormGroup tag='fieldset'>
					<FormGroup check>
						<Label check>
							<CustomInput
								onChange={() => setPlantillaUrl(usuariosApi.plantillaVacia)}
								type='radio'
								id='vacia'
								name='customRadio'
								label='Descargar plantilla vacía'
							/>
						</Label>
					</FormGroup>
					<FormGroup check>
						<Label check>
							<CustomInput
								onChange={() => setPlantillaUrl(usuariosApi.plantilla)}
								type='radio'
								id='datos'
								name='customRadio'
								label='Descargar plantilla con registros'
							/>
						</Label>
					</FormGroup>
				</FormGroup>
			</ModalBody>
			<ModalFooter>
				<Button color='secondary' onClick={toggleDownload}>
					<FontAwesomeIcon icon={faArrowLeft} /> Cancelar
				</Button>
				<Button
					color='primary'
					onClick={() => {
						if (
							filterDataExport.sizePerPage > 0 ||
							plantillaUrl === usuariosApi.plantillaVacia
						) {
							networkService
								.post(
									process.env.REACT_APP_API_URL + plantillaUrl,
									filterDataExport,
									{
										responseType: 'blob'
									}
								)
								.then(response => {
									var FileSaver = require('file-saver');
									FileSaver.saveAs(
										response.data,
										'plantilla_listado_usuarios.csv'
									);
								});
						} else {
							notificationInfo({
								message: MESSAGE_INFO.INFO_REGISTROS_EXPORTAR
							});
						}
					}}
					disabled={plantillaUrl === '' ? true : false}>
					<FontAwesomeIcon icon={faSave} /> Guardar
				</Button>
			</ModalFooter>
		</Modal>
	);

	//return
	return (
		<div className='flex-row'>
			<div className='col-12'>
				<ToolBar breadcrumbs={USUARIOS_BREADCRUMBS} buttons={toolbarButtons} />
				<div className='bg-white'>
					<Datatable
						keyField='id'
						data={usuariosList}
						columns={columns}
						rowEvents={rowEvents}
						callbackCustomFilter={handleUsuariosFilter}
						customPagination={paginationUsuarios}
					/>
					{FragmentModalUploadCsv}
					{FragmentModalDownloadCsv}
				</div>
			</div>
		</div>
	);
};
export default withRouter(Usuario);
