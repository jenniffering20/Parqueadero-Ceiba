package com.ceiba.estacionamiento.jenniffer.alvarez.model;

public class RespuestaParaControlador<T> {

	private String mensage;
	private T dato;
	
	
	public RespuestaParaControlador(String mensage, T dato) {
		this.mensage = mensage;
		this.dato = dato;
	}

	public RespuestaParaControlador(String mensage) {
		this.mensage = mensage;
	}
	
	public RespuestaParaControlador(T dato) {
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
