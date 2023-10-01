package api.tpo_g04_reclamos.app.controller.dto;

import java.util.ArrayList;
import java.util.List;

import api.tpo_g04_reclamos.app.model.entity.Edificio;

public class EdificioDto {

    private Long id;
    private String direccion;
    private List<AreaComunDto> areasComunes = new ArrayList<>();
    private List<UnidadDto> unidades = new ArrayList<>();

    public EdificioDto(Edificio e) {
		this.id = e.getId();
		this.direccion = e.getDireccion();
		this.areasComunes = AreaComunDto.fromList(e.getAreasComunes());
		this.unidades = UnidadDto.fromList(e.getUnidades());
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
    
    public static List<EdificioDto> fromList(List<Edificio> le)
    {
    	var ldto = new ArrayList<EdificioDto>(); 
    	for (Edificio e : le)
			ldto.add(new EdificioDto(e));
    	return ldto;
    }

}
