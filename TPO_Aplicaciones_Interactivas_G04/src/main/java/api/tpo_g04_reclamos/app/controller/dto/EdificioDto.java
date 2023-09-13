package api.tpo_g04_reclamos.app.controller.dto;

import api.tpo_g04_reclamos.app.model.entity.AreaComun;
import api.tpo_g04_reclamos.app.model.entity.Unidad;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

public class EdificioDto {

    private Long id;
    private String direccion;
    private List<AreaComunDto> areasComunes = new ArrayList<>();
    private List<UnidadDto> unidades = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public String getDireccion() {
        return direccion;
    }

    public List<AreaComunDto> getAreasComunes() {
        return areasComunes;
    }

    public List<UnidadDto> getUnidades() {
        return unidades;
    }

}
