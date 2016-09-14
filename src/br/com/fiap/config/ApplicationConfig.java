package br.com.fiap.config;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("rest")
public class ApplicationConfig extends ResourceConfig{
	
	public ApplicationConfig() {
		packages("br.com.fiap.resource");
	}
	
}
