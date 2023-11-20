package api.tpo_g04_reclamos.app.controller.request;

import api.tpo_g04_reclamos.app.model.entity.Edificio;
import api.tpo_g04_reclamos.app.model.entity.Unidad;
import api.tpo_g04_reclamos.app.model.enums.EstadoUnidad;

public class UnidadUpdateRequestDto {

    private int piso;
    private int numero;

    private EstadoUnidad estado;

    private Long propietarioId;

    public UnidadUpdateRequestDto(Unidad unidad) {
    	this.piso = unidad.getPiso();
    	this.numero = unidad.getNumero();
    	this.estado = unidad.getEstado();
    }

    public UnidadUpdateRequestDto(int piso, int numero, Edificio edificio, EstadoUnidad estado) {
        this.piso = piso;
        this.numero = numero;
        this.estado = estado;
    }

    public int getPiso() {
        return piso;
    }

    public int getNumero() {
        return numero;
    }

    public EstadoUnidad getEstado() {
        return estado;
    }

    public Long getPropietarioId() {
        return propietarioId;
    }

}
