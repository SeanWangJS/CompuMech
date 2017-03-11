package com.haswalk.solver.fvm2d.processors.support;

import com.haswalk.solver.fvm2d.annotation.Inject;
import com.haswalk.solver.fvm2d.annotation.Injection;
import com.haswalk.solver.fvm2d.components.FieldData;
import com.haswalk.solver.fvm2d.components.MaterialProperty;
import com.haswalk.solver.fvm2d.components.TimeControl;
import com.haswalk.solver.fvm2d.processors.Processor;

public class StressDevUpdate implements Processor{
	private double[] srx;
	private double[] sry;
	private double[] srxy;
	private double[] sdvx;
	private double[] sdvy;
	private double[] sdvxy;
	private TimeControl time;
	private double G;

	@Override
	public void calc() {
		int NOE = srx.length;
		for(int i = 0; i < NOE; i++){
			double sin_theta = srxy[i] * time.getTimeStep();
			double sin_2theta = 2 * sin_theta / (1 + sin_theta * sin_theta);
			double delta_x = 0.5 * (sdvx[i] - sdvy[i]) * (-sin_2theta) - sdvxy[i] * sin_2theta;
			double delta_y = -delta_x;
			double delta_xy = 0.5 * (sdvx[i] - sdvy[i]) * sin_2theta + sdvxy[i] * (-sin_2theta);
			
			sdvx[i] += 2 * G * time.getTimeStep() * (srx[i] - 1 / 3.0 * (srx[i] + sry[i])) + delta_x;
			sdvy[i] += 2 * G * time.getTimeStep() * (sry[i] - 1 / 3.0 * (srx[i] + sry[i])) + delta_y;
			sdvxy[i] += 2 * G * time.getTimeStep() * srxy[i] + delta_xy;
		}
	}
	
	@Inject(FieldData.ELEM_STRAIN_RATE_X)
	public void setStrainRateX(double[] srx) {
		this.srx = srx;
	}
	@Inject(FieldData.ELEM_STRAIN_RATE_Y)
	public void setStrainRateY(double[] sry) {
		this.sry = sry;
	}
	@Inject(FieldData.ELEM_STRAIN_RATE_XY)
	public void setStrainRateXY(double[] srxy) {
		this.srxy = srxy;
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
	@Injection
	public void setTimeControl(TimeControl time) {
		this.time = time;
	}
	@Injection
	public void setMaterialProperty(MaterialProperty property) {
		this.G = (double)property.get(MaterialProperty.SHEAR_MODULE);
	}
}
