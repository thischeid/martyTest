package com.marty.service;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.luizalabs.marty.model.Printers;
import com.luizalabs.marty.service.contract.PrinterService;

@RunWith(SpringRunner.class)
public class PrinterServiceTest {

	@MockBean
	private PrinterService service;

	@Test
	public void shouldNotNullService() {
		Assert.assertNotNull(this.service);
	}

	@Test
	public void printServerTestSucess() {

		List<Printers> response = service.getPrinterServer();

		assertTrue(response != null);
	}

	@Test
	public void printCdTestSucess() {

		List<Printers> responseMock = new ArrayList<Printers>();
		responseMock.add(Printers.builder().id(10l).name("Impressora CD").build());

		Mockito.when(this.service.getPrinterBranch(Mockito.anyLong())).thenReturn(responseMock);

		List<Printers> response = service.getPrinterBranch(300L);

		assertTrue(!response.isEmpty());
	}

	@Test
	public void printCdTestEmpty() {
		
		Mockito.when(this.service.getPrinterBranch(Mockito.anyLong())).thenReturn( new ArrayList<Printers>());		
		List<Printers> response = service.getPrinterBranch(301L);
		assertTrue(response.isEmpty());
	}


	@Test
	public void printCdTestNull() {
		
		Mockito.when(this.service.getPrinterBranch(Mockito.anyLong())).thenReturn(null);		
		List<Printers> response = service.getPrinterBranch(301L);
		assertTrue(response==null);
	}



}
