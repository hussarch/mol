/**
 * 
 */
package com.hussar.app.mol.service.mr;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hussar.app.mol.dao.mr.MeetingRoomDao;
import com.hussar.app.mol.dao.sm.ScheduledMeetingDao;
import com.hussar.app.mol.domain.MemberNode;
import com.hussar.app.mol.domain.MemberNodeType;
import com.hussar.app.mol.model.MeetingRoomEntity;
import com.hussar.app.mol.model.OrganizationEntity;
import com.hussar.app.mol.model.UserEntity;
import com.hussar.app.mol.service.org.OrganizationService;
import com.hussar.app.mol.service.user.UserService;
import com.hussar.framework.common.domain.PagingCountBean;

/**
 * @author yi.xiao
 */
@Service("MeetingRoomService")
@Transactional
public class MeetingRoomServiceImpl implements MeetingRoomService {

    @Autowired
    private MeetingRoomDao dao;
    
    @Autowired
    private ScheduledMeetingDao scheduledMeetingDao;
    
    @Autowired
	private OrganizationService organizationService; 
    
    @Autowired
	private UserService userService;
    
    @Override
    public MeetingRoomEntity getEntity(int id) {
        return dao.queryById(id);
    }

    @Override
    public void add(MeetingRoomEntity room) {
        dao.insert(room);
    }

    @Override
    public void update(MeetingRoomEntity room) {
        dao.update(room);
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
    public List<MeetingRoomEntity> getMeetingRoomList() {
        return dao.queryAll();
    }

    @Override
    public List<MeetingRoomEntity> getMeetingRoomList(int currentPage, int pageSize) {
        return dao.pagedQuery(null, currentPage, pageSize);
    }

    @Override
    public PagingCountBean getPagingCountBean(int currentPage, int pageSize) {
        int totalRecordCount = dao.queryCount(null);
        return new PagingCountBean(totalRecordCount, currentPage, pageSize);
    }

	@Override
	public MemberNode getRootMemberNode() {
		MemberNode root = getOrganizationNode();
		for(MemberNode child : root.getChildren()){
			appendMemberNodeChild(child);
		}
		List<UserEntity> list = this.userService.getUserEntityList(root.getOrganization());
		list.addAll(this.userService.getUserEntityList(null));
		for(UserEntity user : list){
			root.addChild(new MemberNode(user.getId(), user.getName(), MemberNodeType.MEMBER));
		}
		return root;
	}
	
	private void appendMemberNodeChild(MemberNode memberNode){
		List<MemberNode> list = memberNode.getChildren();
		if(list != null){
			for(MemberNode node : list){
				appendMemberNodeChild(node);
			}
		}
		List<UserEntity> memberList = this.userService.getUserEntityList(memberNode.getOrganization());
		for(UserEntity user : memberList){
			memberNode.addChild(new MemberNode(user.getId(), user.getName(), MemberNodeType.MEMBER));
		}
	}
	
	private MemberNode getOrganizationNode(){
		List<OrganizationEntity> organizationList = organizationService.getOrganizationList();
		Map<Integer, MemberNode> map = new HashMap<Integer, MemberNode>();
		OrganizationEntity org = null;
		MemberNode root = null;
		while(organizationList.size() > 0){
			org = organizationList.get(0);
			if(org.getSuperOrganization() == null){
				root = new MemberNode(org.getId(), org.getName(), MemberNodeType.ORGANIZATION);
				root.setType(MemberNodeType.ORGANIZATION);
				root.setOrganization(org);
				map.put(org.getId(), root);
				organizationList.remove(0);
			}else{
				MemberNode current = new MemberNode(org.getId(), org.getFullName(), MemberNodeType.ORGANIZATION);
				current.setOrganization(org);
				map.put(current.getId(), current);
				MemberNode parent = map.get(org.getSuperOrganization().getId());
				if(parent != null){
					parent.addChild(current);
					organizationList.remove(0);
				}
			}
		}
		return root;
	}
	

	
}
