package com.luizalabs.marty.controller.dto.tagecom;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OriginDto implements Serializable  {

	private static final long serialVersionUID = -5357590239656410931L;
	private Integer id;
}
