package api.tpo_g04_reclamos.app.controller.dto;

import java.util.ArrayList;
import java.util.List;

import api.tpo_g04_reclamos.app.model.entity.AreaComun;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class AreaComunDto {

    private Long id;

    private Long edificioId;

    private String nombre;

    public Long getId() {
        return id;
    }

    public Long getEdificioId() {
        return edificioId;
    }

    public String getNombre() {
        return nombre;
    }

    public AreaComunDto() {
        super();
    }

	public AreaComunDto(AreaComun ac) {
		this.id = ac.getId();
		this.nombre = ac.getNombre();
		this.edificioId = ac.getEdificio().getId();
	}
	
    public static List<AreaComunDto> fromList(List<AreaComun> areasComunes)
    {
    	var ldto = new ArrayList<AreaComunDto>(); 
    	for (AreaComun areaComun : areasComunes)
			ldto.add(new AreaComunDto(areaComun));
    	return ldto;
    }
}
