package api.tpo_g04_reclamos.app.controller.dto;

import api.tpo_g04_reclamos.app.model.entity.Edificio;

public class AreaComunDto {

    private Long id;

    private Edificio edificio;

    private String nombre;

    public Long getId() {
        return id;
    }

    public Edificio getEdificio() {
        return edificio;
    }

    public String getNombre() {
        return nombre;
    }

}
