package com.luizalabs.marty.model;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Product implements Serializable {

	private static final long serialVersionUID = -5660647719128874004L;

	// Id do produto
	@ApiModelProperty(notes = "Id do produto")
    private Long id;
    
    // Código de barras do produto
	@ApiModelProperty(notes = "Código de barras do produto")
 	private String barcode;

}
