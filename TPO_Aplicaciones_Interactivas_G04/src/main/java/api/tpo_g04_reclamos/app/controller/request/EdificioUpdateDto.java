package api.tpo_g04_reclamos.app.controller.request;

import java.util.ArrayList;
import java.util.List;

public class EdificioUpdateDto {

    private Long id;
    private String direccion;
    private List<Long> areasComunesIds = new ArrayList<>();
    private List<Long> unidadesIds = new ArrayList<>();


    public EdificioUpdateDto() {}


    public EdificioUpdateDto(String direccion) {
    	this.direccion = direccion;
    }

    public EdificioUpdateDto(String direccion, List<Long> areasComunesIds, List<Long> unidadesIds) {
        this.direccion = direccion;
        this.areasComunesIds = areasComunesIds;
        this.unidadesIds = unidadesIds;
    }

	public Long getId() {
        return id;
    }

    public String getDireccion() {
        return direccion;
    }

    public List<Long> getAreasComunesIds() {
        return areasComunesIds;
    }

    public List<Long> getUnidadesIds() {
        return unidadesIds;
    }

}
