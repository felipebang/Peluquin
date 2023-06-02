export const LOGOUT = 'LOGOUT';
export const LOGIN_SUCCESS = 'LOGIN_SUCCESS';

export const logOut = () => {
	return {
		type: LOGOUT
	};
};

export const receiveLogin = user => {
	return {
		type: LOGIN_SUCCESS,
		user: user
	};
};
