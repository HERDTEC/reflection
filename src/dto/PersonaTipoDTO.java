
package dto;

public class PersonaTipoDTO {
	private Long id;
	private PersonaDTO persona;
	private TipoDTO tipo;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public PersonaDTO getPersona() {
		return persona;
	}
	public void setPersona(PersonaDTO persona) {
		this.persona = persona;
	}
	public TipoDTO getTipo() {
		return tipo;
	}
	public void setTipo(TipoDTO tipo) {
		this.tipo = tipo;
	}
	@Override
	public String toString() {
		return "PersonaTipoDTO [id=" + id + ", persona=" + persona + ", tipo=" + tipo + "]";
	}

	
}
