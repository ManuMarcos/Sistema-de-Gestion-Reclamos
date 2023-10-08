package api.tpo_g04_reclamos.app.model.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "edificios")
public class Edificio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String direccion;

	@OneToMany(mappedBy = "edificio", cascade = CascadeType.ALL)
	private List<AreaComun> areasComunes = new ArrayList<>();

	@OneToMany(mappedBy = "edificio", cascade = CascadeType.ALL)
	private List<Unidad> unidades = new ArrayList<>();

	public Edificio() {
		super();
	}

	public Edificio(String direccion) {
		super();
		this.direccion = direccion;
	}

	public Edificio(String direccion, List<AreaComun> areasComunes, List<Unidad> unidades) {
		super();
		this.direccion = direccion;
		this.areasComunes = areasComunes;
		this.unidades = unidades;
	}
	
	public Edificio(Long id, String direccion, List<AreaComun> areasComunes, List<Unidad> unidades) {
		super();
		this.id = id;
		this.direccion = direccion;
		this.areasComunes = areasComunes;
		this.unidades = unidades;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public void agregarAreaComun(AreaComun areaComun) {
		this.areasComunes.add(areaComun);
	}

	public List<AreaComun> getAreasComunes() {
		return areasComunes;
	}

	public void setAreasComunes(List<AreaComun> areasComunes) {
		this.areasComunes = areasComunes;
	}

	public List<Unidad> getUnidades() {
		return unidades;
	}

	public void setUnidades(List<Unidad> unidades) {
		this.unidades = unidades;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Edificio edificio = (Edificio) o;
		return id.equals(edificio.id) && direccion.equals(edificio.direccion);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, direccion, areasComunes, unidades);
	}

}
