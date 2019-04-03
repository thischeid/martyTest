package com.luizalabs.marty.service.contract;

import java.io.IOException;
import java.util.List;

import javax.print.PrintService;

import com.luizalabs.marty.controller.dto.TagClientRequest;
import com.luizalabs.marty.exception.PrinterException;
import com.luizalabs.marty.model.Printers;

public interface PrinterService {

	List<Printers> getPrinterServer();

	List<Printers> getPrinterBranch(Long branchId);

	boolean printTagClient(PrintService printer, TagClientRequest dto) throws PrinterException, IOException;
}
