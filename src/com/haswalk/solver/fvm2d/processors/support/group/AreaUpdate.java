package com.haswalk.solver.fvm2d.processors.support.group;

import java.util.List;

import com.haswalk.solver.fvm2d.annotation.Injection;
import com.haswalk.solver.fvm2d.components.FieldData;
import com.haswalk.solver.fvm2d.components.ModelData;
import com.haswalk.solver.fvm2d.processors.Processor;
import com.sean.utils.Geom;

import static com.haswalk.solver.fvm2d.components.ModelData.*;
import static com.haswalk.solver.fvm2d.components.FieldData.*;

public class AreaUpdate implements Processor{
	private List<int[]> elements;
	private List<double[]> vertices;
	private double[] area;
	private double[] areaLst;
	
	public AreaUpdate(List<int[]> elements, List<double[]> vertices, double[] area, double[] areaLst) {
		this.elements = elements;
		this.vertices = vertices;
		this.area = area;
		this.areaLst = areaLst;
	}

	@Override
	public void calc() {
		int NOE = elements.size();
		for(int i = 0; i < NOE; i++){
			int[] e = elements.get(i);
			double[][] p = new double[e.length][];
			for(int j = 0; j < e.length; j++){
				p[j] = vertices.get(e[j]);
			}
			areaLst[i] = area[i];
			area[i] = Geom.area(p);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Injection
	public void set(FieldData fd, ModelData md){
		vertices = (List<double[]>) md.get(VERTICES);
		elements = (List<int[]>) md.get(ELEMENTS);
		area = fd.get(ELEM_AREA);
		areaLst = fd.get(ELEM_AREA_LST);
	}
}
