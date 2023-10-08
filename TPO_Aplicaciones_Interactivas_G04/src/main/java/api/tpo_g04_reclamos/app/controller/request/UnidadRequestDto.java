package api.tpo_g04_reclamos.app.controller.request;

import api.tpo_g04_reclamos.app.model.entity.Edificio;
import api.tpo_g04_reclamos.app.model.entity.Unidad;
import api.tpo_g04_reclamos.app.model.enums.EstadoUnidad;

import java.util.ArrayList;
import java.util.List;

public class UnidadRequestDto {

    private int piso;
    private int numero;

    private EstadoUnidad estado;

    public UnidadRequestDto(Unidad unidad) {
    	this.piso = unidad.getPiso();
    	this.estado = unidad.getEstado();
    }

    public UnidadRequestDto(int piso, int numero, Edificio edificio, EstadoUnidad estado) {
        this.piso = piso;
        this.numero = numero;
        this.estado = estado;
    }

    public int getPiso() {
        return piso;
    }

    public int getNumero() {
        return numero;
    }

    public EstadoUnidad getEstado() {
        return estado;
    }

	public static List<UnidadRequestDto> fromList(List<Unidad> unidades) {
		// TODO: corregir el DTO. Tiene referencias a clases del modelo. Deberian ser DTOs anidados.
    	var ldto = new ArrayList<UnidadRequestDto>();
    	for (Unidad u : unidades)
			ldto.add(new UnidadRequestDto(u));
    	return ldto;
	}

}
