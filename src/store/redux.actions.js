//Constants
export const STATE_LOADING = 'STATE_LOADING';
export const STATE_TABLE_FILTER = 'STATE_TABLE_FILTER';

//Actions
export const stateLoadingAction = state => ({
	type: STATE_LOADING,
	value: state
});

export const receiveTableFilter = state => ({
	type: STATE_TABLE_FILTER,
	value: state
});