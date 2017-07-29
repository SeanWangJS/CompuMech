package com.haswalk.solver.fvm2d.processors.support;

import static com.haswalk.solver.fvm2d.components.FieldData.ELEM_DENSITY;
import static com.haswalk.solver.fvm2d.components.FieldData.ELEM_PRESSURE;
import static com.haswalk.solver.fvm2d.components.MaterialProperty.DENSITY;
import static com.haswalk.solver.fvm2d.components.MaterialProperty.ELASTIC_MODULE;
import com.haswalk.solver.fvm2d.annotation.Injection;
import com.haswalk.solver.fvm2d.components.FieldData;
import com.haswalk.solver.fvm2d.components.MaterialProperty;
import com.haswalk.solver.fvm2d.processors.PressureUpdate;

public class ElasticPressureUpdate implements PressureUpdate{

	private double[] pressure;
	private double[] density;
	private double K;
	private double initDen;
	
	@Override
	public void calc() {
		int NOE = pressure.length;
		for(int i = 0; i < NOE; i++){

			double miu = density[i] / initDen - 1;
			double P = K * miu;
			pressure[i] = P;
		}		
	}
	
	@Injection
	public void set(FieldData fd, MaterialProperty mp){
		density = fd.get(ELEM_DENSITY);
		pressure = fd.get(ELEM_PRESSURE);
		K = (double) mp.get(ELASTIC_MODULE);
		initDen = (double) mp.get(DENSITY);
		
	}
}
