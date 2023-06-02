import React from 'react';
import { Breadcrumb, BreadcrumbItem } from 'reactstrap';

const BreadcrumbPag = ({ items }) => {
	if (Array.isArray(items)) {
		return (
			<Breadcrumb
				className='p-1'
				style={{ height: '100%!important' }}
				tag='nav'
				listTag='div'>
				{items.map((item, index) => {
					if (item.path) {
						return (
							<BreadcrumbItem
								key={'bl-' + index + item.label}
								tag='a'
								href={item.path}>
								{item.label}
							</BreadcrumbItem>
						);
					} else {
						return (
							<BreadcrumbItem
								key={'bl-' + index + item.label}
								active
								tag='span'>
								{item.label}
							</BreadcrumbItem>
						);
					}
				})}
			</Breadcrumb>
		);
	} else {
		return <h6>Ítems enviados a breadcrumb inválidos</h6>;
	}
};
export default BreadcrumbPag;
