package com.vetris.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * @author anandu
 * User role menu rights entity
 */
@Entity
@Getter
@Setter
@Table(name = "user_role_menu_rights")
public class UserRoleMenuRights extends AuditEntityModel {

	@Id
	@Column(name = "user_role_id" , nullable = false)
	Integer userRoleId;
	
	@Column(name = "menu_id" , nullable = false)
	Integer menuId;
	
}
