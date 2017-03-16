package com.haswalk.solver.fvm2d.components.creation;

import java.util.List;

import com.haswalk.solver.fvm2d.components.ComponentCreationMethod;
import com.haswalk.solver.fvm2d.components.FieldData;
import com.haswalk.solver.fvm2d.config.Config;

public class FieldDataCreationMethod implements ComponentCreationMethod{

	@Override
	public Object invoke(int partId, Config config) {
		FieldData fieldData = new FieldData(config.getParts().get(partId).getMesh().getNON(),
				config.getParts().get(partId).getMesh().getNOE());
		fieldData.setInitDensity(config
									.getMaterials()
									.get(config.getParts()
											   .get(partId)
											   .getMaterialID())
									.getDensity(),
								 config.getParts().get(partId).getMesh().getVertices(),
								 config.getParts().get(partId).getMesh().getElements(),
								 config.getParts().get(partId).getMesh().getNodesE());
		List<String> saveItems = config.getOutputs().get(
					config.getParts().get(partId).getOutputID()
				)
										.getSaveItems()
										.getItems(); 
		saveItems.forEach(item -> FieldData.regist(item));
		List<String> gaugeItems = config.getOutputs().get(
				config.getParts().get(partId).getOutputID()
			)
									.getGaugeItem()
									.getItems();
		gaugeItems.forEach(item -> FieldData.regist(item));
		return fieldData;
	}

}
