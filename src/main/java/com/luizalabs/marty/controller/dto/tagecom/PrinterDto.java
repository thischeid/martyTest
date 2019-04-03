package com.luizalabs.marty.controller.dto.tagecom;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrinterDto implements Serializable {

	private static final long serialVersionUID = 4918995563311127844L;
	private String name;
}
