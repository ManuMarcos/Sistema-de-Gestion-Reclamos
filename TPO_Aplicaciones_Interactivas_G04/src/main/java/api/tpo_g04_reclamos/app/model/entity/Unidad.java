package api.tpo_g04_reclamos.app.model.entity;

import api.tpo_g04_reclamos.app.model.enums.EstadoUnidad;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity(name = "unidades")
public class Unidad {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer piso;
	private Integer numero;
	
	@ManyToOne
	@JoinColumn(name = "edificio_id")
	private Edificio edificio;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "propietario_id")
	private Usuario propietario;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "usuario_unidades",
				joinColumns = @JoinColumn(name = "inquilino_id"),
				inverseJoinColumns = @JoinColumn(name = "unidad_id"))
	private List<Usuario> inquilinos;
	
	private EstadoUnidad estado;

	public Unidad() {
		super();
	}

	public Unidad(Integer piso, Integer numero, Edificio edificio, EstadoUnidad estado) {
		super();
		this.piso = piso;
		this.numero = numero;
		this.edificio = edificio;
		this.estado = estado;
	}

	public Unidad(Integer piso, Integer numero, EstadoUnidad estado) {
		super();
		this.piso = piso;
		this.numero = numero;
		this.estado = estado;
	}

	public Unidad(Integer piso, Integer numero, Edificio edificio, Usuario propietario, EstadoUnidad estado) {
		super();
		this.piso = piso;
		this.numero = numero;
		this.edificio = edificio;
		this.propietario = propietario;
		this.estado = estado;
	}

	public Unidad(Long id, Integer piso, Integer numero, Edificio edificio, Usuario propietario, EstadoUnidad estado) {
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

	public Integer getPiso() {
		return piso;
	}

	public void setPiso(Integer piso) {
		this.piso = piso;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
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

	public void setPropietario(Usuario propietario) {
		this.propietario = propietario;
	}

	public Usuario getPropietario() {
		return propietario;
	}

	public List<Usuario> getInquilinos() {
		return inquilinos;
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Unidad unidad = (Unidad) o;
		return piso == unidad.piso && numero == unidad.numero && id.equals(unidad.id) && edificio.getId().equals(unidad.edificio.getId()) && propietario.getId().equals(unidad.propietario.getId()) && estado == unidad.estado;
	}

	@Override
	public Integer hashCode() {
		return Objects.hash(id, piso, numero, edificio, propietario, inquilinos, estado);
	}
}
