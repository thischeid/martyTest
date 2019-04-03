package com.luizalabs.marty.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class Volume implements Serializable {

	private static final long serialVersionUID = 866913898099609681L;

	// Número
    private Integer number;
    
    // Quantidade
    private Integer quantity;


	
}
