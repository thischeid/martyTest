package com.marty.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.luizalabs.marty.controller.PrinterController;
import com.luizalabs.marty.controller.dto.PrinterResponse;
import com.luizalabs.marty.model.Printers;
import com.luizalabs.marty.service.contract.PrinterService;

@RunWith(SpringRunner.class)
public class PrinterControllerTest {	
		
	@MockBean
	private PrinterService service;
	
	private List<Printers> GeneratePrint(String mode) {
		
		List<Printers> list = new ArrayList<Printers>();
		
		for (int i = 0; i < 5; i++){
           
			// Servidor
			if(mode == "S") {
				
				Printers print = new Printers();
				print.setId(null);
				print.setName("Zebra" + i++);		
				list.add(print);				
			} 
			// Database
			else {
								
				Printers print = new Printers();
				print.setId(1L + i);
				print.setName("Zebra" + i++);		
				list.add(print);					
			}
        }
		
		return list;
	}		
    
	@Test
	public void printServerTestSucess() {
		
		Mockito.when(service.getPrinterServer()).thenReturn(GeneratePrint("S"));
		
		PrinterController controller = new PrinterController(service);
		
		ResponseEntity<PrinterResponse> response = (ResponseEntity<PrinterResponse>) controller.prints();
		
		assertEquals(response.toString(), 200, response.getStatusCodeValue());
	}
	
	@Test
	public void printCdTestValid() {
		
		
		Mockito.when(service.getPrinterBranch(113L)).thenReturn(GeneratePrint("D"));
		
		PrinterController controller = new PrinterController(service);
		
		ResponseEntity<PrinterResponse> response = (ResponseEntity<PrinterResponse>) controller.prints(null);
		
		assertEquals(response.toString(), 403, response.getStatusCodeValue());
	}
	
	@Test
	public void printCdTestValidZero() {
		
		
		Mockito.when(service.getPrinterBranch(0L)).thenReturn(GeneratePrint("D"));
		
		PrinterController controller = new PrinterController(service);
		
		ResponseEntity<PrinterResponse> response = (ResponseEntity<PrinterResponse>) controller.prints(0L);
		
		assertEquals(response.toString(), 403, response.getStatusCodeValue());
	}
	
	@Test
	public void printCdTestSucess() {
		
		Mockito.when(service.getPrinterBranch(0L)).thenReturn(GeneratePrint("D"));
		
		PrinterController controller = new PrinterController(service);
		
		ResponseEntity<PrinterResponse> response = (ResponseEntity<PrinterResponse>) controller.prints(300L);
		
		assertEquals(response.toString(), 200, response.getStatusCodeValue());
	}
	

}
