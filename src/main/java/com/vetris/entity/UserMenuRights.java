package com.vetris.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * @author anandu
 * User menu rights entity
 */
@Entity
@Getter
@Setter
@Table(name = "user_menu_rights")
public class UserMenuRights extends AuditEntityModel{
	
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "user_id" , nullable = false , unique = true)
	String userId;
	
	@Column(name = "menu_id" , nullable = false)
	Integer menuId;

}
