package api.tpo_g04_reclamos.app.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AreaComunRequestDto {

	private String nombre;

	@JsonProperty("edificio")
	private EdificioDto edificioDto;

	public AreaComunRequestDto() {
		super();
	}

	public AreaComunRequestDto(String nombre, EdificioDto edificioDto) {
		super();
		this.nombre = nombre;
		this.edificioDto = edificioDto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public EdificioDto getEdificioDto() {
		return edificioDto;
	}

	public void setEdificioDto(EdificioDto edificioDto) {
		this.edificioDto = edificioDto;
	}

}
