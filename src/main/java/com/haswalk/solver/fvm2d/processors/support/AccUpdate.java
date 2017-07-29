package com.haswalk.solver.fvm2d.processors.support;

import com.haswalk.solver.fvm2d.annotation.Injection;
import com.haswalk.solver.fvm2d.components.FieldData;
import com.haswalk.solver.fvm2d.components.MaterialProperty;
import com.haswalk.solver.fvm2d.processors.Processor;

public class AccUpdate implements Processor{

	private double[] forceX;
	private double[] forceY;
	private double[] ax;
	private double[] ay;
	private double[] vx;
	private double[] vy;
	private double[] nMass;
	private double dampingRatio;
	private double frequency;
	
	@Override
	public void calc() {
		int n = ax.length;
		for(int i =0 ; i < n; i++) {
			ax[i] = forceX[i] / nMass[i] - 2 * dampingRatio * frequency * vx[i];
			ay[i] = forceY[i] / nMass[i] - 2 * dampingRatio * frequency * vy[i];
		}
	}
	
	@Injection
	public void setFieldData(FieldData data, MaterialProperty mp){
		forceX = data.get(FieldData.FORCE_X);
		forceY = data.get(FieldData.FORCE_Y);
		ax = data.get(FieldData.ACC_X);
		ay = data.get(FieldData.ACC_Y);
		vx = data.get(FieldData.VEL_X);
		vy = data.get(FieldData.VEL_Y);
		nMass = data.get(FieldData.NODE_MASS);
		dampingRatio = (double)mp.get(MaterialProperty.DAMPING_RATIO);
		frequency = (double)mp.get(MaterialProperty.NATRUAL_FREQUENCY);
	}
}
