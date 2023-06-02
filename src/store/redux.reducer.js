import { combineReducers } from 'redux';

/** Import de todas las Acciones */
import { STATE_LOADING, STATE_TABLE_FILTER } from './redux.actions';
/** Import de todos los Reducers */
import authReducer from '../core/auth/auth.reducer';

/**Estado general de la App */
let generalState = {
	stateLoading: false
};

const generalReducer = (state = generalState, action) => {
	switch (action.type) {
		case STATE_LOADING:
			return { ...state, stateLoading: action.value };
		case STATE_TABLE_FILTER:
			return { ...state, currentFilter: action.value };
		default:
			return state;
	}
};

export default combineReducers({ authReducer, generalReducer });
