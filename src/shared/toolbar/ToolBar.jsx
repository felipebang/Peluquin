import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import React, { useState } from 'react';
import {
	ButtonDropdown,
	DropdownItem,
	DropdownMenu,
	DropdownToggle
} from 'reactstrap';
import BreadcrumbPag from '../../core/layout/breadcrumb/BreadcrumbPag';
import './ToolBar.css';
import ability from '../../core/auth/ability';

const ToolBar = props => {
	const [dropOpen, setDropOpen] = useState(false);

	const handleToggle = open => {
		if (open === 'clickaway') {
			return;
		}
		setDropOpen(!dropOpen);
	};

	return (
		<div className='tool-bar rounded'>
			<div className='d-flex bd-highlight'>
				<div className='p-1 flex-grow-1 bd-highlight'>
					<BreadcrumbPag items={props.breadcrumbs} />
				</div>
				<div className='p-1 flex bd-highlight'>
					<div className='btn-group' role='group'>
						{props.buttons.map((boton, index) => {
							if (boton.children && boton.children.length > 0) {
								return (
									<ButtonDropdown
										key={'btng' + index}
										isOpen={dropOpen}
										toggle={handleToggle}>
										<DropdownToggle
											caret
											className='btn-outline-warning toolbar-button-nav rounded'>
											{boton.label}{' '}
										</DropdownToggle>
										<DropdownMenu>
											{boton.children.map((item, index) => {
												if (item.module && item.permissions) {
													for (let i = 0; i < item.permissions.length; i++) {
														if (ability.can(item.permissions[i], item.module)) {
															return (
																<DropdownItem
																	key={'ddi' + index}
																	{...item.actions}>
																	<FontAwesomeIcon icon={item.icon} />
																	{' ' + item.label}
																</DropdownItem>
															);
														}
													}
												} else {
													return (
														<DropdownItem key={'ddi' + index} {...item.actions}>
															<FontAwesomeIcon icon={item.icon} />
															{' ' + item.label}
														</DropdownItem>
													);
												}
											})}
										</DropdownMenu>
									</ButtonDropdown>
								);
							} else {
								if (boton.module && boton.permissions) {
									for (let i = 0; i < boton.permissions.length; i++) {
										if (ability.can(boton.permissions[i], boton.module)) {
											return (
												<button
													{...boton.actions}
													key={index}
													form={boton.form}
													style={{ marginRight: '2px' }}
													className='btn-outline-warning toolbar-button-nav rounded'>
													<FontAwesomeIcon
														icon={boton.icon}
														className='card-img-top'
													/>
													{' ' + boton.label}
												</button>
											);
										}
									}
								} else {
									return (
										<button
											id='buttonVolver'
											{...boton.actions}
											key={index}
											form={boton.form}
											style={{ marginRight: '2px' }}
											className='btn-outline-warning toolbar-button-nav rounded'>
											<FontAwesomeIcon
												icon={boton.icon}
												className='card-img-top'
											/>
											{' ' + boton.label}
										</button>
									);
								}
							}
						})}
					</div>
				</div>
			</div>
		</div>
	);
};
export default ToolBar;
