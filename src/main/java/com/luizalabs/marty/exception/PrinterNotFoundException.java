package com.luizalabs.marty.exception;

public class PrinterNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 4279305450080078146L;
	
	public PrinterNotFoundException(String printerName) {		
		super(printerName + " not found.");
	}


}
