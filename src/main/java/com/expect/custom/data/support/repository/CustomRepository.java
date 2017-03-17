package com.expect.custom.data.support.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface CustomRepository<T, ID extends Serializable>
		extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {

	public List<T> findByCondition(T entity);

	public List<T> findByCondition(T entity, Map<String, Object> betweenParams1, Map<String, Object> betweenParams2);

	public Page<T> findByCondition(T entity, Pageable pageable);

	public Page<T> findByCondition(T entity, Map<String, Object> betweenParams1, Map<String, Object> betweenParams2,
			Pageable pageable);

}
