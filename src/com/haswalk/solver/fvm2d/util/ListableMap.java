package com.haswalk.solver.fvm2d.util;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class ListableMap<K, V>{
	private List<KVPair> list = new ArrayList<>();
	
	public ListableMap<K, V> put(K key, V value){
		int index = indexOf(key);
		if(index < 0) {
			list.add(new KVPair(key, value));
			return this;
		}
		list.set(index, new KVPair(key, value));
		return this;
	}
	
	public V get(K key){
		V v = null;
		for(KVPair p:list) {
			if(key == p.key) {
				v = p.value;
				break;
			}
		}
		return v;
	}
	
	private int indexOf(K key){
		int index = -1;
		for(int i = 0; i < list.size(); i++) {
			KVPair p = list.get(i);
			if(p.key == key) {
				index = i;
				break;
			}
		}
		return index;
	}
	
	public boolean contains(K key){
		boolean isContain = false;
		for(KVPair p:list) {
			if(key == p.key) {
				isContain = true;
			}
		}
		return isContain;
	}
	
	public V get(int index) {
		if(index >= size()) {
			throw new RuntimeException("Error: out of bound: " + index);
		}
		return list.get(index).value;
	}
	
	public int size() {
		return list.size();
	}
	
	public void forEach(BiConsumer<K, V> action){
		for(int i = 0, len = list.size(); i < len; i++) {
			action.accept(list.get(i).key, list.get(i).value);
		}
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		list.forEach(p -> builder.append(p.key.toString()+ ": " + p.value.toString() + "\n"));
		return builder.toString();
	}
	
	class KVPair{
		KVPair(K key, V value){
			this.key = key;
			this.value = value;
		}
		K key;
		V value;
	}
}
