package org.acme;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.annotations.jaxrs.PathParam;

@Path("/hello")
public class ExampleResource {

	
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello RESTEasy";
    }
	
	@Path("/name/{name}")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello2(@PathParam String name) {
        return "Hello " + name;
    }
}