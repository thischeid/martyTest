package com.luizalabs.marty.model;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Orders implements Serializable {

	private static final long serialVersionUID = 490520274303750624L;

	// Id do pedido
	@ApiModelProperty(notes = "Id do pedido")
	private Long id;
	
	// Rota
	@ApiModelProperty(notes = "Rota")
    private String route;	
    
    // Nome do cliente
	@ApiModelProperty(notes = "Nome do cliente")
    private String nameClient;	
	
	// Embalagem
	@ApiModelProperty(notes = "Embalagem")
	private String packing;	

	// Data da entrega
	@ApiModelProperty(notes = "Data da entrega")
	private String deliveryDate;
	
	// Data do pedido
	@ApiModelProperty(notes = "Data do pedido")
	private String requestDate;
	
	// Endereço
	@ApiModelProperty(notes = "Endereço")
	private String address;
	
	// CEP
	@ApiModelProperty(notes = "CEP")
	private String cep;
	
	// Descrição do produto
	@ApiModelProperty(notes = "Descrição do produto")
	private String productDescription;
	
	// Qrcode
	@ApiModelProperty(notes = "Qrcode")
	private Qrcode qrcode;
	
	// Código do produto
	@ApiModelProperty(notes = "Código do produto")
	private Long productCode;

	// Volume
	@ApiModelProperty(notes = "Volume")
	private Long volume;

	// Lote
	@ApiModelProperty(notes = "Lote")
	private Long lot;

	// IMEI
	@ApiModelProperty(notes = "IMEI")
	private String imei;	

	// Filial de venda
	@ApiModelProperty(notes = "Filial de venda")
	private String salesSubsidiary;

	// Filial de entrega
	@ApiModelProperty(notes = "Filial de entrega")
	private String deliverySubsidiary;
	
	// Porta / Doca
	@ApiModelProperty(notes = "Porta / Doca")
	private Long door;

	// Código de barras
	@ApiModelProperty(notes = "Código de barras")
	private String barcode;
	
	// EAN
	@ApiModelProperty(notes = "EAN")
	private String ean;
	
	
}
