package api.tpo_g04_reclamos.app.controller.dto;

import api.tpo_g04_reclamos.app.model.entity.Edificio;
import api.tpo_g04_reclamos.app.model.entity.Unidad;
import api.tpo_g04_reclamos.app.model.entity.Usuario;
import api.tpo_g04_reclamos.app.model.enums.EstadoUnidad;

public class UnidadDto {

    private Long id;
    private int piso;
    private int numero;
    private Edificio edificio;
    private Usuario propietario;
    private EstadoUnidad estado;

    public UnidadDto(int piso, int numero, Edificio edificio, EstadoUnidad estado) {
        this.piso = piso;
        this.numero = numero;
        this.edificio = edificio;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public int getPiso() {
        return piso;
    }

    public int getNumero() {
        return numero;
    }

    public Edificio getEdificio() {
        return edificio;
    }

    public Usuario getPropietario() {
        return propietario;
    }

    public EstadoUnidad getEstado() {
        return estado;
    }

}
