package com.haswalk.solver.fvm2d.blueprint;

import com.haswalk.solver.fvm2d.Blueprint;
import com.haswalk.solver.fvm2d.components.Components;
import com.haswalk.solver.fvm2d.components.creation.FieldDataCreationMethod;
import com.haswalk.solver.fvm2d.components.creation.MaterialPropertyCreationMethod;
import com.haswalk.solver.fvm2d.components.creation.ModelDataCreationMethod;
import com.haswalk.solver.fvm2d.components.creation.TimeControlCreationMethod;
import com.haswalk.solver.fvm2d.processors.Processor;
import com.haswalk.solver.fvm2d.processors.extend.MessageUpdate;
import com.haswalk.solver.fvm2d.processors.support.AccUpdate;
import com.haswalk.solver.fvm2d.processors.support.DefaultDispUpdate;
import com.haswalk.solver.fvm2d.processors.support.DefaultForceUpdate;
import com.haswalk.solver.fvm2d.processors.support.DefaultStrainUpdate;
import com.haswalk.solver.fvm2d.processors.support.DefaultStressUpdate;
import com.haswalk.solver.fvm2d.processors.support.DefaultVelUpdate;
import com.haswalk.solver.fvm2d.processors.support.ElasticPressureUpdate;
import com.haswalk.solver.fvm2d.processors.support.GroupUpdate;
import com.haswalk.solver.fvm2d.processors.support.StressDevUpdate;

public class ElasticModelBlueprint extends Blueprint{

	public ElasticModelBlueprint(){
		registComponentCreationMethod(Components.FIELD_DATA, new FieldDataCreationMethod());
		registComponentCreationMethod(Components.MODEL_DATA, new ModelDataCreationMethod());
		registComponentCreationMethod(Components.TIME_CONTROL, new TimeControlCreationMethod());
		registComponentCreationMethod(Components.MATERIAL_PROPERTY, new MaterialPropertyCreationMethod());
		
		registProcessor(Processor.FORCE_UPDATE, DefaultForceUpdate.class);
		registProcessor(Processor.ACC_UPDATE, AccUpdate.class);
		registProcessor(Processor.VEL_UPDATE, DefaultVelUpdate.class);
		registProcessor(Processor.DISP_UPDATE, DefaultDispUpdate.class);
		registProcessor(Processor.GROUP_UPDATE, GroupUpdate.class);
		registProcessor(Processor.STRAIN_UPDATE, DefaultStrainUpdate.class);
		registProcessor(Processor.STRESS_DEV_UPDATE, StressDevUpdate.class);
		registProcessor(Processor.PRESSURE_UPDATE, ElasticPressureUpdate.class);
		registProcessor(Processor.STRESS_UPDATE, DefaultStressUpdate.class);
		registProcessor("MessageUpdate", MessageUpdate.class);
	}
	
}