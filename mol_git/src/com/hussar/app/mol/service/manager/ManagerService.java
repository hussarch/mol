package com.hussar.app.mol.service.manager;

import java.util.List;

import com.hussar.app.mol.model.ManagerEntity;
import com.hussar.app.mol.model.UserEntity;
import com.hussar.framework.common.domain.PagingCountBean;

/**
 * @UserService.java
 * @author XiaoYi(hussarch@126.com)
 * Created on 下午10:48:48 2014-12-22
 * ©2014, some rights reserved
 */
public interface ManagerService {
	ManagerEntity getEntity(int id);
	ManagerEntity getEntity(UserEntity user);
	void add(ManagerEntity organization);
	void update(ManagerEntity organization);
	void delete(int id);
	boolean checkExist(String fieldName, String name);
	boolean checkExist(String fieldName, String name, Integer id);
	List<ManagerEntity> getManagerList();
	List<ManagerEntity> getManagerEntityList(int currentPage, int pageSize);
	PagingCountBean getPagingCountBean(int currentPage, int pageSize);
	
}
