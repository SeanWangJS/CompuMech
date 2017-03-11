package com.haswalk.solver.fvm2d.processors.support;

import com.haswalk.solver.fvm2d.annotation.Inject;
import com.haswalk.solver.fvm2d.annotation.Injection;
import com.haswalk.solver.fvm2d.components.FieldData;
import com.haswalk.solver.fvm2d.components.ModelData;
import com.haswalk.solver.fvm2d.components.TimeControl;
import com.haswalk.solver.fvm2d.processors.StrainUpdate;
import com.haswalk.solver.fvm2d.processors.support.strain.StrainRateUpdate;
import static com.haswalk.solver.fvm2d.components.FieldData.*;
import static com.haswalk.solver.fvm2d.components.ModelData.*;

import java.util.List;

public class DefaultStrainUpdate implements StrainUpdate{

	private double[] stnx;
	private double[] stny;
	private double[] stnxy;
	private double[] srx;
	private double[] sry;
	private double[] srxy;
	private TimeControl time;
	
	private StrainRateUpdate sru;
	
	@Override
	public void calc() {
		
		sru.calc();
		
		int NOE = stnx.length;
		for(int i = 0; i < NOE; i++) {
			stnx[i] += srx[i] * time.getTimeStep();
			stny[i] = sry[i] * time.getTimeStep();
			stnxy[i] = srxy[i] * time.getTimeStep();
		}
	}
	
	@Inject(ELEM_STRAIN_X)
	public void setStrainX(double[] stnx){
		this.stnx = stnx;
	}
	@Inject(ELEM_STRAIN_Y)
	public void setStrainY(double[] stny){
		this.stny = stny;
	}
	@Inject(ELEM_STRAIN_XY)
	public void setStrainXY(double[] stnxy){
		this.stnxy = stnxy;
	}
	@Inject(ELEM_STRAIN_RATE_X)
	public void setStrainRateX(double[] srx) {
		this.srx = srx;
	}
	@Inject(ELEM_STRAIN_RATE_Y)
	public void setStrainRateY(double[] sry) {
		this.sry = sry;
	}
	@Inject(ELEM_STRAIN_RATE_XY)
	public void setStrainRateXY(double[] srxy) {
		this.srxy = srxy;
	}
	
	@Injection
	public void setTimeControl(TimeControl time) {
		this.time = time;
	}

	@SuppressWarnings("unchecked")
	@Injection
	public void set(FieldData fd, ModelData md) {
		sru = new StrainRateUpdate((List<int[]>)md.get(ELEMENTS), (List<double[]>) md.get(VERTICES), 
				fd.get(NODE_COOR_LST_X), fd.get(NODE_COOR_LST_Y), fd.get(VEL_X), fd.get(VEL_Y), 
				fd.get(ELEM_STRAIN_RATE_X), fd.get(ELEM_STRAIN_RATE_Y), fd.get(ELEM_STRAIN_RATE_XY), 
				fd.get(ELEM_AREA), fd.get(ELEM_AREA_LST));
		
	}
}
