package com.haswalk.solver.fvm1d.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public interface SetMethod {
	
	static List<String> box = Arrays.asList("Integer", "Float", "Double",
			"Short", "Character", "Long", "Boolean");
	static List<Class<?>> pri = Arrays.asList(int.class, float.class, double.class, short.class,
			char.class, long.class, boolean.class);
	
	default void setProperty(Object obj, String property, Object value){
		
		String first = property.substring(0, 1);
		Method method = null;
		String setMethodName = "set" + property.replaceFirst(first, first.toUpperCase());
		try {
			method = obj.getClass().getMethod(setMethodName, value.getClass());
		} catch (SecurityException | IllegalArgumentException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			Class<?> clazz = pri.get(box.indexOf(value.getClass().getSimpleName()));
			try {
				method = obj.getClass().getMethod(setMethodName, clazz);
			} catch (NoSuchMethodException | SecurityException e1) {
				e1.printStackTrace();
			}
		}
		try {
			method.invoke(obj, value);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}
}
