package com.luizalabs.marty.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.luizalabs.marty.controller.dto.TagClientRequest;
import com.luizalabs.marty.exception.PrinterException;
import com.luizalabs.marty.model.Orders;
import com.luizalabs.marty.model.Printer;
import com.luizalabs.marty.model.Printers;
import com.luizalabs.marty.repository.wis.contract.PrinterRepository;
import com.luizalabs.marty.service.contract.PrinterService;
import com.luizalabs.marty.util.PrinterTagConfig;

@Service
public class PrinterServiceImpl implements PrinterService {

	@Autowired
	private PrinterRepository printerRepository;

	public List<Printers> getPrinterServer() {

		PrintService[] services = null;

		// Lista impressoras instaladas
		services = PrintServiceLookup.lookupPrintServices(null, null);

		List<Printers> list = new ArrayList<>();

		for (PrintService ps : services) {

			Printers print = new Printers();
			print.setId(null);
			print.setName(ps.getName());

			list.add(print);
		}

		return list;
	}

	public List<Printers> getPrinterBranch(Long branchId) {

		branchId = branchId + 1000;
		List<Printer> listPrinter = printerRepository.findAllByCompany(branchId, "S");

		List<Printers> list = new ArrayList<>();

		for (Printer ps : listPrinter) {

			Printers print = new Printers();
			print.setId(ps.getId());
			print.setName(ps.getName());

			list.add(print);
		}

		return list;
	}

