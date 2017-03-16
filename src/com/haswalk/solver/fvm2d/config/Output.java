package com.haswalk.solver.fvm2d.config;

import com.haswalk.solver.fvm2d.config.output.GaugeItem;
import com.haswalk.solver.fvm2d.config.output.SaveItem;

public class Output {

	private SaveItem save;
	private GaugeItem gauge;
	
	public SaveItem getSaveItems() {
		return save;
	}

	public GaugeItem getGaugeItem() {
		return gauge;
	}

	public String toString() {
		return save.toString() +"\n"+gauge.toString();
	}
}
