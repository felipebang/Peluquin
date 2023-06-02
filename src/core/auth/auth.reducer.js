import { LOGOUT, LOGIN_SUCCESS } from './auth.actions';

const initialState = {
	isLoggingIn: false,
	isAuthenticated: false,
	user: {}
};

export default (state = initialState, action) => {
	switch (action.type) {
		case LOGIN_SUCCESS:
			return {
				...state,
				isLoggingIn: true,
				isAuthenticated: true,
				user: action.user
			};
		case LOGOUT:
			return {
				...state,
				isLoggingIn: false,
				isAuthenticated: false,
				user: null
			};
		default:
			return state;
	}
};
