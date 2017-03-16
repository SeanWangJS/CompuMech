package com.haswalk.solver.fvm2d.processors.creation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.haswalk.solver.fvm2d.components.Components;
import com.haswalk.solver.fvm2d.components.FieldData;
import com.haswalk.solver.fvm2d.components.TimeControl;
import com.haswalk.solver.fvm2d.config.Config;
import com.haswalk.solver.fvm2d.processors.Processor;
import com.haswalk.solver.fvm2d.processors.ProcessorCreationMethod;
import com.haswalk.solver.fvm2d.processors.support.SinglePartTimestepUpdate;
import com.haswalk.solver.fvm2d.processors.support.TimestepUpdate;

public class TimestepUpdateCreationMethod implements ProcessorCreationMethod{

	@Override
	public Processor invoke(int pid, Config config, HashMap<Integer, Components> componentsMap) {
		TimestepUpdate tsu = new TimestepUpdate();
		List<SinglePartTimestepUpdate> sptus = new ArrayList<>();
		config.getParts().forEach((partId, part) -> {
			sptus.add(new SinglePartTimestepUpdate(config.getMaterials()
														 .get(part.getMaterialID())
														 .getCp(), 
												   part.getMesh().getVertices(), 
												   part.getMesh().getElements(), 
												   ((FieldData)componentsMap.get(partId)
														   					 .get(Components.FIELD_DATA))
												   						     .get(FieldData.ELEM_AREA),
												   ((FieldData)componentsMap.get(partId)
														     				 .get(Components.FIELD_DATA))
														   	                 .get(FieldData.ELEM_CHAR_LEN)));
		});
		int id = (int) config.getParts().keySet().toArray()[0];
		tsu.setTimeUpdaters(sptus);
		tsu.setTime((TimeControl)(componentsMap.get(id).get(Components.TIME_CONTROL)));
		return tsu;
	}

}
