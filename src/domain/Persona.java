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
	
	public Persona(Long id, String nombre, String apellido, List<PersonaTipo> personaTipo) {

		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.personaTipo = personaTipo;
	}
	
	public Persona() {
	}
	@Override
	public String toString() {
		return "Persona [id=" + id 
				+ "]";
	}
	
	
	
	
}
