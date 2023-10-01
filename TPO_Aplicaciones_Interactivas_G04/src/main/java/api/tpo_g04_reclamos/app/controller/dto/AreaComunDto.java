package api.tpo_g04_reclamos.app.controller.dto;

import java.util.ArrayList;
import java.util.List;

import api.tpo_g04_reclamos.app.model.entity.AreaComun;

public class AreaComunDto {

    private Long id;

    private EdificioDto edificio;

    private String nombre;

    public Long getId() {
        return id;
    }

    public EdificioDto getEdificio() {
        return edificio;
    }

    public String getNombre() {
        return nombre;
    }

	public AreaComunDto(AreaComun ac) {
		this.id = ac.getId();
		this.nombre = ac.getNombre();
		this.edificio = new EdificioDto(ac.getEdificio());
	}
	
    public static List<AreaComunDto> fromList(List<AreaComun> lac)
    {
    	var ldto = new ArrayList<AreaComunDto>(); 
    	for (AreaComun ac : lac)
			ldto.add(new AreaComunDto(ac));
    	return ldto;
    }
}
