package com.ceiba.estacionamiento.jenniffer.alvarez.repo;

import com.ceiba.estacionamiento.jenniffer.alvarez.model.VehiculoModel; 
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Repositorio extends MongoRepository<VehiculoModel,String>{

}
