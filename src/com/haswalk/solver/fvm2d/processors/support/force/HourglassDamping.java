package com.haswalk.solver.fvm2d.processors.support.force;

import java.util.List;

import com.haswalk.solver.fvm2d.annotation.Component;
import com.haswalk.solver.fvm2d.components.TimeControl;

@Component
public class HourglassDamping {

	private List<int[]> elements;
	private double[] forceX;
	private double[] forceY;
	private double[] vx;
	private double[] vy;
	private double[] eMass;
	private TimeControl time;

	public HourglassDamping(List<int[]> elements, double[] forceX, double[] forceY, double[] vx, double[] vy,
			double[] eMass, TimeControl time) {
		this.elements = elements;
		this.forceX = forceX;
		this.forceY = forceY;
		this.vx = vx;
		this.vy = vy;
		this.eMass = eMass;
		this.time = time;
	}

	public void calc() {
		double C_H = 0.1;

		for (int i = 0, len = elements.size(); i < len; i++) {
			int[] e = elements.get(i);
			if (e.length != 4) {
				continue;
			}
			double m = eMass[i];
			double fx1 = -C_H * m * (vx[e[0]] - vx[e[1]] + vx[e[2]] - vx[e[3]]) / (16 * time.getTimeStep());
			double fy1 = -C_H * m * (vy[e[0]] - vy[e[1]] + vy[e[2]] - vy[e[3]]) / (16 * time.getTimeStep());

			double fx2 = -fx1;
			double fy2 = -fy1;
			double fx3 = fx1;
			double fy3 = fy1;
			double fx4 = -fx1;
			double fy4 = -fy1;
			forceX[e[0]] += fx1;
			forceY[e[0]] += fy1;
			forceX[e[1]] += fx2;
			forceY[e[1]] += fy2;
			forceX[e[2]] += fx3;
			forceY[e[2]] += fy3;
			forceX[e[3]] += fx4;
			forceY[e[3]] += fy4;
		}
	}

}
