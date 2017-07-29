package com.haswalk.solver.fvm2d.processors.support.group;

import java.util.List;

import com.haswalk.solver.fvm2d.processors.Processor;
import com.sean.utils.ArrUtil;
import com.sean.utils.Geom;
import com.sean.utils.LsUtil;

public class NodeMassUpdate implements Processor {
	private List<List<Integer>> nodesE;
	private List<int[]> elements;
	private List<double[]> vertices;
	private double[] nMass;
	private double[] density;

	public NodeMassUpdate(List<List<Integer>> nodesE, List<int[]> elements, List<double[]> vertices, double[] nMass,
			double[] density) {
		this.nodesE = nodesE;
		this.elements = elements;
		this.vertices = vertices;
		this.nMass = nMass;
		this.density = density;
	}

	@Override
	public void calc() {
		int NON = vertices.size();
		for (int i = 0; i < NON; i++) {
			List<Integer> ean = nodesE.get(i);
			double mass = 0;
			for (int eid : ean) {
				int[] elem = elements.get(eid);
				int ni = ArrUtil.findNext(elem, i);
				int nj = ArrUtil.findPre(elem, i);
				double[] centroid = Geom.center(LsUtil.select(vertices, elem));
				double area = Geom.area(vertices.get(i), Geom.middle(vertices.get(ni), vertices.get(i)), centroid,
						Geom.middle(vertices.get(nj), vertices.get(i)));
				mass += area * density[eid];
				nMass[i] = mass;
			}
		}
	}
}
