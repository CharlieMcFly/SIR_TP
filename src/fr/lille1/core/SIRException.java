package fr.lille1.core;

import java.io.IOException;

public class SIRException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public SIRException(String message, IOException e) {
		super(message, e);
	}

}
