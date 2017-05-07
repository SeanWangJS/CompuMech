package com.haswalk.solver.fvm2d.processors.creation;

import java.util.HashMap;
import java.util.List;

import com.haswalk.solver.fvm2d.components.Components;
import com.haswalk.solver.fvm2d.components.FieldData;
import com.haswalk.solver.fvm2d.components.TimeControl;
import com.haswalk.solver.fvm2d.config.Config;
import com.haswalk.solver.fvm2d.processors.Processor;
import com.haswalk.solver.fvm2d.processors.ProcessorCreationMethod;
import com.haswalk.solver.fvm2d.processors.extend.Gauger;

public class GaugerCreationMethod implements ProcessorCreationMethod{

	@Override
	public Processor invoke(int partId, Config config, HashMap<Integer, Components> componentsMap) {
		Gauger gauger = new Gauger();
		HashMap<String, double[]> data = new HashMap<>();
		List<String > items = config.getOutputs().get(config.getParts().get(partId).getOutputID())
						   .getGaugeItem()
						   .getItems();
		for(String item:items) {
			double[] value = ((FieldData)componentsMap.get(partId).get(Components.FIELD_DATA)).get(item);
			if(value == null) {
				System.err.println("Error: can not find param named + " + item + ", please check out your config.");
				continue;
			}
			data.put(item, value);
		}	   
		
		gauger.set(config.getParts().get(partId).getMesh().getWorkspace(), 
				config.getOutputs().get(config.getParts().get(partId).getOutputID())
				                   .getGaugeItem()
				                   .getInc(), 
				partId,
				(TimeControl)componentsMap.get(partId).get(Components.TIME_CONTROL), 
				config.getParts().get(partId).getGauge().getGaugeNodesID(), 
				data);
		return gauger;
	}

}
