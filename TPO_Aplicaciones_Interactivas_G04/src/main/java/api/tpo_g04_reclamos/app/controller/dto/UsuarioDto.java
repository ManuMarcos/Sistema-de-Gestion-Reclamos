package api.tpo_g04_reclamos.app.controller.dto;

import api.tpo_g04_reclamos.app.model.entity.Usuario;
import api.tpo_g04_reclamos.app.model.enums.TipoUsuario;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UsuarioDto {

    private Long id;
    private String nombre;
    private String password;
    private TipoUsuario tipoUsuario;
    private Date fechaCreacion;


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

    public UsuarioDto(Usuario u) {
        super();
        this.id = u.getId();
        this.nombre = u.getNombre();
        this.password = u.getPassword();
        this.tipoUsuario = u.getTipoUsuario();
        this.fechaCreacion = u.getFechaCreacion();
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
    
    public static List<UsuarioDto> fromList(List<Usuario> lu)
    {
    	var ldto = new ArrayList<UsuarioDto>(); 
    	for (Usuario u : lu)
			ldto.add(new UsuarioDto(u));
    	return ldto;
    }
}
