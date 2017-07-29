package com.haswalk.solver.fvm2d.processors.support;

import java.util.List;

import com.haswalk.solver.fvm2d.annotation.Inject;
import com.haswalk.solver.fvm2d.annotation.Injection;
import com.haswalk.solver.fvm2d.components.FieldData;
import com.haswalk.solver.fvm2d.components.ModelData;
import com.haswalk.solver.fvm2d.components.TimeControl;
import com.haswalk.solver.fvm2d.processors.DispUpdate;

public class DefaultDispUpdate implements DispUpdate{
	private List<double[]> vertices;
	private double[] vertLstX;
	private double[] vertLstY;
	private double[] dispX;
	private double[] dispY;
	private double[] velX;
	private double[] velY;
	private TimeControl time;
	
	@Override
	public void calc() {
		int NON = vertices.size();
		for(int i = 0; i < NON; i++) {
			double dx = velX[i] * time.getTimeStep();
			double dy = velY[i] * time.getTimeStep();
			dispX[i] += dx;
			dispY[i] += dy;
			vertLstX[i] = vertices.get(i)[0];
			vertLstY[i] = vertices.get(i)[1];
			vertices.get(i)[0] += dx;
			vertices.get(i)[1] += dy;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Inject(ModelData.VERTICES)
	public void setVertices(List<?> vertices) {
		this.vertices = (List<double[]>) vertices;
	}
	
	@Injection
	public void setFieldData(FieldData data) {
		vertLstX = data.get(FieldData.NODE_COOR_LST_X);
		vertLstY = data.get(FieldData.NODE_COOR_LST_Y);
		dispX = data.get(FieldData.DISP_X);
		dispY = data.get(FieldData.DISP_Y);
		velX = data.get(FieldData.VEL_X);
		velY = data.get(FieldData.VEL_Y);
	}
	@Injection
	public void setTimeControl(TimeControl time){
		this.time = time;
	} 
}
