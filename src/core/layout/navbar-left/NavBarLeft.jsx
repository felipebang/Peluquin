import { PERMISOS } from '../../../shared/constants/permisos';
import React, { useState } from 'react';
import Can from '../../auth/can';
import { ButtonGroup } from 'reactstrap';
import './NavBarLeft.css';
import {
	MODULES,
	DASHBOARD_MODULE
} from '../../../components/dashboard/dashboardUtils';

const NavBarLeft = history => {
	const [modulos, setModulos] = useState(MODULES);
	const [isParentModules, setIsParentModules] = useState(true);

	return (
		<div className='bg-primary navbar-lef'>
			<ButtonGroup vertical>
				<label
					id='label-navbar-mod'
					className='pt-4 text-light text-center label-navbar font-weight-bold'>
					Módulos
				</label>
				{modulos.map((modulo, index) => {
					return (
						<Can I={PERMISOS.lectura} a={modulo.codigo} key={index}>
							<img
								id={modulo.label}
								src={modulo.srcNavLeft}
								className='icon-nav-left'
								alt='Logo'
								title={modulo.label}
								onClick={() => {
									if (modulo.hasOwnProperty('modules') && modulo.modules) {
										setIsParentModules(false);
										setModulos(modulo.modules);
									} else {
										history.push(modulo.link);
									}
								}}
							/>
						</Can>
					);
				})}
				{!isParentModules ? (
					<label className='pt-4 text-light text-center label-navbar font-weight-bold'>
						Atrás
					</label>
				) : (
					''
				)}
				{!isParentModules ? (
					<img
						id={DASHBOARD_MODULE.label}
						src={DASHBOARD_MODULE.srcNavLeft}
						className='icon-nav-left'
						alt='Logo'
						title={DASHBOARD_MODULE.label}
						onClick={() => {
							setIsParentModules(true);
							setModulos(MODULES);
						}}
					/>
				) : (
					''
				)}
			</ButtonGroup>
		</div>
	);
};

export default NavBarLeft;
