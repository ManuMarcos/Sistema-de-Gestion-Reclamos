package api.tpo_g04_reclamos.app.model.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "id")
public class EdificioDto {

	private int id;
	
	private String direccion;

	private List<AreaComunDto> areasComunes;

	public EdificioDto() {
		super();
	}

	public EdificioDto(int id, String direccion, List<AreaComunDto> areasComunes) {
		super();
		this.id = id;
		this.direccion = direccion;
		this.areasComunes = areasComunes;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public List<AreaComunDto> getAreasComunes() {
		return areasComunes;
	}

	public void setAreasComunes(List<AreaComunDto> areasComunes) {
		this.areasComunes = areasComunes;
	}

	
	// private List<UnidadDto> unidades;

}
