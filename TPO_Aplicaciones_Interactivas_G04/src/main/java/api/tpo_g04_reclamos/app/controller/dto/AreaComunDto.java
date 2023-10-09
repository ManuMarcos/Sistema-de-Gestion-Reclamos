package api.tpo_g04_reclamos.app.controller.dto;

import api.tpo_g04_reclamos.app.model.entity.AreaComun;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.List;

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
	
    public static List<AreaComunDto> fromList(List<AreaComun> areasComunes) {
        return areasComunes.stream().map(AreaComunDto::new).toList();
    }
}
