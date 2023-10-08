package api.tpo_g04_reclamos.app.controller.request;

public class EdificioSummaryDto {

	private String direccion;

	public EdificioSummaryDto() {
		super();
	}

	public EdificioSummaryDto(String direccion) {
		super();
		this.direccion = direccion;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

}
