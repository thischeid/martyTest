package com.luizalabs.marty.repository.erp;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.luizalabs.marty.mapper.OrdersMapper;
import com.luizalabs.marty.model.Orders;
import com.luizalabs.marty.repository.erp.contract.TagRepository;

@Repository
public class TagRepositoryImpl implements TagRepository {

	private static final String SELECT_TAG_ECOM 
			= "SELECT "
					+"	PED.NUMPEDVEN,"
					+"	PED.CODROTA,"
					+"	CLI.NOMCLI,"
					+"	EMB.DESCRICAO AS EMBDESCRICAO,"
					+"	(SELECT MAX(DTENTREGA) FROM MOV_ITRESERVA RES WHERE RES.CODRESERVA =PED.CODRESERVA) AS DTENTREGA,"
					+"	PED.DTPEDIDO,"
					+"	PEND.CEP,"
					+"	PEND.ENDERECO,"
					+"	NVL(PEND.COMPLEMENTO,' ')  COMPLEMENTO,"
					+"	PEND.BAIRRO,"
					+"	PEND.CIDADE,"
					+"	PEND.ESTADO,"
					+"	PROD.DESCRICAO || ' '  || COR.DESCRES || ' '  || ESPEC.DESCRES AS DESCITPROD,"
					//QRCODE
					+"	IPROD.CODITPROD || IPROD.DIGITPROD AS CODITPROD,"
					+"  IPED.ITEM," //SEQ ITEM
					+"  1 AS VOLUME," //VOLUME
					+"	IL.NLOTE,"
					+"  S.NRSERIE AS NRSERIE," //IMEI					
					+"	FIL.FANTASIA AS FILIALVENDA,"
					+"	FENT.FANTASIA AS FILIALENTREGA,"				
					+ " IPROD.CODGTIN,"//EAN
					+"	PED.CODCLI,"	
					+"	IPED.QTCOMP,"					
					+"	PED.FILORIG,"					
					+"	L.CODDOCA" 	//DOCA
			+"	FROM  MOV_PEDIDO   PED "
			+"  INNER JOIN MOV_PEDIDO_ENDER PEND 	ON PED.CODFIL = PEND.CODFIL "
	        									+"  AND PED.TIPOPED = PEND.TIPOPED "
	        									+"  AND PED.NUMPEDVEN = PEND.NUMPEDVEN "
	        									+"  AND PEND.TIPOEND = CASE "
	        									+"  WHEN NVL(PED.CODCLIPRES, 0) = 0 THEN 'E' ELSE 'P' END "
			+"	INNER JOIN CAD_FILIAL  FIL  ON PED.CODFIL  = FIL.CODFIL "
			+"	INNER JOIN CAD_FILIAL  FENT ON PED.FILORIG = FENT.CODFIL "
			+"	INNER JOIN CAD_CLIENTE CLI  ON CLI.CODCLI =  NVL(PED.CODCLIPRES,PED.CODCLI) "
			+"	INNER JOIN  MOV_ITPED  IPED ON PED.CODFIL = IPED.CODFIL "
										+"	AND PED.TIPOPED = IPED.TIPOPED "
										+"	AND PED.NUMPEDVEN = IPED.NUMPEDVEN "
										+"	AND  IPED.CODTPSERV = 0 "
			+"	INNER JOIN CAD_ITPROD IPROD ON IPED.CODITPROD = IPROD.CODITPROD "
			+"	INNER JOIN CAD_PROD   PROD  ON IPROD.CODPROD  = PROD.CODPROD "
			+"	INNER JOIN CAD_COR    COR   ON IPROD.CODCOR = COR.CODCOR "
			+"	INNER JOIN CAD_ESPEC  ESPEC ON IPROD.ESPECIFIC = ESPEC.ESPECIFIC "
										+"	AND IPROD.CODFAM = ESPEC.CODFAM "
			+"	LEFT  JOIN CAD_EMBALAGEM  EMB    ON IPROD.CODEMBALAGEM = EMB.CODEMBALAGEM "
			+"	INNER JOIN DIS_ITLOTE           IL     ON PED.FILORIG = IL.CODFIL "
											  		+" AND PED.NUMPEDVEN = IL.NUMPEDVEN "
	  		+"	INNER JOIN DIS_LOTE             L      ON  L.CODFIL = IL.CODFIL "
											  		+" AND L.NLOTE  = IL.NLOTE "
			+"  LEFT JOIN CAD_NRSERIE           S      ON IPED.NUMPEDVEN = S.NUMPEDVEN " 
													+" AND IPED.CODITPROD = S.CODITPROD " 	
			+"	WHERE PED.NUMPEDVEN IN (:ids) ";
	
	@Autowired
	@Qualifier("dsErp")
	private DataSource dataSource;
	
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	@Override
	public List<Orders> findAllByBranchOriginAndOrderNumbers(int branchOrigin, List<Long> orderNumbers) {
		
		jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
				
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("ids", orderNumbers);
		
		return jdbcTemplate.query(SELECT_TAG_ECOM,  parameters , new OrdersMapper());
		
	}

}
