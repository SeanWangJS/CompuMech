package com.haswalk.solver.fvm1d.test;

import org.junit.Test;

import com.haswalk.solver.fvm1d.config.Config1D;
import com.haswalk.solver.fvm1d.config.Config1DBuilder;
import com.haswalk.solver.fvm1d.config.Material;

public class ConfigTest {

	@Test
	public void test1() {
		Config1D config = new Config1DBuilder()
								.material(1)
									.set(Material.ELASTIC_MODULE, 3.27e10)
									.set(Material.DENSITY, 2490)
									.build()
								.load(1)
									.set("type", "piecewise")
									.set("time", new double[]{0, 0.01, 0.02})
									.set("value", new double[]{0, 10000, 0})
									.build()
								.part(1)
									.set("meshUri", "E:/fvm1d/1/mesh.txt")
									.set("materialID", 1)
									.set("outputID", 1)
									.set("loadID", 1, "point", new double[]{})
									.set("gauges", new double[][]{{}})
									.build()
								.control()
									.time("endTime", 0.02, "factor", 0.667)
									.build()
								.output(1)
									.save()
										.set("inc", -1)
										.set("items", "node_vel", "node_stress")
										.build()
									.gauge()
										.set("inc", -1)
										.set("items", "node_vel", "node_stress")
										.build()
									.build()
								.build();
								
									
	}
}
