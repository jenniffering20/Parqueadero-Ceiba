package com.ceiba.estacionamiento.jenniffer.alvarez.model;

public class ResponseController<T> {

	private String mensage;
	private T dato;
	
	
	public ResponseController(String mensage, T dato) {
		this.mensage = mensage;
		this.dato = dato;
	}

	public ResponseController(String mensage) {
		this.mensage = mensage;
	}
	
	public ResponseController(T dato) {
		this.dato = dato;
	}

	public String getMensage() {
		return mensage;
	}
	public void setMensage(String mensage) {
		this.mensage = mensage;
	}
	public T getDato() {
		return dato;
	}
	public void setDato(T dato) {
		this.dato = dato;
	}
	
	
}
