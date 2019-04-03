package com.luizalabs.marty.controller.dto;
import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor 
@NoArgsConstructor 
public class TagEcomResponse implements Serializable {

	private static final long serialVersionUID = 5489092734927573011L;
	private List<Long> errorTranportList;		
	private String message;	
	
}
