package com.hussar.app.mol.service.user;

import java.util.List;

import com.hussar.app.mol.model.UserEntity;
import com.hussar.framework.common.domain.PagingCountBean;

/**
 * @UserService.java
 * @author XiaoYi(hussarch@126.com)
 * Created on 下午10:48:48 2014-12-22
 * ©2014, some rights reserved
 */
public interface UserService {
	UserEntity getUserEntity(String value);
	UserEntity getUserEntity(int id);
	void addUser(UserEntity user);
	void updateUser(UserEntity user);
	void deleteUser(int userId);
	boolean checkExist(String fieldName, Class<?> clazz, Object value, Integer id);
	List<UserEntity> getUserEntityList(String condition, int page, int pageSize);
	PagingCountBean getPagingCountBean(String condition, int currentPage, int pageSize);
}
