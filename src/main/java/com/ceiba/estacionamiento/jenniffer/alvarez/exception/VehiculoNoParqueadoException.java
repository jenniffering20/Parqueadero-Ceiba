package com.ceiba.estacionamiento.jenniffer.alvarez.exception;

import com.ceiba.estacionamiento.jenniffer.alvarez.model.ConstantesMensajes;

public class VehiculoNoParqueadoException extends DomainException {
	
	public VehiculoNoParqueadoException() {
		super(ConstantesMensajes.NOT_VEHICLE_ISPARKING);
	}

}
