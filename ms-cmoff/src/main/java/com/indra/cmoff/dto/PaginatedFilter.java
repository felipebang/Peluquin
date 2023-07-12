package com.indra.cmoff.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaginatedFilter<F extends BaseDTO> {
	F filter;
	Integer page;
	Integer sizePerPage;
	String column;
	String order;
	
}
