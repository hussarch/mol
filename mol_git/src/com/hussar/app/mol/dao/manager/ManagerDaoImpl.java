package com.hussar.app.mol.dao.manager;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.stereotype.Repository;

import com.hussar.app.mol.model.ManagerEntity;
import com.hussar.app.mol.model.UserEntity;
import com.hussar.framework.dao.GenericDaoImpl;

/**
 * @OrganizationDaoImpl.java
 * @author XiaoYi(hussarch@126.com)
 * Created on 2015-5-1, ©2015 some rights reserved
 */
@Repository("ManagerDao")
public class ManagerDaoImpl extends GenericDaoImpl<ManagerEntity> implements ManagerDao{
	
    HibernateTransactionManager tx;
    
    
    
	public ManagerEntity getEntity(UserEntity user){
		Query query = super.getCurrentSession().createQuery("from ManagerEntity where user = ?");
		query.setParameter(0, user);
		return (ManagerEntity) query.uniqueResult();
	}
}
