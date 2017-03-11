package com.haswalk.solver.fvm2d.processors.support;

import com.haswalk.solver.fvm2d.annotation.Inject;
import com.haswalk.solver.fvm2d.components.FieldData;
import com.haswalk.solver.fvm2d.processors.StressUpdate;

public class DefaultStressUpdate implements StressUpdate{
	private double[] sx;
	private double[] sy;
	private double[] sxy;
	private double[] pressure;
	private double[] sdvx;
	private double[] sdvy;
	private double[] sdvxy;

	@Override
	public void calc() {
		int NOE = sx.length;
		for(int i = 0; i < NOE; i++){
			sx[i] = - pressure[i] + sdvx[i];
			sy[i] = - pressure[i] + sdvy[i];
			sxy[i] = sdvxy[i];
		}		
	}
	
	@Inject(FieldData.ELEM_STRESS_X)
	public void setStressX(double[] sx){
		this.sx = sx;
	}
	@Inject(FieldData.ELEM_STRESS_Y)
	public void setStressY(double[] sy){
		this.sy = sy;
	}
	@Inject(FieldData.ELEM_STRESS_XY)
	public void setStressXY(double[] sxy){
		this.sxy = sxy;
	}
	@Inject(FieldData.ELEM_STRESS_DEV_X)
	public void setStressDevX(double[] sdvx) {
		this.sdvx = sdvx;
	}
	@Inject(FieldData.ELEM_STRESS_DEV_Y)
	public void setStressDevY(double[] sdvy) {
		this.sdvy = sdvy;
	}
	@Inject(FieldData.ELEM_STRESS_DEV_XY)
	public void setStressDevXY(double[] sdvxy) {
		this.sdvxy = sdvxy;
	}
	@Inject(FieldData.ELEM_PRESSURE)
	public void setPressure(double[] pressure){
		this.pressure = pressure;
	}
}
