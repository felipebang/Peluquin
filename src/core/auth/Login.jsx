import { faKey, faUser } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import {
	AvFeedback,
	AvForm,
	AvGroup,
	AvInput
} from 'availity-reactstrap-validation';
import React, { useState } from 'react';
import { connect } from 'react-redux';
import {
	Button,
	Card,
	CardBody,
	CardLink,
	CardTitle,
	Col,
	Container,
	InputGroup,
	InputGroupAddon,
	InputGroupText,
	Jumbotron,
	Label,
	CardText
} from 'reactstrap';
import { receiveLogin } from './auth.actions';
import { executeJwtAuthenticationService } from './auth.service';
import { VALIDATIONS_LABELS } from '../../shared/constants/client';
import './Login.css';
import '../../img/GettyImages-539654405.jpg';
import indraVerde from '../../img/indra1.png';
import { DASHBOARD } from '../../shared/constants/routesApp';

const Login = ({ actionSuccessLogin, props }) => {
	const [loginForm, setLoginForm] = useState({});
	const handleChange = event => {
		setLoginForm({ ...loginForm, [event.target.name]: event.target.value });
	};

	const handleLogin = () => {
		const { username, password } = loginForm;
		executeJwtAuthenticationService(username, password).then(response => {
			if (response.data.accessToken) {
				actionSuccessLogin(response.data);
				props.history.push(DASHBOARD);
			}
		});
	};

	const FragmentLoginForm = (
		<AvForm onValidSubmit={handleLogin}>
			<AvGroup>
				<Label for='amount'>{'Usuario'}</Label>
				<InputGroup>
					<InputGroupAddon addonType='prepend'>
						<InputGroupText className=''>
							<FontAwesomeIcon icon={faUser} />
						</InputGroupText>
					</InputGroupAddon>
					<AvInput
						name='username'
						required
						onChange={handleChange}
						errorMessage={VALIDATIONS_LABELS.USER_INVALID}
						validate={{
							pattern: {
								value: '^[a-z0-9]+$'
							},
							required: {
								value: true
							}
						}}
					/>
					<AvFeedback>Ingrese un usuario válido</AvFeedback>
				</InputGroup>
			</AvGroup>

			<AvGroup>
				<Label for='amount'>{'Contraseña'}</Label>
				<InputGroup>
					<InputGroupAddon addonType='prepend'>
						<InputGroupText className=''>
							<FontAwesomeIcon icon={faKey} />
						</InputGroupText>
					</InputGroupAddon>
					<AvInput
						onChange={handleChange}
						name='password'
						type='password'
						errorMessage={VALIDATIONS_LABELS.PASSWORD_INVALID}
						validate={{
							required: {
								value: true
							},
							minLength: {
								value: 4
							},
							maxLength: {
								value: 16
							}
						}}
					/>
					<AvFeedback>Ingrese una contraseña válida</AvFeedback>
				</InputGroup>
			</AvGroup>

			<InputGroup className='pt-5'>
				<Button id='buttonLogin' color='primary' type='submit'>
					Ingresar
				</Button>
			</InputGroup>
		</AvForm>
	);

	return (
		<Container className='login-container' id='loginContainer' fluid={true}>
			<div className='d-flex flex-row justify-content-center justify-content-md-end justify-content-xl-center'>
				<Col className='d-none d-sm-none d-md-block align-self-center'></Col>
				<Col className='m-0 p-0 col-11 col-sm-10 col-md-6 col-lg-5 col-xl-3 left-shadow'>
					<Jumbotron className='login-form-container mb-0'>
						<Card className='center-h-v'>
							<CardTitle className='login-form-title'>
								<img src={indraVerde} className='login-logo' alt='Logo Indra' />
							</CardTitle>
							<CardBody>
								{FragmentLoginForm}
								<div className='login-footer'>
									<CardLink href='#'>Peluqueria</CardLink>
									<CardText>
										<small className='text-muted'>
											{'versión ' + process.env.REACT_APP_VERSION}
										</small>
									</CardText>
								</div>
							</CardBody>
						</Card>
					</Jumbotron>
				</Col>
			</div>
		</Container>
	);
};
const mapStateToProps = (state, props) => ({
	isAuthenticated: state.authReducer.isAuthenticated,
	props: props
});
const mapDispatchToProps = dispatch => ({
	actionSuccessLogin(result) {
		dispatch(receiveLogin(result));
	}
});
export default connect(mapStateToProps, mapDispatchToProps)(Login);
