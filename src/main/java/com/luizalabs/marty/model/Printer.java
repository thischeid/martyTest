package com.luizalabs.marty.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class Printer implements Serializable {

	private static final long serialVersionUID = 1858441892601659235L;
	private Long id;
	private String name;

}
