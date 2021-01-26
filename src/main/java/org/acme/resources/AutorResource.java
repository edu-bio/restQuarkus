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

import org.acme.clases.Autor;
import org.acme.services.AutorService;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;

//los endopoints que pones en el puerto quarkus


@Path("/authors")
@ApplicationScoped
public class AutorResource {

    @Inject
    @RestClient
    AutorService autorService;

   
    @Counted(description = "Numero de veces que es invocado el metodo acceso")
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    @Timed(description = "Media de cuanto le supone ejecutar el metodo.")
    public List<Autor> listado() {
        return autorService.listado();
    }
    

    @Counted(value = "time.now", description = "Numero de veces que es invocado el metodo acceso")
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
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    public Autor actualizarAutor(Autor autor) {
    	System.err.println("init resoruce");
    	return autorService.actualizarAutor(autor);
    }

}
