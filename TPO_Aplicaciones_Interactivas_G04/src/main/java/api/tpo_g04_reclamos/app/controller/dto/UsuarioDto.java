package api.tpo_g04_reclamos.app.controller.dto;

import api.tpo_g04_reclamos.app.model.entity.Usuario;
import api.tpo_g04_reclamos.app.model.enums.RoleType;
import api.tpo_g04_reclamos.app.model.enums.TipoUsuario;

import java.util.Date;
import java.util.List;

public class UsuarioDto {

    private Long id;
    private String nombre;
    private String password;
    private TipoUsuario tipoUsuario;
    private Date fechaCreacion;
    private RoleType roleType;


    public UsuarioDto() {
        super();
        this.nombre = "";
        this.password = "";
    }

    public UsuarioDto(String nombre, String password, TipoUsuario tipoUsuario) {
        super();
        this.nombre = nombre;
        this.password = password;
        this.tipoUsuario = tipoUsuario;
    }

    public UsuarioDto(String nombre, String password, RoleType roleType, TipoUsuario tipoUsuario) {
        super();
        this.nombre = nombre;
        this.password = password;
        this.tipoUsuario = tipoUsuario;
        this.roleType = roleType;
    }

    public UsuarioDto(Usuario usuario) {
        super();
        this.id = usuario.getId();
        this.nombre = usuario.getNombre();
        this.password = usuario.getPassword();
        this.tipoUsuario = usuario.getTipoUsuario();
        this.fechaCreacion = usuario.getFechaCreacion();
    }
    
    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPassword() {
        return password;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public RoleType getRoleType() {
        return roleType;
    }
    
    public static List<UsuarioDto> fromList(List<Usuario> usuarios) {
        return usuarios.stream().map(UsuarioDto::new).toList();
    }
}
