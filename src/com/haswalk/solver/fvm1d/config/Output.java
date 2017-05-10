package com.haswalk.solver.fvm1d.config;

public class Output {

	public final static String INCREMENT = "increment";
	public final static String START = "start";
	public final static String END = "end";
	
	private int id;
	private Config1DBuilder builder;
	
	private Save save;
	private Gauge gauge;
	
	public Output(int id, Config1DBuilder builder) {
		this.id = id;
		this.builder = builder;
		save = new Save(this);
		gauge = new Gauge(this);
	}
	
	public int getId() {
		return id;
	}
	
	public Save save(){
		return save;
	}
	
	public Gauge gauge() {
		return gauge;
	}
	
	public Config1DBuilder build() {
		builder.putOutput(this);
		return builder;
	}
	
	public class Save{

		private Output output;
		
		private int inc;
		private int start;
		private int end;
		private String[] items;
		
		public Save(Output output){
			this.output = output;
		}
		
		public Save set(String property, int value) {
			switch (property) {
			case INCREMENT:
				inc = value;
				break;
			case START:
				start = value;
				break;
			case END:
				end = value;
				break;
			default:
				System.err.println("Error: can not match property " + property + " in output.");
				break;
			}
			return this;
		}
		
		public Save set(String property, String... values) {
			items = values;
			return this;
		}
		
		public Output build() {
			return output;
		}
	}
	public class Gauge{
		private Output output;
		
		private int inc;
		private int start;
		private int end;
		private String[] items;
		
		public Gauge(Output output) {
			this.output = output;
		}
		
		public Gauge set(String property, int value) {
			switch (property) {
			case INCREMENT:
				inc = value;
				break;
			case START:
				start = value;
				break;
			case END:
				end = value;
				break;
			default:
				System.err.println("Error: can not match property " + property + " in output.");
				break;
			}
			return this;
		}
		
		public Gauge set(String property, String... values) {
			items = values;
			return this;
		}
		
		public Output build() {
			return output;
		}
	}
	
}
