import React from 'react';
import CheckboxTree from 'react-checkbox-tree';
import 'react-checkbox-tree/lib/react-checkbox-tree.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import {
	faCheckSquare,
	faSquare,
	faPlusSquare,
	faMinusSquare
} from '@fortawesome/free-regular-svg-icons';
import {
	faChevronRight,
	faChevronDown,
	faFolder,
	faFolderOpen,
	faFile
} from '@fortawesome/free-solid-svg-icons';
import './CustomCheckTree.css';

const CustomCheckTree = props => {
	return (
		<CheckboxTree
			checked={props.checked}
			expanded={props.expanded}
			iconsClass='fa5'
			nodes={props.nodes}
			showExpandAll={props.showExpandAll}
			expandOnClick={props.expandOnClick} // Si es TRUE al dar clic sobre la etiqueta de cada nodo se expandirán los hijos
			disabled={props.disabled}
			onClick={props.onClick} // Para visualizar label, value, y más propiedad del nodo clickeado
			onCheck={props.onCheck}
			onExpand={props.onExpand}
			onlyLeafCheckboxes={props.onlyLeafCheckboxes} // Si es TRUE sólo muestra casillas en los nodos hijos
			icons={{
				check: <FontAwesomeIcon className='regular' icon={faCheckSquare} />,
				uncheck: <FontAwesomeIcon className='chck-tree' icon={faSquare} />,
				halfCheck: (
					<FontAwesomeIcon className='chck-tree' icon={faCheckSquare} />
				),
				expandClose: <FontAwesomeIcon icon={faChevronRight} />,
				expandOpen: <FontAwesomeIcon icon={faChevronDown} />,
				expandAll: <FontAwesomeIcon icon={faPlusSquare} />,
				collapseAll: <FontAwesomeIcon icon={faMinusSquare} />,
				parentClose: <FontAwesomeIcon icon={faFolder} />,
				parentOpen: <FontAwesomeIcon icon={faFolderOpen} />,
				leaf: <FontAwesomeIcon icon={faFile} />
			}}
		/>
	);
};

export default CustomCheckTree;
