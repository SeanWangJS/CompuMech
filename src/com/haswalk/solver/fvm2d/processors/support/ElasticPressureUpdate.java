package com.haswalk.solver.fvm2d.processors.support;

import com.haswalk.solver.fvm2d.annotation.Inject;
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
			//压缩为正，拉伸为负
			double miu = density[i] / initDen - 1;
			double P = K * miu;
			pressure[i] = P;
		}		
	}
	
	@Inject(FieldData.ELEM_PRESSURE)
	public void setPressure(double[] pressure) {
		this.pressure = pressure;
	} 
	
	@Inject(FieldData.ELEM_DENSITY)
	public void setDensity(double[] density) {
		this.density = density;
	}
	
	@Injection
	public void setMaterialProperty(MaterialProperty property) {
		K = (double)property.get(MaterialProperty.BULK_MODULE);
		initDen = (double)property.get(MaterialProperty.DENSITY);
	}
}
