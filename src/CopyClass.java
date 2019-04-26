import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import javax.lang.model.util.Types;

public class CopyClass {

	public static void main(String[] args)
			throws ClassNotFoundException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException, InvocationTargetException, NoSuchMethodException {


		Persona persona = new Persona();
		persona.setApellido("Cuichan");
		persona.setId(1L);
		persona.setNombre("Paul");
		ClassReflection classReflection = new ClassReflection();
		Class clasePersona = persona.getClass();
		Class claseCopyPersona = classReflection.getClass();


		Method[] methods = clasePersona.getMethods();
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
		    		Long value = (Long) m.invoke(persona, null);
		    		System.out.println("valor devuelto es:" +value);
		    		Method methodInvokeforCopy =  claseCopyPersona.getMethod(nombreMetodo,Long.class);
		    		methodInvokeforCopy.invoke(classReflection, value);
		    		System.out.println("valor copiado es:" +classReflection.getId());
		    	}
		    }
			
		}
		System.out.println();

	}

}
