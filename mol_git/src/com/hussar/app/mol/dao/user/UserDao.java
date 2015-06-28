package com.hussar.app.mol.dao.user;

import java.util.List;

import com.hussar.app.mol.model.OrganizationEntity;
import com.hussar.app.mol.model.UserEntity;
import com.hussar.framework.dao.GenericDao;

public interface UserDao extends GenericDao<UserEntity>{
	
	UserEntity getUserEntity(String value);
	List<UserEntity> getUserEntityList(OrganizationEntity org);
	
}
