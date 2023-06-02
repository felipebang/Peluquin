import { faPowerOff } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import React, { useState } from 'react';
import {
	ButtonDropdown,
	DropdownItem,
	DropdownMenu,
	DropdownToggle
} from 'reactstrap';
import { getLoggedUser, userLogOut } from '../../auth/auth.service';
import './UserStatus.css';

const UserStatus = history => {
	const [dropdownOpen, setDropdownOpen] = useState(false);
	const userLogged = getLoggedUser();
	const userLoggedRoles = getLoggedUser() ? getLoggedUser().roles : [];

	function toogleUserStatusDD() {
		setDropdownOpen(!dropdownOpen);
	}

	/* function handleProfile(r) {
		setDropdownOpen(false);
		//history.replace(generatePath(ADM_USUARIOS_EDIT, { id: userLogged.id }));
		window.location.reload();
	} */

	return (
		<div className='user-status float-right'>
			<span id='headeruser' className='header__user'>
				<ButtonDropdown isOpen={dropdownOpen} toggle={toogleUserStatusDD}>
					{userLogged && userLogged.persona ? (
						<DropdownToggle tag='span'>
							{userLogged.persona.nombres[0].toUpperCase() +
								userLogged.persona.apellido1[0].toUpperCase()}
						</DropdownToggle>
					) : (
						<DropdownToggle tag='span'>SU</DropdownToggle>
					)}

					<DropdownMenu right>
						{/* {userLogged.id &&
						userLoggedRoles[0] &&
						userLoggedRoles[0].authority === 'ROLE_ADM' ? (
							<DropdownItem onClick={handleProfile}>
								<FontAwesomeIcon icon={faUser} />
								{' Perfil'}
							</DropdownItem>
						) : (
							''
						)} */}
						{userLogged.id &&
						userLoggedRoles[0] &&
						userLoggedRoles[0].authority === 'ROLE_ADM' ? (
							<DropdownItem divider />
						) : (
							''
						)}
						<DropdownItem
							onClick={() => {
								window.location.reload();
								userLogOut();
							}}>
							<FontAwesomeIcon icon={faPowerOff} />
							{' Salir'}
						</DropdownItem>
					</DropdownMenu>
				</ButtonDropdown>
			</span>
		</div>
	);
};
export default UserStatus;
