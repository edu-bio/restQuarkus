package org.acme.services;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.acme.clases.Autor;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

//los endpoints del microservicio al que llamas

@Path("/autores")
@RegisterRestClient
public interface AutorService {

	@GET
    @Path("/todos")
    @Produces("application/json")
    List<Autor> listado();
	
    @GET
    @Path("/{autorId}")
    @Produces("application/json")
    Autor getAutor(@PathParam int autorId);

    @POST
    @Path("/guardar")
    @Produces("application/json")
    Autor crearAutor(Autor autor);

    @DELETE
    @Path("/borrar/{libroId}")
    @Produces("application/json")
    void borrarAutor(@PathParam int libroId);
    
    @PUT
    @Path("/actualizar")
    @Produces("application/json")
    Autor actualizarAutor(Autor autor);
}
