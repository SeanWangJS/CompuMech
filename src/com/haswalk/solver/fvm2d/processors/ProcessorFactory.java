package com.haswalk.solver.fvm2d.processors;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import com.haswalk.solver.fvm2d.annotation.Inject;
import com.haswalk.solver.fvm2d.annotation.Injection;
import com.haswalk.solver.fvm2d.components.Components;
import com.haswalk.solver.fvm2d.config.Config;

public class ProcessorFactory {
	
	public Processor create(ProcessorCreationMethod method, Config config, HashMap<Integer, Components> componentsMap){
		return method.invoke(config, componentsMap);
	}
	
	public Processor create(String name, Class<?> clazz, Components components) {
		Processor processor = null;
		try {
			processor = (Processor) clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e1) {
			System.err.println("Error: create instance of " + name
					+ " failed. \n...Please check out if you have already regist this class");
			e1.printStackTrace();
		}

		Method[] methods = processor.getClass().getMethods();
		for (Method method : methods) {
			if (method.isAnnotationPresent(Inject.class)) {
				String paramName = method.getAnnotation(Inject.class).value();
				for (Object component : components.getAll()) {
					Method[] cmethods = component.getClass().getMethods();
					Method getMethod = null;
					for (Method m : cmethods) {
						if (m.getName().equals("get")) {
							getMethod = m;
							break;
						}
					}
					Object param = null;
					if (getMethod != null) {
						try {
							param = getMethod.invoke(component, paramName);
						} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
							System.err.println("Error: Inject " + paramName + " to " + processor.getClass().getSimpleName() + " from " + component.getClass().getSimpleName() + " failed.");
							e.printStackTrace();
						}
					}
					if (param != null) {
						try {
							method.invoke(processor, param);
							break;
						} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
							e.printStackTrace();
						}
					}
				}

			}
			if (method.isAnnotationPresent(Injection.class)) {
				Class<?>[] paramsClazz = method.getParameterTypes();
				Object[] objects = new Object[paramsClazz.length];
				for (int i = 0; i < paramsClazz.length; i++) {
					objects[i] = components.get(paramsClazz[i].getSimpleName());
				}
				try {
					method.invoke(processor, objects);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}

		return processor;
	}

}
