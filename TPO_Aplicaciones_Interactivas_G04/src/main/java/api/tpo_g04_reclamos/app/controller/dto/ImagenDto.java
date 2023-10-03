package api.tpo_g04_reclamos.app.controller.dto;

import api.tpo_g04_reclamos.app.model.entity.Imagen;

public class ImagenDto {
    private String id;
    private String nombre;
    private String tipo;
    private byte[] data;
    public String getId() {
		return id;
	}
    public String getNombre() {
		return nombre;
	}
    public String getTipo() {
		return tipo;
	}
    public byte[] getData() {
		return data;
	}
    public ImagenDto(Imagen img) {
    	this.id = img.getId();
    	this.nombre = img.getNombre();
    	this.tipo = img.getTipo();
    	this.data = img.getData();
    }
}
