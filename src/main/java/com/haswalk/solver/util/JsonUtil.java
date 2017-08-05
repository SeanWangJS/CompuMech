package com.haswalk.solver.util;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

public class JsonUtil {

	private final static Gson gson = new GsonBuilder().setPrettyPrinting().create();
	public static JsonObject merge(List<JsonObject> objs) {
		JsonObject result = objs.get(0);
		for (int i = 1; i < objs.size(); i++) {
			result = merge(result, objs.get(i));
		}
		return result;
	}

	public static JsonObject merge(JsonObject obj1, JsonObject obj2) {

		JsonObject result = new JsonObject();
		List<String> sameKey = new ArrayList<>();

		obj1.entrySet().forEach(e1 -> {
			obj2.entrySet().forEach(e2 -> {
				if (e1.getKey().equals(e2.getKey())) {
					sameKey.add(e1.getKey());
				} else {
					result.add(e1.getKey(), e1.getValue());
					result.add(e2.getKey(), e2.getValue());
				}
			});
		});

		sameKey.forEach(key -> {
			JsonElement je1 = obj1.get(key);
			JsonElement je2 = obj2.get(key);
			if (je1.isJsonObject() && je2.isJsonObject()) {
				result.add(key, merge(gson.fromJson(je1, JsonObject.class), gson.fromJson(je2, JsonObject.class)));
			} else if (!je1.isJsonObject() && !je2.isJsonObject()) {
				if (je1.isJsonArray() && je2.isJsonArray()) {
					result.add(key, merge(je1.getAsJsonArray(), je2.getAsJsonArray()));
				} else if (je1.isJsonArray() && !je2.isJsonArray()) {
					result.add(key, merge(je1.getAsJsonArray(), je2));
				} else if (!je1.isJsonArray() && je2.isJsonArray()) {
					result.add(key, merge(je2.getAsJsonArray(), je1));
				} else {
					result.add(key, obj1.get(key));
				}
			} else {
				System.err.println("Warnging: can not merge json key: '" + key
						+ "' because one is JsonObject but another one is not");
			}
		});

		return result;
	}

	public static JsonArray merge(JsonArray jarr1, JsonArray jarr2) {
		Type listType = new TypeToken<List<String>>() {
		}.getType();
		List<String> str1 = gson.fromJson(jarr1.toString(), listType);
		List<String> str2 = gson.fromJson(jarr2.toString(), listType);
		str1.addAll(str2);
		return gson.fromJson(str1.toString(), JsonArray.class);
	}

	public static JsonArray merge(JsonArray jarr, JsonElement jelem) {
		Type listType = new TypeToken<List<String>>() {
		}.getType();
		List<String> list = gson.fromJson(jarr.toString(), listType);
		if (!list.contains(jelem.getAsString())) {
			list.add(jelem.getAsString());
		}
		return gson.fromJson(list.toString(), JsonArray.class);
	}

	public static JsonObject read(String uri) {
		try {
			String json = new String(Files.readAllBytes(Paths.get(uri)));
			JsonObject jo = gson.fromJson(json, JsonObject.class);
			return jo;
		} catch (IOException | JsonSyntaxException e) {
			System.err.println("Error: when reading json file " + uri);
			e.printStackTrace();
		}
		return null;
	}

	public static String toJsonString(Field field, Object obj) {
		StringBuilder builder = new StringBuilder();
		field.setAccessible(true);
//		String key = StrUtil.wrapWith(field.getName(), "'");
		String key = "'" + field.getName() + "'";
		boolean isString = "String".equals(field.getType().getSimpleName());
		String value = null;
		try {
			Class<?> clazz = field.get(obj).getClass();
			if(clazz.isArray()){
				String name = clazz.getName();
				switch(name){
				case "[I":
					value = Arrays.toString((int[]) field.get(obj));
					break;
				case "[D":
					value = Arrays.toString((double[]) field.get(obj));
					break;
				case "[F":
					value = Arrays.toString((float[]) field.get(obj));
					break;
				case "[S":
					value = Arrays.toString((short[]) field.get(obj));
					break;
				case "[B":
					value = Arrays.toString((byte[]) field.get(obj));
					break;
				case "[Z":
					value = Arrays.toString((boolean[]) field.get(obj));
					break;
				case "[C":
					value = Arrays.toString((char[]) field.get(obj));
					break;
				default:
					value = Arrays.deepToString((Object[]) field.get(obj));
					break;
				}
			}else{
				value = field.get(obj).toString();
			}
			
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
	
			builder.append("").append(key)
								.append(":")
								.append(isString? "'" + value + "'" : value);
		return builder.toString();
	}
}
