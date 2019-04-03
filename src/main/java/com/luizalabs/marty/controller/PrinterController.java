package com.luizalabs.marty.controller;

import java.util.List;

import javax.print.PrintService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.luizalabs.marty.controller.dto.PrinterResponse;
import com.luizalabs.marty.exception.PrinterNotFoundException;
import com.luizalabs.marty.model.Printers;
import com.luizalabs.marty.service.contract.PrinterService;
import com.luizalabs.marty.util.PrinterTagConfig;

import io.swagger.annotations.ApiOperation;

@RestController
public class PrinterController{
	
	@Autowired
	private PrinterService service;
	
	public PrinterController() {

	}

	public PrinterController(PrinterService service) {
		this.service = service;
	}
	
	@GetMapping(value="/printers", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "Lista de impressoras cadastradas no servidor", notes = "Lista de impressoras cadastradas no servidor", response = PrinterResponse.class)
	public ResponseEntity<PrinterResponse> prints() {		
		
		PrinterResponse response = new PrinterResponse();
		
		response.setSystem("marty");
		
		try {
			
			List<Printers> list = service.getPrinterServer();

			response.setPrinters(list);			

		} catch (Exception ex) {

			response.setMessage(ex.getMessage());
			
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}			
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping(value="/printers/{printerName}", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "Verifica a impressora cadastrada no servidor", notes = "Verifica a impressora cadastrada no servidor", response = PrinterResponse.class)
	public ResponseEntity<String> printDetect(@PathVariable("printerName") String printerName) {		
		
		try 
		{
			PrintService printer =  PrinterTagConfig.detectPrinter(printerName);
			
			if (printer==null)
				return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
			else 
				return new ResponseEntity<>("OK", HttpStatus.OK);
		} catch (PrinterNotFoundException ex)
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		 catch (Exception ex)
		{
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}
	
	
	@GetMapping(value="/printers/branches/{branchId}", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "Lista de impressoras cadastradas por CD", notes = "Lista de impressoras cadastradas por CD", response = PrinterResponse.class)
	public ResponseEntity<PrinterResponse> prints(@PathVariable("branchId")  Long branchId) {		
		
		PrinterResponse response = new PrinterResponse();
		
		if(branchId == null) {			
			response.setMessage("Parâmetro de entrada inválido.");			
			return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
		}	
		
		if(branchId <= 0) {			
			response.setMessage("Parâmetro de entrada inválido.");			
			return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
		}
				
		response.setSystem("marty");
		
		try {	
			
			List<Printers> list = service.getPrinterBranch(branchId);			
			 					
			response.setPrinters(list);			

		} catch (Exception ex) {

			response.setMessage(ex.getMessage());
			
			return new ResponseEntity<>(response, HttpStatus.SERVICE_UNAVAILABLE);
		}			
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	

	
}
