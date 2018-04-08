package com.haswalk.solver.fvm2d.processors.extend;

import com.haswalk.solver.fvm1d.processors.Processor;

/**
 * Created by wangx on 2018/3/21.
 */
public class VonMises implements Processor{

    private double Y;
    private double[] sdvx;
    private double[] sdvy;
    private double[] sdvxy;


    @Override
    public void calc() {
        int NOE = sdvx.length;
        for(int i = 0; i < NOE; i++) {
            double[] s = {sdvx[i], sdvy[i], sdvxy[i]};
            double t;
            if((t = Math.pow(s[0], 2) + Math.pow(s[1], 2) - s[0] * s[1] + 3 * Math.pow(s[3], 2)) > Y * Y){
                double ratio = Y / Math.sqrt(t);
                s[0] *= ratio;
                s[1] *= ratio;
                s[2] *= ratio;
            }
        }
    }
}
