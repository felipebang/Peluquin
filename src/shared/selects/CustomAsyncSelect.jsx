import React from 'react';
import AsyncSelect from 'react-select/async';

const CustomAsyncSelect = props => {
	const customPlaceholder = <div>Seleccionar...</div>;

	return (
		<AsyncSelect
			name={props.name}
			isClearable={true}
			isDisabled={props.isDisabled}
			cacheOptions
			defaultOptions
			value={props.value}
			onChange={props.onChange}
			loadOptions={props.loadOptions}
			placeholder={customPlaceholder}
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
export default CustomAsyncSelect;
