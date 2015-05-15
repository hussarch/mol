package com.hussar.app.mol.dao.manager;

import com.hussar.app.mol.model.ManagerEntity;
import com.hussar.app.mol.model.UserEntity;
import com.hussar.framework.dao.GenericDao;


/**
 * @OrganizationDao.java
 * @author XiaoYi(hussarch@126.com)
 * Created on 2015-5-1, ©2015 some rights reserved
 */
public interface ManagerDao extends GenericDao<ManagerEntity> {
	ManagerEntity getEntity(UserEntity user);
}
