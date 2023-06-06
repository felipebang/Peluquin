package com.indra.cmoff.repository.custom;

import org.springframework.data.domain.Page;

import com.indra.cmoff.dto.BaseDTO;

import java.util.List;
import java.util.Optional;

public interface ICrudDAO <T extends BaseDTO> {

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the optional
	 */
	Optional<T> findById(final long id);
	
	/**
	 * Find all.
	 *
	 * @return the list
	 */
	List<T> findAll();

	/**
	 * Find all paginated.
	 *
	 * @param page the page
	 * @param size the size
	 * @return the list
	 */
	Page<T> findAllPaginated(int page, int size);

	/**
	 * Filter paginated.
	 *
	 * @param page      the page
	 * @param size      the size
	 * @param entity    the entity
	 * @param column    the column
	 * @param orderType the order type
	 * @return the list
	 */
	
	
	/**
	 * Filter paginated.
	 *
	 * @param page      the page
	 * @param size      the size
	 * @param entity    the entity
	 * @param column    the column
	 * @param orderType the order type
	 * @return the page
	 */
	Page<T> filterPaginated(int page, int size, T entity, String column, String orderType);
	/**
	/**
	 * Count filter.
	 *
	 * @param entity the entity
	 * @return the long
	 */
	long countFilter(T entity);

	/**
	 * Creates the.
	 *
	 * @param entity the entity
	 * @return the t
	 */
	T save(final T entity);
	List<T> saveList(final List<T> entities);
	/**
	 * Delete.
	 *
	 * @param entity the entity
	 */
	void delete(final T entity);

	/**
	 * Delete by id.
	 *
	 * @param entityId the entity id

	/**
	 * Save list.
	 *
	 * @param list the entity
	 * @return the list
	 */
	void deleteById(final long entityId);
}
