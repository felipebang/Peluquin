import React from 'react';
import Can from '../../core/auth/can';
import { PERMISOS } from '../../shared/constants/permisos';
import { withRouter } from 'react-router-dom';
import IconButton from '../../shared/IconButton';
import { DASHBOARD_MODULE, MODULES } from './dashboardUtils';
import { Container, Row } from 'reactstrap';
import { receiveTableFilter } from '../../store/redux.actions';
import store from '../../store/redux.store';

const Dashboard = props => {
	if (Array.isArray(MODULES)) {
		const location = props.location;
		const currentModules =
			location.pathname === DASHBOARD_MODULE.link
				? MODULES
				: MODULES.find(module => module.link === location.pathname).modules;
		store.dispatch(receiveTableFilter(null));
		return (
			<Container id='menuPrincipal' fluid={false}>
				<Row>
					{location.pathname !== DASHBOARD_MODULE.link ? (
						<IconButton
							src={DASHBOARD_MODULE.src}
							text={DASHBOARD_MODULE.text}
							link={DASHBOARD_MODULE.link}
						/>
					) : (
						''
					)}
					{currentModules.map((module, index) => {
						return (
							<Can I={PERMISOS.lectura} a={module.codigo} key={index}>
								<IconButton
									key={index}
									text={module.text}
									link={module.link}
									src={module.src}
								/>
							</Can>
						);
					})}
				</Row>
			</Container>
		);
	}
};
export default withRouter(Dashboard);
