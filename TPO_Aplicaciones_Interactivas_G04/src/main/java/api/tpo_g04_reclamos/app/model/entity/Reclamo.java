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

    @OneToOne
    private AreaComun areaComun;

}
