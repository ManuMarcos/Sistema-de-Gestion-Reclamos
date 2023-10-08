package api.tpo_g04_reclamos.app.model.request;

import api.tpo_g04_reclamos.app.model.entity.Imagen;

import java.util.List;

public class ImagenDto {

    private String id;

    private String nombre;

    private String tipo;

    private byte[] data;

    private ReclamoRequestDto reclamo;

    public ImagenDto(String id, String nombre, String tipo, byte[] data, ReclamoRequestDto reclamo) {
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
        this.reclamo = imagen.getReclamo() != null ? new ReclamoRequestDto(imagen.getReclamo()) : null;
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

    public ReclamoRequestDto getReclamo() {
        return reclamo;
    }

    public static List<ImagenDto> fromList(List<Imagen> imagenes) {
        return imagenes.stream().map(ImagenDto::new).toList();
    }

}
