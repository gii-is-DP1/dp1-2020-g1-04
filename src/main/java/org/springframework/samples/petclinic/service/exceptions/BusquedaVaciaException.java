package org.springframework.samples.petclinic.service.exceptions;

public class BusquedaVaciaException extends Exception {

	public BusquedaVaciaException(String message) {
		super(message);
	}
}
