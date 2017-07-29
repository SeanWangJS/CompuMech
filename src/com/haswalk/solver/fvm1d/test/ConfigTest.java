package com.haswalk.solver.fvm1d.test;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.haswalk.solver.fvm1d.config.BoundaryCondition;
import com.haswalk.solver.fvm1d.config.Config1D;
import com.haswalk.solver.fvm1d.config.Config1DBuilder;
import com.haswalk.solver.fvm1d.config.Control;
import com.haswalk.solver.fvm1d.config.Material;
import com.haswalk.solver.fvm1d.config.Output;
import com.haswalk.solver.fvm1d.config.Part;
import com.haswalk.solver.fvm1d.config.support.boundarycondition.PiecewiseLoadBC;
import com.haswalk.solver.fvm1d.config.support.control.TimeControl;
import com.haswalk.solver.fvm1d.config.support.part.BoundaryPosition;
import com.haswalk.solver.fvm1d.config.support.part.Mesh;
import com.sean.utils.FileIO;

public class ConfigTest {

	private Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
	@Test
	public void testMaterial() {
		Material m = new Material(1, null)
				.set(Material.ELASTIC_MODULE, 3.27e10)
				.set(Material.DENSITY, 2490.0);
		System.out.println(m.toString());
		JsonObject json = gson.fromJson(m.toString(), JsonObject.class);
		System.out.println(gson.toJson(json).toString());
	}
	
	@Test
	public void testBoundary() {
		BoundaryCondition bc = new PiecewiseLoadBC()
				.set(PiecewiseLoadBC.ID, 1)
				.set(PiecewiseLoadBC.TIMES, new double[]{0, 0.01, 0.02})
				.set(PiecewiseLoadBC.VALUES, new double[]{0, 10000, 0});
		System.out.println(bc.toString());
		JsonObject json = gson.fromJson(bc.toString(), JsonObject.class);
		System.out.println(gson.toJson(json).toString());
	}
	
	@Test
	public void testPart() {
		Part p = new Part(1, null)
				.set(Part.MATERIAL_ID, 1)
				.set(Part.OUTPUT_ID, 1)
				.set(Part.GAUGE_POINTS, new double[][]{{1,1},{0,0}})
				.boundaryPosition()
					.set(BoundaryPosition.BC_ID, 1)
					.set(BoundaryPosition.POINT, new double[]{0, 0})
					.build()
				.mesh()
					.set(Mesh.URI, "E:/fvm1d/1/mesh.txt")
					.build();
		System.out.println(p.toString());
		System.out.println(toJsonStr(p.toString()));
		
	}
	
	@Test
	public void testControl() {
		Control c = new Control(null)
				.time()
				.set(TimeControl.END_TIME, 0.001)
				.set(TimeControl.FACTOR, 0.667)
				.build();
		System.out.println(toJsonStr(c.toString()));
	}
	
	@Test
	public void testOutput() {
		Output o = new Output(1, null)
				.recorder()
					.set(Output.INCREMENT, -1)
					.set(Output.START, 0)
					.set(Output.END, 10000)
					.set(Output.ITEMS, new String[]{"node_vel_x, node_force_x"})
					.build()
				.gauge()
					.set(Output.INCREMENT, -1)
					.set(Output.START, 0)
					.set(Output.END, 10000)
					.set(Output.ITEMS, new String[]{"node_vel_x, node_force_x"})
					.build();
		System.out.println(toJsonStr(o.toString()));
	}
	
	private String toJsonStr(String str) {
		return gson.toJson(gson.fromJson(str, JsonObject.class)).toString();
	}
	
	@Test
	public void test1() {
		Config1D config = new Config1DBuilder()
								.material(1)
									.set(Material.ELASTIC_MODULE, 3.27e10d)
									.set(Material.DENSITY, 2490d)
									.build()
								.boundary(1, BoundaryCondition.PIECEWISE_LOAD_BC)
									.set(PiecewiseLoadBC.TIMES, new double[]{0, 0.01, 0.02})
									.set(PiecewiseLoadBC.VALUES, new double[]{0, 10000, 0})
									.build()
								.part(1)
									.set(Part.MATERIAL_ID, 1)
									.set(Part.OUTPUT_ID, 1)
									.set(Part.GAUGE_POINTS, new double[][]{{1,1},{0,0}})
									.boundaryPosition()
										.set(BoundaryPosition.BC_ID, 1)
										.set(BoundaryPosition.POINT, new double[]{0, 0})
										.build()
									.mesh()
										.set(Mesh.URI, "E:/fvm1d/1/mesh.txt")
										.build()
									.build()
								.control()
									.time()
										.set(TimeControl.END_TIME, 0.001)
										.set(TimeControl.FACTOR, 0.667)
										.build()
									.build()
								.output(1)
									.recorder()
										.set(Output.INCREMENT, -1)
										.set(Output.START, 0)
										.set(Output.END, 10000)
										.set(Output.ITEMS, new String[]{"node_vel_x, node_force_x"})
										.build()
									.gauge()
										.set(Output.INCREMENT, -1)
										.set(Output.START, 0)
										.set(Output.END, 10000)
										.set(Output.ITEMS, new String[]{"node_vel_x, node_force_x"})
										.build()
									.build()
								.build();
			String json = toJsonStr(config.toString());
			FileIO.write(json, "E:/fvm1d/.json");
	}
	
}
