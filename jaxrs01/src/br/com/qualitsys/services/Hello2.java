package br.com.qualitsys.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello2")
public class Hello2 {
	
	@GET
    @Produces(MediaType.TEXT_HTML)
	 
    public String helloJersey() {
		 return "Hello Jersey - Recurso devolvido em formato Texto...";
    }

}