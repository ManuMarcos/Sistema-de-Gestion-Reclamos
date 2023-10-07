package api.tpo_g04_reclamos.app.model.dto;

import api.tpo_g04_reclamos.app.controller.dto.AreaComunDto;
import api.tpo_g04_reclamos.app.controller.dto.UnidadDto;
import api.tpo_g04_reclamos.app.controller.dto.UsuarioDto;
import api.tpo_g04_reclamos.app.model.entity.AreaComun;
import api.tpo_g04_reclamos.app.model.entity.Imagen;
import api.tpo_g04_reclamos.app.model.entity.Unidad;
import api.tpo_g04_reclamos.app.model.entity.Usuario;
import api.tpo_g04_reclamos.app.model.enums.EstadoReclamo;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import java.util.ArrayList;
import java.util.List;

public class ReclamoDto {

    private Long id;

    private int numero;

    private List<ImagenDto> imagenes = new ArrayList<>();

    private String descripcion;
    
    private String motivo;

    private EstadoReclamo estado;

    private UsuarioDto usuario;

    private UnidadDto unidad;

    private AreaComunDto areaComun;

    public ReclamoDto(Long id, int numero, List<ImagenDto> imagenes, String descripcion, String motivo, EstadoReclamo estado, UsuarioDto usuario, UnidadDto unidad, AreaComunDto areaComun) {
        super();
        this.id = id;
        this.numero = numero;
        this.imagenes = imagenes;
        this.descripcion = descripcion;
        this.motivo = motivo;
        this.estado = estado;
        this.usuario = usuario;
        this.unidad = unidad;
        this.areaComun = areaComun;
    }

    public Long getId() {
        return id;
    }

    public int getNumero() {
        return numero;
    }

    public List<ImagenDto> getImagenes() {
        return imagenes;
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

    public UsuarioDto getUsuario() {
        return usuario;
    }

    public UnidadDto getUnidad() {
        return unidad;
    }

    public AreaComunDto getAreaComun() {
        return areaComun;
    }

}
