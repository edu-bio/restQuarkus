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
		Autor a= new Autor();
		a.setApellidos("perez");
		a.setId(1);
		a.setNombre("pepito");
		Autor b= new Autor(1,"Ramon","Ramirez");
		Autor c= new Autor(1,"Pedro","Picapiedra");
        lista.add(a);
        lista.add(b);
        lista.add(c);
        
        //Uso el mockito con los service de las clases, y para las aserciones uso el resource
        
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
		
		//Mockito.when(service.getLibro(1)).thenReturn(null);
		resource.crearAutor(a);
		resource.borrarAutor(a.getId());
		Assertions.assertEquals(resource.getAutor(1), null);
	}
	
	@Test
	public void testActualizarAutor() {
		Autor a = new Autor(112,"Cobaya", "DeLaboratorio");
		Autor a2 = new Autor(112,"Cobaya", "DeLaboratorio insignificante");
		Mockito.when(service.actualizarAutor(Mockito.any())).thenReturn(a2);
		a= resource.actualizarAutor(a2);
		Assertions.assertEquals(a.getApellidos(),"DeLaboratorio insignificante");
	}
}
