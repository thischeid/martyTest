package com.luizalabs.marty.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Printers implements Serializable {

	private static final long serialVersionUID = 6345316260154511869L;

	// Id da impressora
	private Long id;
	
	// Nome da impressora
	private String name;

	
}
