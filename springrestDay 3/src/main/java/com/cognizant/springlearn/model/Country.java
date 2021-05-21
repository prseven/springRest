package com.cognizant.springlearn.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Country {

	private static final Logger LOGGER = LoggerFactory.getLogger(Country.class);

	// Validations
	@NotNull
	@Size(min=2, max=2, message="Country code should be 2 charcaters")
	private String code;
	private String name;

	private Country() {
		LOGGER.debug("Inside Country Constructor.");
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Country [code=" + code + ", name=" + name + "]";
	}

}
