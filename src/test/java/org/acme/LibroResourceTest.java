package org.acme;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.acme.clases.Libro;
import org.acme.resources.LibroResource;
import org.acme.services.LibroService;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;


@QuarkusTest
public class LibroResourceTest {

	@RestClient
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
		
		Mockito.when(service.getLibro(Mockito.anyInt())).thenReturn(a);
		
        Assertions.assertEquals("La Sombra del Viento", resource.getLibro(a.getId()).getTitulo());
    }
	
	@Test
    public void testListado() {
        
        Assertions.assertEquals("titulo2", resource.listado().get(1).getTitulo());
       
    }
	
	@Test
	public void testCrearLibro() {
		Libro a= new Libro(1,41,"elLibroSinNombre");
		Libro b = service.crearLibro(a);
		
		Mockito.when(service.crearLibro(Mockito.any())).thenReturn(a);
		
		Assertions.assertEquals("elLibroSinNombre", resource.crearLibro(b).getTitulo());
		
	}
	
	@Test
	public void testBorrarLibro() {
		Libro a = new Libro(1, 200, "LibroBorrado");
		
		Mockito.when(service.getLibro(1)).thenReturn(null);
		service.borrarLibro(a.getId());
		Assertions.assertEquals(service.getLibro(1), null);
	}
	
	@Test
	public void testActualizarLibro() {
		Libro a = new Libro(1,112,"Experimento");
		Mockito.when(service.actualizarLibro(Mockito.any())).thenReturn(a);
		Libro actualizado = service.actualizarLibro(a);
		Assertions.assertEquals(actualizado.getTitulo(),"Experimento");
	}
}
