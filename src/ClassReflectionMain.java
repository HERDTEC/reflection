import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class ClassReflectionMain {

	public static void main(String[] args) throws ClassNotFoundException, IllegalArgumentException, IllegalAccessException  {
 
        // 1 - Al usar el método Class.forname ()
		Class c1 = Class.forName("ClassReflection");
 
        // 2- Usando el método getClass ()
		ClassReflection classReflection = new ClassReflection();
		Class c2 = classReflection.getClass();
 
        // 3- Al usar .class
		Class c3 = ClassReflection.class;
		
		
		// Imprimir el nombre de la clase
        System.out.println("El nombre de la clase es:" + c3.getName());
 
        // Imprimir nombre de super clase
        System.out.println("El nombre de la superclase es:" + c3.getSuperclass().getName());
        
        
        Class[] interfaceList = c3.getInterfaces();
        
        // Imprimir las interfaces implementadas mediante el bucle foreach
        System.out.print("Las interfaces implementadas son:");
        for(Class i:  interfaceList) {
            System.out.println(i.getName() + "");
        }
        System.out.println();
        
        
     // Obtenga los modificadores de acceso usando el método get Modifiers () y el método toString () de la clase java.lang.reflect.Modifier
        int modifier = c3.getModifiers();
     
        // Imprimir los modificadores de acceso
        System.out.println("Los modificadores de acceso de la clase son:" + Modifier.toString(modifier));
        
//        El método getFields () devuelve los metadatos de la variable pública de la clase especificada y de su superclase.
        Field [] fields = c2.getFields ();
        System.out.println("Las propiedades publicas implementadas son:");
        for(Field f:  fields) {
            System.out.println(f.getName() + "");
            f.set(classReflection, "cargado por metodo reflextion");
            System.out.println("get:=>>"+ f.get(classReflection));
        }
        System.out.println();
//        El método getDeclaredFields () solo devuelve los metadatos de todas las variables de la clase especificada.
        Field [] allFields = c3.getDeclaredFields();
        
        System.out.println("Las propiedades implementadas son:");
        for(Field f:  allFields) {
            System.out.println(f.getName() + "");
        }
        System.out.println();
    }
}