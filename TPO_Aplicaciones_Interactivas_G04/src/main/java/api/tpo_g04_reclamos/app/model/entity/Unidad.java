package api.tpo_g04_reclamos.app.model.entity;

import api.tpo_g04_reclamos.app.model.enums.EstadoUnidad;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Unidad {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int piso;
	private int numero;
	
	@ManyToOne
	@JoinColumn(name = "edificio_id")
	private Edificio edificio;
	
	//private Usuario propietario;
	private EstadoUnidad estado;

	
	public Unidad() {
		super();
	}

	public Unidad(int piso, int numero, Edificio edificio, EstadoUnidad estado) {
		super();
		this.piso = piso;
		this.numero = numero;
		this.edificio = edificio;
		this.estado = estado;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPiso() {
		return piso;
	}

	public void setPiso(int piso) {
		this.piso = piso;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public Edificio getEdificio() {
		return edificio;
	}

	public void setEdificio(Edificio edificio) {
		this.edificio = edificio;
	}

	public EstadoUnidad getEstado() {
		return estado;
	}

	public void setEstado(EstadoUnidad estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Unidad [id=" + id + ", piso=" + piso + ", numero=" + numero + ", edificio=" + edificio + ", estado="
				+ estado + "]";
	}
	
	
	
	
}
