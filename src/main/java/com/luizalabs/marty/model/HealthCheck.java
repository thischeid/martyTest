package com.luizalabs.marty.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class HealthCheck implements Serializable {

	private static final long serialVersionUID = -4456172819902716385L;

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
