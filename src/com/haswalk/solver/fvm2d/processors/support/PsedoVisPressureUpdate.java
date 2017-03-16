package com.haswalk.solver.fvm2d.processors.support;

import com.haswalk.solver.fvm2d.annotation.Injection;
import com.haswalk.solver.fvm2d.components.FieldData;
import com.haswalk.solver.fvm2d.components.MaterialProperty;
import com.haswalk.solver.fvm2d.processors.PressureUpdate;

import static com.haswalk.solver.fvm2d.components.FieldData.*;
import static com.haswalk.solver.fvm2d.components.MaterialProperty.*;

public class PsedoVisPressureUpdate implements PressureUpdate{

	private double Cq = Math.sqrt(1.5);
	private double Cl = 0.06;
	private double[] density;
	private double[] srx;
	private double[] sry;
	private double[] charLen;
	private double cp;
	private double K;
	private double initDen;
	private double[] pressure;
	
	@Override
	public void calc() {
		int NOE = pressure.length;
		for(int i = 0; i < NOE; i++){
			//压缩为正，拉伸为负
			double miu = density[i] / initDen - 1;
			double P = K * miu;
			double sr = srx[i] + sry[i];
			double q = 0;
			
			if(sr < 0 ) {
				q = density[i] * Math.pow(Cq * charLen[i] * sr, 2)
						 - density[i] * Cl * charLen[i] * cp * sr;
			}
			pressure[i] = P + q;
		}	
	}

	@Injection
	public void set(FieldData fd, MaterialProperty mp){
		density = fd.get(ELEM_DENSITY);
		srx = fd.get(ELEM_STRAIN_RATE_X);
		sry = fd.get(ELEM_STRAIN_RATE_Y);
		pressure = fd.get(ELEM_PRESSURE);
		charLen = fd.get(ELEM_CHAR_LEN);
		cp = (double) mp.get(SOUND_SPEED);
		K = (double) mp.get(ELASTIC_MODULE);
		initDen = (double) mp.get(DENSITY);
		
	}
	
}















