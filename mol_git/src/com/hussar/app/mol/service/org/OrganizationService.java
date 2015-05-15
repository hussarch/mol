package com.hussar.app.mol.service.org;

import java.util.List;

import com.hussar.app.mol.model.OrganizationEntity;
import com.hussar.framework.common.domain.PagingCountBean;

/**
 * @UserService.java
 * @author XiaoYi(hussarch@126.com)
 * Created on 下午10:48:48 2014-12-22
 * ©2014, some rights reserved
 */
public interface OrganizationService {
	OrganizationEntity getEntity(int id);
	void add(OrganizationEntity organization);
	void update(OrganizationEntity organization);
	void delete(int id);
	boolean checkExist(String fieldName, String value);
	boolean checkExist(String fieldName, String value, Integer id);
	List<OrganizationEntity> getOrganizationList();
	List<OrganizationEntity> getOrganizationEntityList(int currentPage, int pageSize);
	PagingCountBean getPagingCountBean(int currentPage, int pageSize);
	
}
