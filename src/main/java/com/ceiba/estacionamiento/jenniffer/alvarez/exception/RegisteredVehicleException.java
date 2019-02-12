package com.ceiba.estacionamiento.jenniffer.alvarez.exception;
import com.ceiba.estacionamiento.jenniffer.alvarez.model.ConstantesMensajes;

public class RegisteredVehicleException extends DomainException{

	public RegisteredVehicleException() {
		super(ConstantesMensajes.REGISTERED_MESSAGE);
		
	}

}

