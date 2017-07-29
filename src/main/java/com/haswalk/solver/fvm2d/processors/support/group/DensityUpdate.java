package com.haswalk.solver.fvm2d.processors.support.group;

import com.haswalk.solver.fvm2d.processors.Processor;

public class DensityUpdate implements Processor{
	
	private double[] density;
	private double[] mass;
	private double[] area;

	public DensityUpdate(double[] density, double[] mass, double[] area) {
		this.density = density;
		this.mass = mass;
		this.area = area;
	}

	@Override
	public void calc() {
		int NOE = density.length;
		for(int i = 0; i < NOE; i++){
			density[i] = mass[i] / area[i];
		}
	}
	
}
