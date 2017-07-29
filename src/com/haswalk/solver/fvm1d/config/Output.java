package com.haswalk.solver.fvm1d.config;

import com.haswalk.jsonutil.Serialize;
import com.haswalk.solver.fvm1d.config.support.output.Gauger;
import com.haswalk.solver.fvm1d.config.support.output.Recorder;
import com.haswalk.solver.fvm1d.util.ToString;

public class Output implements ToString{

	public final static String INCREMENT = "inc";
	public final static String START = "start";
	public final static String END = "end";
	public final static String ITEMS = "items";
	
	@Serialize
	private int id;
	private Config1DBuilder builder;
	
	@Serialize
	private Recorder recorder;
	@Serialize
	private Gauger gauge;
	
	public Output(int id, Config1DBuilder builder) {
		this.id = id;
		this.builder = builder;
		recorder = new Recorder(this);
		gauge = new Gauger(this);
	}
	
	public int getId() {
		return id;
	}
	
	public Recorder recorder(){
		return recorder;
	}
	
	public Gauger gauge() {
		return gauge;
	}
	
	public Config1DBuilder build() {
		builder.putOutput(this);
		return builder;
	}
	
	@Override
	public String toString() {
		return asString();
	}
	
}
