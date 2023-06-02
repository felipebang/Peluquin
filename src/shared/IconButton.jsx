import React from 'react';
import { withRouter, Link } from 'react-router-dom';
import './IconButton.css';
import { Col } from 'reactstrap';

const IconButton = props => {
	return (
		<React.Fragment>
			<Col sm={{ size: 'auto' }} style={styleCol}>
				<Link to={props.link} className='card mb-4' style={myStyles}>
					<div className='card-body text-center text-break'>
						<img src={props.src} className='icon-button-dashboard' alt='Logo' />
						<p className='card-text'>{props.text}</p>
					</div>
				</Link>
			</Col>
		</React.Fragment>
	);
};

//Styles
const myStyles = {
	width: '12rem',
	//width: '11.5625rem',
	borderRadius: '8px !important'
};

const styleCol = {
	display: 'flex'
};

export default withRouter(IconButton);
