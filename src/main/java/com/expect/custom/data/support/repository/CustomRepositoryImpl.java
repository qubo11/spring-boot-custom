package com.expect.custom.data.support.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import com.expect.custom.data.support.specs.CustomerSpesc;

public class CustomRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID>
		implements CustomRepository<T, ID> {

	private EntityManager entityManager;

	public CustomRepositoryImpl(Class<T> domainClass, EntityManager em) {
		super(domainClass, em);
		this.entityManager = em;
	}

	@Override
	public Page<T> findByCondition(T entity, Map<String, Object> betweenParams1, Map<String, Object> betweenParams2,
			Pageable pageable) {
		CustomerSpesc cs = new CustomerSpesc();
		return findAll(cs.conditionSearch(entityManager, entity, betweenParams1, betweenParams2), pageable);
	}

	@Override
	public Page<T> findByCondition(T entity, Pageable pageable) {
		CustomerSpesc cs = new CustomerSpesc();
		return findAll(cs.conditionSearch(entityManager, entity, null, null), pageable);
	}

	@Override
	public List<T> findByCondition(T entity) {
		CustomerSpesc cs = new CustomerSpesc();
		return findAll(cs.conditionSearch(entityManager, entity, null, null));
	}

	@Override
	public List<T> findByCondition(T entity, Map<String, Object> betweenParams1, Map<String, Object> betweenParams2) {
		CustomerSpesc cs = new CustomerSpesc();
		return findAll(cs.conditionSearch(entityManager, entity, betweenParams1, betweenParams2));
	}

}
