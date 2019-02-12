package com.ceiba.estacionamiento.jenniffer.alvarez.exception;
import com.ceiba.estacionamiento.jenniffer.alvarez.model.ConstantesMensajes;

public class TypeInvalidException extends DomainException{
	
	public TypeInvalidException() {
		super(ConstantesMensajes.NOT_VALID_TYPE);
	}

	

}


