import React from 'react';
import {
	DASHBOARD_MODULE,
	MODULES
} from '../../components/dashboard/dashboardUtils';
import { RAIZ } from '../../shared/constants/routesApp';
import NavBarMain from './navbar-main/NavBarMain';
import NavBarLeft from './navbar-left/NavBarLeft';
import './LayoutBack.css';

const LayoutBack = props => {
	const childrenPath = props.children.props.location.pathname;
	const isParentModulePath = MODULES.find(
		module => module.link === childrenPath
	)
		? true
		: false;
	const showNavbarLeft =
		childrenPath !== DASHBOARD_MODULE.link &&
		childrenPath !== RAIZ &&
		!isParentModulePath;

	return (
		<div className='h-100'>
			<NavBarMain
				showNavbarLeft={showNavbarLeft}
				{...props.children.props.history}
			/>
			<div className='layout-back-container-0'>
				{showNavbarLeft ? <NavBarLeft {...props.children.props.history} /> : ''}
				<div className='layout-back'>
					<div className='layout-back-container-1 rounded'>
						<div className='layout-back-container-2'>{props.children}</div>
					</div>
				</div>
			</div>
		</div>
	);
};

export default LayoutBack;
