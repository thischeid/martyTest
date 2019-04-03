package com.luizalabs.marty.controller.dto.tagecom;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransportListDto implements Serializable {
	
	private static final long serialVersionUID = 6148834915834107167L;
	private Long id;
	private PrinterDto printer;
	private BranchDto branch;
	private List<Long> orders;

}
