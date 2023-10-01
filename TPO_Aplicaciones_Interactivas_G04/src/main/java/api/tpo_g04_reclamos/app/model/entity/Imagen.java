package api.tpo_g04_reclamos.app.model.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity(name = "imagenes")
public class Imagen {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    private String nombre;
    private String tipo;
    
    @Lob
    @Column(name="image_data", nullable=false, columnDefinition="LONGBLOB")
    private byte[] data;

	@ManyToOne
	@JoinColumn(name = "reclamo_id")
	private Reclamo reclamo;
    
	public Imagen() {
		super();
	}


	public Imagen(String nombre, String tipo, byte[] data) {
		super();
		this.nombre = nombre;
		this.tipo = tipo;
		this.data = data;
	}

	public Imagen(String id, String nombre, String tipo, byte[] data) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.tipo = tipo;
		this.data = data;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public byte[] getData() {
		return data;
	}


	public void setData(byte[] data) {
		this.data = data;
	}
    
	@Override
	public boolean equals(Object obj) {
		return ((Imagen)obj).getId() == this.getId();
	}
    
}
