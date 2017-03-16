package com.haswalk.solver.fvm2d.processors.creation;

import java.util.HashMap;
import java.util.List;

import com.haswalk.solver.fvm2d.components.Components;
import com.haswalk.solver.fvm2d.components.FieldData;
import com.haswalk.solver.fvm2d.components.TimeControl;
import com.haswalk.solver.fvm2d.config.Config;
import com.haswalk.solver.fvm2d.processors.Processor;
import com.haswalk.solver.fvm2d.processors.ProcessorCreationMethod;
import com.haswalk.solver.fvm2d.processors.extend.Saver;

public class SaverCreationMethod implements ProcessorCreationMethod{

	@Override
	public Processor invoke(int partId, Config config, HashMap<Integer, Components> componentsMap) {

		Saver saver = new Saver();
		HashMap<String, double[]> data = new HashMap<>();
		List<String> items = config.getOutputs().get(
				config.getParts().get(partId).getOutputID()
			)
									.getSaveItems()
									.getItems(); 
		items.forEach(item -> data.put(item, 
									    ((FieldData)componentsMap.get(partId)
													 			  .get(Components.FIELD_DATA))
									    .get(item)));
		saver.set(config.getParts().get(partId).getMesh().getWorkspace(), 
				  config.getOutputs().get(config.getParts().get(partId).getOutputID()).getSaveItems().getInc(), 
				  partId,
				  (TimeControl) componentsMap.get(partId).get(Components.TIME_CONTROL), 
				  data);
		return saver;
	}

}
