package com.ceiba.estacionamiento.jenniffer.alvarez.logic;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

	static String FULL_MESSAGE = "El estacionamiento no tiene espacio disponible";
	static String REGISTERED_MESSAGE = "la Placa ya se encuentra registrada, verificar";
	static String MESSAGE_NO_AUTHORIZATION = "No esta autorizado para ingresar";
	static final String LETRA_RESTRICCION = "A";

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
	public VehiculoModel checkIn(VehiculoModel vehiculo) throws Exception {
		LocalDateTime day = LocalDateTime.now();

		if (fullParking(vehiculo.getTipo())) {
			System.out.println(FULL_MESSAGE);
			throw new Exception(FULL_MESSAGE);
		}

		if (restrictionLetter(vehiculo.getPlaca())) {

			if (validDate(day)) {
				throw new Exception(MESSAGE_NO_AUTHORIZATION);
			}
		}

		if (findVehiculo(vehiculo.getPlaca()) == null) {
			VehiculoModel newVehiculo = new VehiculoModel(vehiculo.getTipo(), vehiculo.getPlaca(),
					vehiculo.getCilindraje());
			newVehiculo.setFechaIngreso(day);
			VehiculoModel vehiculos = repositorio.save(newVehiculo);
			UpdateNumberOfVehicles();
			return vehiculos;
		} else {
			throw new Exception(REGISTERED_MESSAGE);
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
		boolean restriction = (letter) ? true : false;
		return restriction;
	}

	public Boolean fullParking(String tipo) {
		Boolean full = false;

		full = (getFullCarros() == 20 && getFullMotos() == 10) ? true : false;

		if (tipo.equalsIgnoreCase("CARRO") && getFullCarros() == 20) {
			full = true;
		}

		if (tipo.equalsIgnoreCase("MOTO") && getFullMotos() == 10) {
			full = true;
		}

		return full;
	}

	private Boolean validDate(LocalDateTime dateCheckIn) {
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
	public VehiculoModel findVehiculo(String placa) {
		return repositorio.findByPlaca(placa);
	}

	@Override
	public List<VehiculoModel> registeredVehicle(String tipo, String id) {
		List<VehiculoModel> vehiculos = vehicles(tipo);
		return vehiculos;
	}

	@Override
	public List<VehiculoModel> vehicles(String tipo) {
		return repositorio.findByTipo(tipo);
	}

}
