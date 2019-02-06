package com.ceiba.estacionamiento.jenniffer.alvarez.exception;
import com.ceiba.estacionamiento.jenniffer.alvarez.model.Constantes;

public class RegisteredVehicleException extends GeneralException{

	public RegisteredVehicleException() {
		super(Constantes.REGISTERED_MESSAGE);
		
	}

}

