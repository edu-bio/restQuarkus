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
		Libro a= new Libro();
		a.setId(1);
		a.setIsbn(231);
		a.setTitulo("titulo1");
		Libro b= new Libro(2,123,"titulo2");
		Libro c= new Libro(3,213,"titulo3");
        lista.add(a);
        lista.add(b);
        lista.add(c);
        
      //Uso el mockito con los service de las clases, y para las aserciones uso el resource
        
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
		
		Mockito.when(service.crearLibro(Mockito.any())).thenReturn(a);
		
		Assertions.assertEquals("elLibroSinNombre", resource.crearLibro(a).getTitulo());
		
	}
	
	@Test
	public void testBorrarLibro() {
		Libro a = new Libro(1, 200, "LibroBorrado");
		
		//Mockito.when(service.getLibro(1)).thenReturn(null);
		resource.crearLibro(a);
		resource.borrarLibro(a.getId());
		Assertions.assertEquals(resource.getLibro(1), null);
		
	}
	
	@Test
	public void testActualizarLibro() {
		Libro a = new Libro(1,112,"Experimento");
		Libro a2 = new Libro (1,112,"Experimento conseguido");
		Mockito.when(service.actualizarLibro(Mockito.any())).thenReturn(a2);
		a= resource.actualizarLibro(a2);
		Assertions.assertEquals(a.getTitulo(),"Experimento conseguido");
	}
}
