package com.bourntec.vetris.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * Entity for UserManagement
 * @author Anandu
 *
 */


@Getter
@Setter
@Entity
@Table(name = "Users")
public class User {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "firstName")
	private String firstName;
	
	@Column(name = "lastName")
	private String lastName;
	
	@Column(name = "age")
	private Integer age;
	
	@Column(name = "Country")
	private String country;
	
	@Column(name = "city")
	private String city;
	
	@Column(name = "phoneNumber")
	private long phoneNumber;
	
	@Column(name = "emailId")
	private String emailId;

}
