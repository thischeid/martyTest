package com.luizalabs.marty;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableAsync;
import com.luizalabs.marty.config.SwaggerConfig;
import com.luizalabs.marty.util.ConfigInfo;

@SpringBootApplication
@Import(SwaggerConfig.class)
@EnableAsync
@EnableCaching
@ComponentScan({"com.luizalabs.util.Health Controller","com.luizalabs.marty"})
public class MartyApplication {

	private static Logger logger = LoggerFactory.getLogger(MartyApplication.class);
	
	public static void main(String[] args) throws UnknownHostException {
		ConfigurableApplicationContext ctx = SpringApplication.run(MartyApplication.class, args);
		Environment env = ctx.getEnvironment();
   	
    	ConfigInfo.config().setAppName(env.getProperty("spring.application.name"));    	
    	ConfigInfo.config().setPort(env.getProperty("server.port"));
    	ConfigInfo.config().setBaseUrl(env.getProperty("server.context-path"));
    	ConfigInfo.config().setVersion(env.getProperty("info.build.version"));
    	ConfigInfo.config().setEnv(env.getProperty("spring.profiles.active"));
    	
    	List<String> serviceAdressList = new ArrayList<>();   
    	
    	try {
			for (NetworkInterface ni :
			            Collections.list(NetworkInterface.getNetworkInterfaces())) {
				for (InetAddress address : Collections.list(ni.getInetAddresses())) {
					if (address instanceof Inet4Address && !address.isLoopbackAddress())
						{
							serviceAdressList.add(address.toString());
							logger.debug("http:/{}:{}{}/swagger-ui.html", 
									address,
									ConfigInfo.config().getPort(),
					    			ConfigInfo.config().getBaseUrl()==null? "" :ConfigInfo.config().getBaseUrl());
						}
					
				}
			}
		} catch (SocketException e) {
			logger.error(e.getMessage());
		}   
    	
    	MDC.put("env",ConfigInfo.config().getEnv());    	
		MDC.put("version", ConfigInfo.config().getVersion());
    	logger.info("Application '{}' is running! " , ConfigInfo.config().getAppName());
	}

}
