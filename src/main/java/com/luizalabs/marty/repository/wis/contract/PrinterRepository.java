package com.luizalabs.marty.repository.wis.contract;

import java.util.List;

import com.luizalabs.marty.model.Printer;

public interface PrinterRepository {

	List<Printer> findAllByCompany(Long company, String printerColetor);

}
