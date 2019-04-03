package com.luizalabs.marty.controller.dto;

import java.io.Serializable;
import java.util.List;

import com.luizalabs.marty.model.Printers;

import lombok.Data;

@Data
public class PrinterResponse implements Serializable {	

	private static final long serialVersionUID = 5821028638522309197L;

	// Lista de impressoras
	private List<Printers> printers;
	
	// Mensagem
	private String message;	
	
	// Sistema
	private String system;
	
	// Status
	private boolean status;
	
	
}
