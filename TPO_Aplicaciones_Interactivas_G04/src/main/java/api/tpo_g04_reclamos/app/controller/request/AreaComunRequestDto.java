package api.tpo_g04_reclamos.app.controller.request;

import api.tpo_g04_reclamos.app.controller.dto.EdificioDto;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AreaComunRequestDto {

	private String nombre;

	public AreaComunRequestDto() {
		super();
	}

	public AreaComunRequestDto(String nombre) {
		super();
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

}
