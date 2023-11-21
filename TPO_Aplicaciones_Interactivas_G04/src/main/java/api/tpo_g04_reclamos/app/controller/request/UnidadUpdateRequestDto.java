package api.tpo_g04_reclamos.app.controller.request;

import api.tpo_g04_reclamos.app.model.entity.Edificio;
import api.tpo_g04_reclamos.app.model.entity.Unidad;
import api.tpo_g04_reclamos.app.model.enums.EstadoUnidad;

public class UnidadUpdateRequestDto {

    private Integer piso;
    private Integer numero;

    private EstadoUnidad estado;

    private Long propietarioId;

    public UnidadUpdateRequestDto(Unidad unidad) {
    	this.piso = unidad.getPiso();
    	this.numero = unidad.getNumero();
    	this.estado = unidad.getEstado();
    }

    public UnidadUpdateRequestDto(Integer piso, Integer numero, Edificio edificio, EstadoUnidad estado) {
        this.piso = piso;
        this.numero = numero;
        this.estado = estado;
    }

    public Integer getPiso() {
        return piso;
    }

    public Integer getNumero() {
        return numero;
    }

    public EstadoUnidad getEstado() {
        return estado;
    }

    public Long getPropietarioId() {
        return propietarioId;
    }

}
