package com.vetris.apimanagement.v1.dto.request;

import java.io.File;
import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;

import lombok.Getter;
import lombok.Setter;

/**
 * requestDTO for institution
 * 
 * @author ShekarReddySamreddy
 * 
 */

@Getter
@Setter
@Validated
public class InstitutionRequestDTO {

	@Size(max = 5, message = "code  must be atmost 5 characters")
	private String code;

	@Size(max = 100, message = "name  must be atmost 100 characters")
	@NotNull
	private String name;

	@Size(max = 100, message = "address_1 must be atmost 100 characters")
	private String address1;

	@Size(max = 100, message = "address_2 must be atmost 100 characters")
	private String address2;

	@Size(max = 100, message = "city must be atmost 100 characters")
	private String city;

	private Integer stateId;

	private Integer countryId;

	@Size(max = 20, message = "zip must be atmost 20 characters")
	private String zip;

	@Email(message = "Invalid EmailId")
    @NotNull
	@Size(max = 50, message = "email_id must be atmost 50 characters")
	private String emailId;

	@Pattern(regexp = "^\\+\\d*$", message = "Invalid Phone Number")
	@Size(max = 13, message = "phone_no must be atmost 13 characters")
	private String phoneNo;

	@Pattern(regexp = "^\\+\\d*$", message = "Invalid Mobile Number")
	@Size(max = 13, message = "mobile_no must be atmost 13 characters")
	private String mobileNo;

	@Size(max = 100, message = "contact_person_name must be atmost 100 characters")
	private String contactPersonName;

	@Pattern(regexp = "^\\+\\d*$", message = "Invalid contactPersonMobile Number")
	@Size(max = 20, message = "contact_person_mobile must be atmost 13 characters")
	private String contactPersonMobile;

	@Size(max = 1, message = "is_active must be atmost 1 characters")
	private String isActive;

	@Size(max = 1, message = "is_new must be atmost 1 characters")
	private String isNew;

	@Size(max = 250, message = "notes must be atmost 250 characters")
	private String notes;

	private Double discountPer;

	private String discountUpdatedby;

	private Date discountUpdatedOn;

	@Size(max = 50, message = "info_source must be atmost 50 characters")
	private String infoSource;

	private Integer businessSourceID;

	@Size(max = 5, message = "old_code must be atmost 5 characters")
	private String oldCode;

	@Size(max = 250, message = "accountant_name must be atmost 250 characters")
	private String accountantName;

	private Integer patientIdSrl;

	@Size(max = 1, message = "is_online must be atmost 1 characters")
	private String isOnline;

	@Size(max = 1, message = "is_email_verified must be atmost 1 characters")
	private String isEmailVerified;

	@Size(max = 1, message = "is_mobile_verified must be atmost 1 characters")
	private String isMobileVerified;

	@Size(max = 1, message = "link_existing_bill_acct must be atmost 1 characters")
	private String linkExistingBillAcct;

	private String billingAcctId;

	@Size(max = 1, message = "format_dcm_files must be atmost 1 characters")
	private String formatDcmFiles;

	@Size(max = 1, message = "dcm_files_xfer_pacs_mode must be atmost 1 characters")
	private String dcmFilesXferPacsMode;

	@Size(max = 250, message = "study_image_manual_recieve_path must be atmost 250 characters")
	private String studyImageManualRecievePath;

	@Size(max = 1, message = "consult_applicable must be atmost 1 characters")
	private String consultApplicable;

	@Size(max = 1, message = "custom_report must be atmost 1 characters")
	private String customReport;

	private File logoImage;

	@Size(max = 20, message = "image_content_type must be atmost 20 characters")
	private String imageContentType;

	@Size(max = 1, message = "storage_applicable must be atmost 1 characters")
	private String storageApplicable;

	@Size(max = 1, message = "xfer_files_compress must be atmost 1 characters")
	private String xferFilesCompress;

	@Size(max = 100, message = "submitted_by must be atmost 100 characters")
	private String submittedBy;

	@Size(max = 100, message = "img_software_name must be atmost 100 characters")
	private String imgSoftwareName;

	@Size(max = 1, message = "fax_rpt must be atmost 1 characters")
	private String faxRpt;

	@Size(max = 30, message = "fax_no must be atmost 30 characters")
	private String faxNo;
	
	@Size(max = 1, message = "rpt_format must be atmost 1 characters")
	private String rptFormat;

}
