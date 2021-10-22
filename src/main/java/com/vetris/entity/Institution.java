package com.vetris.entity;

import java.io.File;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** entity for institution
 * @author ShekarReddySamreddy
 * 
 */

@Getter
@Setter
@Entity
@Table(name = "institutions")
@AllArgsConstructor
@NoArgsConstructor
public class Institution extends AuditEntityModel {

    @Id
	@Column(name = "id",length=200)
	private String id;
		
	@Column(name = "code",length = 5,nullable=true)
	private String code;
	
	@Column(name = "name",length = 100,nullable=false)
	private String name;
	
	@Column(name = "address_1",length = 100,nullable=true)
	private String address1;
	
	@Column(name = "address_2",length = 100,nullable=true)
	private String address2;
	
	@Column(name = "city",length = 100,nullable=true)
	private String city;
	
	@Column(name = "state_id",nullable=true)
	private Integer stateId;
	
	@Column(name = "country_id",nullable=true)
	private Integer countryId;
	
	@Column(name = "zip",length = 20,nullable=true)
	private String zip;
	
	@Column(name = "email_id",length = 50,nullable=true)
	private String emailId;
	
	@Column(name = "phone_no",length = 30,nullable=true)
	private String phoneNo;
	
	@Column(name = "mobile_no",length = 20,nullable=true)
	private String mobileNo;
	
	@Column(name = "contact_person_name",length = 100,nullable=true)
	private String contactPersonName;
	
	@Column(name = "contact_person_mobile",length = 20,nullable=true)
	private String contactPersonMobile;
	
	@Column(name = "is_active",length = 1,nullable=true)
	private String isActive;
	
	@Column(name = "is_new",length = 1,nullable=true)
	private String isNew;
	
	@Column(name = "notes",length = 250,nullable=true)
	private String notes;
	
	@Column(name = "discount_per",nullable=true)
	private Double discountPer;
	
	@Column(name = "discount_updated_by",nullable=true)
	private String discountUpdatedby;
	
	@Column(name = "discount_updated_on",nullable=true)
	private Date discountUpdatedOn;
		
	@Column(name = "info_source",length = 50,nullable=true)
	private String infoSource;
	
	@Column(name = "business_source_id",nullable=true)
	private Integer businessSourceID;
	
	@Column(name = "old_code",length = 5,nullable=true)
	private String oldCode;
	
	@Column(name = "accountant_name",length = 250,nullable=true)
	private String accountantName;
	
	@Column(name = "patient_id_srl",nullable=true)
	private Integer patientIdSrl;
	
	@Column(name = "is_online",length = 1,nullable=true)
	private String isOnline;
	
	@Column(name = "is_email_verified",length = 1,nullable=true)
	private String isEmailVerified;
	
	@Column(name = "is_mobile_verified",length = 1,nullable=true)
	private String isMobileVerified;
	
	@Column(name = "link_existing_bill_acct",length = 1,nullable=true)
	private String linkExistingBillAcct;
	
	@Column(name = "billing_acct_id",nullable=true)
	private String billingAcctId;
	
	@Column(name = "format_dcm_files",length = 1,nullable=true)
	private String formatDcmFiles;
	
	@Column(name = "dcm_files_xfer_pacs_mode",length = 1,nullable=true)
	private String dcmFilesXferPacsMode;
	
	@Column(name = "study_image_manual_recieve_path",length = 250,nullable=true)
	private String studyImageManualRecievePath;
	
	@Column(name = "consult_applicable",length = 1,nullable=true)
	private String consultApplicable;
	
	@Column(name = "custom_report",length = 1,nullable=true)
	private String customReport;
	
	@Column(name = "logo_image",nullable=true)
	private File logoImage;
	
	@Column(name = "image_content_type",length = 20,nullable=true)
	private String imageContentType;
	
	@Column(name = "storage_applicable",length = 1,nullable=true)
	private String storageApplicable;
	
	@Column(name = "xfer_files_compress",length = 1,nullable=true)
	private String xferFilesCompress;
	
	@Column(name = "submitted_by",length = 100,nullable=true)
	private String submittedBy;
	
	@Column(name = "img_software_name",length = 100,nullable=true)
	private String imgSoftwareName;
	
	@Column(name = "fax_rpt",length = 1,nullable=true)
	private String faxRpt;
	
	@Column(name = "fax_no",length = 30,nullable=true)
	private String faxNo;
	
	@Column(name = "rpt_format",length = 1,nullable=true)
	private String rptFormat;
	
}


