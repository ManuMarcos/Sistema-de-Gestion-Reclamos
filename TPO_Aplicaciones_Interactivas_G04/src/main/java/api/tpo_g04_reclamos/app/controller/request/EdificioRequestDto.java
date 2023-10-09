package api.tpo_g04_reclamos.app.controller.request;

public class EdificioRequestDto {
    private String direccion;


    public EdificioRequestDto() {
        super();
    }


    public EdificioRequestDto(String direccion) {
        this.direccion = direccion;
    }


    public String getDireccion() {
        return direccion;
    }

}
