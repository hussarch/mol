package com.hussar.app.mol.dao.user;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.hussar.app.mol.model.UserEntity;
import com.hussar.framework.dao.GenericDaoImpl;

/**
 * @UserDaoImpl.java
 * @author XiaoYi(hussarch@126.com)
 * Created on 下午10:08:36 2014-12-22
 * ©2014, some rights reserved
 */
@Repository("UserDao")
public class UserDaoImpl extends GenericDaoImpl<UserEntity> implements UserDao {

	@Override
	public UserEntity getUserEntity(String value) {
		StringBuilder hql = new StringBuilder();
		hql.append("from UserEntity where name = ? or employeeId  = ? or email = ? or skypeId = ? or mobileNumber = ? ");
		Query query = getCurrentSession().createQuery(hql.toString());
		int i = 0;
		query.setString(i++, value);
		query.setInteger(i++, getValue(value));
		query.setString(i++, value);
		query.setString(i++, value);
		query.setString(i++, value);
        List<?> list = query.list();
		if(list == null || list.size() == 0){
			return null;
		}else{
			return (UserEntity) list.get(0);
		}
	}
	
	private int getValue(String value){
		if(StringUtils.isNumeric(value)){
			if(value.length() < 9){
				return Integer.parseInt(value);
			}
		}
		return -1;
	}

}
