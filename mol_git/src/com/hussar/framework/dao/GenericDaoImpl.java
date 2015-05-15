package com.hussar.framework.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.hussar.framework.entity.BaseEntity;

/**
 * @GenericDaoImpl.java
 * @author XiaoYi(hussarch@126.com) Created on 下午1:35:25 2014-8-30 ©2014, some
 *         rights reserved
 */
public class GenericDaoImpl<T extends BaseEntity> implements GenericDao<T> {

	private Class<?> entityClass;

	public GenericDaoImpl() {
		this.entityClass = getSuperClassGenricType(getClass(), 0);
	}

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void insert(T t) {
		t.setCreatedDate(new Date());
		getCurrentSession().save(t);
	}

	@Override
	public void delete(T t) {
		getCurrentSession().delete(t);
	}

	@Override
	public void update(T t) {
		t.setUpdatedDate(new Date());
		getCurrentSession().merge(t);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T queryById(int id) {
		return (T) getCurrentSession().get(entityClass, id);
	}
	
	
	public void deleteById(int id){
		Query query = getCurrentSession().createQuery("delete from " + entityClass.getSimpleName() + " where id = ?");
		query.setParameter(0, id);
		query.executeUpdate();
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public T queryByAttributeValue(String name, String value) {
		return (T) getCurrentSession().get(entityClass, value);
	}

	@Override
	public List<T> queryAll() {
		String hql = "from " + entityClass.getSimpleName();
		return queryForList(hql, null);
	}

	@SuppressWarnings("unchecked")
	protected T queryForObject(String hql, Object[] params) {
		Query query = getCurrentSession().createQuery(hql);
		setQueryParams(query, params);
		return (T) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	protected T queryForTopObject(String hql, Object[] params) {
		Query query = getCurrentSession().createQuery(hql);
		setQueryParams(query, params);
		return (T) query.setFirstResult(0).setMaxResults(1).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	protected List<T> queryForList(String hql, Object[] params) {
		Query query = getCurrentSession().createQuery(hql);
		setQueryParams(query, params);
		return query.list();
	}

	protected List<T> queryForList(final String hql, final Object[] params, final int recordNum) {
		return queryForPage(hql, params, 0, recordNum);
	}

	@SuppressWarnings("unchecked")
	protected List<T> queryForPage(final String hql, final Object[] params, int start, int size) {
		Query query = getCurrentSession().createQuery(hql);
		setQueryParams(query, params);
		query.setFirstResult(start);
		query.setMaxResults(size);
		return query.list();
	}

	protected Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	private void setQueryParams(Query query, Object[] params) {
		if (params != null && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
	}

	@SuppressWarnings("unchecked")
	public Class<Object> getSuperClassGenricType(Class<?> clazz, final int index) {
		// 返回表示此 Class 所表示的实体（类、接口、基本类型或 void）的直接超类的 Type。
		Type genType = clazz.getGenericSuperclass();
		if (!(genType instanceof ParameterizedType)) {
			return Object.class;
		}
		// 返回表示此类型实际类型参数的 Type 对象的数组。
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		if (index >= params.length || index < 0) {
			return Object.class;
		}
		if (!(params[index] instanceof Class)) {
			return Object.class;
		}
		return (Class<Object>) params[index];
	}

	@Override
    public boolean checkExist(String fieldName, Class<?> clazz, Object value) {
		Query query = getCurrentSession().createQuery("from " + entityClass.getSimpleName() + " where " + fieldName + " = ?");
		if(Integer.class.equals(clazz)){
			query.setInteger(0, Integer.parseInt(value.toString()));
		}else{
			query.setParameter(0, value);
		}
		return query.list().size() > 0;
	}
	
	@Override
    public boolean checkExist(String fieldName, Class<?> clazz, Object value, int id) {
		Query query = getCurrentSession().createQuery("from " + entityClass.getSimpleName() + " where " + fieldName + " = ? and id != ?");
		if(Integer.class.equals(clazz)){
			query.setInteger(0, Integer.valueOf(value.toString()));
		}else{
			query.setParameter(0, value);
		}
		query.setInteger(1, id);
		
		return query.list().size() > 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> pagedQuery(String condition, int currentPage, int pageSize) {
		StringBuilder sql = new StringBuilder(60);
		sql.append("from ").append(entityClass.getSimpleName());
		if(StringUtils.isNotEmpty(condition)){
			sql.append(" where ").append(condition);
		}
		Query query = getCurrentSession().createQuery(sql.toString());
		query.setFirstResult((currentPage - 1) * pageSize);
		query.setMaxResults(pageSize);
		return query.list();
	}
	
	@Override
	public int queryCount(String condition) {
		StringBuilder sql = new StringBuilder(60);
		sql.append("select count(*) from ").append(entityClass.getSimpleName());
		if(StringUtils.isNotEmpty(condition)){
			sql.append(" where ").append(condition);
		}
		Query query = getCurrentSession().createQuery(sql.toString());
		return ((Long)query.uniqueResult()).intValue();
	}
	
	

}
