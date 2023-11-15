package api.tpo_g04_reclamos.app.model.entity;

import api.tpo_g04_reclamos.app.model.enums.EstadoReclamo;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity(name = "reclamos")
public class Reclamo {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private int numero;

    @OneToMany(mappedBy = "reclamo", cascade = CascadeType.REMOVE)
    private List<Imagen> imagenes = new ArrayList<>();

    private String descripcion;
    private String motivo;
    // edificio deberia borrarse del UML, referencia la tiene unidad

    private EstadoReclamo estado;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToOne
    @JoinColumn(name = "unidad_id")
    private Unidad unidad;

    @OneToOne
    @JoinColumn(name = "area_comun_id")
    private AreaComun areaComun;

    private Long edificioId;

    public Reclamo() {
        super();
    }

    public Reclamo(int numero, List<Imagen> imagenes, String descripcion, String motivo, EstadoReclamo estado, Usuario usuario, Unidad unidad, AreaComun areaComun, Long edificioId) {
        this.numero = numero;
        this.imagenes = imagenes;
        this.descripcion = descripcion;
        this.motivo = motivo;
        this.estado = estado;
        this.usuario = usuario;
        this.unidad = unidad;
        this.areaComun = areaComun;
        this.edificioId = edificioId;
    }

    public Reclamo(Long id, int numero, List<Imagen> imagenes, String descripcion, String motivo, EstadoReclamo estado, Usuario usuario, Unidad unidad, AreaComun areaComun) {
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

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public List<Imagen> getImagenes() {
        return imagenes;
    }

    public void setImagenes(List<Imagen> imagenes) {
        this.imagenes = imagenes;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMotivo() {
		return motivo;
	}
    
    public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
    
    public EstadoReclamo getEstado() {
        return estado;
    }

    public void setEstado(EstadoReclamo estado) {
        this.estado = estado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Unidad getUnidad() {
        return unidad;
    }

    public void setUnidad(Unidad unidad) {
        this.unidad = unidad;
    }

    public AreaComun getAreaComun() {
        return areaComun;
    }

    public Long getEdificioId() {
        return this.edificioId;
    }

    public void setAreaComun(AreaComun areaComun) {
        this.areaComun = areaComun;
    }

    public void setEdificioId(Long edificioId) {
        this.edificioId = edificioId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reclamo reclamo = (Reclamo) o;
        return numero == reclamo.numero && id.equals(reclamo.id) && descripcion.equals(reclamo.descripcion) && estado == reclamo.estado && Objects.equals(usuario, reclamo.usuario) && Objects.equals(unidad, reclamo.unidad) && Objects.equals(areaComun, reclamo.areaComun);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numero, descripcion, estado, usuario, unidad, areaComun);
    }
}
