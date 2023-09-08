package api.tpo_g04_reclamos.app.model.entity;

import api.tpo_g04_reclamos.app.model.enums.EstadoReclamo;
import jakarta.persistence.*;

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

    @OneToMany
    private List<Imagen> imagenes = new ArrayList<>();

    private String descripcion;

    // edificio deberia borrarse del UML, referencia la tiene unidad

    private EstadoReclamo estado;

    @OneToOne
    private Usuario usuario;

    @OneToOne
    private Unidad unidad;

    // @Column(name = "area_comun") 
    //TODO: (Ivo) me da error al levantar la app esto. lo comento. no se si hace falta.
    // org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'entityManagerFactory' defined in class path resource [org/springframework/boot/autoconfigure/orm/jpa/HibernateJpaConfiguration.class]: Property 'api.tpo_g04_reclamos.app.model.entity.Reclamo.areaComun' is a '@OneToOne' association and may not use '@Column' to specify column mappings (use '@PrimaryKeyJoinColumn' instead)
    @OneToOne
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
