package com.haswalk.solver.fvm2d.config;

import java.util.HashMap;

import com.google.gson.annotations.SerializedName;
import com.haswalk.solver.fvm2d.config.material.ElasticModel;
import com.haswalk.solver.fvm2d.config.material.MohrCoulomb;
import com.haswalk.solver.fvm2d.config.material.StrengthModel;

public class Material {

	public final static HashMap<String, Class<?>> strengthModelMap = new HashMap<>();
	public final static HashMap<String, Double> strengthModelExp = new HashMap<>();
	static{
		strengthModelMap.put("elastic", ElasticModel.class);
		strengthModelMap.put("mohrCoulomb", MohrCoulomb.class);
		strengthModelMap.put("mohr_coulomb", MohrCoulomb.class);
		strengthModelExp.put("elastic", 1.0);
		strengthModelExp.put("mohrCoulomb", 2.0);
		strengthModelExp.put("mohr_coulomb", 2.0);
		
	}
	
	@SerializedName(value = "elasticModule", alternate = { "elastic_module", "E" })
	private double E;

	@SerializedName(value = "poissonRatio", alternate = { "poisson_ratio", "nu" })
	private double poissonRatio;
	
	private double density;
	
	@SerializedName(value = "dampingRatio", alternate = {"damping_ratio"})
	private double dampingRatio;
	
	@SerializedName(value = "naturalFrequency", alternate = {"natural_frequency"})
	private double naturalFrequency;
	
	@SerializedName(value = "strengthModelType", alternate = {"strength_model_type"})
	private String strengthModelType;
	
	private StrengthModel strengthModel;
	
	private double cp;
	private double cs;
	private double K;
	private double G;
	
	public void init(){
		K = E / (3 * (1 - 2 * poissonRatio));
		G = E / (2 * (1 + poissonRatio));
		cp = Math.sqrt(( K + 4 / 3.0 * G)/ density); 
		cs = Math.sqrt(G/density);
	}
	
	
	
	@Override
	public String toString() {
		return "Material \n[E=" + E + "\npoissonRatio=" + poissonRatio + "\ndensity=" + density + "\ndampingRatio="
				+ dampingRatio + "\nnaturalFrequency=" + naturalFrequency + "\nstrengthModelType=" + strengthModelType
				+ "\nstrengthModel=" + strengthModel + "\ncp=" + cp + "\ncs=" +cs+"\nK=" + K + "\nG=" + G + "]";
	}



	public void setStrengthModel(StrengthModel strengthModel) {
		this.strengthModel = strengthModel;
	}
	public StrengthModel getStrengthModel() {
		return strengthModel;
	}
	
	public double getCp() {
		return cp;
	}

	public double getK() {
		return K;
	}

	public double getDensity() {
		return density;
	}

	public double getG() {
		return G;
	}

	public String getStrengthModelType(){
		return strengthModelType;
	}

	public double getE() {
		return E;
	}

	public double getPoissonRatio() {
		return poissonRatio;
	}

	public double getDampingRatio() {
		return dampingRatio;
	}

	public double getNaturalFrequency() {
		return naturalFrequency;
	}
	
}
