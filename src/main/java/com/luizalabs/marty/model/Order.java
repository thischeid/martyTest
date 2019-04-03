package com.luizalabs.marty.model;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Order implements Serializable {

	private static final long serialVersionUID = 7102948583875185291L;

	// Id do pedido
	@ApiModelProperty(notes = "Id do pedido")
    private Long id;
		
    // Modalidade
	@ApiModelProperty(notes = "Modalidade")
 	private String modal;
 	
    // Volume
	@ApiModelProperty(notes = "Volume")
  	private Volume volume;


}
