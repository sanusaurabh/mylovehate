package com.licence.emaillicence.execption;

public class EmailEntityNotFoundException extends Exception {

	public EmailEntityNotFoundException(long id) {
		super(String.format("EmailEntity is not found with id : '%s'", id));
	}

}
