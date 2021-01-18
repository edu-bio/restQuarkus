package org.acme;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.acme.clases.Autor;
import org.acme.resources.AutorResource;
import org.acme.services.AutorService;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;


@QuarkusTest
public class AutorResourceTest {

	@InjectMock
	@RestClient
	AutorService service;
	
	@Inject
    AutorResource resource;
	
	@BeforeEach
    void setUp() {
		List<Autor> lista = new ArrayList<>();
		Autor a= new Autor(1,"pepito","perez");
		Autor b= new Autor(1,"Ramon","Ramirez");
		Autor c= new Autor(1,"Pedro","Picapiedra");
        lista.add(a);
        lista.add(b);
        lista.add(c);
        
        Mockito.when(service.listado()).thenReturn(lista);
        
    }
	
	@Test
	public void testGetAutor() {
		Autor a= new Autor(1,"pepito","perez");
		
		Mockito.when(service.getAutor(Mockito.anyInt())).thenReturn(a);
		
        Assertions.assertEquals("pepito", resource.getAutor(a.getId()).getNombre());
    }
	
	@Test
    public void testListado() {
        
        Assertions.assertEquals("Ramirez", resource.listado().get(1).getApellidos());
       
    }
	
	@Test
	public void testCrearAutor() {
		Autor a= new Autor(1,"edu","sanchez");
		
		Autor b = new Autor();
		
		Mockito.when(service.crearAutor(Mockito.any())).thenReturn(a);
		
		Assertions.assertEquals("edu", resource.crearAutor(b).getNombre());
		
	}
	
	@Test
	public void testBorrarAutor() {
		Autor a = new Autor(102, "SinNombre", "SinApellido");
		
		Mockito.when(service.getAutor(102)).thenReturn(null);
		service.borrarAutor(a.getId());
		Assertions.assertEquals(service.getAutor(102), null);
		
	}
	
	@Test
	public void testActualizarAutor() {
		Autor a = new Autor(112,"Cobaya", "DeLaboratorio");
		Mockito.when(service.actualizarAutor(Mockito.any())).thenReturn(a);
		Autor actualizado = service.actualizarAutor(a);
		Assertions.assertEquals(actualizado.getId(),112);
	}
}
