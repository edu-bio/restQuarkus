package org.acme;

import java.util.ArrayList;
import java.util.List;

import org.acme.clases.Libro;
import org.acme.resources.LibroResource;
import org.acme.services.LibroService;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.google.inject.Inject;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;

@RegisterRestClient
@QuarkusTest
public class LibroResourceTest {

	@InjectMock
	LibroService service;
	
	@Inject 
    LibroResource resource;
	
	@BeforeEach
    void setUp() {
		List<Libro> lista = new ArrayList<>();
		Libro a= new Libro(1,231,"titulo1");
		Libro b= new Libro(2,123,"titulo2");
		Libro c= new Libro(3,213,"titulo3");
        lista.add(a);
        lista.add(b);
        lista.add(c);
        
        Mockito.when(service.listado()).thenReturn(lista);
        
    }
	
	@Test
	public void testGetLibro() {
		Libro a= new Libro (1,123,"La Sombra del Viento");
		
        Assertions.assertEquals("pepito", resource.getLibro(a.getId()).getTitulo());
    }
	
	@Test
    public void testListado() {
		List<Libro> lista = new ArrayList<>();
		Libro a= new Libro(1,231,"titulo1");
		Libro b= new Libro(2,123,"titulo2");
		Libro c= new Libro(3,213,"titulo3");
        lista.add(a);
        lista.add(b);
        lista.add(c);
        
        Assertions.assertEquals("titulo2", resource.listado().get(1).getTitulo());
       
    }
	
	@Test
	public void testCrearLibro() {
		Libro a= new Libro(1,41,"elLibroSinNombre");
		Libro b = service.crearLibro(a);
		
		Assertions.assertEquals("edu", resource.crearLibro(b).getTitulo());
		
	}
	
	@Test
	public void testBorrarLibro() {
		
	}
	
	@Test
	public void testActualizarLibro() {
		
	}
}
