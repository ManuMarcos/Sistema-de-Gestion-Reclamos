package api.tpo_g04_reclamos.app.model.entity;

import api.tpo_g04_reclamos.app.model.enums.TipoUsuario;
import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;

@Entity(name = "usuarios")
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private String password;
	private TipoUsuario tipoUsuario;
	
	@Column(name = "fecha_creacion")
	@Temporal(TemporalType.DATE)
	private Date fechaCreacion;

	public Usuario() {
		super();
		this.nombre = "";
		this.password = "";
		this.fechaCreacion = new Date();
	}
	
	public Usuario(String nombre, String password, TipoUsuario tipoUsuario) {
		super();
		this.nombre = nombre;
		this.password = password;
		this.tipoUsuario = tipoUsuario;
		this.fechaCreacion = new Date();
	}

	public Usuario(Long id, String nombre, String password, TipoUsuario tipoUsuario) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.password = password;
		this.tipoUsuario = tipoUsuario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	
	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Usuario usuario = (Usuario) o;
		return id.equals(usuario.id) && nombre.equals(usuario.nombre) && password.equals(usuario.password) && tipoUsuario == usuario.tipoUsuario;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nombre, password, tipoUsuario);
	}

	@Override
	public String toString() {
		return String.format("Id:[%d];Nombre:[%s];Password[%s];FechaCreacion:[%s]", 
				this.id, this.nombre, this.password, this.fechaCreacion.toString());
	}
}
