package com.haswalk.solver.fvm1d.util;

import java.lang.reflect.Field;
import com.haswalk.jsonutil.Serialize;
import com.sean.utils.JsonUtil;

public interface ToString {

	public default String asString() {
		
		StringBuilder builder = new StringBuilder();
		builder.append("{");
		for(Field f: this.getClass().getDeclaredFields()) {
			if(f.isAnnotationPresent(Serialize.class)) {
				builder.append(JsonUtil.toJsonString(f, this))
						.append(",");
			}
		}
		builder.delete(builder.length() - 1, builder.length());
		builder.append("}");
		return builder.toString();
	}
}
