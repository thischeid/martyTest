package com.luizalabs.marty.controller.dto;

import java.io.Serializable;
import java.util.List;

import com.luizalabs.marty.model.Orders;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TagClientRequest implements Serializable {

	private static final long serialVersionUID = 8465159633302547321L;

	// Nome da impressora
	@ApiModelProperty(notes = "Nome da Impressora", required = true)
	private String printer;
	
	// Estação de impressão
	@ApiModelProperty(notes = "Estação de impressão", required = true)
	private String station;
	
	// Lista de pedidos
	@ApiModelProperty(notes = "Lista de pedidos", required = true)
	private List<Orders> orders;
		
	
}
