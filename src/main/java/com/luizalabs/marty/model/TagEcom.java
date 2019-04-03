package com.luizalabs.marty.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TagEcom implements Serializable {
	
	private static final long serialVersionUID = 6293258316826213477L;
	private Long lot;
	private Long originBranch;
	private String nameBranch;
	private Long orderNumber;
	private Long clientId;
	private String clientName;
	private String zip;
	private String address;
	private String addressNumber;
	private String complement;
	private String district;
	private String state;
	private Date orderDate;
	private Date deliveryDate;
	private long productId;
	private String productName;	
	
}
