package com.ceiba.estacionamiento.jenniffer.alvarez.repo;

import com.ceiba.estacionamiento.jenniffer.alvarez.model.Vehiculo;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioVehiculo extends MongoRepository<Vehiculo,String>{
	public Vehiculo findByPlaca(String placa);
	public List<Vehiculo> findByTipo(String tipo);
	public Long countByTipo(String tipo);
	public void delete(Vehiculo vehiculo);

	 
	
	
}

