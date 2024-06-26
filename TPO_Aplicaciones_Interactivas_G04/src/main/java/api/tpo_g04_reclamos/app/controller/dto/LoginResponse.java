package api.tpo_g04_reclamos.app.controller.dto;

import api.tpo_g04_reclamos.app.model.enums.RoleType;
import api.tpo_g04_reclamos.app.model.enums.TipoUsuario;

public class LoginResponse {

    private String accessToken;
    private Long userId;
    private TipoUsuario tipo;
    private Long edificioId;
	private RoleType role;

    public LoginResponse() {
        super();
    }

    public LoginResponse(String accessToken, Long userId, TipoUsuario tipo, Long edificioId) {
        super();
        this.accessToken = accessToken;
        this.userId = userId;
        this.tipo = tipo;
        this.edificioId = edificioId;
    }

	public LoginResponse(String accessToken, Long userId, TipoUsuario tipo, Long edificioId, RoleType role) {
		super();
		this.accessToken = accessToken;
		this.userId = userId;
		this.tipo = tipo;
		this.edificioId = edificioId;
		this.role = role;
	}

    public String getAccessToken() {
        return this.accessToken;
    }

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public TipoUsuario getTipo() {
		return tipo;
	}

	public void setTipo(TipoUsuario tipo) {
		this.tipo = tipo;
	}

	public Long getEdificioId() {
		return edificioId;
	}

	public void setEdificioId(Long edificioId) {
		this.edificioId = edificioId;
	}

	public void setRole(RoleType roleType) {
		this.role = roleType;
	}

	public RoleType getRole() {
		return role;
	}

}
