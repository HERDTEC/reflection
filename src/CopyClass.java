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
import domain.ObjectsValids;
import dto.PersonaDTO;
import dto.PersonaTipoDTO;
import dto.TipoDTO;

public class CopyClass {

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
		
		
		validos.add(new ObjectsValids(Persona.class,PersonaDTO.class,new int[]{1}) );
		validos.add(new ObjectsValids(PersonaTipo.class,PersonaTipoDTO.class,new int[]{2,3}) );
		validos.add(new ObjectsValids(Tipo.class, TipoDTO.class,new int[]{2,1}) );

		personaTipoDTO=(PersonaTipoDTO) copiar(tipo, personaTipoDTO, validos,1);
		System.out.println("88888888888888888888888888888888888888888888888888888888888888888888888888888888888888888");
		System.out.println("88888888888888888888888888888888888888888888888888888888888888888888888888888888888888888");
		System.out.println(tipo.toString());
		System.out.println(personaTipoDTO.toString());
		System.out.println("88888888888888888888888888888888888888888888888888888888888888888888888888888888888888888");
		System.out.println("88888888888888888888888888888888888888888888888888888888888888888888888888888888888888888");
		
	}

	public static Boolean compareArrayLevels(int[] levels, int level) {
		if (level!=0&&levels!=null)
			for (int i : levels) {
				if(i==level)
					return true;
			}
		return false;
	} 
	public static ObjectsValids compareArrayTypesWithLevel(Type type, List<ObjectsValids> objectValids, int level) {
		Optional<ObjectsValids> optional = objectValids.stream()
				.filter(ov -> 
						type.equals(ov.getType()) &&  compareArrayLevels(ov.getLevels(), level)
					).findFirst();
		if (optional.isPresent()) {// Check whether optional has element you are looking for
			return optional.get();
		}
		return null;
	}
	public static ObjectsValids compareArrayTypes(Type type, List<ObjectsValids> objectValids) {
		Optional<ObjectsValids> optional = objectValids.stream()
				.filter(ov -> 
						type.equals(ov.getType())
					).findFirst();
		if (optional.isPresent()) {// Check whether optional has element you are looking for
			return optional.get();
		}
		return null;
	}


	@SuppressWarnings({ "unchecked", "null" })
