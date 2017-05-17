package com.haswalk.solver.fvm1d.util;

public interface FormSetMethodName {

	default String formSetMethodName(String property) {
		String first = property.substring(0, 1);
		return "set" + property.replaceFirst(first, first.toUpperCase());
	}
	
}
