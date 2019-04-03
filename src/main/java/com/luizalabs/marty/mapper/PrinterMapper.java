package com.luizalabs.marty.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import com.luizalabs.marty.model.Printer;

public class PrinterMapper implements RowMapper<Printer> {

	@Override
	public Printer mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Printer register = new Printer();
		register.setId(rs.getLong("cd_impressora"));
		register.setName(rs.getString("ds_impressora"));		    
				
		return register;
	}
}
