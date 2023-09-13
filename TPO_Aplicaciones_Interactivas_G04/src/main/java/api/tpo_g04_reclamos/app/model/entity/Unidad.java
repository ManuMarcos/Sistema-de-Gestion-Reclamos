package api.tpo_g04_reclamos.app.model.entity;

import java.util.List;
import java.util.Objects;

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
	private Long id;
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

	public Unidad(int piso, int numero, Edificio edificio, Usuario propietario, EstadoUnidad estado) {
		super();
		this.piso = piso;
		this.numero = numero;
		this.edificio = edificio;
		this.propietario = propietario;
		this.estado = estado;
	}

	public Unidad(Long id, int piso, int numero, Edificio edificio, Usuario propietario, EstadoUnidad estado) {
		super();
		this.id = id;
		this.piso = piso;
		this.numero = numero;
		this.edificio = edificio;
		this.propietario = propietario;
		this.estado = estado;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Unidad unidad = (Unidad) o;
		return piso == unidad.piso && numero == unidad.numero && id.equals(unidad.id) && edificio.getId().equals(unidad.edificio.getId()) && propietario.getId().equals(unidad.propietario.getId()) && estado == unidad.estado;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, piso, numero, edificio, propietario, inquilinos, estado);
	}
}
