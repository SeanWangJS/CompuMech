package com.haswalk.solver.fvm2d.processors.support;

import com.haswalk.solver.fvm2d.annotation.Injection;
import com.haswalk.solver.fvm2d.components.FieldData;
import com.haswalk.solver.fvm2d.components.ModelData;
import com.haswalk.solver.fvm2d.components.TimeControl;
import com.haswalk.solver.fvm2d.components.modedata.BoundaryCondition;
import com.haswalk.solver.fvm2d.components.modedata.ForceBoundaryCondition;
import com.haswalk.solver.fvm2d.components.modedata.StressBoundaryCondition;
import com.haswalk.solver.fvm2d.config.Boundary;
import com.haswalk.solver.fvm2d.processors.ForceUpdate;
import com.haswalk.solver.fvm2d.processors.support.force.LoadBoundaryApply;
import com.haswalk.solver.fvm2d.processors.support.force.StressBoundaryApply;
import com.haswalk.solver.fvm2d.processors.support.force.ForceBoundaryApply;
import com.haswalk.solver.fvm2d.processors.support.force.HourglassDamping;
import com.haswalk.solver.fvm2d.processors.support.force.InternalForceUpdate;
import static com.haswalk.solver.fvm2d.components.ModelData.*;

import java.util.ArrayList;
import java.util.List;

import static com.haswalk.solver.fvm2d.components.FieldData.*;

public class DefaultForceUpdate implements ForceUpdate {

	private InternalForceUpdate ifu;
	private List<LoadBoundaryApply> bfs;
	private HourglassDamping hd;

	@Override
	public void calc() {
		ifu.calc();
		bfs.forEach(bf -> bf.calc());
		hd.calc();
	}

	@SuppressWarnings("unchecked")
	@Injection
	public void set(FieldData fd, ModelData md, TimeControl tc) {
		ifu = new InternalForceUpdate((List<double[]>) md.get(VERTICES), (List<int[]>) md.get(ELEMENTS),
				(List<List<Integer>>) md.get(NODES_AROUND_ELEM), 
				fd.get(FORCE_X), fd.get(FORCE_Y), fd.get(ELEM_STRESS_X), fd.get(ELEM_STRESS_Y),
				fd.get(ELEM_STRESS_XY));
		hd = new HourglassDamping((List<int[]>) md.get(ELEMENTS), fd.get(FORCE_X), fd.get(FORCE_Y), fd.get(VEL_X),
				fd.get(VEL_Y), fd.get(ELEM_MASS), tc);
		bfs = new ArrayList<>();
		md.get(BOUNDARY_CONDITION).forEach(b -> {
			if(Boundary.FORCE.equals(((BoundaryCondition)b).getType())) {
				ForceBoundaryCondition bc = (ForceBoundaryCondition) b;
				bfs.add(new ForceBoundaryApply(fd.get(FORCE_X), fd.get(FORCE_Y), tc, bc.getLoad(), bc.getApplyNodeId(),
						bc.getAngle()));
			}else if(Boundary.STRESS.equals(((BoundaryCondition)b).getType())) {
				StressBoundaryCondition bc = (StressBoundaryCondition) b;
				bfs.add(new StressBoundaryApply(fd.get(FORCE_X), fd.get(FORCE_Y), (List<double[]>) md.get(VERTICES), tc,
						bc.getLoad(), bc.getApplyNodesId()));
			}
		});

	}

}
