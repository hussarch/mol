package com.hussar.app.mol.dao.user;

import com.hussar.app.mol.model.UserEntity;
import com.hussar.framework.dao.GenericDao;

public interface UserDao extends GenericDao<UserEntity>{
	
	UserEntity getUserEntity(String value);
	
}
