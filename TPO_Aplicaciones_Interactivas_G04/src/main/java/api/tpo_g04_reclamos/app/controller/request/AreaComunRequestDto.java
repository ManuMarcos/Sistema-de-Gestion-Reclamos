package api.tpo_g04_reclamos.app.controller.request;

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
