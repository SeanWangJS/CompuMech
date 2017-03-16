package com.haswalk.solver.fvm2d.processors.support;

import java.util.List;

import com.haswalk.solver.fvm2d.annotation.Injection;
import com.haswalk.solver.fvm2d.components.FieldData;
import com.haswalk.solver.fvm2d.components.ModelData;
import com.haswalk.solver.fvm2d.components.ModelData.BoundaryCondition;
import com.haswalk.solver.fvm2d.components.ModelData.SymmetricBoundaryCondition;
import com.haswalk.solver.fvm2d.config.Boundary;
import com.haswalk.solver.fvm2d.processors.Processor;

public class SymmetricBCApplyUpdate implements Processor{

	private double[] force;
	private List<Integer> applyNodesId;
	
	@Override
	public void calc() {
		if(force == null) {
			return;
		}
		applyNodesId.forEach(nid -> force[nid] = 0);
	}
	
	@Injection
	public void set(FieldData fd, ModelData md){
		md.get(ModelData.BOUNDARY_CONDITION).forEach(bc -> {
			BoundaryCondition b = (BoundaryCondition) bc;
			if(Boundary.SYMMETRIC.equals(b.getType())){
				String symmetric = ((SymmetricBoundaryCondition)b).getSymmetric();
				force = fd.get(("x".equals(symmetric))? FieldData.FORCE_X: FieldData.FORCE_Y);
				applyNodesId = ((SymmetricBoundaryCondition)b).getApplyNodesId();
			}
		});
	}

}

