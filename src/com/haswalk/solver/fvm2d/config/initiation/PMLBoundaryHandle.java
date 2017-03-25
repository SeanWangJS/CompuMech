package com.haswalk.solver.fvm2d.config.initiation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.haswalk.solver.fvm2d.config.Boundary;
import com.haswalk.solver.fvm2d.config.Config;
import com.haswalk.solver.fvm2d.config.Part;
import com.haswalk.solver.fvm2d.config.boundary.PMLBoundary;
import com.haswalk.solver.fvm2d.util.Geom;

public class PMLBoundaryHandle implements InitiationMethod{

	@Override
	public void invoke(Config config) {
		HashMap<Integer, List<Integer>> pmlBcIdsMap = getPmlBcIds(config);
		if(pmlBcIdsMap.isEmpty()) {
			return;
		}
		pmlBcIdsMap.forEach((partId, pmlBcIds) ->{
			for(int bid: pmlBcIds) {
				PMLBoundary pmlBc = (PMLBoundary) config.getBoundary(bid);
				Part part = config.getPart(partId);
				List<double[]> vertices = part.getMesh().getVertices();
				List<int[]> elements = part.getMesh().getElements();
				
				List<Integer> applyNodesId = part.getBoundaryCondition().getApplyNodesId(bid);
				
				double size = Geom.dist(vertices.get(applyNodesId.get(0)), vertices.get(applyNodesId.get(applyNodesId.size() - 1))) / 
						(applyNodesId.size() - 1);
				
				double[] norm = pmlBc.getOutNorm();
				
				int layerNum = pmlBc.getLayerNum();
				
				int total = vertices.size();
				
				double delta = layerNum * size;
				
				List<double[]> bounds = new ArrayList<>();
				applyNodesId.forEach(id -> bounds.add(vertices.get(id)));
				
				List<double[]> verts = new ArrayList<>();
				List<int[]> elems = new ArrayList<>();
				
				double[] dist = new double[applyNodesId.size() * layerNum];
				
				int[] PMLNodesIds = new int[applyNodesId.size() * layerNum];
				
				create(verts, elems, dist, PMLNodesIds, size, norm, bounds, applyNodesId, layerNum, total);
				
				vertices.addAll(verts);
				elements.addAll(elems);
				part.getMesh().initWith(vertices, elements);
				 
				pmlBc.setDelta(delta);
				pmlBc.setDist(dist);
				pmlBc.setPMLNodesIds(PMLNodesIds);
			}
			
		});
		
	}

	private HashMap<Integer, List<Integer>> getPmlBcIds(Config config) {
		HashMap<Integer, List<Integer>> idsMap = new HashMap<>();
		config.getParts().forEach((partId, part) -> {
			List<Integer> ids = new ArrayList<>();
			List<Integer> bcids = part.getBoundaryCondition().getBcIds();
			bcids.forEach(id ->{
				if(Boundary.PERFECT_MATCHED_LAYER.equals(config.getBoundary(id).getType())) {
					ids.add(id);
				}
			});
			idsMap.put(partId, ids);
		});
		return idsMap;
	}
	
	private void create(List<double[]> verts, List<int[]> elems, double[] dist, int[] PMLNodesId, double size, 
			double[] norm, List<double[]> bounds, List<Integer> bNodesId, int n, int total) {
		int m = bounds.size();
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				double[] v = bounds.get(j);
				verts.add(new double[]{v[0] + (i + 1) *norm[0] * size, v[1] +(i + 1)* norm[1] * size});
				dist[i * m + j] = size * (i+ 1);
				PMLNodesId[i * m + j] = total + i * m + j;
			}
		}
		
		for(int i = 0; i < m - 1; i++){ 
			elems.add(new int[]{bNodesId.get(i), bNodesId.get(i + 1), i + 1 + total, i + total});
		}
		
		for(int i = 0; i < n - 1; i++) {
			for(int j = 0; j < m - 1;j++) {
				elems.add(new int[]{j + i * (m - 1) + total, j + 1 + i * (m - 1) + total, j + 1 + i * (m - 1) + m + total, j + i * (m - 1) + m + total});
			}
		}
	}
}
