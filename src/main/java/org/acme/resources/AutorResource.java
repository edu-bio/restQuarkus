package org.acme.resources;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.acme.interfaces.AutorService;
import org.acme.modelos.Autor;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

//los endopoints que pones en el puerto quarkus

@Path("/authors")
public class AutorResource {

    @Inject
    @RestClient
    AutorService autorService;

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Autor> listado() {
        return autorService.listado();
    }
    
    @GET
    @Path("/{autorId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Autor getAutor(@PathParam int autorId) {
        return autorService.getAutor(autorId);
    }

    @POST
    @Path("/save")
    @Produces(MediaType.APPLICATION_JSON)
    public Autor crearAutor (Autor autor) {
    return autorService.crearAutor(autor);
    }

    @DELETE
    @Path("/delete/{autorId}")
    @Produces(MediaType.APPLICATION_JSON)
    public void borrarAutor(@PathParam int autorId) {
    	autorService.borrarAutor(autorId);
    }
    
    @PUT
    @Path("/dd")
    @Produces(MediaType.APPLICATION_JSON)
    Autor actualizarLibro(Autor autor) {
    	System.err.println("init resoruce");
    	return autorService.actualizarAutor(autor);
    }
}