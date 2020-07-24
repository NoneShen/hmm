package com.company;


public class HMM {
    private int numStates=3; // Same as numEmissions

    public enum Feel
    {
        Cold,
        Hot,
        Mild,

    }
    public enum Weather
    {
        Snow,
        Rain,
        Sunshine,

    }
    private int[] states=new int[]{Weather.Snow.ordinal(),Weather.Rain.ordinal(),Weather.Sunshine.ordinal()};

    private double[][] transitionProb={
            {0.3,0.3,0.4},
            {0.1,0.45,0.45},
            {0.2,0.3,0.5}
    };
    private double[][] emissionProb={
            {0.8,0.1,0.1},
            {0.1,0.1,0.8},
            {0.2,0.7,0.1}
    };

    public int[] getStates() {
        return states;
    }

    public void setStates(int[] states) {
        this.states = states;
    }

    public int getNumStates() {
        return numStates;
    }

    public void setNumStates(int numStates) {
        this.numStates = numStates;
    }

    public double[][] getTransitionProb() {
        return transitionProb;
    }

    public void setTransitionProb(double[][] transitionProb) {
        this.transitionProb = transitionProb;
    }

    public double[][] getEmissionProb() {
        return emissionProb;
    }

    public void setEmissionProb(double[][] emissionProb) {
        this.emissionProb = emissionProb;
    }
}