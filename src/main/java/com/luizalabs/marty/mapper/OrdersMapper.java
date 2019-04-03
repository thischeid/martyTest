package com.luizalabs.marty.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import org.springframework.jdbc.core.RowMapper;

import com.luizalabs.marty.model.Orders;

public class OrdersMapper implements RowMapper<Orders> {

	@Override
	public Orders mapRow(ResultSet rs, int rowNum) throws SQLException {
		Orders tag= new Orders();
		SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
		String strBarcode="";		
		String strAddress = "";
		
		try 
		{		
			tag.setId(rs.getLong("NUMPEDVEN"));
			tag.setRoute(rs.getString("CODROTA"));
			tag.setNameClient(rs.getString("NOMCLI"));		 
			tag.setPacking(rs.getString("EMBDESCRICAO"));
			tag.setDeliveryDate(formatDate.format(rs.getDate("DTENTREGA")));
			tag.setRequestDate(formatDate.format(rs.getDate("DTPEDIDO")));
			tag.setCep(rs.getString("CEP"));
			strAddress = strAddress
				.concat(rs.getString("ENDERECO")).trim()
				.concat(" ")
				.concat(rs.getString("COMPLEMENTO")).trim()
				.concat(" ")
				.concat(rs.getString("BAIRRO").trim())
				.concat(" - ")
				.concat(rs.getString("CIDADE").trim())
				.concat(" - ")
				.concat(rs.getString("ESTADO").trim());		
			tag.setAddress(strAddress);		
			//tag.setQrcode(qrcode);
			tag.setProductCode(rs.getLong("CODITPROD"));
			tag.setProductDescription(rs.getString("DESCITPROD"));
			
			tag.setVolume(rs.getLong("VOLUME"));		
			tag.setLot(rs.getLong("NLOTE"));
			tag.setImei(rs.getString("NRSERIE"));
			tag.setSalesSubsidiary(rs.getString("FILIALVENDA"));
			tag.setDeliverySubsidiary(rs.getString("FILIALENTREGA"));
			tag.setDoor(rs.getLong("CODDOCA"));
			tag.setEan(rs.getString("CODGTIN"));		
			
			strBarcode = String.format("%04d", rs.getLong("FILORIG"))
					.concat(String.format("%012d", tag.getId()))
					.concat(tag.getCep())
					.concat(String.format("%03d",rs.getInt("ITEM")))
					.concat(String.format("%03d", rs.getInt("QTCOMP")))
					;
			
			tag.setBarcode(strBarcode);
		
		} catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return tag;
	}

}
