package com.haswalk.solver.fvm1d.config.support.control;

import com.haswalk.solver.fvm1d.config.Control;
import com.haswalk.solver.fvm1d.util.SetMethod;
import com.haswalk.solver.fvm1d.util.ToString;
import com.haswalk.solver.util.Serialize;

public class TimeControl implements SetMethod, ToString{
	public final static String END_TIME = "endTime";
	public final static String FACTOR = "factor";
	@Serialize
	private double endTime;
	@Serialize
	private double factor;
	
	private Control control;
	
	public TimeControl(Control control) {
		this.control = control;
	}
	
	public double getEndTime() {
		return endTime;
	}
	public double getFactor() {
		return factor;
	}
	public TimeControl set(String property, Object value) {
		setProperty(this, property, value);
		return this;
	}
	public void setEndTime(double endTime) {
		this.endTime = endTime;
	}
	public void setFactor(double factor) {
		this.factor = factor;
	}
	
	public Control build() {
		return control;
	}
	
	@Override
	public String toString() {
		return asString();
	}
}
