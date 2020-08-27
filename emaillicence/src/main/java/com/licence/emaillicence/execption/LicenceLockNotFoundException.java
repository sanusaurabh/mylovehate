package com.licence.emaillicence.execption;

public class LicenceLockNotFoundException extends Exception {
	public LicenceLockNotFoundException(long id) {
		super(String.format("LicenseKeyEntity is not found with id : '%s'", id));
	}
}
