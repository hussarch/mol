package com.hussar.framework.dao;

import java.util.List;

import com.hussar.framework.entity.BaseEntity;

public interface GenericDao<T extends BaseEntity> {

	void insert(T t);
	void delete(T t);
	void update(T t);
	T queryById(int id);
	void deleteById(int id);
	T queryByAttributeValue(String name, String value);
	List<T> queryAll();
	List<T> pagedQuery(String condition, int currentPage, final int pageSize);
	public abstract boolean checkExist(String fieldName, Class<?> clazz, Object value, int id);
	public abstract boolean checkExist(String fieldName, Class<?> clazz, Object value);
	int queryCount(String condition);

}