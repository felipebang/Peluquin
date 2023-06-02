import React from 'react';
import { LOGIN, DASHBOARD } from '../../shared/constants/routesApp';
import { Redirect, Route } from 'react-router-dom';
import { isUserLoggedIn } from '../auth/auth.service';

const RouteCustom = ({
	component: Component,
	layout: Layout,
	privateRoute,
	isVerifying,
	...rest
}) => (
	<Route
		{...rest}
		render={props =>
			privateRoute === true ? (
				isUserLoggedIn() ? (
					<Layout>
						<Component {...props} />
					</Layout>
				) : (
					<Redirect to={{ pathname: LOGIN }} />
				)
			) : isUserLoggedIn() ? (
				<Redirect to={{ pathname: DASHBOARD }} />
			) : (
				<Layout>
					<Component {...props} />
				</Layout>
			)
		}
	/>
);

export default RouteCustom;
