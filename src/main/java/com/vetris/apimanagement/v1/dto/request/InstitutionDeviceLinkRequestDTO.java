package com.vetris.apimanagement.v1.dto.request;

import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;

/**
 * Institution Device Link Request DTO class
 * 
 * @author Aldrin
 */

@Getter
@Setter
@Validated
public class InstitutionDeviceLinkRequestDTO {

	@Size(max = 200, message = "institution id  must be atmost 200 characters")
	@NotNull
	private String institutionId;

	@Size(max = 200, message = "manufacturer  must be atmost 200 characters")
	@NotNull
	private String manufacturer;

	@Size(max = 200, message = "model  must be atmost 200 characters")
	private String model;

	@Size(max = 20, message = "serial no  must be atmost 20 characters")
	private String serialNo;

	@Size(max = 50, message = "modality ae title  must be atmost 50 characters")
	private String modalityAeTitle;

	@Size(max = 30, message = "modality  must be atmost 30 characters")
	private String modality;

	@Size(max = 10, message = "weight uom  must be atmost 10 characters")
	private String weightUom;

}
