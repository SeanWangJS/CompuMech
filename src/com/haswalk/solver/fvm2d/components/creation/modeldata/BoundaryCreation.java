package com.haswalk.solver.fvm2d.components.creation.modeldata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.haswalk.solver.fvm2d.components.creation.modeldata.boundary.BoundaryConditionCreationMethod;
import com.haswalk.solver.fvm2d.components.creation.modeldata.boundary.ForceBoundaryConditionCreation;
import com.haswalk.solver.fvm2d.components.creation.modeldata.boundary.StressBoundaryConditionCreation;
import com.haswalk.solver.fvm2d.components.creation.modeldata.boundary.SymmetricBoundaryConditionCreation;
import com.haswalk.solver.fvm2d.components.modedata.BoundaryCondition;
import com.haswalk.solver.fvm2d.config.Boundary;
import com.haswalk.solver.fvm2d.config.Config;

public class BoundaryCreation implements ModelDataAttributeCreationMethod{

	private static HashMap<String, BoundaryConditionCreationMethod> methods = new HashMap<>();
	
	static{
		regist(Boundary.FORCE, new ForceBoundaryConditionCreation());
		regist(Boundary.STRESS, new StressBoundaryConditionCreation());
		regist(Boundary.SYMMETRIC, new SymmetricBoundaryConditionCreation());
	}
	
	@Override
	public List<?> invoke(int partId, Config config) {
		List<BoundaryCondition> bc = new ArrayList<>();
		List<Integer> ids = config.getParts().get(partId).getBoundaryCondition().getBcIds();
		HashMap<Integer, String> idTypeMap = new HashMap<>();
		ids.forEach(id -> idTypeMap.put(id, config.getBoundaries().get(id).getType()));
		
		idTypeMap.forEach((bcId, type) -> {
			bc.add(methods.get(type).invoke(bcId, partId, config));
//				PMLBoundary b = (PMLBoundary) config.getBoundaries().get(bcid);
//				bc.add(md.new PMLBoundaryCondition(type, 
//														config.getParts().get(partId).getBoundaryCondition().getApplyNodesId(bcid),
//														b.getDist(),
//														b.getPMLNodesIds(),
//														b.getDelta()));
		});
		return bc;
	}
	public static void regist(String name, BoundaryConditionCreationMethod method) {
		methods.put(name, method);
	}
}