	public boolean printTagClient(PrintService printer, TagClientRequest dto) throws PrinterException, IOException {

		

		try {

			if (dto.getOrders() == null || dto.getOrders().isEmpty()) {
				throw new PrinterException("Não existe pedidos para impressão.");
			}

			// Configurações do template - Pedidos
			for (Orders order : dto.getOrders()) {

				// Volume
				for (int i = 0; i < order.getVolume(); i++) {

					String text = "^XA" + "^MMT" + "^LL0559" + "^PW799" + "^LS0" + "^FT252,40" + "^A0N,23,24" + "^FH\\"
							+ "^FD" + order.getRoute() + "^FS" + "^FT550,372" + "^A0N,17,16" + "^FH\\" + "^FD"
							+ "Filial de Entrega" + "^FS" + "^FT680,372" + "^A0N,17,16" + "^FH\\" + "^FD"
							+ "Porta / Doca" + "^FS" + "^FT360,399" + "^A0N,60,80" + "^FH\\" + "^FD" + "^FS"
							+ "^FT12,40" + "^A0N,23,24" + "^FH\\" + "^FD" + "MAGAZINE LUIZA/ROTA:" + "^FS"
							+ "^FO540,352" + "^GB0,55,2" + "^FS" + "^FO670,352" + "^GB0,55,2" + "^FS" + "^FO350,295"
							+ "^GB0,55,2" + "^FS" + "^FO480,295" + "^GB0,55,2" + "^FS" + "^FO350,352" + "^GB0,55,2"
							+ "^FS" + "^FO220,295" + "^GB0,113,2" + "^FS" + "^FT515,542" + "^A0N,23,24" + "^FH\\"
							+ "^FD" + "^FS" + "^FT397,312" + "^A0N,17,16" + "^FH\\" + "^FD" + "Lote" + "^FS"
							+ "^FT325,542" + "^A0N,23,24" + "^FH\\" + "^FD" + "^FS" + "^FO455,57" + "^GB0,55,1" + "^FS"
							+ "^FO658,55" + "^GB0,55,1" + "^FS" + "^FO543,55" + "^GB0,55,2" + "^FS" + "^FT554,399"
							+ "^A0N,27,34" + "^FH\\" + "^FD" + order.getDeliverySubsidiary() + "^FS" + "^FT680,399"
							+ "^A0N,27,34" + "^FH\\" + "^FD" + order.getDoor() + "^FS" + "^FT225,399" + "^A0N,27,34"
							+ "^FH\\" + "^FD" + order.getSalesSubsidiary() + "^FS" + "^FT676,105" + "^A0N,25,24"
							+ "^FH\\" + "^FD" + order.getRequestDate() + "^FS" + "^FT550,105" + "^A0N,25,24" + "^FH\\"
							+ "^FD" + order.getDeliveryDate() + "^FS" + "^FT669,74" + "^A0N,17,16" + "^FH\\" + "^FD"
							+ "Data Pedido" + "^FS" + "^FT550,74" + "^A0N,17,16" + "^FH\\" + "^FD" + "Data de entrega"
							+ "^FS" + "^FT9,372" + "^A0N,17,16" + "^FH\\" + "^FD" + "Pedido" + "^FS" + "^FT225,372"
							+ "^A0N,17,16" + "^FH\\" + "^FD" + "Filial de Venda" + "^FS" + "^FT461,74" + "^A0N,17,16"
							+ "^FH\\" + "^FD\r\n" + "Embalagem" + "^FS" + "^FT225,312" + "^A0N,18,16" + "^FH\\" + "^FD"
							+ "Volume Pedido" + "^FS" + "^FO0,2" + "^GB797,0,2" + "^FS" + "^FO0,55" + "^GB797,0,2"
							+ "^FS" + "^FO0,112" + "^GB797,0,2" + "^FS" + "^FT221,346" + "^A0N,27,34" + "^FH\\" + "^FD"
							+ String.format("%03d", i + 1) + "/" + String.format("%03d", order.getVolume()) + "^FS"
							+ "^FO1,204" + "^GB600,0,3" + "^FS" + "^FO1,408" + "^GB796,0,3" + "^FS" + "^FO1,350"
							+ "^GB796,0,2" + "^FS" + "^FO1,291" + "^GB796,0,3" + "^FS" + "^FT180,521" + "^A0N,28,28"
							+ "^FH\\" + "^FD" + order.getBarcode() + "^FS" + "^FT9,405" + "^A0N,28,28" + "^FH\\" + "^FD"
							+ order.getId() + "^FS" + "^FT500,312" + "^A0N,17,16" + "^FH\\" + "^FD" + "IMEI" + "^FS"
							+ "^FT9,250" + "^A0N,24,24" + "^FH\\" + "^FD" + order.getProductDescription() + "^FS"
							+ "^FT9,275" + "^A0N,24,24" + "^FH\\" + "^FD" + "^FS" + "^FT9,312" + "^A0N,17,16" + "^FH\\"
							+ "^FD" + "Codigo Produto" + "^FS" + "^FT357,346" + "^A0N,27,34" + "^FH\\" + "^FD"
							+ order.getLot() + "^FS" + "^FT500,346" + "^A0N,27,34" + "^FH\\" + "^FD"
							+ (order.getImei() == null ? "" : order.getImei()) + "^FS" + "^FT9,346" + "^A0N,31,31"
							+ "^FH\\" + "^FD" + order.getProductCode() + "^FS" + "^FT9,163" + "^A0N,25,24" + "^FH\\"
							+ "^FD" + order.getAddress() + "^FS" + "^FT9,195" + "^A0N,25,24" + "^FH\\" + "^FD"
							+ order.getCep() + "^FS" + "^FT9,223" + "^A0N,17,16" + "^FH\\" + "^FD" + "Descricao Produto"
							+ "^FS" + "^FT9,133" + "^A0N,17,16" + "^FH\\" + "^FD" + "Endereco" + "^FS" + "^FT470,106"
							+ "^A0N,34,33" + "^FH\\" + "^FD" + (order.getPacking() == null ? "" : order.getPacking())
							+ "^FS" + "^FT9,74" + "^A0N,17,16" + "^FH\\" + "^FD" + "Cliente" + "^FS" + "^FT9,105"
							+ "^A0N,25,24" + "^FH\\" + "^FD" + order.getNameClient() + "^FS" + "^FT637,290" +
							// "^BQN,4,3" +
							"^FD" +
							// "LA,{\"system\":
							// \"WIS\",\"order\":{\"id\":\"452091147\",\"modal\":\"VND\",\"volume\":{\"number\":1,\"quantity\":1}},\"product\":{\"id\":\"4809456\",\"barcode\":\"07891129232891\"}}"
							// +
							"^FS" + "^BY2,3,64" + "^FT30,488" + "^BCN,,N,N" + "^FD" + order.getBarcode() + "^FS"
							+ "^FT15,542" + "^A0N,23,24" + "^FH\\" + "^FD" + "EAN:" + order.getEan() + "^FS"
							+ "^PQ1,0,1,Y" + "^XZ";
					
					try (InputStream input = new ByteArrayInputStream(text.getBytes()))
					{
						// Imprime
						PrinterTagConfig.printOut(printer, input);
					}
				}
			}

		} catch (PrintException | PrinterException ex) {
			throw new PrinterException(ex.getMessage());
		}
		return true;
	}
}
