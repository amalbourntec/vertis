package com.vetris.usermanagement.v1.dto.response;

import java.io.File;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/** ResponseDto for institution
 * @author ShekarReddySamreddy
 * 
 */

@Getter
@Setter
public class InstitutionResponseDTO {
	
	private String code;
	private String name;
	private String address1;
	private String address2;
	private String city;
    private Integer stateId;
	private Integer countryId;
	private String zip;
	private String emailId;
	private String phoneNo;
	private String mobileNo;
	private String contactPersonName;
	private String contactPersonMobile;
	private String isActive;
	private String isNew;
	private String notes;
    private Double discountPer;
	private String discountUpdatedby;
	private Date discountUpdatedOn;
	private String infoSource;
	private Integer businessSourceID;
	private String oldCode;
	private String accountantName;
	private String patientIdSrl;
	private String isOnline;
	private String isEmailVerified;
	private String isMobileVerified;
	private String linkExistingBillAcct;
	private String billingAcctId;
	private String formatDcmFiles;
	private String dcmFilesXferPacsMode;
	private String studyImageManualRecievePath;
	private String consultApplicable;
	private String customReport;
	private File logoImage;
	private String imageContentType;
	private String storageApplicable;
	private String xferFilesCompress;
	private String submittedBy;
	private String imageSoftwareName;
	private String faxRpt;
	private String faxNo;
	private String createdBy;
	private Date dateCreated;
	private String updateBy;
	private Date dateUpdated;

}
