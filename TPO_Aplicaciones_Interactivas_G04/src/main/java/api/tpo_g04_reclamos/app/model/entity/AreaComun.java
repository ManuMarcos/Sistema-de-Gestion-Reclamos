package api.tpo_g04_reclamos.app.model.entity;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "area_comun")
public class AreaComun{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "edificio_id")
	private Edificio edificio;
	
	private String nombre;

	public AreaComun() {
		super();
	}

	public AreaComun(Edificio edificio, String nombre) {
		super();
		this.edificio = edificio;
		this.nombre = nombre;
	}

	public AreaComun(Long id, Edificio edificio, String nombre) {
		super();
		this.id = id;
		this.edificio = edificio;
		this.nombre = nombre;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Edificio getEdificio() {
		return this.edificio;
	}

	public void setEdificio(Edificio edificio) {
		this.edificio = edificio;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "AreaComun [id=" + this.id + ", edificio=" + this.edificio + ", nombre=" + this.nombre + "]";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		AreaComun areaComun = (AreaComun) o;
		return id.equals(areaComun.id) && edificio.getId().equals(areaComun.edificio.getId()) && nombre.equals(areaComun.nombre);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, edificio, nombre);
	}
}
