package com.haswalk.solver.fvm1d;

import com.haswalk.solver.Solver;
import com.haswalk.solver.fvm1d.processors.Processor;
import com.haswalk.solver.util.ListableMap;

public class FVM1DSolver implements Solver{

    private ListableMap<String, Processor> processors;

    @Override
    public Solver run() {
        processors.forEach((name, p) -> p.calc());
        return this;
    }
    @Override
    public Object getResult() {
        return null;
    }

}
