package com.ceiba.estacionamiento.jenniffer.alvarez.model;

import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Estacionamiento")

public class VehiculoModel {
	@Id
	private String id;
	
	private String tipo;
	private String placa;
	private int cilindraje;
	
	
	public VehiculoModel(String tipo,String placa,int cilindraje) {
		this.tipo=tipo;
		this.placa=placa;
		this.cilindraje=cilindraje;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public int getCilindraje() {
		return cilindraje;
	}

	public void setCilindraje(int cilindraje) {
		this.cilindraje = cilindraje;
	}

	
	

	@Override
	public String toString() {
		return "VehiculoModel [id=" + id + ", tipo=" + tipo + ", placa=" + placa + ", cilindraje=" + cilindraje
				+ ", fechaIngreso=" ;
	}
	
	
}
