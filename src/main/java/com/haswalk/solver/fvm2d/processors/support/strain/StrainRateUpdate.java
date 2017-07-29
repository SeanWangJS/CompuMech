package com.haswalk.solver.fvm2d.processors.support.strain;

import java.util.List;

import com.haswalk.solver.fvm2d.annotation.Injection;
import com.haswalk.solver.fvm2d.components.FieldData;
import com.haswalk.solver.fvm2d.processors.Processor;

public class StrainRateUpdate implements Processor{
	private List<int[]> elements;
	private List<double[]> vertices;
	private double[] vertLstX;
	private double[] vertLstY;
	private double[] velX;
	private double[] velY;
	private double[] srx;
	private double[] sry;
	private double[] srxy;
	private double[] area;
	private double[] areaLst;
	
	public StrainRateUpdate(List<int[]> elements, List<double[]> vertices, double[] vertLstX, double[] vertLstY,
			double[] velX, double[] velY, double[] srx, double[] sry, double[] srxy, double[] area, double[] areaLst) {
		super();
		this.elements = elements;
		this.vertices = vertices;
		this.vertLstX = vertLstX;
		this.vertLstY = vertLstY;
		this.velX = velX;
		this.velY = velY;
		this.srx = srx;
		this.sry = sry;
		this.srxy = srxy;
		this.area = area;
		this.areaLst = areaLst;
	}

	@Override
	public void calc() {
		int NOE = elements.size();
		for(int i = 0; i < NOE; i++){
			int[] e = elements.get(i);
			int n = e.length;
			double[][] v = new double[n][];
			double[][] p = new double[n][];
			for(int j = 0; j < n; j++){
				p[j] = new double[]{(vertices.get(e[j])[0] + vertLstX[e[j]]) / 2.0, 
									 (vertices.get(e[j])[1] + vertLstY[e[j]]) / 2.0};
				v[j] = new double[]{velX[e[j]], velY[e[j]]};
			}
			
			double A = (area[i] + areaLst[i]) / 2.0;
			double[] sr = new double[3];
			for(int j = 0; j < n; j++){
				sr[0] += (v[(j + 1) % n][0] + v[j][0]) * (p[(j + 1) % n][1] - p[j][1]);
				sr[1] += - (v[(j + 1) % n][1] + v[j][1]) * (p[(j + 1) % n][0] - p[j][0]);
				sr[2] += - (v[(j + 1) % n][0] + v[j][0]) * (p[(j + 1) % n][0] - p[j][0]) 
						+(v[(j + 1) % n][1] + v[j][1]) * (p[(j + 1) % n][1] - p[j][1]);
			}
			sr[0] /= (2 * A);
			sr[1] /= (2 * A);
			sr[2] /= (4 * A);
			srx[i] = sr[0];
			sry[i] = sr[1];
			srxy[i] = sr[2];
		}		
		
	}
	
	@Injection
	public void setSolverData(FieldData data) {
		vertLstX = data.get(FieldData.NODE_COOR_LST_X);
		vertLstY = data.get(FieldData.NODE_COOR_LST_Y);
		velX = data.get(FieldData.VEL_X);
		velY = data.get(FieldData.VEL_Y);
		srx = data.get(FieldData.ELEM_STRAIN_RATE_X);
		sry = data.get(FieldData.ELEM_STRAIN_RATE_Y);
		srxy = data.get(FieldData.ELEM_STRAIN_RATE_XY);
		area = data.get(FieldData.ELEM_AREA);
		areaLst = data.get(FieldData.ELEM_AREA_LST);
	}
}
