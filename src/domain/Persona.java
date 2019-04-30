package domain;

import java.util.List;

public class Persona {
	private Long id;
	private String nombre;
	private String apellido;
	private List<PersonaTipo> personaTipo;
	

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
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	
	public List<PersonaTipo> getPersonaTipo() {
		return personaTipo;
	}
	public void setPersonaTipo(List<PersonaTipo> personaTipo) {
		this.personaTipo = personaTipo;
	}
	@Override
	public String toString() {
		return "Persona [id=" + id 
				+ "]";
	}
	
	
	
	
}
