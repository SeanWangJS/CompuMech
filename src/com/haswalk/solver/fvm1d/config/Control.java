package com.haswalk.solver.fvm1d.config;

public class Control {

	private Config1DBuilder builder;
	
	private TimeControl time = new TimeControl(); 
	
	public Control(Config1DBuilder builder) {
		this.builder = builder;
	}
	
	public Control time(String property1, double value1, String property2, double value2) {
		time.set(property1, value1);
		time.set(property2, value2);
		return this;
	}
	
	public Config1DBuilder build() {
		builder.setControl(this);
		return builder;
	}
	
	public class TimeControl{
		public final static String END_TIME = "endTime";
		public final static String FACTOR = "factor";
		private double endTime;
		private double factor;
		public double getEndTime() {
			return endTime;
		}
		public double getFactor() {
			return factor;
		}
		public void set(String property, double value) {
			switch (property) {
			case END_TIME:
				endTime = value;
				break;
			case FACTOR:
				factor = value;
				break;
			default:
				System.err.println("Error: can not match property " + property + " in time control.");
				break;
			}
		}
	}
}
