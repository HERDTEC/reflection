import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import javax.lang.model.util.Types;

import domain.Persona;
import domain.PersonaTipo;
import domain.Tipo;
import dto.PersonaDTO;
import dto.PersonaTipoDTO;

public class CopyClass {

	public static void main(String[] args)
			throws ClassNotFoundException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException, InvocationTargetException, NoSuchMethodException {

		Persona persona = new Persona();
		persona.setApellido("Cuichan");
		persona.setId(1L);
		persona.setNombre("Paul");
		//PersonaDTO personaDTO = new PersonaDTO();
		//copiar(persona, personaDTO);
		
		PersonaTipo tipo = new PersonaTipo();
		tipo.setPersona(persona);
		tipo.setId(1L);
		tipo.setTipo(null);
		
		PersonaTipoDTO personaTipoDTO = new PersonaTipoDTO();
		copiar(tipo, personaTipoDTO);
	}
	
	public static Object copiar(Object objetoPersistente,  Object objetoDTO ) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		
		Class classObjectPersistent = objetoPersistente.getClass();
		Class classObjectDTO = objetoDTO.getClass();


		Method[] methods = classObjectPersistent.getMethods();
		System.out.println("Las propiedades publicas implementadas son:");
		for (Method m : methods) {
			
		    String nombreMetodo= m.getName();
		    int isGet = nombreMetodo.indexOf("g");
		    if(isGet ==0 )
		    {
		    	System.out.println("es una funcion Get");
		    	nombreMetodo= nombreMetodo.replaceFirst("g", "s");
		    	System.out.println(nombreMetodo);
		    	if(m.getReturnType().equals(Long.class)) {
		    		Long value = (Long) m.invoke(objetoPersistente, null);
		    		System.out.println("valor devuelto es:" +value);
		    		Method methodInvokeforCopy =  classObjectDTO.getMethod(nombreMetodo,Long.class);
		    		methodInvokeforCopy.invoke(objetoDTO, value);
		    		
		    	}
		    	if(m.getReturnType().equals(String.class)) {
		    		String value = (String) m.invoke(objetoPersistente, null);
		    		System.out.println("valor devuelto es:" +value);
		    		Method methodInvokeforCopy =  classObjectDTO.getMethod(nombreMetodo,String.class);
		    		methodInvokeforCopy.invoke(objetoDTO, value);
		    		
		    	}
		    	if(m.getReturnType().equals(Object.class)) {
		    		
		    		System.out.println("hay un objeto" );
		    		
		    	}
		    	
		    }
			
		}
		System.out.println();
		System.out.println("***********************************************************");
		System.out.println(objetoDTO.toString());
		return objetoDTO;

	}

}
