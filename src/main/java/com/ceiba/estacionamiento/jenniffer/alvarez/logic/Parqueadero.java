package com.ceiba.estacionamiento.jenniffer.alvarez.logic;

import java.time.LocalDateTime;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ceiba.estacionamiento.jenniffer.alvarez.exception.DayNotValidException;
import com.ceiba.estacionamiento.jenniffer.alvarez.exception.DomainException;
import com.ceiba.estacionamiento.jenniffer.alvarez.exception.ParkingFullException;
import com.ceiba.estacionamiento.jenniffer.alvarez.exception.RegisteredVehicleException;
import com.ceiba.estacionamiento.jenniffer.alvarez.exception.TypeInvalidException;
import com.ceiba.estacionamiento.jenniffer.alvarez.model.Constantes;
import com.ceiba.estacionamiento.jenniffer.alvarez.model.ResponseController;
import com.ceiba.estacionamiento.jenniffer.alvarez.model.Vehiculo;
import com.ceiba.estacionamiento.jenniffer.alvarez.repo.Repositorio;
import com.ceiba.estacionamiento.jenniffer.alvarez.service.BillService;
import com.ceiba.estacionamiento.jenniffer.alvarez.service.ParkingService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/")
@CrossOrigin("*")
public class Parqueadero implements ParkingService {

	@Autowired
	Repositorio repositorio;

	public Parqueadero(Repositorio repositorio) {
		this.repositorio = repositorio;
	}

	int fullCarros;
	int fullMotos;
	private LocalDateTime dia;

	public LocalDateTime getDay() {
		return dia;
	}

	public void setDay(LocalDateTime day) {
		this.dia = day;
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
	public void ingresar(Vehiculo vehiculo) throws DomainException {
		if (vehiculo.getTipo() == "") {
			throw new TypeInvalidException();
		}

		if (fullParking(vehiculo.getTipo())) {

			throw new ParkingFullException();
		}

		if (restrictionLetter(vehiculo.getPlaca()) && validDate(getDay())) {

			throw new DayNotValidException();
		}
		if (findVehiculo(vehiculo.getPlaca()) == null) {

			Vehiculo newVehiculo = new Vehiculo(vehiculo.getTipo(), vehiculo.getPlaca(),
					vehiculo.getCilindraje());
			setDay(LocalDateTime.now());
			newVehiculo.setFechaIngreso(getDay());
			repositorio.save(newVehiculo);
			UpdateNumberOfVehicles();

		} else {
			throw new RegisteredVehicleException();
		}

	}

	public LocalDateTime date(LocalDateTime day) {
		setDay(day);
		return getDay();
	}

	@Override
	public ResponseController<List<Vehiculo>> checkOut(String placa) {
		BillService bill = new Bill();
		Vehiculo vehiculoToLeave = findVehiculo(placa);
		vehiculoToLeave.setFechaSalida(LocalDateTime.now());
		Vehiculo vehiculoToUpdate = bill.goOut(vehiculoToLeave);
		repositorio.save(vehiculoToUpdate);

		return new ResponseController<List<Vehiculo>>(Constantes.CHECKED_VEHICLE);

	}

	public Boolean restrictionLetter(String placa) {
		return placa.toUpperCase().startsWith(Constantes.LETRA_RESTRICCION);
	}

	public Boolean fullParking(String tipo) {
		Boolean full = false;

		full = (getFullCarros() == 20 && getFullMotos() == 10);

		if (tipo.equalsIgnoreCase(Constantes.CARRO) && getFullCarros() == 20) {
			full = true;
		}

		if (tipo.equalsIgnoreCase(Constantes.MOTO) && getFullMotos() == 10) {
			full = true;
		}

		return full;
	}

	public Boolean validDate(LocalDateTime dateCheckIn) {
		Boolean validDate = (dateCheckIn.getDayOfWeek().getValue() == 1 || dateCheckIn.getDayOfWeek().getValue() == 0)
				? false
				: true;
		return validDate;
	}

	public void UpdateNumberOfVehicles() {
		Long cantidadVehiculosCarros = repositorio.countByTipo("carro");
		Long cantidadVehiculosMotos = repositorio.countByTipo("moto");
		setFullCarros(cantidadVehiculosCarros);
		setFullMotos(cantidadVehiculosMotos);
	}

	@Override
	public Vehiculo findVehiculo(String placa) {
		return repositorio.findByPlaca(placa);

	}

	@Override
	public ResponseController<List<Vehiculo>> findAll() {
		List<Vehiculo> vehicleList = repositorio.findAll();
		if (vehicleList.isEmpty()) {
			return new ResponseController<List<Vehiculo>>(Constantes.NOT_VEHICLES);
		} else {
			return new ResponseController<List<Vehiculo>>(vehicleList);
		}
	}

}
