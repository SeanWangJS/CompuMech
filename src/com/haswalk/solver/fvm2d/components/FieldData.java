package com.haswalk.solver.fvm2d.components;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.sean.utils.ArrUtil;
import com.sean.utils.Geom;
import com.sean.utils.LsUtil;

public class FieldData {
	
	public final static String NODE_COOR_LST_X = "node_coor_lst_x";
	public final static String NODE_COOR_LST_Y = "node_coor_lst_y";
	public final static String FORCE_X = "node_force_x";
	public final static String FORCE_Y = "node_force_y";
	public final static String ACC_X = "node_acc_x";
	public final static String ACC_Y = "node_acc_y";
	public final static String VEL_X = "node_vel_x";
	public final static String VEL_Y = "node_vel_y";
	public final static String DISP_X = "node_disp_x";
	public final static String DISP_Y = "node_disp_y";
	public final static String NODE_STRESS_X = "node_stress_x";
	public final static String NODE_STRESS_Y = "node_stress_y";
	public final static String NODE_STRESS_XY = "node_stress_xy";
	public final static String NODE_MASS = "node_mass";
	
	public final static String ELEM_MASS = "elem_mass";
	public final static String ELEM_AREA = "elem_area";
	public final static String ELEM_AREA_LST = "elem_area_lst";
	public final static String ELEM_DENSITY= "elem_density";
	public final static String ELEM_STRESS_X = "elem_stress_x";
	public final static String ELEM_STRESS_Y = "elem_stress_y";
	public final static String ELEM_STRESS_XY = "elem_stress_xy";
	public final static String ELEM_STRAIN_X = "elem_strain_x";
	public final static String ELEM_STRAIN_Y = "elem_strain_y";
	public final static String ELEM_STRAIN_XY = "elem_strain_xy";
	public final static String ELEM_STRAIN_RATE_X = "elem_strain_rate_x";
	public final static String ELEM_STRAIN_RATE_Y = "elem_strain_rate_y";
	public final static String ELEM_STRAIN_RATE_XY = "elem_strain_rate_xy";
	public final static String ELEM_STRESS_DEV_X = "elem_stress_dev_x";
	public final static String ELEM_STRESS_DEV_Y = "elem_stress_dev_y";
	public final static String ELEM_STRESS_DEV_XY = "elem_stress_dev_xy";
	public final static String ELEM_PRESSURE = "elem_pressure";
	public final static String ELEM_CHAR_LEN = "elem_char_len";
	
	private HashMap<String, double[]> data1d = new HashMap<>();
	private static List<String> data1dName = new ArrayList<>();
	private int NOE;
	private int NON;
	
	private int partId;
	
	static{
		data1dName.addAll(Arrays.asList(NODE_COOR_LST_X, NODE_COOR_LST_Y, FORCE_X, FORCE_Y, ACC_X, ACC_Y, VEL_X, VEL_Y, DISP_X, DISP_Y,
				NODE_STRESS_X, NODE_STRESS_Y, NODE_STRESS_XY, NODE_MASS,
				ELEM_MASS, ELEM_AREA, ELEM_AREA_LST, ELEM_DENSITY, ELEM_STRESS_X, ELEM_STRESS_Y, ELEM_STRESS_XY,
				ELEM_STRAIN_X, ELEM_STRAIN_Y, ELEM_STRAIN_XY, ELEM_STRAIN_RATE_X, ELEM_STRAIN_RATE_Y, ELEM_STRAIN_RATE_XY, ELEM_STRESS_DEV_X,
				ELEM_STRESS_DEV_Y, ELEM_STRESS_DEV_XY, ELEM_PRESSURE, ELEM_CHAR_LEN));
	}
	
	public FieldData(int NON, int NOE, int partId){
		this.NOE = NOE;
		this.NON = NON;
		this.partId = partId;
		init();
	}
	
	public static void regist(String item){
		data1dName.add(item);
	}
	
	public void regist(String item, double[] d){
		data1d.put(item, d);
	}
	
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("FieldData-------------------------------\n");
		builder.append("ready\n");
		builder.append("FieldData End---------------------------\n");
		return builder.toString();
	}
	
	private void init(){
		data1dName.forEach(item -> {
			boolean isNodeItem = "node".equals(item.split("_")[0]);
			boolean isElemItem = "elem".equals(item.split("_")[0]);
			if(isNodeItem) {
				data1d.put(item, new double[NON]);
			}else if(isElemItem){
				data1d.put(item, new double[NOE]);
			}else{
				System.err.println("Warning: illegal item name: " + item + ", please checkout if it is start with 'node' or 'elem'.");
			}
		});
	}
	
	public void setInitDensity(double initDensity, List<double[]> vertices, List<int[]> elements, List<List<Integer>> nodesE){
		double[] eArea = data1d.get(ELEM_AREA);
		double[] eMass = data1d.get(ELEM_MASS);
		double[] nMass = data1d.get(NODE_MASS);
		for(int i = 0; i < NOE; i++) {
			int[] elem = elements.get(i);
			double area = Geom.area(LsUtil.select(vertices, elem));
			eArea[i] = area;
			eMass[i] = area * initDensity;
		}
		
		for(int i = 0; i < NON; i++) {
			List<Integer> ean = nodesE.get(i);
			double[] masses = ArrUtil.select(eMass, ean);
//			nMass[i] = ArrUtil.sum(masses) / 4.0;
			for(int j = 0; j < masses.length; j++) {
				nMass[i] += masses[j] / (double)elements.get(ean.get(j)).length;
			}
		}
//		FileIO.writeDoubleArr_stream(nMass, "E:/fvm/15/nMass" + partId + ".txt");
//		FileIO.writeIntListList(nodesE, "E:/fvm/15/nodesE"+partId+".txt", "\t");
	}
	
	public double[] get(String name){
		return data1d.get(name);
	}
	
}
