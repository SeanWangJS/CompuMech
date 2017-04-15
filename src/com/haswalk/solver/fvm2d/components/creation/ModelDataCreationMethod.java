package com.haswalk.solver.fvm2d.components.creation;

import com.haswalk.solver.fvm2d.components.ComponentCreationMethod;
import com.haswalk.solver.fvm2d.components.ModelData;
import com.haswalk.solver.fvm2d.components.creation.modeldata.BoundaryCreation;
import com.haswalk.solver.fvm2d.components.creation.modeldata.ElementsCreation;
import com.haswalk.solver.fvm2d.components.creation.modeldata.ModelDataAttributeCreationMethod;
import com.haswalk.solver.fvm2d.components.creation.modeldata.NOECreation;
import com.haswalk.solver.fvm2d.components.creation.modeldata.NONCreation;
import com.haswalk.solver.fvm2d.components.creation.modeldata.VerticesCreation;
import com.haswalk.solver.fvm2d.config.Config;
import com.haswalk.solver.fvm2d.util.ListableMap;

public class ModelDataCreationMethod implements ComponentCreationMethod{

	private static ListableMap<String, ModelDataAttributeCreationMethod> methods = new ListableMap<>();
	
	static{
		regist(ModelData.VERTICES, new VerticesCreation());
		regist(ModelData.ELEMENTS, new ElementsCreation());
		regist(ModelData.NODES_AROUDN_NODE, new NONCreation());
		regist(ModelData.NODES_AROUND_ELEM, new NOECreation());
		regist(ModelData.BOUNDARY_CONDITION, new BoundaryCreation());
	}
	
	@Override
	public Object invoke(int partId, Config config) {
		ModelData md = new ModelData();
		methods.forEach((att, method) -> {
			md.put(att, method.invoke(partId, config));
		});
		return md;
	}

	public static void regist(String name, ModelDataAttributeCreationMethod method) {
		methods.put(name, method);
	}
}
