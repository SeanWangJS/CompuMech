package com.haswalk.solver.fvm2d.test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.haswalk.solver.fvm2d.config.Boundary;
import com.haswalk.solver.fvm2d.config.Config;
import com.haswalk.solver.fvm2d.config.Material;
import com.haswalk.solver.fvm2d.config.Part;
import com.haswalk.solver.fvm2d.config.boundary.LoadBoundary;
import com.haswalk.solver.fvm2d.config.deserializer.BoundaryDeserializer;
import com.haswalk.solver.fvm2d.config.deserializer.MaterialDeserializer;
import com.haswalk.solver.fvm2d.config.part.BoundaryConditionApplyPosition;
import com.haswalk.solver.fvm2d.config.part.Gauge;
import com.haswalk.solver.fvm2d.config.part.Mesh;
import com.sean.wang.utils.DouArr;

public class ConfigParserTest {

	@Test
	public void test1() {

		String json = new StringBuilder().append("{").append("'elasticModule': 1,").append("'poissonRatio': 1,")
				.append("'density':'1',").append("'dampingRatio': 1,").append("'naturalFrequency': 1,")
				.append("'strengthModelType': 'elastic',").append("'strengthModel': {").append("  'just': 1")
				.append(" } ").append("}").toString();
		Gson gson = new GsonBuilder().registerTypeAdapter(Material.class, new MaterialDeserializer())
				.setPrettyPrinting().create();
		gson.fromJson(json, Material.class);

	}

	@Test
	public void test2() {
		String json = new StringBuilder().append("{'config':{'material':{'1':{").append("'elasticModule': 1,")
				.append("'poissonRatio': 1,").append("'density':'1',").append("'dampingRatio': 1,")
				.append("'naturalFrequency': 1,").append("'strengthModelType': 'elastic',").append("'strengthModel': {")
				.append("  'just': 1").append(" } ").append("}}}}").toString();
		new Config().parse(json);
	}

	@Test
	public void test3() {
		String json = new StringBuilder().append("{").append("  'type':'stress',").append("  'loadType':'expression',")
				.append("  't':[[0.0, 1.0]],").append("  'value':[\"SIN(t)\"]").append("}").toString();
		Gson gson = new GsonBuilder().registerTypeAdapter(Boundary.class, new BoundaryDeserializer())
				.setPrettyPrinting().create();
		gson.fromJson(json, Boundary.class);
	}

	@Test
	public void test4() {
		String json = new StringBuilder()
				.append("{'config':{'boundary': {")
				.append("  '1':{")
				.append("    'type':'stress',")
				.append("    'loadType':'expression',")
				.append("'load':{")
				.append("    't':[[0, 1]],")
				.append("    'value':[\"SIN(t)\"]}")
				.append("  },")
				.append("  '2':{")
				.append("    'type':'force',")
				.append("    'loadType':'file',")
				.append("'load':{")
				.append("    'uri':'E:/fvm/7/load.txt'}")
				.append("  },")
				.append("  '3':{")
				.append("    'type':'force',")
				.append("    'loadType':'piecewise',")
				.append("'load':{")
				.append("    't':[0, 1.5, 2],")
				.append("    'value':[0, 100, 0]}")
				.append("   }")
				.append("}}}")
				.toString();
		new Config().parse(json);
	}

	@Test
	public void test6() {
		String json = new StringBuilder()
				.append("{'config':{'part':{")
				.append("  '1':{")
				.append("     'mesh':{")
				.append("       'uri':'E:/fvm/7/mesh.txt'")
				.append("	  },")
				.append("    'boundaryCondition':{")
				.append("'idLineMap':{")
				.append("'1':[[1, 0], [1, 1]],")
				.append("'2':[[2, 0], [2, 1]]")
				.append("}")
				.append("    },")
				.append("    'gauge':{")
				.append("      'fixed': false,")
				.append("      'points': [[1.0, 2.0],[3,4]]")
				.append("    }")
				.append("  }")
				.append("}}}")
				.toString();
		new Config().parse(json);
	}
	
	@Test
	public void test8() {
		String json = new StringBuilder()
				.append("{'config':{'control':{")
				.append("  'time':{")
				.append("    'endTime': 1,")
				.append("    'factor': 0.8")
				.append("  }")
				.append("}}}")
				.toString();
		new Config().parse(json);
	}
	
	@Test
	public void test10() {
		String json = new StringBuilder()
				.append("{'config':{'output':{").append("'save':{").append("'inc':10,")
				.append("'items':[\"stressX\", \"stressY\"]").append("},").append("'gauge': {").append("'inc':2,")
				.append("'items':[\"stressX\", \"stressY\"]").append("}").append("}}}").toString();
		new Config().parse(json);
	}
	
	private String json;
	private Config config;
	
	@Before
	public void before() throws IOException{
		json = new String(Files.readAllBytes(Paths.get("E:/fvm/15/config.json")));
		config = new Config();
		config.parse(json);
	}
	
	@Test
	public void testConfigParse() throws IOException {
		System.out.println(config.toString());
	}
	
	@Test
	public void testMaterial() throws IOException{
		HashMap<Integer, Material> materials = config.getMaterials();
		materials.forEach((id, material) -> {
			material.init();
			System.out.println(id + "\n" + material.toString() + "\nend");
			});
	}
	
	@Test
	public void testFileLoad(){
		LoadBoundary boundary = (LoadBoundary)config.getBoundaries().get(2);
		System.out.println(boundary.toString());
		boundary.init();
		double value = boundary.getLoad().apply(2.0);
		System.out.println(value);
		double value2 = boundary.getLoad().apply(1.5);
		System.out.println(value2);
	}
	
	@Test
	public void testExpLoad(){
		Boundary boundary = config.getBoundaries().get(1);
		System.out.println(boundary.toString());
		boundary.init();
		DouArr.gen(0, 10, 0.1).stream().forEach(va -> {
			System.out.println(va + ":" + ((LoadBoundary)boundary).getLoad().apply(va) + ":" + Math.sin(va));
		});
	}
	
	@Test
	public void testPiecewiseLoad(){
		LoadBoundary boundary = (LoadBoundary)config.getBoundaries().get(3);
		System.out.println(boundary.toString());
		boundary.init();
		DouArr.gen(0, 2, 0.04).stream().forEach(va -> {
			System.out.println(boundary.getLoad().apply(va));
		});
	}
	
	@Test
	public void testMesh(){
		Part part = config.getParts().get(1);
		Mesh mesh = part.getMesh();
		mesh.init();
		System.out.println(mesh.getVertices().size() + ", " + mesh.getElements().size());
		System.out.println(mesh.getNodesE().size() + ", " + mesh.getNodesN().size());
	}
	
	@Test
	public void testBoundary(){
		Part part = config.getParts().get(1);
		Mesh mesh = part.getMesh();
		mesh.init();
		
		BoundaryConditionApplyPosition boundary = part.getBoundaryCondition();
		boundary.init(mesh.getVertices());
		List<Integer> ids1 = boundary.getApplyNodesId(1);
		System.out.println(ids1.toString());
		
		List<Integer> ids2 = boundary.getApplyNodesId(2);
		System.out.println(ids2.toString());
		
	}
	
	@Test
	public void testGauge(){
		Part part = config.getParts().get(1);
		Mesh mesh = part.getMesh();
		mesh.init();
		Gauge gauge = part.getGauge();
		
		gauge.init(mesh.getVertices());
		System.out.println(gauge.getGaugeNodesID());
	}
}
