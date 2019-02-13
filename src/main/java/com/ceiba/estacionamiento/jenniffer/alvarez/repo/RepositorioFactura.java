package com.ceiba.estacionamiento.jenniffer.alvarez.repo;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.ceiba.estacionamiento.jenniffer.alvarez.model.Factura;
import com.ceiba.estacionamiento.jenniffer.alvarez.model.Vehiculo;

public interface RepositorioFactura extends MongoRepository<Factura,Vehiculo> {
	public Factura findByPlaca(String placa);
	public Long countByVehiculoTipo(String tipo);
	


}