public static Object copiar(Object objetoPersistente, Object objetoDTO, List<ObjectsValids> objectValids, int level)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException,
			SecurityException, ClassNotFoundException, InstantiationException {
		Class classObjectPersistent = objetoPersistente.getClass();
		Class classObjectDTO = objetoDTO.getClass();
		Method[] methods = classObjectPersistent.getMethods();
		System.out.println("-+-+--+-+--+-+--+-+--+-+--+-+--+-+--+-+--+-+--+-+--+-+--+-+--+-+--+-+--+-+--+-+--+-+--+-+-");
		System.out.println("-+-+--+-+--+-+--+-+--+-+--+-+--+-+--+-+--+-+--+-+--+-+--+-+--+-+--+-+--+-+--+-+--+-+--+-+-");
		System.out.println("ITERACION:     "+objetoPersistente.getClass().getName());
		System.out.println(objetoPersistente.toString() );
		System.out.println("-+-+--+-+--+-+--+-+--+-+--+-+--+-+--+-+--+-+--+-+--+-+--+-+--+-+--+-+--+-+--+-+--+-+--+-+-");
		System.out.println("-+-+--+-+--+-+--+-+--+-+--+-+--+-+--+-+--+-+--+-+--+-+--+-+--+-+--+-+--+-+--+-+--+-+--+-+-");
		for (Method m : methods) {
			String nombreMetodo = m.getName();
			String nombreOriginalMetodo= m.getName();
			int isGet = nombreMetodo.indexOf("g");
			if (isGet == 0) {
				try {
					
			    	nombreMetodo= nombreOriginalMetodo.replaceFirst("g", "s");
			    	System.out.println("+++++++++++++++++++++++++ "+nombreMetodo+" +++++++++++++++++++++++++");
			    	ObjectsValids valido= compareArrayTypesWithLevel(m.getReturnType(), objectValids,level);
			    	if(m.getReturnType().equals(List.class)){
		    			System.out.println("OOOOOOOOOOOOOOOOOOOOO es una lista OOOOOOOOOOOOOOOOOOOOOOo");
		    			Object valuesWthT =   m.invoke(objetoPersistente, null);
		    			Class c = Class.forName(valuesWthT.getClass().getName());
		    			Object values = c.newInstance();
		    			values= m.invoke(objetoPersistente, null);
		    			
		    			
		    			Class stringArrayComponentType = valuesWthT.getClass().getComponentType();
		    			System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
		    			System.out.println(stringArrayComponentType.getClass());
		    			
		    			
		    			List objListDTO=null;
		    			((List)values).forEach(v->{
		    				 System.out.println(level);
		    			     ObjectsValids validoArr= compareArrayTypesWithLevel(v.getClass(), objectValids,level);
		    			 	if(validoArr!=null) 
		    			 	{
		    			 		Class cfe;
								try {
									cfe = Class.forName(validoArr.getNewType().getTypeName());
						    		Object objectIDTO = cfe.newInstance();
						    		if(v!=null) {
						    			objectIDTO=copiar(v, objectIDTO, objectValids,level+1);
						    			objListDTO.add(objectIDTO);//
						    		}
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} 
				    		}
		    			 });
		    			if(objListDTO!=null)
			    		{
			    			Method methodForGetClass = classObjectDTO.getMethod(nombreOriginalMetodo,null);
			    			Method methodInvokeforCopy =  classObjectDTO.getMethod(nombreMetodo,methodForGetClass.getReturnType());
			    			methodInvokeforCopy.invoke(objetoDTO, objListDTO);
			    		}
		    			System.out.println("OOOOOOOOOOOOOOOOOOOOO es una lista OOOOOOOOOOOOOOOOOOOOOOo");
		    			
		    			
		    			
		    			
		    		}
		    		else
			    	if(valido!=null) 
			    	{
			    		Class c = Class.forName(valido.getNewType().getTypeName());
			    		Object objectIDTO = c.newInstance();
			    		c = Class.forName(valido.getType().getTypeName());
			    		Object objectIPersistent = c.newInstance();
			    		objectIPersistent=m.invoke(objetoPersistente, null);
			    		if(objectIPersistent!=null) {
			    			objectIDTO=copiar(objectIPersistent, objectIDTO, objectValids,level+1);
			    			Method methodForGetClass = classObjectDTO.getMethod(nombreOriginalMetodo,null);
							Method methodInvokeforCopy =  classObjectDTO.getMethod(nombreMetodo,methodForGetClass.getReturnType());
			    			methodInvokeforCopy.invoke(objetoDTO, objectIDTO);	
			    		}
			    	}
			    	else if(compareArrayTypes(m.getReturnType(), objectValids)==null){
			    		Object value =  m.invoke(objetoPersistente, null);
			    		if(value!=null)
			    		{
			    			Method methodForGetClass = classObjectDTO.getMethod(nombreOriginalMetodo,null);
			    			Method methodInvokeforCopy =  classObjectDTO.getMethod(nombreMetodo,methodForGetClass.getReturnType());
			    			methodInvokeforCopy.invoke(objetoDTO, value);
			    		}
			    		
			    	}

				} catch (NoSuchMethodException e) {
					System.out.println("-----------------------"+nombreOriginalMetodo+"---------------------------");
					System.out.println("EEEEEEEEEEEEEEEEE  PROPIEDADES FUERA DE COPIA    EEEEEEEEEEEEEEEEE");
					System.out.println("-------------------------------------------------------");
				}

			}

		}
		System.out.println();

		return objetoDTO;

	}

}
