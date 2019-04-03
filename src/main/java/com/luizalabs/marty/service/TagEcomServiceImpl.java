package com.luizalabs.marty.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.print.PrintService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.luizalabs.marty.controller.dto.TagClientRequest;
import com.luizalabs.marty.controller.dto.TagEcomRequest;
import com.luizalabs.marty.controller.dto.TagEcomResponse;
import com.luizalabs.marty.controller.dto.tagecom.TransportListDto;
import com.luizalabs.marty.exception.PrinterException;
import com.luizalabs.marty.model.Orders;
import com.luizalabs.marty.repository.erp.contract.TagRepository;
import com.luizalabs.marty.service.contract.PrinterService;
import com.luizalabs.marty.service.contract.TagEcomService;
import com.luizalabs.marty.util.PrinterTagConfig;

@Service
public class TagEcomServiceImpl implements TagEcomService {

	@Autowired
	private TagRepository repo;

	@Autowired
	private PrinterService printSrv;

	public void setPrintSrv(PrinterService printSrv) {
		this.printSrv = printSrv;
	}

	@Override
	public TagEcomResponse print(TagEcomRequest request) throws PrinterException, IOException {

		TagEcomResponse response = new TagEcomResponse();
		List<Long> errorTranportList = new ArrayList<>();
		
	
		TagClientRequest dto = new TagClientRequest();
		List<Orders> listaPedidos;
		for (TransportListDto item : request.getTransportLists()) {
			PrintService printer = PrinterTagConfig.detectPrinter(item.getPrinter().getName());

			listaPedidos = repo.findAllByBranchOriginAndOrderNumbers(item.getBranch().getOrigin().getId(),
					item.getOrders());
			dto.setOrders(listaPedidos);
			dto.setPrinter(item.getPrinter().getName());

			printSrv.printTagClient(printer, dto);

		}
		if (!errorTranportList.isEmpty()) {
			response.setErrorTranportList(errorTranportList);

		} 
		
		return response;
	}

	@Override
	public TagClientRequest layout(TagEcomRequest request) {

		TagClientRequest req = new TagClientRequest();
		List<Orders> listaPedidos;
		for (TransportListDto item : request.getTransportLists()) {
			listaPedidos = repo.findAllByBranchOriginAndOrderNumbers(item.getBranch().getOrigin().getId(),
					item.getOrders());
			req.setOrders(listaPedidos);

		}
		return req;
	}

}
