package domain;

import java.lang.reflect.Type;
import java.util.List;

public class ObjectsValids {
	private Type type;
	private Type newType;
	private int[] levels;
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}

	public ObjectsValids(Type type) {
		this.type = type;
	}
	
	
	public int[] getLevels() {
		return levels;
	}
	public void setLevels(int[] levels) {
		this.levels = levels;
	}
	public ObjectsValids(Type type, Type newType) {
		
		this.type = type;
		this.newType = newType;
	}
	
	public ObjectsValids(Type type, Type newType, int[] levels) {
		this.type = type;
		this.newType = newType;
		this.levels = levels;
	}
	
	public Type getNewType() {
		return newType;
	}
	public void setNewType(Type newType) {
		this.newType = newType;
	}
	
	
	
}
