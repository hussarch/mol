package com.hussar.app.mol.domain;

import java.util.ArrayList;
import java.util.List;

import com.hussar.app.mol.model.OrganizationEntity;

/**
 * @MemberNode.java
 * @author XiaoYi(hussarch@126.com) Created on 2015-6-28, ©2015 some rights reserved
 */
public class MemberNode {

	private Integer id;
	private String name;
	private MemberNodeType type;
	private List<MemberNode> children;
	private OrganizationEntity organization;
	private final static String SAPERATOR = "\n";
	
	public MemberNode(Integer id, String name, MemberNodeType type) {
		this.id = id;
		this.name = name;
		this.type = type;
	}
	
	public MemberNode(String name) {
		this.name = name;
	}

	/**
     * {
         'text' : '迈韦尼通讯技术',
         'icon' : '',
         'state' : {
           'opened' : true,
           'selected' : true
         },
         'children' : [
           { 'text' : 'Child 1' },
           'Child 2'
         ]
       }
     */
	public String toJson(){
		StringBuilder builder = new StringBuilder();
		builder.append("{");
		builder.append("'id' : ").append(this.getTid()).append(",").append(SAPERATOR);
		builder.append("'text' : '").append(this.getName()).append("',").append(SAPERATOR);
		builder.append("'type' : '").append(this.getType()).append("',").append(SAPERATOR);
		builder.append("'dbId' : '").append(this.getId()).append("',").append(SAPERATOR);
		if(children != null && children.size() > 0){
			builder.append("'children' : [").append(SAPERATOR);
			int size = children.size();
			for(int i = 0; i < size; i++){
				builder.append(children.get(i).toJson());
				if(i < size - 1){
					builder.append(",");
				}
				builder.append(SAPERATOR);
			}
			builder.append("]").append(SAPERATOR);
		}
		builder.append("}");
		return builder.toString();
	}
	
	
	private String getTid(){
		if(MemberNodeType.ORGANIZATION.equals(this.type)){
			return "'org:" + this.getId() + "'";
		}else{
			return "'user:" + this.getId() + "'";
		}
	}
	
	
	public void addChild(MemberNode child){
		if(children == null){
			children = new ArrayList<MemberNode>();
		}
		children.add(child);
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<MemberNode> getChildren() {
		return children;
	}

	public void setChildren(List<MemberNode> children) {
		this.children = children;
	}

	public MemberNodeType getType() {
		return type;
	}

	public void setType(MemberNodeType type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((children == null) ? 0 : children.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MemberNode other = (MemberNode) obj;
		if (children == null) {
			if (other.children != null)
				return false;
		} else if (!children.equals(other.children))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MemberNode [id=" + id + ", name=" + name + ", type=" + type + ", children=" + children + "]";
	}

	public OrganizationEntity getOrganization() {
		return organization;
	}

	public void setOrganization(OrganizationEntity organization) {
		this.organization = organization;
	}

}
