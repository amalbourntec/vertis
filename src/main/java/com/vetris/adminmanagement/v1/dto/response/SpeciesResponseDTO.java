package com.vetris.adminmanagement.v1.dto.response;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SpeciesResponseDTO {

	Integer id;
	String code;
	String name;
	String isActive;
	String createdBy;
	Date dateCreated;
	String updatedBy;
	Date dateupdated;

}
