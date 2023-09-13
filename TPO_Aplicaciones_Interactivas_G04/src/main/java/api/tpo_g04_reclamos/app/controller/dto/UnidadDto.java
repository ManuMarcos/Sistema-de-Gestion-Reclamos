package api.tpo_g04_reclamos.app.controller.dto;

import api.tpo_g04_reclamos.app.model.entity.Edificio;
import api.tpo_g04_reclamos.app.model.enums.EstadoUnidad;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class UnidadDto {

    private Long id;

    private int piso;

    private int numero;

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

    public EstadoUnidad getEstado() {
        return estado;
    }

    private Edificio edificio;

    private EstadoUnidad estado;

}
