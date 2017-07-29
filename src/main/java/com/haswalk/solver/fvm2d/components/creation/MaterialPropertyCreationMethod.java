package com.haswalk.solver.fvm2d.components.creation;

import com.haswalk.solver.fvm2d.components.ComponentCreationMethod;
import com.haswalk.solver.fvm2d.components.MaterialProperty;
import com.haswalk.solver.fvm2d.config.Config;
import com.haswalk.solver.fvm2d.config.Material;

import static com.haswalk.solver.fvm2d.components.MaterialProperty.*;

public class MaterialPropertyCreationMethod implements ComponentCreationMethod{

	@Override
	public Object invoke(int partId, Config config) {
		MaterialProperty property = new MaterialProperty();
		Material material = config.getMaterials().get(config.getParts().get(partId).getMaterialID());
		property.set(BULK_MODULE, material.getK());
		property.set(DAMPING_RATIO, material.getDampingRatio());
		property.set(DENSITY, material.getDensity());
		property.set(ELASTIC_MODULE, material.getE());
		property.set(NATRUAL_FREQUENCY, material.getNaturalFrequency());
		property.set(POISSON_RATIO, material.getPoissonRatio());
		property.set(SHEAR_MODULE, material.getG());
		property.set(SOUND_SPEED, material.getCp());
		property.set(STRENGTH_MODEL, Material.strengthModelExp.get(material.getStrengthModelType()));
		return property;
	}

}
