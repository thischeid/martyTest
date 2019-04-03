/**
 * 
 */
package com.luizalabs.marty.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.luizalabs.marty.controller.dto.TagEcomRequest;
import com.luizalabs.marty.controller.dto.TagEcomResponse;
import com.luizalabs.marty.exception.PrinterNotFoundException;
import com.luizalabs.marty.service.contract.TagEcomService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/tag/ecom/")
public class TagEComController {

	@Autowired 
	private TagEcomService service;
	


	public void setService(TagEcomService service) {
		this.service = service;
	}
	
	@PostMapping(value = "/print", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "Imprimir etiqueta ECom")
	public ResponseEntity<TagEcomResponse> print(@RequestBody TagEcomRequest request)  {
		
		TagEcomResponse response = new TagEcomResponse();
		
		if(request == null) {			
			response.setMessage("Parâmetro de entrada inválido.");			
			return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
		}			
		
		try 
		{
			response = service.print(request);
			
			if (response.getErrorTranportList()!=null && !response.getErrorTranportList().isEmpty())
				return new ResponseEntity<>(response, HttpStatus.SERVICE_UNAVAILABLE);
			
		} catch (PrinterNotFoundException | CannotGetJdbcConnectionException ex )
		{
			return  new ResponseEntity<>( HttpStatus.SERVICE_UNAVAILABLE);
		}
		catch (Exception ex)
		{
			response.setMessage(ex.getMessage());
			return  new ResponseEntity<>( response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return  new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
	}
	
}
