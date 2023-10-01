package api.tpo_g04_reclamos.app.controller.dto;

import api.tpo_g04_reclamos.app.model.enums.EstadoReclamo;

public class ReclamoSearchDto {

    private EstadoReclamo estado;

    public ReclamoSearchDto() {
        super();
    }

    public ReclamoSearchDto(EstadoReclamo estado) {
        super();
        this.estado = estado;
    }

    public EstadoReclamo getEstado() {
        return estado;
    }

}
