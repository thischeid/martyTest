package com.luizalabs.marty.repository.wis;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.luizalabs.marty.mapper.PrinterMapper;
import com.luizalabs.marty.model.Printer;
import com.luizalabs.marty.repository.wis.contract.PrinterRepository;

@Repository
public class PrinterRepositoryImpl implements PrinterRepository {	
		
	@Autowired
	@Qualifier("jdbcWis")
	JdbcTemplate jdbcTemplate;
	
	public List<Printer> findAllByCompany(Long company, String printerColetor) {
		
		String query = "select cd_impressora, ds_impressora from wis50.t_impressora where cd_empresa = ? and id_impressao_coletor = ? order by cd_impressora";
		
		return jdbcTemplate.query(query, new Object[] { company, printerColetor }, new PrinterMapper());
	}


}
