package com.haswalk.solver.fvm1d.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public interface SetMethod {
	default void setProperty(Object obj, String property, Object value){
		String first = property.substring(0, 1);
		try {
			Method method = obj.getClass().getMethod("set" + property.replaceFirst(first, first.toUpperCase()), value.getClass());
			method.invoke(obj, value);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
		
	}
}
