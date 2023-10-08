package api.tpo_g04_reclamos.app.controller.dto;

public class LoginResponse {

    private String accessToken;

    public LoginResponse() {
        super();
    }

    public LoginResponse(String accessToken) {
        super();
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return this.accessToken;
    }

}
