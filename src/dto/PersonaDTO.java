package dto;

import java.util.List;

public class PersonaDTO {
	private Long id;
	private String nombre;
	private String apellido;
	private List<PersonaTipoDTO> personaTipo;
	

	public PersonaDTO() {
		
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
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	
	public List<PersonaTipoDTO> getPersonaTipo() {
		return personaTipo;
	}
	public void setPersonaTipo(List<PersonaTipoDTO> personaTipo) {
		this.personaTipo = personaTipo;
	}
	@Override
	public String toString() {
		return "PersonaDTO [id=" + id + "]";
	}
	
	
	
	
}
