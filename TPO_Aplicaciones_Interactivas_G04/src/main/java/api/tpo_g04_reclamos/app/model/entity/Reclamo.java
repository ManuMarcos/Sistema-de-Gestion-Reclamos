package api.tpo_g04_reclamos.app.model.entity;

import api.tpo_g04_reclamos.app.model.enums.EstadoReclamo;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.ArrayList;
import java.util.List;

public class Reclamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int numero;

    private List<Imagen> imagenes = new ArrayList<>();

    private String descripcion;

    // edificio deberia borrarse del UML, referencia la tiene unidad

    private EstadoReclamo estado;

    private Usuario usuario;

    private Unidad unidad;

    private AreaComun areaComun;

}
