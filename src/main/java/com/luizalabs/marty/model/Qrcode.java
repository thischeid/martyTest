package com.luizalabs.marty.model;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Qrcode implements Serializable {

	private static final long serialVersionUID = -6996861485209854051L;

	// Sistema
	@ApiModelProperty(notes = "Sistema")
    private String system;
    
    // Pedido
	@ApiModelProperty(notes = "Pedido")
    private Order order;
    
    // Produto
	@ApiModelProperty(notes = "Produto")
    private Product product;


}
