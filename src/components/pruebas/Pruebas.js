import React from 'react';
import { withRouter } from 'react-router-dom';

const Pruebas = () => {
	const handlePrueba = () => {};
	const handlePrueba2 = () => {};

	return (
		<div className='col-12'>
			<button className='btn btn-danger' onClick={handlePrueba}>
				Prueba AXIOS
			</button>
			<button className='btn btn-warning' onClick={handlePrueba2}>
				Prueba FETCH
			</button>
		</div>
	);
};
// export default Pruebas;
export default withRouter(Pruebas);
