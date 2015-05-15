package com.hussar.app.mol.service.org;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hussar.app.mol.dao.org.OrganizationDao;
import com.hussar.app.mol.model.OrganizationEntity;
import com.hussar.framework.common.domain.PagingCountBean;

/**
 * @OrganizationServiceImpl.java
 * @author XiaoYi(hussarch@126.com)
 * Created on 2015-5-1, ©2015 some rights reserved
 */
@Service("OrganizationService")
@Transactional
public class OrganizationServiceImpl implements OrganizationService {

	@Autowired
	private OrganizationDao dao;
	
	@Override
	public OrganizationEntity getEntity(int id) {
		return dao.queryById(id);
	}

	@Override
	public void add(OrganizationEntity organization) {
		dao.insert(organization);
	}

	@Override
	public void update(OrganizationEntity organization) {
		dao.update(organization);
	}

	@Override
	public void delete(int id) {
		dao.deleteById(id);
	}

	@Override
	public boolean checkExist(String fieldName, String value) {
		return dao.checkExist(fieldName, String.class, value);
	}
	
	@Override
	public boolean checkExist(String fieldName, String value, Integer id) {
		return dao.checkExist(fieldName, String.class, value, id);
	}

	@Override
	public List<OrganizationEntity> getOrganizationList() {
		return dao.queryAll();
	}

	@Override
    public List<OrganizationEntity> getOrganizationEntityList(int currentPage, int pageSize) {
	    return dao.pagedQuery(null, currentPage, pageSize);
    }

	@Override
    public PagingCountBean getPagingCountBean(int currentPage, int pageSize) {
		int totalRecordCount = dao.queryCount(null);
		return new PagingCountBean(totalRecordCount, currentPage, pageSize);
    }

}
