package com.haswalk.solver.fvm2d.processors.extend;

import static com.haswalk.solver.fvm2d.components.FieldData.ELEM_DENSITY;
import static com.haswalk.solver.fvm2d.components.FieldData.ELEM_PRESSURE;
import static com.haswalk.solver.fvm2d.components.MaterialProperty.DENSITY;
import com.haswalk.solver.fvm2d.annotation.Injection;
import com.haswalk.solver.fvm2d.components.FieldData;
import com.haswalk.solver.fvm2d.components.MaterialProperty;
import com.haswalk.solver.fvm2d.processors.Processor;

public class PolyNomiPressureUpdate implements Processor{

	private double[] pressure;
	private double[] density;
	private double initDen;
	
	private double a1 = 9.5e10;
	private double a2 = 61.3e10;
	private double a3 = 124.9e10;
//	private double a1 = 7.4e10;
//	private double a2 = 47.7e10;
//	private double a3 = 97.1e10;
	
	@Override
	public void calc() {
		int NOE = pressure.length;
		for(int i = 0; i < NOE; i++){

			double miu = density[i] / initDen - 1;
			double P = a1 * miu + a2 * Math.pow(miu, 2) + a3 * Math.pow(miu, 3);
			pressure[i] = P;
		}		
	}
	
	@Injection
	public void set(FieldData fd, MaterialProperty mp){
		density = fd.get(ELEM_DENSITY);
		pressure = fd.get(ELEM_PRESSURE);
		initDen = (double) mp.get(DENSITY);
	}

}
