package org.acme.services;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.acme.clases.Libro;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

@Path("/libros")
@RegisterRestClient
@ApplicationScoped
public interface LibroService {
	
	//los endpoints del microservicio al que llamas


		@GET
	    @Path("/todos")
	    @Produces("application/json")
	    List<Libro> listado();
		
	    @GET
	    @Path("/{libroId}")
	    @Produces("application/json")
	    Libro getLibro(@PathParam int libroId);

	    @POST
	    @Path("/guardar")
	    @Produces("application/json")
	    Libro crearLibro(Libro libro);

	    @PUT
	    @Path("/actualizar")
	    @Produces("application/json")
	    Libro actualizarLibro(Libro libro);
	
	    @DELETE
	    @Path("/borrar/{libroId}")
	    @Produces("application/json")
	    void borrarLibro(@PathParam int libroId);

}
