package dto;

public class TipoDTO {
	private Long id;
	private String nombre;
	private String detalle;
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
	public String getDetalle() {
		return detalle;
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	
	
	@Override
	public String toString() {
		return "TipoDTO [id=" + id + ", nombre=" + nombre + ", detalle=" + detalle + "]";
	}
	public TipoDTO(Long id, String nombre, String detalle) {
		
		this.id = id;
		this.nombre = nombre;
		this.detalle = detalle;
	}
	public TipoDTO() {
		
	}
	
}
