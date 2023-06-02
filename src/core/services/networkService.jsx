import axios from 'axios';
import store from '../../store/redux.store';
import { stateLoadingAction } from '../../store/redux.actions';
import {
	notificationClose,
	notificationError,
	notificationLoading
} from './notificationService';
import { DASHBOARD } from '../../shared/constants/routesApp';
import { getJwtToken, userLogOut } from '../auth/auth.service';

const networkService = axios.create({
	baseURL: process.env.REACT_APP_API_URL
});

networkService.interceptors.request.use(
	config => {
		if (getJwtToken()) config.headers.Authorization = getJwtToken();
		store.dispatch(stateLoadingAction(true));
		notificationLoading();
		return config;
	},
	function(error) {
		store.dispatch(stateLoadingAction(false));
		notificationError();
		return Promise.reject(error);
	}
);
networkService.interceptors.response.use(
	response => {
		store.dispatch(stateLoadingAction(false));
		notificationClose();
		return response;
	},
	error => {
		store.dispatch(stateLoadingAction(false));
		const params = {};
		if (getJwtToken()) {
			if (
				error.response &&
				error.response.data &&
				error.response.data.status === 401
			) {
				params.message = 'Su sesión ha expirado';
				notificationError(params).then(r => {
					window.location.reload();
					userLogOut();
				});
			}
			if (
				error.response &&
				error.response.data &&
				error.response.data.status === 403
			) {
				params.message = 'No esta autorizado';
				notificationError(params).then(r => (window.location.href = DASHBOARD));
			}
			if (
				error.response &&
				error.response.data &&
				(error.response.data.code === 'INTERNAL_SERVER_ERROR' ||
					error.response.data.code === 'BAD_REQUEST')
			) {
				notificationError(error.response.data);
			}
			if (
				error.code !== 'ECONNABORTED' &&
				error.hasOwnProperty('response') &&
				!error.response
			) {
				params.message = 'Error de conexión';
				notificationError(params).then(r => {
					window.location.reload();
					userLogOut();
				});
			} else if (
				error.request.responseType === 'blob' &&
				error.response.data instanceof Blob &&
				error.response.data.type &&
				error.response.data.type.toLowerCase().indexOf('json') != -1
			) {
				return new Promise((resolve, reject) => {
					let reader = new FileReader();
					reader.onload = () => {
						error.response.data = JSON.parse(reader.result);
						notificationError(error.response.data);
						resolve(Promise.reject(error));
					};
					reader.onerror = () => {
						reject(error);
					};
					reader.readAsText(error.response.data);
				});
			} else if (
				error.response &&
				error.response.data &&
				error.response.data.errors
			) {
				const er = error.response.data.errors[0];
				params.message =
					er.defaultMessage + ': ' + er.field + ' - ' + er.objectName;
				notificationError(params);
			} else if (
				error.response &&
				((error.response.data &&
					!error.response.data.status &&
					!error.response.data.code) ||
					!error.response.data)
			) {
				notificationError();
			}
		} else {
			if (
				error.response &&
				error.response.data &&
				error.response.data.status === 401 &&
				error.response.data.message === 'User is disabled'
			) {
				params.message = 'Usuario inactivo'; //Mensaje para cuando el usuario corresponde a una persona no existente en DB
				notificationError(params);
			} else if (
				error.response &&
				error.response.data &&
				error.response.data.status === 401 &&
				error.response.data.message === 'User does not have permissions'
			) {
				params.message = 'No esta autorizado';
				notificationError(params);
			} else if (
				error.response &&
				error.response.data &&
				error.response.data.status === 401
			) {
				params.message = 'Su usuario o contraseña no es correcta';
				notificationError(params);
			}
			if (
				error.code !== 'ECONNABORTED' &&
				error.hasOwnProperty('response') &&
				!error.response
			) {
				params.message = 'Error de conexión';
				notificationError(params);
			} else if (
				error.response &&
				((error.response.data && !error.response.data.status) ||
					!error.response.data)
			)
				notificationError();
		}
		return Promise.reject(error);
	}
);

export default networkService;
