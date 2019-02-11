package com.ceiba.estacionamiento.jenniffer.alvarez.logic;

import java.time.LocalDateTime;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ceiba.estacionamiento.jenniffer.alvarez.exception.DayNotValidException;
import com.ceiba.estacionamiento.jenniffer.alvarez.exception.GeneralException;
import com.ceiba.estacionamiento.jenniffer.alvarez.exception.ParkingFullException;
import com.ceiba.estacionamiento.jenniffer.alvarez.exception.RegisteredVehicleException;
import com.ceiba.estacionamiento.jenniffer.alvarez.exception.TypeInvalidException;
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
public class Parking implements ParkingService {

	@Autowired
	Repositorio repositorio;
	
	

	public Parking(Repositorio repositorio) {
		this.repositorio = repositorio;
	}


	int fullCarros;
	int fullMotos;
	private LocalDateTime day;

	public LocalDateTime getDay() {
		return day;
	}

	public void setDay(LocalDateTime day) {
		this.day = day;
	}

	public int getFullCarros() {
		return fullCarros;
	}

	public void setFullCarros(Long numeroVehiculos) {
		fullCarros = numeroVehiculos.intValue();
	}

	public int getFullMotos() {
		return fullMotos;
	}

	public void setFullMotos(Long numeroVehiculos) {
		fullMotos = numeroVehiculos.intValue();
	}
	



	@Override
	public ResponseController<List<VehiculoModel>> checkIn(VehiculoModel vehiculo) throws GeneralException {
		if(vehiculo.getTipo() == "") {
			throw new TypeInvalidException();
		}
		
		if (fullParking(vehiculo.getTipo())) {
			
			throw new ParkingFullException();
		}

		if (restrictionLetter(vehiculo.getPlaca())) {

			if (validDate(getDay())) {
				
				throw new DayNotValidException();
			}
		}
		if (findVehiculo(vehiculo.getPlaca()) == null) {
			
			VehiculoModel newVehiculo = new VehiculoModel(vehiculo.getTipo(), vehiculo.getPlaca(),vehiculo.getCilindraje());
			setDay(LocalDateTime.now());
			newVehiculo.setFechaIngreso(getDay());
			repositorio.save(newVehiculo);
			UpdateNumberOfVehicles();
			return new ResponseController<List<VehiculoModel>>(Constantes.VEHICLE_REGISTERED_SUCCESSFUL);
			
		} else {
			throw new RegisteredVehicleException();
		}

	}

	public LocalDateTime date(LocalDateTime day) {
		setDay(day);
		return getDay();
	}
	
	@Override
	public ResponseController<List<VehiculoModel>> checkOut(String placa) {
		BillService bill = new Bill();
		VehiculoModel vehiculoToLeave = findVehiculo(placa);
		vehiculoToLeave.setFechaSalida(LocalDateTime.now());
		VehiculoModel vehiculoToUpdate= bill.goOut(vehiculoToLeave);
		repositorio.save(vehiculoToUpdate);
		
		return new ResponseController<List<VehiculoModel>>(Constantes.CHECKED_VEHICLE);

	}
	

	public Boolean restrictionLetter(String placa) {
		Boolean letter = placa.toUpperCase().startsWith(Constantes.LETRA_RESTRICCION);
		boolean restriction = (letter) ?true : false;
		return restriction;
	}

	public Boolean fullParking(String tipo) {
		Boolean full = false;
		
		full = (getFullCarros() == 20 && getFullMotos() == 10) ? true : false;

		if (tipo.equalsIgnoreCase(Constantes.CARRO) && getFullCarros() == 20) {
			full = true;
		}

		if (tipo.equalsIgnoreCase(Constantes.MOTO) && getFullMotos() == 10) {
			full = true;
		}

		return full;
	}

	public Boolean validDate(LocalDateTime dateCheckIn) {
		Boolean validDate = (dateCheckIn.getDayOfWeek().getValue() == 1 || dateCheckIn.getDayOfWeek().getValue() == 0)? false: true;
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
	public ResponseController<List<VehiculoModel>> findAll() {
		List<VehiculoModel>vehicleList = repositorio.findAll();
		if(vehicleList.isEmpty()) {
			return new ResponseController<List<VehiculoModel>>(Constantes.NOT_VEHICLES);
		}else {
			return new ResponseController<List<VehiculoModel>>(vehicleList);
		}
	}

	

}
