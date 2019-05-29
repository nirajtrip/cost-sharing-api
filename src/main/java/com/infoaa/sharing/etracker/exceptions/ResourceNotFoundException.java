package com.infoaa.sharing.etracker.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends Exception{

	/**
	 *
	 */
	private static final long serialVersionUID = 4545454542L;

    public ResourceNotFoundException(String message){
        super(message);
    }

}

