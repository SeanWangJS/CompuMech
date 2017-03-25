package com.haswalk.solver.fvm2d.processors.support;

import static com.haswalk.solver.fvm2d.components.FieldData.ELEM_CHAR_LEN;
import static com.haswalk.solver.fvm2d.components.FieldData.ELEM_DENSITY;
import static com.haswalk.solver.fvm2d.components.FieldData.ELEM_PRESSURE;
import static com.haswalk.solver.fvm2d.components.FieldData.ELEM_STRAIN_RATE_X;
import static com.haswalk.solver.fvm2d.components.FieldData.ELEM_STRAIN_RATE_Y;
import static com.haswalk.solver.fvm2d.components.MaterialProperty.SOUND_SPEED;

import com.haswalk.solver.fvm2d.annotation.Injection;
import com.haswalk.solver.fvm2d.components.FieldData;
import com.haswalk.solver.fvm2d.components.MaterialProperty;
import com.haswalk.solver.fvm2d.processors.Processor;

public class PsedoViscousUpdate implements Processor{

	private double Cq = Math.sqrt(1.5);
	private double Cl = 0.06;;
	private double[] pressure;
	private double[] srx;
	private double[] sry;
	private double[] density;
	private double[] charLen;
	private double cp;
	
	@Override
	public void calc() {
		int NOE = pressure.length;
		for(int i = 0; i< NOE; i++) {
			double sr = srx[i] + sry[i];
			double q = 0;
			
			if(sr < 0 ) {
				q = density[i] * Math.pow(Cq * charLen[i] * sr, 2)
						 - density[i] * Cl * charLen[i] * cp * sr;
			}
			pressure[i] += q;
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
		
	}
}
