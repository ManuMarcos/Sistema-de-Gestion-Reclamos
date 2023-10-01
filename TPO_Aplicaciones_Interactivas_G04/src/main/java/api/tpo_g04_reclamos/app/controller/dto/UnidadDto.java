package api.tpo_g04_reclamos.app.controller.dto;

import java.util.ArrayList;
import java.util.List;

import api.tpo_g04_reclamos.app.model.entity.Edificio;
import api.tpo_g04_reclamos.app.model.entity.Unidad;
import api.tpo_g04_reclamos.app.model.entity.Usuario;
import api.tpo_g04_reclamos.app.model.enums.EstadoUnidad;

public class UnidadDto {

    private Long id;
    private int piso;
    private int numero;
    private Edificio edificio;
    private Usuario propietario;
    private EstadoUnidad estado;

    public UnidadDto(Unidad u) {
    	this.id = u.getId();
    	this.piso = u.getPiso();
    	this.edificio = u.getEdificio();
    	this.propietario = null;
    	this.estado = u.getEstado();
    }
    
    public UnidadDto(int piso, int numero, Edificio edificio, EstadoUnidad estado) {
        this.piso = piso;
        this.numero = numero;
        this.edificio = edificio;
        this.estado = estado;
    }

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

    public Usuario getPropietario() {
        return propietario;
    }

    public EstadoUnidad getEstado() {
        return estado;
    }

	public static List<UnidadDto> fromList(List<Unidad> unidades) {
		// TODO: corregir el DTO. Tiene referencias a clases del modelo. Deberian ser DTOs anidados.
    	var ldto = new ArrayList<UnidadDto>(); 
    	for (Unidad u : unidades)
			ldto.add(new UnidadDto(u));
    	return ldto;
	}

}
