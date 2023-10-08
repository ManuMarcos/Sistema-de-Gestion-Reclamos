package api.tpo_g04_reclamos.app.model.request;

import api.tpo_g04_reclamos.app.model.enums.EstadoReclamo;

import java.util.ArrayList;
import java.util.List;

public class ReclamoRequestDto {

    private int numero;

    private List<Long> imagenesIds = new ArrayList<>();

    private String descripcion;
    
    private String motivo;

    private EstadoReclamo estado;

    private Long usuarioId;

    private Long unidadId;

    private Long areaComunId;

    public ReclamoRequestDto(int numero, List<Long> imagenesIds, String descripcion, String motivo, EstadoReclamo estado, Long usuarioId, Long unidadId, Long areaComunId) {
        this.numero = numero;
        this.imagenesIds = imagenesIds;
        this.descripcion = descripcion;
        this.motivo = motivo;
        this.estado = estado;
        this.usuarioId = usuarioId;
        this.unidadId = unidadId;
        this.areaComunId = areaComunId;
    }

    public int getNumero() {
        return numero;
    }

    public List<Long> getImagenesIds() {
        return imagenesIds;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getMotivo() {
		return motivo;
	}
    
    public EstadoReclamo getEstado() {
        return estado;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public Long getUnidadId() {
        return unidadId;
    }

    public Long getAreaComunId() {
        return areaComunId;
    }

}
