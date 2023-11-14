package api.tpo_g04_reclamos.app.model.entity;

import jakarta.persistence.*;

@Entity(name = "imagenes")
public class Imagen {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
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

	public Imagen(Long id, String nombre, String tipo, byte[] data) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.tipo = tipo;
		this.data = data;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
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

	public Reclamo getReclamo() {
		return reclamo;
	}

	public void setReclamo(Reclamo reclamo) {
		this.reclamo = reclamo;
	}

	@Override
	public boolean equals(Object obj) {
		return ((Imagen)obj).getId() == this.getId();
	}
    
}
