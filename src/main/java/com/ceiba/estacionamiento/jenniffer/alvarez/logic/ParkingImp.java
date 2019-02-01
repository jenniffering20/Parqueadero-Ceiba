package com.ceiba.estacionamiento.jenniffer.alvarez.logic;

import java.time.LocalDateTime;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ceiba.estacionamiento.jenniffer.alvarez.exception.DayNotValidException;
import com.ceiba.estacionamiento.jenniffer.alvarez.exception.GeneralException;
import com.ceiba.estacionamiento.jenniffer.alvarez.exception.ParkingFullException;
import com.ceiba.estacionamiento.jenniffer.alvarez.exception.RegisteredVehicleException;
import com.ceiba.estacionamiento.jenniffer.alvarez.model.Constantes;
import com.ceiba.estacionamiento.jenniffer.alvarez.model.ResponseController;
import com.ceiba.estacionamiento.jenniffer.alvarez.model.VehiculoModel;
import com.ceiba.estacionamiento.jenniffer.alvarez.repo.Repositorio;
import com.ceiba.estacionamiento.jenniffer.alvarez.service.BillService;
import com.ceiba.estacionamiento.jenniffer.alvarez.service.ParkingService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;



@RestController
@RequestMapping("/")
@CrossOrigin("*")
public class ParkingImp implements ParkingService {

	@Autowired
	Repositorio repositorio;

	public ParkingImp(Repositorio repositorio) {
		this.repositorio = repositorio;
	}

	
	
	static final String LETRA_RESTRICCION = "A";
	static final String CARRO="CARRO";
	static final String MOTO="MOTO";

	int fullCarros;
	int fullMotos;

	public int getFullCarros() {
		return fullCarros;
	}

	public void setFullCarros(Long NumeroVehiculos) {
		fullCarros = NumeroVehiculos.intValue();
	}

	public int getFullMotos() {
		return fullMotos;
	}

	public void setFullMotos(Long NumeroVehiculos) {
		fullMotos = NumeroVehiculos.intValue();
	}

	public ParkingImp() {

	}

	@Override
	public ResponseController<List<VehiculoModel>> checkIn(VehiculoModel vehiculo) throws GeneralException {
		LocalDateTime day = LocalDateTime.now();

		if (fullParking(vehiculo.getTipo())) {
			
			throw new ParkingFullException();
		}

		if (restrictionLetter(vehiculo.getPlaca())) {

			if (validDate(day)) {
				
				throw new DayNotValidException();
			}
		}
		if (findVehiculo(vehiculo.getPlaca()) == null) {
			
			VehiculoModel newVehiculo = new VehiculoModel(vehiculo.getTipo(), vehiculo.getPlaca(),vehiculo.getCilindraje());
			newVehiculo.setFechaIngreso(day);
			repositorio.save(newVehiculo);
			UpdateNumberOfVehicles();
			return new ResponseController<List<VehiculoModel>>(Constantes.VEHICLE_REGISTERED_SUCCESSFUL);
			
		} else {
			throw new RegisteredVehicleException();
		}

	}

	@Override
	public void checkOut(String placa) {
		BillService bill = new Bill();
		VehiculoModel vehiculoToLeave = findVehiculo(placa);
		bill.goOut(vehiculoToLeave);

	}

	public Boolean restrictionLetter(String placa) {
		Boolean letter = placa.toUpperCase().startsWith(LETRA_RESTRICCION);
		boolean restriction = (letter) ?true : false;
		return restriction;
	}

	public Boolean fullParking(String tipo) {
		Boolean full = false;

		full = (getFullCarros() == 20 && getFullMotos() == 10) ? true : false;

		if (tipo.equalsIgnoreCase(CARRO) && getFullCarros() == 20) {
			full = true;
		}

		if (tipo.equalsIgnoreCase(MOTO) && getFullMotos() == 10) {
			full = true;
		}

		return full;
	}

	public Boolean validDate(LocalDateTime dateCheckIn) {
		Boolean validDate = (dateCheckIn.getDayOfWeek().getValue() == 1 || dateCheckIn.getDayOfWeek().getValue() == 0)
				? false: true;
		return validDate;
	}

	public void UpdateNumberOfVehicles() {
		Long cantidadVehiculosCarros = repositorio.countByTipo("carro");
		Long cantidadVehiculosMotos = repositorio.countByTipo("moto");
		setFullCarros(cantidadVehiculosCarros);
		setFullMotos(cantidadVehiculosMotos);
	}

	@Override
	public VehiculoModel findVehiculo(String placa) {
		return repositorio.findByPlaca(placa);
		
	}

	@Override
	public List<VehiculoModel> registeredVehicle(String tipo, String id) {
	
		return vehicles(tipo);
	}

	@Override
	public List<VehiculoModel> vehicles(String tipo) {
		return repositorio.findByTipo(tipo);
	}

	@Override
	public ResponseController<List<VehiculoModel>> findAll() {
		List<VehiculoModel>vehicleList = repositorio.findAll();
		if(vehicleList.isEmpty()) {
			return new ResponseController<List<VehiculoModel>>(Constantes.NOT_VEHICLES);
		}else {
			return new ResponseController<List<VehiculoModel>>(vehicleList);
		}
		
	}

}
