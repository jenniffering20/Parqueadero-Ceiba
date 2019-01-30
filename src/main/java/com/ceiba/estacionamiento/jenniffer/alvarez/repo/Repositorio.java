package com.ceiba.estacionamiento.jenniffer.alvarez.repo;

import com.ceiba.estacionamiento.jenniffer.alvarez.model.VehiculoModel;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Repositorio extends MongoRepository<VehiculoModel,String>{
	public VehiculoModel findByPlaca(String placa);
	public List<VehiculoModel> findByTipo(String tipo);
	public Long countByTipo(String tipo);
	 
	
	
}

