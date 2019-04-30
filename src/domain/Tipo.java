package domain;

public class Tipo {
	private Long id;
	private String nombre;
	private String detalle;
	public Long getId() {
		return id;
	}
	
	public Tipo() {
		
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
	public String getDetalle() {
		return detalle;
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	

	public Tipo(Long id, String nombre, String detalle) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.detalle = detalle;
	}
	@Override
	public String toString() {
		return "Tipo [id=" + id + "]";
	}
	
	
	
}
