import React, { useEffect } from 'react';
import { withRouter } from 'react-router-dom';
import { PERFILES_HISTORICO_BREADCRUMBS } from '../../../shared/constants/client';
import ToolBar from '../../../shared/toolbar/ToolBar';
import { faArrowLeft } from '@fortawesome/free-solid-svg-icons';

const Historico = props => {
	useEffect(() => {}, []);

	const toolbarButtons = [
		{
			label: 'Volver',
			actions: { onClick: () => props.history.goBack() },
			icon: faArrowLeft
		}
	];

	return (
		<div className='flex-row'>
			<div className='col-12'>
				<ToolBar
					breadcrumbs={PERFILES_HISTORICO_BREADCRUMBS}
					buttons={toolbarButtons}
				/>
			</div>
		</div>
	);
};
export default withRouter(Historico);
