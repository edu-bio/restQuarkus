package org.acme.resources;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.acme.clases.Libro;
import org.acme.services.LibroService;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

@Path("/books")
@ApplicationScoped
public class LibroResource {

	//los endopoints que pones en el puerto quarkus


	    @Inject
	    @RestClient
	    LibroService libroService;

	    @GET
	    @Path("/all")
	    @Produces(MediaType.APPLICATION_JSON)
	    public List<Libro> listado() {
	        return libroService.listado();
	    }
	    
	    @GET
	    @Path("/{libroId}")
	    @Produces(MediaType.APPLICATION_JSON)
	    public Libro getLibro(@PathParam int libroId) {
	        return libroService.getLibro(libroId);
	    }

	    @POST
	    @Path("/save")
	    @Produces(MediaType.APPLICATION_JSON)
	    public Libro crearLibro(Libro libro) {
	    	return libroService.crearLibro(libro);
	    }

	    @PUT
	    @Path("/update")
	    @Produces(MediaType.APPLICATION_JSON)
	    Libro actualizarLibro(Libro libro) {
	    	return libroService.actualizarLibro(libro);
	    }
	    
	    @DELETE
	    @Path("/delete/{libroId}")
	    @Produces(MediaType.APPLICATION_JSON)
	    public void borrarLibro(@PathParam int libroId) {
	    	libroService.borrarLibro(libroId);
	    }
	
}
