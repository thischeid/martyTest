package com.luizalabs.marty.controller.dto;

import java.io.Serializable;
import java.util.List;

import com.luizalabs.marty.controller.dto.tagecom.TransportListDto;

import lombok.Data;


@Data
public class TagEcomRequest implements Serializable {

	private static final long serialVersionUID = -7009777956485520209L;
	private List<TransportListDto> transportLists;
}
