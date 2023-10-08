package api.tpo_g04_reclamos.app.controller.request;

import api.tpo_g04_reclamos.app.controller.dto.ReclamoDto;
import api.tpo_g04_reclamos.app.model.entity.Imagen;

import java.util.List;

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

    public ImagenDto(Imagen imagen) {
        super();
        this.id = imagen.getId();
        this.nombre = imagen.getNombre();
        this.tipo = imagen.getTipo();
        this.data = imagen.getData();
        this.reclamo = imagen.getReclamo() != null ? new ReclamoDto(imagen.getReclamo()) : null;
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

    public static List<ImagenDto> fromList(List<Imagen> imagenes) {
        return imagenes.stream().map(ImagenDto::new).toList();
    }

}
