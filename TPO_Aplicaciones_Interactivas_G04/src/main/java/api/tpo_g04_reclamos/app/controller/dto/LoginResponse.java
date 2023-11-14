package api.tpo_g04_reclamos.app.controller.dto;

public class LoginResponse {

    private String accessToken;
    private Long userId;

    public LoginResponse() {
        super();
    }

    public LoginResponse(String accessToken, Long userId) {
        super();
        this.accessToken = accessToken;
        this.userId = userId;
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

}
