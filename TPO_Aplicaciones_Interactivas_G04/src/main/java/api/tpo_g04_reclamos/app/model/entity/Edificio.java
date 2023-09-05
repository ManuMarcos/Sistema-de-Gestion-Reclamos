package api.tpo_g04_reclamos.app.model.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Edificio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String direccion;

	@OneToMany(mappedBy = "edificio", cascade = CascadeType.ALL)
	private List<AreaComun> areasComunes;

	@OneToMany(mappedBy = "edificio", cascade = CascadeType.ALL)
	private List<Unidad> unidades;


	public Edificio() {
		super();
	}

	public Edificio(String direccion, List<AreaComun> areasComunes, List<Unidad> unidades) {
		super();
		this.direccion = direccion;
		this.areasComunes = areasComunes;
		this.unidades = unidades;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
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
	public String toString() {
		return "Edificio [id=" + id + ", direccion=" + direccion + ", areasComunes=" + areasComunes + ", unidades="
				+ unidades + "]";
	}

}
