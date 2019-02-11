package com.ceiba.estacionamiento.jenniffer.alvarez.exception;

import com.ceiba.estacionamiento.jenniffer.alvarez.model.Constantes;

public class VehiculoNoParqueadoException extends DomainException {
	
	public VehiculoNoParqueadoException() {
		super(Constantes.NOT_VEHICLE_ISPARKING);
	}

}
