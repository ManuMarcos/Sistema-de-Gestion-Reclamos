package api.tpo_g04_reclamos.app.model.entity;

import java.util.List;

import api.tpo_g04_reclamos.app.model.enums.EstadoUnidad;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

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
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "propietario_fk_id")
	private Usuario propietario;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "usuario_unidad",
				joinColumns = @JoinColumn(name = "inquilino_fk_id"),
				inverseJoinColumns = @JoinColumn(name = "unidad_fk_id"))
	private List<Usuario> inquilinos;
	
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
