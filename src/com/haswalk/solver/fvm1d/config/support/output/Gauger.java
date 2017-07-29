package com.haswalk.solver.fvm1d.config.support.output;

import com.haswalk.jsonutil.Serialize;
import com.haswalk.solver.fvm1d.config.Output;
import com.haswalk.solver.fvm1d.util.SetMethod;
import com.haswalk.solver.fvm1d.util.ToString;

public class Gauger implements SetMethod, ToString{
	
	private Output output;
	
	@Serialize
	private int inc;
	@Serialize
	private int start;
	@Serialize
	private int end;
	@Serialize
	private String[] items;
	
	public Gauger(Output output) {
		this.output = output;
	}
	
	public Gauger set(String property, Object value) {
		setProperty(this, property, value);
		return this;
	}
	
	public void setInc(int inc) {
		this.inc = inc;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public void setItems(String[] items) {
		this.items = items;
	}

	public Output build() {
		return output;
	}
	
	@Override
	public String toString() {
		return asString();
	}
}