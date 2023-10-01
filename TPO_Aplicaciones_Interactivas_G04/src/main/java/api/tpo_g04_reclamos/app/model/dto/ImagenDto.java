package api.tpo_g04_reclamos.app.model.dto;

import api.tpo_g04_reclamos.app.model.entity.Reclamo;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;

public class ImagenDto {

    private String id;

    private String nombre;

    private String tipo;

    private byte[] data;

    private ReclamoDto reclamo;

    public ImagenDto(String id, String nombre, String tipo, byte[] data, ReclamoDto reclamo) {
        super();
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.data = data;
        this.reclamo = reclamo;
    }

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

    public ReclamoDto getReclamo() {
        return reclamo;
    }

}
