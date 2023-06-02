import React, { useState } from 'react';
import { Collapse, Navbar, NavbarBrand, NavbarToggler } from 'reactstrap';
import UserStatus from '../user-status/UserStatus';
import './NavbarMain.scss';
import indraNegro from '../../../img/indraNegro.png';
import { DASHBOARD } from '../../../shared/constants/routesApp';

const NavBarMain = props => {
	const [dropdownOpen, setDropdownOpen] = useState(true);

	function toogleUserStatusDD() {
		setDropdownOpen(!dropdownOpen);
	}

	return (
		<Navbar dark expand='xs' className='navbar-main'>
			<NavbarBrand href={DASHBOARD}>
				<img
					className='logo-indra-login img-fluid'
					src={indraNegro}
					alt='Indra'
				/>
			</NavbarBrand>
			<NavbarToggler onClick={toogleUserStatusDD} />
			<Collapse isOpen={dropdownOpen} navbar style={{ height: 'inherit' }}>
				<div className='flex-grow-1 right-content'>
					<UserStatus {...props} />
				</div>
			</Collapse>
		</Navbar>
	);
};

export default NavBarMain;
