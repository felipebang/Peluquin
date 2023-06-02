import React, { useEffect } from 'react';
import { withRouter } from 'react-router-dom';
import { PERFILES_DETAIL_BREADCRUMBS } from '../../../shared/constants/client';
import { CMOFF } from '../../../shared/constants/routesApp';
import ToolBar from '../../../shared/toolbar/ToolBar';
import { faArrowLeft } from '@fortawesome/free-solid-svg-icons';

const PerfilesDetail = props => {
	useEffect(() => {}, []);

	const toolbarButtons = [
		{
			label: 'Volver',
			actions: { onClick: () => props.history.push(CMOFF) },
			icon: faArrowLeft
		}
	];

	return (
		<div className='flex-row'>
			<div className='col-12'>
				<ToolBar
					breadcrumbs={PERFILES_DETAIL_BREADCRUMBS}
					buttons={toolbarButtons}
				/>
			</div>
		</div>
	);
};
export default withRouter(PerfilesDetail);
