package com.haswalk.solver.fvm1d;

import com.haswalk.solver.Solver;
import com.haswalk.solver.SolverBuilder;
import com.haswalk.solver.fvm1d.config.Config1D;

public class FVM1DSolverBuilder implements SolverBuilder{


    private Config1D config;

    public SolverBuilder setConfig(Config1D config){
        this.config = config;
        return this;
    }

    @Override
    public SolverBuilder parseConfig(String config) {
        return null;
    }

    @Override
    public Solver create() {
        return null;
    }
}
