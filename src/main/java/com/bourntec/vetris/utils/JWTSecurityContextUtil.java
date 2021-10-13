package com.bourntec.vetris.utils;

import java.util.Date;

import org.springframework.stereotype.Component;

/**
 * @author Donepudi Suresh
 *
 */
@Component
public class JWTSecurityContextUtil {
	

	private String id;
	private Date currentDate;
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the currentDate
	 */
	public Date getCurrentDate() {
		currentDate = new Date();
		return currentDate;
	}
}
