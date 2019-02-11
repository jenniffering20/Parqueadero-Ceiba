package com.ceiba.estacionamiento.jenniffer.alvarez.exception;
import com.ceiba.estacionamiento.jenniffer.alvarez.model.Constantes;

public class TypeInvalidException extends DomainException{
	
	public TypeInvalidException() {
		super(Constantes.NOT_VALID_TYPE);
	}

	

}


