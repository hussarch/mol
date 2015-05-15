package com.hussar.app.mol.service.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hussar.app.mol.dao.manager.ManagerDao;
import com.hussar.app.mol.model.ManagerEntity;
import com.hussar.app.mol.model.UserEntity;
import com.hussar.framework.common.domain.PagingCountBean;

/**
 * @OrganizationServiceImpl.java
 * @author XiaoYi(hussarch@126.com)
 * Created on 2015-5-1, ©2015 some rights reserved
 */
@Service("ManagerService")
@Transactional
public class ManagerServiceImpl implements ManagerService {

	@Autowired
	private ManagerDao dao;
	
	@Override
	public ManagerEntity getEntity(int id) {
		return dao.queryById(id);
	}

	@Override
	public void add(ManagerEntity organization) {
		dao.insert(organization);
	}

	@Override
	public void update(ManagerEntity manager) {
		dao.update(manager);
	}

	@Override
	public void delete(int id) {
		dao.deleteById(id);
	}

	@Override
	public boolean checkExist(String fieldName, String name) {
		return dao.checkExist(fieldName, String.class, name);
	}
	
	@Override
	public boolean checkExist(String fieldName, String name, Integer id) {
		return dao.checkExist(fieldName, String.class, name, id);
	}

	@Override
	public List<ManagerEntity> getManagerList() {
		return dao.queryAll();
	}

	@Override
    public ManagerEntity getEntity(UserEntity user) {
	    return dao.getEntity(user);
    }

	@Override
    public List<ManagerEntity> getManagerEntityList(int currentPage, int pageSize) {
	    return dao.pagedQuery(null, currentPage, pageSize);
    }

	@Override
    public PagingCountBean getPagingCountBean(int currentPage, int pageSize) {
		int totalRecordCount = dao.queryCount(null);
		return new PagingCountBean(totalRecordCount, currentPage, pageSize);
    }

}
