package api.tpo_g04_reclamos.app.controller.dto;

import api.tpo_g04_reclamos.app.model.entity.Edificio;
import api.tpo_g04_reclamos.app.model.entity.Unidad;
import api.tpo_g04_reclamos.app.model.enums.EstadoUnidad;

import java.util.List;

public class UnidadDto {

    private Long id;
    private Integer piso;
    private Integer numero;
    private Long edificioId;
    private Long propietarioId;
    private EstadoUnidad estado;

    public UnidadDto(Unidad unidad) {
    	this.id = unidad.getId();
    	this.piso = unidad.getPiso();
    	this.edificioId = unidad.getEdificio().getId();
        this.propietarioId = unidad.getPropietario() != null ? unidad.getPropietario().getId() : null;
    	this.estado = unidad.getEstado();
        this.piso = unidad.getPiso();
        this.numero = unidad.getNumero();
    }
    
    public UnidadDto(Integer piso, Integer numero, Edificio edificio, EstadoUnidad estado) {
        this.piso = piso;
        this.numero = numero;
        this.edificioId = edificio.getId();
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public Integer getPiso() {
        return piso;
    }

    public Integer getNumero() {
        return numero;
    }

    public Long getEdificioId() {
        return edificioId;
    }

    public Long getPropietarioId() {
        return propietarioId;
    }

    public EstadoUnidad getEstado() {
        return estado;
    }

	public static List<UnidadDto> fromList(List<Unidad> unidades) {
        return unidades.stream().map(UnidadDto::new).toList();
	}

}
