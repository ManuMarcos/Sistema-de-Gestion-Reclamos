package api.tpo_g04_reclamos.app.model.entity;

import api.tpo_g04_reclamos.app.model.enums.EstadoReclamo;
import jakarta.persistence.*;
import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "reclamos")
public class Reclamo {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private int numero;

    @OneToMany(mappedBy = "reclamo", cascade = CascadeType.ALL)
    private List<Imagen> imagenes = new ArrayList<>();

    private String descripcion;

    // edificio deberia borrarse del UML, referencia la tiene unidad

    private EstadoReclamo estado;

    @OneToOne
    @JoinColumn(name = "usuario_fk_id")
    private Usuario usuario;

    @OneToOne
    @JoinColumn(name = "unidad_fk_id")
    private Unidad unidad;

    @OneToOne
    @JoinColumn(name = "area_comun_fk_id")
    private AreaComun areaComun;

    public Reclamo() {
        super();
    }

    public Reclamo(int numero, List<Imagen> imagenes, String descripcion, EstadoReclamo estado, Usuario usuario, Unidad unidad, AreaComun areaComun) {
        this.numero = numero;
        this.imagenes = imagenes;
        this.descripcion = descripcion;
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

    public void setAreaComun(AreaComun areaComun) {
        this.areaComun = areaComun;
    }

}
