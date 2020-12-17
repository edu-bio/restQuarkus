package org.acme.modelos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Libro {

    public String titulo;
    public int id;
    public int isbn;
    public List<Autor> autores;
    
    public Libro() {
	
	}
	
	public Libro (int id, int isbn, String titulo) {
		this.id=id;
		this.isbn=isbn;
		this.titulo=titulo;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public int getIsbn() {
		return isbn;
	}
	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}
	
}
