package com.haswalk.solver.fvm2d.processors.support;

import com.haswalk.solver.fvm2d.annotation.Injection;
import com.haswalk.solver.fvm2d.components.FieldData;
import com.haswalk.solver.fvm2d.components.TimeControl;
import com.haswalk.solver.fvm2d.processors.VelUpdate;

public class DefaultVelUpdate implements VelUpdate{

	private double[] vx;
	private double[] vy;
	private double[] ax;
	private double[] ay;
	private TimeControl time;
	
	@Override
	public void calc() {
		int NON = vx.length;
		double step = (time.getTimeStep() + time.getTimeStepLst()) / 2.0;
		for(int i = 0; i < NON; i++) {
			vx[i] += ax[i] * step;
			vy[i] += ay[i] * step;
 		}
	}
	@Injection
	public void setFieldData(FieldData data) {
		vx = data.get(FieldData.VEL_X);
		vy = data.get(FieldData.VEL_Y);
		ax = data.get(FieldData.ACC_X);
		ay = data.get(FieldData.ACC_Y);
	}
	@Injection
	public void setTimeControl(TimeControl time) {
		this.time = time;
	}
}
