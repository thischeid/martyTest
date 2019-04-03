package com.luizalabs.marty.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DataSourceConfig {

	
	@Value("${jdbc.wis.driverclassname}") 
	private String wisDriverClassName;

	@Value("${jdbc.wis.url}") 
	private String wisUrl;

	@Value("${jdbc.wis.username}") 
	private String wisUsername;

	@Value("${jdbc.wis.password}") 
	private String wisPassword;

	@Value("${jdbc.wis.maxPoolSize}")
	private int wisMaxPollSize;
		
	@Value("${jdbc.wis.idleTimeout}")
	private Long wisIdleTimeout;
	
	@Value("${jdbc.wis.connectionTimeout}")
	private int wisConnectionTimeout;
	
	@Value("${jdbc.wis.minimumIdle}")
	private int wisMinimumIdle;
	
	@Value("${jdbc.erp.driverclassname}") 
	private String erpDriverClassName;

	@Value("${jdbc.erp.url}") 
	private String erpUrl;

	@Value("${jdbc.erp.username}") 
	private String erpUsername;

	@Value("${jdbc.erp.password}") 
	private String erpPassword;
	
	@Value("${jdbc.erp.maxPoolSize}")
	private int erpMaxPollSize;
		
	@Value("${jdbc.erp.idleTimeout}")
	private Long erpIdleTimeout;
	
	@Value("${jdbc.erp.connectionTimeout}")
	private int erpConnectionTimeout;
	
	@Value("${jdbc.erp.minimumIdle}")
	private int erpMinimumIdle;
	
	
	@Bean(name="dsWis") 
	@Primary
	public DataSource dataSourceWis() { 
		HikariDataSource dataSource =  new HikariDataSource(); 
		dataSource.setDriverClassName(wisDriverClassName);
		dataSource.setJdbcUrl(wisUrl); 
		dataSource.setUsername(wisUsername);
		dataSource.setPassword(wisPassword); 
		dataSource.setMaximumPoolSize(wisMaxPollSize);
		dataSource.setIdleTimeout(wisIdleTimeout);
		dataSource.setConnectionTimeout(wisConnectionTimeout);
		dataSource.setMinimumIdle(wisMinimumIdle);		
		return dataSource; 
	}
	
	@Bean(name="dsErp")
	public DataSource dataSourceErp() { 
		HikariDataSource dataSource =  new HikariDataSource(); 
		dataSource.setDriverClassName(erpDriverClassName);
		dataSource.setJdbcUrl(erpUrl); 
		dataSource.setUsername(erpUsername);
		dataSource.setPassword(erpPassword);
		dataSource.setMaximumPoolSize(erpMaxPollSize);
		dataSource.setIdleTimeout(erpIdleTimeout);
		dataSource.setConnectionTimeout(erpConnectionTimeout);
		dataSource.setMinimumIdle(erpMinimumIdle);
		return dataSource; 
	}
		
	@Bean(name="jdbcWis")
	@Primary
    public JdbcTemplate jdbcTemplateWIS() {
        return new JdbcTemplate(dataSourceWis());
    }
	
	@Bean(name="jdbcErp")
    public JdbcTemplate jdbcTemplateErp() {
        return new JdbcTemplate(dataSourceErp());
    }
	
}
