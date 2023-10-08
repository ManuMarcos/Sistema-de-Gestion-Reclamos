package api.tpo_g04_reclamos.app.controller.request;

import api.tpo_g04_reclamos.app.controller.dto.AreaComunDto;
import api.tpo_g04_reclamos.app.controller.dto.EdificioDto;
import api.tpo_g04_reclamos.app.controller.dto.UnidadDto;
import api.tpo_g04_reclamos.app.model.entity.Edificio;

import java.util.ArrayList;
import java.util.List;

public class EdificioRequestDto {
    private String direccion;


    public EdificioRequestDto() {
        super();
    }


    public EdificioRequestDto(String direccion) {
        this.direccion = direccion;
    }


    public String getDireccion() {
        return direccion;
    }

}
