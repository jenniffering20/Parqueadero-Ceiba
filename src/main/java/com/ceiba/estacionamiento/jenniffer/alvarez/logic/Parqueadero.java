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
import com.ceiba.estacionamiento.jenniffer.alvarez.model.Vehiculo;
import com.ceiba.estacionamiento.jenniffer.alvarez.model.Factura;
import com.ceiba.estacionamiento.jenniffer.alvarez.repo.RepositorioFactura;
import com.ceiba.estacionamiento.jenniffer.alvarez.service.BillService;
import com.ceiba.estacionamiento.jenniffer.alvarez.service.ParkingService;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/")
@CrossOrigin("*")
public class Parqueadero implements ParkingService {


	
	@Autowired
	RepositorioFactura repositorioFactura;

	public Parqueadero(RepositorioFactura repositorio) {
		this. repositorioFactura = repositorio;
	}

	int fullCarros;
	int fullMotos;
	private LocalDateTime dia;
	private String placaVacia="";

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

	

	public LocalDateTime fecha(LocalDateTime day) {
		setDay(day);
		return getDay();
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

	public void updateNumberOfVehicles() {
		Long cantidadVehiculosCarros = repositorioFactura.countByVehiculoTipo("carro");
		Long cantidadVehiculosMotos = repositorioFactura.countByVehiculoTipo("moto");
		setFullCarros(cantidadVehiculosCarros);
		setFullMotos(cantidadVehiculosMotos);
		
	}

	
	
	@Override
	public void ingresarVehiculoFactura(Vehiculo vehiculo) throws DomainException {
		if (vehiculo.getTipo().equals(placaVacia)) {
			throw new TypeInvalidException();
		}

		if (fullParking(vehiculo.getTipo())) {

			throw new ParkingFullException();
		}

		if (restriccionPlacasQueInicianConLetraA(vehiculo.getPlaca())) {
			throw new DayNotValidException();
			
		}
		if(repositorioFactura.findByPlaca(vehiculo.getPlaca())==null) {

			Vehiculo newVehiculo = new Vehiculo(vehiculo.getTipo(), vehiculo.getPlaca(),vehiculo.getCilindraje());
			setDay(LocalDateTime.now());
			Factura facturaVehiculo = new Factura(vehiculo.getPlaca(),newVehiculo);
			facturaVehiculo.setFechaIngreso(getDay());
			repositorioFactura.save(facturaVehiculo);
			
			updateNumberOfVehicles();
			
		}else {
			throw new RegisteredVehicleException();
		}
		
	}
	
	@Override
	public Factura facturacionVehiculoSalida(String placa) throws DomainException {
		BillService bill = new FacturaI();
		Factura facturaVehiculo= encontrarFactura(placa);
		
		facturaVehiculo.setFechaSalida(LocalDateTime.now());
	
		Factura facturaToUpdate = bill.goOutt(facturaVehiculo);
		repositorioFactura.save(facturaToUpdate);
		updateNumberOfVehicles();
		return facturaToUpdate;
	}
	
	
	public Factura encontrarFactura(String placa) throws DomainException {
		Factura facturaVehiculo= repositorioFactura.findByPlaca(placa);
		if(facturaVehiculo==null) {
			throw new VehiculoNoParqueadoException();
		}else {
			return facturaVehiculo;
		}
	}

	@Override
	public List<Factura> todasFacturas() {
		return repositorioFactura.findAll();
		
	}
	
	
	

}
