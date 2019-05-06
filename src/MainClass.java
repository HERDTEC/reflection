import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.lang.model.util.Types;

import domain.Persona;
import domain.PersonaTipo;
import domain.Tipo;
import dto.PersonaDTO;
import dto.PersonaTipoDTO;
import dto.TipoDTO;
import util.dto.DTOUtil;
import util.dto.ObjectsValids;

public class MainClass {

	public static void main(String[] args)
			throws ClassNotFoundException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException,
			SecurityException, InvocationTargetException, NoSuchMethodException, InstantiationException {

		Persona persona = new Persona();
		persona.setApellido("Cuichan");
		persona.setId(1L);
		persona.setNombre("Paul");
		
		List<PersonaTipo> personasTipo= new ArrayList<PersonaTipo>();
		PersonaTipo tipop = new PersonaTipo();
		tipop.setPersona(persona);
		tipop.setId(2L);
		tipop.setTipo(new Tipo(2L,"x2","xxx2"));
		personasTipo.add(tipop);
		persona.setPersonaTipo(personasTipo);
		
		// PersonaDTO personaDTO = new PersonaDTO();
		// copiar(persona, personaDTO);

		PersonaTipo tipo = new PersonaTipo();
		tipo.setPersona(persona);
		tipo.setId(1L);
		tipo.setTipo(new Tipo(1L,"x","xxx"));

		PersonaTipoDTO personaTipoDTO = new PersonaTipoDTO();

		List<ObjectsValids> validos = new ArrayList<>();
		
		
		validos.add(new ObjectsValids(Persona.class,PersonaDTO.class,new int[]{1,3,4}) );
		validos.add(new ObjectsValids(PersonaTipo.class,PersonaTipoDTO.class,new int[]{2,3}) );
		validos.add(new ObjectsValids(Tipo.class, TipoDTO.class,new int[]{2,1}) );
		DTOUtil.production=true;
		personaTipoDTO=(PersonaTipoDTO) DTOUtil.copyDTO(tipo, personaTipoDTO, validos,1);
		System.out.println("88888888888888888888888888888888888888888888888888888888888888888888888888888888888888888");
		System.out.println("88888888888888888888888888888888888888888888888888888888888888888888888888888888888888888");
		System.out.println(tipo.toString());
		System.out.println(personaTipoDTO.toString());
		System.out.println("88888888888888888888888888888888888888888888888888888888888888888888888888888888888888888");
		System.out.println("88888888888888888888888888888888888888888888888888888888888888888888888888888888888888888");	
	}
	

}
