import React from 'react';
import Select from 'react-select';

const CustomSelect = props => {
	const customPlaceholder = <div>Seleccionar...</div>;

	return (
		<Select
			name={props.name}
			isMulti={props.isMulti}
			isClearable={true}
			isDisabled={props.isDisabled}
			options={props.options}
			value={props.value}
			onChange={props.onChange}
			placeholder={customPlaceholder}
			className={props.isMulti ? 'basic-multi-select' : undefined}
			classNamePrefix={props.isMulti ? 'select' : undefined}
			noOptionsMessage={() => 'Sin opciones'}
			styles={{
				control: provided =>
					props.error
						? {
								...provided,
								borderColor: 'red !important'
						  }
						: provided,
				option: (styles, { isDisabled, isFocused, isSelected }) => {
					return {
						...styles,
						backgroundColor: isDisabled
							? null
							: isSelected
							? '#fbbb21'
							: isFocused
							? '#e8e8e8'
							: null
					};
				}
			}}
		/>
	);
};
export default CustomSelect;
