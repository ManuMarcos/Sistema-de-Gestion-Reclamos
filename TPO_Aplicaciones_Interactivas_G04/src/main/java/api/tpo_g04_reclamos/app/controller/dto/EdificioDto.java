package api.tpo_g04_reclamos.app.controller.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import api.tpo_g04_reclamos.app.model.entity.Edificio;

import static java.util.stream.Collectors.toList;

public class EdificioDto {

    private Long id;
    private String direccion;
    private List<AreaComunDto> areasComunes = new ArrayList<>();
    private List<UnidadDto> unidades = new ArrayList<>();

    
    public EdificioDto() {}
    
    
    public EdificioDto(String direccion) {
    	this.direccion = direccion;
    }
    
    
    public EdificioDto(Edificio edificio) {
		this.id = edificio.getId();
		this.direccion = edificio.getDireccion();
        if(!edificio.getAreasComunes().isEmpty()) {
            this.areasComunes = AreaComunDto.fromList(edificio.getAreasComunes());
        }
        if(!edificio.getUnidades().isEmpty()) {
            this.unidades = UnidadDto.fromList(edificio.getUnidades());
        }
	}

	public Long getId() {
        return id;
    }

    public String getDireccion() {
        return direccion;
    }

    public List<AreaComunDto> getAreasComunes() {
        return areasComunes;
    }

    public List<UnidadDto> getUnidades() {
        return unidades;
    }
    
    public static List<EdificioDto> fromList(List<Edificio> edificios) {
        return edificios.stream().map(EdificioDto::new).collect(toList());
    }

}
