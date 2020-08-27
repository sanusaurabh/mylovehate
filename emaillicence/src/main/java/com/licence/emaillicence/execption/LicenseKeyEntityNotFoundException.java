package com.licence.emaillicence.execption;

public class LicenseKeyEntityNotFoundException extends Exception {
	public LicenseKeyEntityNotFoundException(long id) {
		super(String.format("LicenseKeyEntity is not found with id : '%s'", id));
	}
}
