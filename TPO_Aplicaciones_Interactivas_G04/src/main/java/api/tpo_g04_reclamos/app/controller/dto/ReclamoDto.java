package api.tpo_g04_reclamos.app.controller.dto;

import api.tpo_g04_reclamos.app.model.entity.Imagen;
import api.tpo_g04_reclamos.app.model.entity.Reclamo;
import api.tpo_g04_reclamos.app.model.enums.EstadoReclamo;

import java.util.ArrayList;
import java.util.List;

public class ReclamoDto {

    private Long id;

    private int numero;

    private List<Long> imagenesIds = new ArrayList<>();

    private String descripcion;

    private String motivo;

    private EstadoReclamo estado;

    private Long usuarioId;

    private Long unidadId;

    private Long areaComunId;

    public ReclamoDto(int numero, List<Long> imagenesIds, String descripcion, String motivo, EstadoReclamo estado, Long usuarioId, Long unidadId, Long areaComunId) {
        this.numero = numero;
        this.imagenesIds = imagenesIds;
        this.descripcion = descripcion;
        this.motivo = motivo;
        this.estado = estado;
        this.usuarioId = usuarioId;
        this.unidadId = unidadId;
        this.areaComunId = areaComunId;
    }

    public ReclamoDto(Reclamo reclamo) {
        this.id = reclamo.getId();
        this.numero = reclamo.getNumero();
        this.imagenesIds = !reclamo.getImagenes().isEmpty() ? reclamo.getImagenes().stream().map(Imagen::getId).toList() : null;
        this.descripcion = reclamo.getDescripcion();
        this.motivo = reclamo.getMotivo();
        this.estado = reclamo.getEstado();
        this.usuarioId = reclamo.getUsuario() != null ? reclamo.getUsuario().getId() : null;
        this.unidadId = reclamo.getUnidad() != null ? reclamo.getUnidad().getId() : null;
        this.areaComunId = reclamo.getAreaComun() != null ? reclamo.getAreaComun().getId() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public static List<ReclamoDto> fromList(List<Reclamo> reclamos) {
        return reclamos.stream().map(ReclamoDto::new).toList();
    }

}
