import networkService from '../services/networkService';
import { LOGIN } from '../../shared/constants/routesApp';
import store from '../../store/redux.store';
import { receiveTableFilter } from '../../store/redux.actions';
import { logOut } from './auth.actions';

export const executeJwtAuthenticationService = (usuario, clave) => {
	console.log(LOGIN);
	return networkService.post(LOGIN, { username: usuario, password: clave });
};

export const getJwtToken = () => {
	const user = store.getState().authReducer.user;
	return user ? 'Bearer ' + user.accessToken : null;
};

export const getLoggedUser = () => {
	return store.getState().authReducer.user;
};

export const isUserLoggedIn = () => {
	return store.getState().authReducer.isLoggingIn;
};

export const userLogOut = () => {
	store.dispatch(receiveTableFilter(null));
	store.dispatch(logOut());
};
