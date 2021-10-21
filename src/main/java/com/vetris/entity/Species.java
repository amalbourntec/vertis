package com.vetris.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "species")
@Getter
@Setter
public class Species extends AuditEntityModel {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer id;

	@Column(name = "code", nullable = false, length = 10)
	String code;

	@Column(name = "name", nullable = false, length = 50)
	String name;

	@Column(name = "is_active", nullable = true, length = 1)
	String isActive;

}
