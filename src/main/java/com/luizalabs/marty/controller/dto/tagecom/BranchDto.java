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
public class BranchDto implements Serializable {
	
	private static final long serialVersionUID = 2341337418173699931L;
	private OriginDto origin;
}
