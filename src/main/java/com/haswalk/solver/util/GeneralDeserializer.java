package com.haswalk.solver.util;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

public class GeneralDeserializer {
	
	private Gson gson = new GsonBuilder().setPrettyPrinting().create();
	private Pattern pattern = Pattern.compile("(?<=<).+?(?=>)");
	
	private Object deJsonArray(JsonArray jsonArr, Class<?> fieldClazz) {
		Class<?> componentType = fieldClazz.getComponentType();
		Object arr = Array.newInstance(componentType, jsonArr.size());
		for(int i = 0; i < jsonArr.size(); i++) {	
			if(jsonArr.get(i).isJsonPrimitive()) {
				Array.set(arr, i, dePrimitive(jsonArr.get(i).getAsJsonPrimitive(), componentType));
			} else{
				Array.set(arr, i, deserialize(jsonArr.get(i), componentType));
			}
		}
		return arr;
	}
	
	private Object deJsonArray(JsonArray jsonArr, Class<?> containerType, Class<?> componentType){
		Object arr = null;
		try {
			arr = containerType.getDeclaredConstructor().newInstance();
			Method addMethod = containerType.getMethod("add", Object.class);
			for(int i = 0; i < jsonArr.size(); i++) {
				JsonElement je = jsonArr.get(i);
				if(je.isJsonPrimitive()) {
					addMethod.invoke(arr, dePrimitive(je.getAsJsonPrimitive(), componentType));
				}
			}
		} catch (InstantiationException | IllegalAccessException | NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
		
		return arr;
	}
	
	private Object deJsonMap(JsonElement jsonElem, Class<?> containerType, Class<?> keyType, Class<?> valueType){
		Object arr = null;
		try {
			arr = containerType.getDeclaredConstructor().newInstance();
			Method putMethod = containerType.getMethod("put", Object.class, Object.class);
			for(Entry<String, JsonElement> e: jsonElem.getAsJsonObject().entrySet()) {
				if(e.getValue().isJsonPrimitive()) {
					putMethod.invoke(arr, dePrimitive(e.getKey(), keyType), dePrimitive(e.getValue().getAsJsonPrimitive(), valueType));
				} else{
					putMethod.invoke(arr, dePrimitive(e.getKey(), keyType), deserialize(jsonElem, valueType));
				}
			}
		} catch (InstantiationException | IllegalAccessException | NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
		
		return arr;
	}
	
	private Object dePrimitive(String str, Class<?> clazz) {
		return dePrimitive(gson.fromJson(str, JsonPrimitive.class), clazz);
	}
	
	private Object dePrimitive(JsonPrimitive jsonPri, Class<?> clazz){
		if(jsonPri.isBoolean()) {
			return jsonPri.getAsBoolean();
		}else if(jsonPri.isString()) {
			return jsonPri.getAsString();
		} else if("char".equals(clazz.getTypeName()) || "java.lang.Character".equals(clazz.getTypeName())){
			return jsonPri.getAsCharacter();
		}else if(jsonPri.isNumber()) {
			switch(clazz.getTypeName()){
			case "int":
				return jsonPri.getAsInt();
			case "java.lang.Integer":
				return jsonPri.getAsInt();
			case "double":
				return jsonPri.getAsDouble();
			case "java.lang.Double":
				return jsonPri.getAsDouble();
			case "float":
				return jsonPri.getAsFloat();
			case "java.lang.Float":
				return jsonPri.getAsFloat();
			case "short":
				return jsonPri.getAsShort();
			case "java.lang.Short":
				return jsonPri.getAsShort();
			case "long":
				return jsonPri.getAsLong();
			case "java.lang.Long":
				return jsonPri.getAsLong();
			case "byte": 
				return jsonPri.getAsByte();
			case "java.lang.Byte":
				return jsonPri.getAsByte();
			default:
				return null;
			}
		}
		return null;
	}
	
	private Object deObject(JsonObject jsonObj, Class<?> clazz) {
		Object obj = null;
		try {
			try {
				obj = clazz.getDeclaredConstructor().newInstance();
			} catch (InvocationTargetException | NoSuchMethodException e) {
				e.printStackTrace();
			}
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		Field[] fields = clazz.getDeclaredFields();
		for(Field field: fields) {
			if(field.isAnnotationPresent(Serialize.class)) {
				String fieldName = field.getName();
				JsonElement fieldJson = jsonObj.get(fieldName);
				
				String fieldTypeName = field.getGenericType().getTypeName();
				Class<?> fieldClazz = field.getType();
				Method method = getFieldSetMethod(clazz, fieldName, fieldClazz);
				
				Object param = null;
				if(isList(fieldTypeName)) {
					Matcher m = pattern.matcher(fieldTypeName);
					Class<?> componentType = null;
					if(m.find()){
						componentType = forName(m.group());
					}
					param = deJsonArray(fieldJson.getAsJsonArray(), ArrayList.class, componentType);
				} else if(isSet(fieldTypeName)) {
					Matcher m = pattern.matcher(fieldTypeName);
					Class<?> componentType = null;
					if(m.find()) {
						componentType = forName(m.group());
					}
					param = deJsonArray(fieldJson.getAsJsonArray(), HashSet.class, componentType);
				} else if(isMap(fieldTypeName)) {
					Matcher m = pattern.matcher(fieldTypeName);
					if(m.find()) {
						String[] tmp = m.group().split(",\\s+");
						Class<?> keyType = forName(tmp[0]);
						Class<?> valueType = forName(tmp[1]);
						param = deJsonMap(fieldJson, HashMap.class, keyType, valueType);
						
					}
				} else{
					param = deserialize(fieldJson, fieldClazz);
				}
				try {
					method.invoke(obj, param);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
		return obj;
	}
	
	public Object deserialize(JsonElement elem, Type type) {
		return deserialize(elem, forName(type.getTypeName()));
	}
	
	public Object deserialize(JsonElement elem, Class<?> clazz){
		if(elem.isJsonArray()) {
			return deJsonArray(elem.getAsJsonArray(), clazz);
		} else if(elem.isJsonPrimitive()) {
			return dePrimitive(elem.getAsJsonPrimitive(), clazz);
		} else if(elem.isJsonNull()) {
			return null;
		} 
		return deObject(elem.getAsJsonObject(), clazz);
		
	}
	
	private Method getFieldSetMethod(Class<?> clazz, String simplifiedFieldName, Class<?> fieldClazz) {
		String firstChar = simplifiedFieldName.substring(0, 1);
		String firstCharUpperCase = firstChar.toUpperCase();
		String fieldSetMethodName = "set" + simplifiedFieldName.replaceFirst(firstChar, firstCharUpperCase);
		try {
			return clazz.getDeclaredMethod(fieldSetMethodName, fieldClazz);
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private Class<?> forName(String clazzName) {
		try {
			return Class.forName(clazzName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private boolean isList(String typeName) {
		return typeName.contains("List");
	}
	
	private boolean isSet(String typeName) {
		return typeName.contains("Set");
	}
	
	private boolean isMap(String typeName){
		return typeName.contains("Map");
	}
}
