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
import com.ceiba.estacionamiento.jenniffer.alvarez.exception.VehiculoNoParqueadoException;
import com.ceiba.estacionamiento.jenniffer.alvarez.model.ConstantesMensajes;
import com.ceiba.estacionamiento.jenniffer.alvarez.model.RespuestaParaControlador;
import com.ceiba.estacionamiento.jenniffer.alvarez.model.Vehiculo;
import com.ceiba.estacionamiento.jenniffer.alvarez.model.Factura;
import com.ceiba.estacionamiento.jenniffer.alvarez.repo.RepositorioFactura;
import com.ceiba.estacionamiento.jenniffer.alvarez.repo.RepositorioVehiculo;
import com.ceiba.estacionamiento.jenniffer.alvarez.service.BillService;
import com.ceiba.estacionamiento.jenniffer.alvarez.service.ParkingService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/")
@CrossOrigin("*")
public class Parqueadero implements ParkingService {

	@Autowired
	RepositorioVehiculo repositorio;
	
	@Autowired
	RepositorioFactura repositorioFactura;

	public Parqueadero(RepositorioVehiculo repositorio) {
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
	public void ingresarVehiculo(Vehiculo vehiculo) throws DomainException {
		if (vehiculo.getTipo() == "") {
			throw new TypeInvalidException();
		}

		if (fullParking(vehiculo.getTipo())) {

			throw new ParkingFullException();
		}

		if (restriccionPlacasQueInicianConLetraA(vehiculo.getPlaca())) {
			throw new DayNotValidException();
			
		}
		if (repositorio.findByPlaca(vehiculo.getPlaca())== null) {

			Vehiculo newVehiculo = new Vehiculo(vehiculo.getTipo(), vehiculo.getPlaca(),
			vehiculo.getCilindraje());
			setDay(LocalDateTime.now());
			newVehiculo.setFechaIngreso(getDay());
			repositorio.save(newVehiculo);
			Factura factura = new Factura(newVehiculo);
			repositorioFactura.save(factura);
			UpdateNumberOfVehicles();

		} else {
			throw new RegisteredVehicleException();
		}

	}

	public LocalDateTime fecha(LocalDateTime day) {
		setDay(day);
		return getDay();
	}

	@Override
	public Vehiculo facturacionVehiculo(String placa) throws DomainException {
		BillService bill = new FacturaI();
		Vehiculo vehiculoToLeave = findVehiculo(placa);
		vehiculoToLeave.setFechaSalida(LocalDateTime.now());
		Vehiculo vehiculoToUpdate = bill.goOut(vehiculoToLeave);
		repositorio.save(vehiculoToUpdate);

		return vehiculoToUpdate;

	}

	public Boolean restriccionPlacasQueInicianConLetraA(String placa) {
		
		 Boolean placaLetraA= placa.toUpperCase().startsWith(ConstantesMensajes.LETRA_RESTRICCION);
		 Boolean fecha= validDate(LocalDateTime.now());
		 return (placaLetraA && fecha);
		 
	}

	public Boolean fullParking(String tipo) {
		Boolean full = false;

		full = (getFullCarros() == ConstantesMensajes.NUMERO_MAXIMO_CARROS_PARQUEADERO
				&& getFullMotos() == ConstantesMensajes.NUMERO_MAXIMO_MOTOS_PARQUEADERO);
		
		if (tipo.equalsIgnoreCase(ConstantesMensajes.CARRO) && getFullCarros() == ConstantesMensajes.NUMERO_MAXIMO_CARROS_PARQUEADERO) {
			full = true;
		}
		
		if (tipo.equalsIgnoreCase(ConstantesMensajes.MOTO) && getFullMotos() == ConstantesMensajes.NUMERO_MAXIMO_MOTOS_PARQUEADERO) {
			full = true;
		}
		return full;
	}

	public Boolean validDate(LocalDateTime dateCheckIn) {
		return(dateCheckIn.getDayOfWeek().getValue() != 0 || dateCheckIn.getDayOfWeek().getValue() != 1);
				
		
	}

	public void UpdateNumberOfVehicles() {
		Long cantidadVehiculosCarros = repositorio.countByTipo("carro");
		Long cantidadVehiculosMotos = repositorio.countByTipo("moto");
		setFullCarros(cantidadVehiculosCarros);
		setFullMotos(cantidadVehiculosMotos);
	}

	@Override
	public Vehiculo findVehiculo(String placa) throws DomainException {
	
		Vehiculo vehiculoEnconntrado = repositorio.findByPlaca(placa);
		if(vehiculoEnconntrado == null) {
			throw new VehiculoNoParqueadoException();
		}else {
		return vehiculoEnconntrado;
		}

	}

	@Override
	public RespuestaParaControlador<List<Vehiculo>> findAll() {
		List<Vehiculo> vehicleList = repositorio.findAll();
		if (vehicleList.isEmpty()) {
			return new RespuestaParaControlador<List<Vehiculo>>(ConstantesMensajes.NOT_VEHICLES);
		} else {
			return new RespuestaParaControlador<List<Vehiculo>>(vehicleList);
		}
	}

}
