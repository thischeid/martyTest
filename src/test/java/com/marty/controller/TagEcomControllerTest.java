package com.marty.controller;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.luizalabs.marty.controller.TagEComController;
import com.luizalabs.marty.controller.dto.TagEcomRequest;
import com.luizalabs.marty.controller.dto.TagEcomResponse;
import com.luizalabs.marty.controller.dto.tagecom.BranchDto;
import com.luizalabs.marty.controller.dto.tagecom.OriginDto;
import com.luizalabs.marty.controller.dto.tagecom.PrinterDto;
import com.luizalabs.marty.controller.dto.tagecom.TransportListDto;
import com.luizalabs.marty.exception.PrinterNotFoundException;
import com.luizalabs.marty.service.contract.TagEcomService;

@RunWith(SpringRunner.class)
public class TagEcomControllerTest {

	@MockBean
	private TagEcomService mockService;
	 
	@Test
	public void shouldNotNullService() {
	    Assert.assertNotNull(this.mockService);
	}
	 
	@Test
	public void printSucess() throws Exception {

		TagEcomResponse responseMock = new TagEcomResponse();
		
		List<TransportListDto> transporList = new ArrayList<TransportListDto>();
		transporList.add(TransportListDto.builder()
				.branch(BranchDto.builder().origin(OriginDto.builder().id(300).build()).build())
				.printer(new PrinterDto("Zebra")).build());

		TagEcomRequest request = new TagEcomRequest();
		request.setTransportLists(transporList);

		Mockito.when(mockService.print(request)).thenReturn(responseMock);
		 
		TagEComController controller = new TagEComController();
		controller.setService(mockService);
		
		ResponseEntity<TagEcomResponse> response = controller.print(request);
		Assert.assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatusCode().value());
	}
	
	@Test
	public void printFail() throws Exception {

		List<TransportListDto> transporList = new ArrayList<TransportListDto>();
		transporList.add(TransportListDto.builder()
				.branch(BranchDto.builder().origin(OriginDto.builder().id(300).build()).build())
				.printer(new PrinterDto("não instalada")).build());

		TagEcomRequest request = new TagEcomRequest();
		request.setTransportLists(transporList);

		Mockito.when(mockService.print(request)).thenThrow(new PrinterNotFoundException("não instalada"));
		 
		TagEComController controller = new TagEComController();
		controller.setService(mockService);
		
		ResponseEntity<TagEcomResponse> response = controller.print(request);
		Assert.assertEquals(HttpStatus.SERVICE_UNAVAILABLE.value(), response.getStatusCode().value());
	}
	
	
}
