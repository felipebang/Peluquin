import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import Routes from './core/routes/routes';
import * as serviceWorker from './serviceWorker';
import 'bootstrap/dist/css/bootstrap.min.css';
import './core/layout/CustomBootstrapTheme.scss';
import { Provider } from 'react-redux';
import store from './store/redux.store';

ReactDOM.render(
	<Provider store={store}>
		<Routes />
	</Provider>,

	document.getElementById('root')
);

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
