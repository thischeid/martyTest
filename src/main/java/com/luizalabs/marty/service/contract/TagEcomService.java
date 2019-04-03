package com.luizalabs.marty.service.contract;

import java.io.IOException;

import com.luizalabs.marty.controller.dto.TagClientRequest;
import com.luizalabs.marty.controller.dto.TagEcomRequest;
import com.luizalabs.marty.controller.dto.TagEcomResponse;
import com.luizalabs.marty.exception.PrinterException;

public interface TagEcomService {

	TagEcomResponse print(TagEcomRequest request) throws PrinterException, IOException ;	
	TagClientRequest layout(TagEcomRequest request);
}
