import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class CopyClass {

	public static void main(String[] args)
			throws ClassNotFoundException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {


		Persona persona = new Persona();
		persona.setApellido("Cuichan");
		persona.setId(1L);
		persona.setNombre("Paul");
		ClassReflection classReflection = new ClassReflection();
		Class clasePersona = persona.getClass();
		Class claseCopyPersona = classReflection.getClass();


		Method[] methods = clasePersona.getMethods();
		System.out.println("Las propiedades publicas implementadas son:");
		for (Method f : methods) {
			f.setAccessible(true);
			System.out.println("Metodo:"+f.getName());
			
			
			
			Method methodForCopy = claseCopyPersona.getMethod(f.getName());
			fieldCopy.setAccessible(true);
			
			
			
			if (f.getType().equals(Long.class)) { 
				long var = 2;
				fieldCopy.setLong(claseCopyPersona,  var);
			}
			else if (f.getType().equals(String.class)) 
				fieldCopy.set(claseCopyPersona, f.get(persona));
			else 
				fieldCopy.set(claseCopyPersona, f.get(persona));
			
			System.out.println("valor copiado:" + fieldCopy.get(persona));
		}
		System.out.println();

	}

}
