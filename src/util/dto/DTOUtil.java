package util.dto;
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

public class DTOUtil {

	    public static Boolean production=false;
		
		public static Boolean compareArrayLevels(int[] levels, int level) {
			if (level!=0&&levels!=null)
				for (int i : levels) {
					if(i==level)
						return true;
				}
			return false;
		} 
		public static ObjectsValids compareArrayTypesWithLevel(Type type, List<ObjectsValids> objectValids, Integer level) {
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


		@SuppressWarnings({ "unchecked", "null", "rawtypes" })
	public static Object copyDTO(Object objetoPersistente, Object objetoDTO, List<ObjectsValids> objectValids, Integer level)
				throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException,
				SecurityException, ClassNotFoundException, InstantiationException {
			Class classObjectPersistent = objetoPersistente.getClass();
			Class classObjectDTO = objetoDTO.getClass();
			Method[] methods = classObjectPersistent.getMethods();
			MyPrint("-+-+--+-+--+-+--+-+--+-+--+-+--+-+--+-+--+-+--+-+--+-+--+-+--+-+--+-+--+-+--+-+--+-+--+-+-");
			MyPrint("-+-+--+-+--+-+--+-+--+-+--+-+--+-+--+-+--+-+--+-+--+-+--+-+--+-+--+-+--+-+--+-+--+-+--+-+-");
			MyPrint("ITERACION:     "+level);
			MyPrint("OBJETO:     "+objetoPersistente.getClass().getName());
			
			for (Method m : methods) {
				String nombreMetodo = m.getName();
				String nombreOriginalMetodo= m.getName();
				int isGet = nombreOriginalMetodo.indexOf("g");
				if (isGet == 0 && !nombreOriginalMetodo.equals("getClass")) {
					try {
						
						MyPrint(level+")"+nombreOriginalMetodo);
						MyPrint("-+-+--+-+--+-+--+-+--+-+--+-+--+-+--+-+--+-+--+-+--+-+--+-+--+-+--+-+--+-+--+-+--+-+--+-+-");
						MyPrint("-+-+--+-+--+-+--+-+--+-+--+-+--+-+--+-+--+-+--+-+--+-+--+-+--+-+--+-+--+-+--+-+--+-+--+-+-");
						
						if(nombreOriginalMetodo=="getTipo" && level ==3) 
			    			MyPrint(level+"///"+nombreOriginalMetodo+"///"+m.getReturnType());
				    	nombreMetodo= nombreOriginalMetodo.replaceFirst("g", "s");
				    	ObjectsValids valido= compareArrayTypesWithLevel(m.getReturnType(), objectValids,level);
				    	List listObjects=new ArrayList<>();
				    	if(m.getReturnType().equals(List.class)){
				    		List values=   (List) m.invoke(objetoPersistente, null);
			    			Method metodoDTO=  classObjectDTO.getMethod(nombreOriginalMetodo, null);
			    			Object valuesArrayDTO =Array.newInstance(metodoDTO.getReturnType(),((List)values).size());
			    			Class classInternalObject= ((List)values).get(0).getClass();
			    			ObjectsValids validateInternalComponent= compareArrayTypesWithLevel(classInternalObject, objectValids,level);
			    			if(validateInternalComponent==null && compareArrayTypes(classInternalObject, objectValids)!=null) {
			    				MyPrint("Carga fuera de rango nivel: "+level+", clase :"+classInternalObject);
			    				Method methodForGetClass = classObjectDTO.getMethod(nombreOriginalMetodo,null);
				    			Method methodInvokeforCopy =  classObjectDTO.getMethod(nombreMetodo,methodForGetClass.getReturnType());   			
				    			methodInvokeforCopy.invoke(objetoDTO, listObjects);
			    			}
			    			else {
				    			Class classInternalDTO= Class.forName(validateInternalComponent.getNewType().getTypeName());
				    			
				    			((List)values).forEach(v->{
				    			 	if(validateInternalComponent!=null) 
				    			 	{
				    			 		Class cfe=null;
										try {
								    		Object objectInternalDTO =classInternalDTO.newInstance();
								    		if(v!=null) {
								    			objectInternalDTO=copyDTO(v, objectInternalDTO, objectValids,level+1);
								    			listObjects.add(objectInternalDTO);
								    		}
										} catch (Exception e) {
											MyPrint(e.getMessage().toString());
										} 
						    		}
				    			 });
				    			Method methodForGetClass = classObjectDTO.getMethod(nombreOriginalMetodo,null);
				    			Method methodInvokeforCopy =  classObjectDTO.getMethod(nombreMetodo,methodForGetClass.getReturnType());   			
				    			methodInvokeforCopy.invoke(objetoDTO, listObjects);	
			    			}
			    		}
			    		else
				    	if(valido!=null&&compareArrayTypes(m.getReturnType(), objectValids)!=null) 
				    	{

				    		
				    		Class c = Class.forName(valido.getNewType().getTypeName());
				    		Object objectIDTO = c.newInstance();
				    		c = Class.forName(valido.getType().getTypeName());
				    		Object objectIPersistent = c.newInstance();
				    		objectIPersistent=m.invoke(objetoPersistente, null);
				    		if(objectIPersistent!=null) {
				    			objectIDTO=copyDTO(objectIPersistent, objectIDTO, objectValids,level+1);
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
						MyPrint(e.getMessage().toString());
					}

				}

			}
			return objetoDTO;
		}
		
		public static void MyPrint(String msg) {
			if(production==false)
				System.out.println(msg);
		}

	
}
