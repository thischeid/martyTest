package com.luizalabs.marty.controller.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class HealthCheckResponse  implements Serializable {

	private static final long serialVersionUID = -7051045301998181272L;

	// Servidor
	private String server;
	
	// Data de hoje
    private String now;
    
    // Database
    private String database; 
    
    // Mensagem
    private String message;
    
    // Data do banco de dados
    private Date dataDatabase;
    
    // Sistema
    private String system;
	
	
}
