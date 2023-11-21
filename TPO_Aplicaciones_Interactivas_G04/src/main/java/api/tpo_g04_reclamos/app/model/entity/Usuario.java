package api.tpo_g04_reclamos.app.model.entity;

import api.tpo_g04_reclamos.app.model.enums.RoleType;
import api.tpo_g04_reclamos.app.model.enums.TipoUsuario;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity(name = "usuarios")
public class Usuario implements UserDetails{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private String password;
	
	@Enumerated(EnumType.STRING)
	private TipoUsuario tipoUsuario;

	@Enumerated(EnumType.STRING)
	private RoleType roleType;
	
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

	public Usuario(String nombre, String password, RoleType roleType, TipoUsuario tipoUsuario) {
		super();
		this.nombre = nombre;
		this.password = password;
		this.tipoUsuario = tipoUsuario;
		this.fechaCreacion = new Date();
		this.roleType = roleType;
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

	public void setRoleType(RoleType roleType) {
		this.roleType = roleType;
	}

	public RoleType getRoleType() {
		return roleType;
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

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return List.of(new SimpleGrantedAuthority(tipoUsuario.toString()));
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.nombre;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
}
