package com.ceiba.estacionamiento.jenniffer.alvarez.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ceiba.estacionamiento.jenniffer.alvarez.model.Factura;

public interface RepositorioFactura extends MongoRepository<Factura,String> {

}
