package com.haswalk.solver.fvm2d.processors.creation;

import java.util.HashMap;
import java.util.List;

import com.haswalk.solver.fvm2d.components.Components;
import com.haswalk.solver.fvm2d.components.FieldData;
import com.haswalk.solver.fvm2d.components.ModelData;
import com.haswalk.solver.fvm2d.components.ModelData.BoundaryCondition;
import com.haswalk.solver.fvm2d.components.ModelData.PMLBoundaryCondition;
import com.haswalk.solver.fvm2d.config.Boundary;
import com.haswalk.solver.fvm2d.config.Config;
import com.haswalk.solver.fvm2d.processors.Processor;
import com.haswalk.solver.fvm2d.processors.ProcessorCreationMethod;
import com.haswalk.solver.fvm2d.processors.support.PMLUpdate;

public class PMLUpdateCreationMethod implements ProcessorCreationMethod{

	
	@SuppressWarnings("unchecked")
	@Override
	public Processor invoke(int partId, Config config, HashMap<Integer, Components> componentsMap) {
		List<BoundaryCondition> bcs = (List<BoundaryCondition>)((ModelData)componentsMap.get(partId).get(Components.MODEL_DATA)).get(ModelData.BOUNDARY_CONDITION);
		PMLBoundaryCondition pmlBc = null;
		for(BoundaryCondition bc : bcs) {
			if(Boundary.PERFECT_MATCHED_LAYER.equals(bc.getType())) {
				pmlBc = (PMLBoundaryCondition) bc;
			}
		}
		return new PMLUpdate(pmlBc.getDelta(), 
				((FieldData)componentsMap.get(partId).get(Components.FIELD_DATA)).get(FieldData.VEL_X), 
				((FieldData)componentsMap.get(partId).get(Components.FIELD_DATA)).get(FieldData.VEL_X), 
				((FieldData)componentsMap.get(partId).get(Components.FIELD_DATA)).get(FieldData.ACC_X), 
				((FieldData)componentsMap.get(partId).get(Components.FIELD_DATA)).get(FieldData.ACC_Y), 
				pmlBc.getPMLNodesId(), 
				pmlBc.getDist(),
				config.getMaterials().get(partId).getCp());
	}

	
}
