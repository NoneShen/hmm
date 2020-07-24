package com.company;

public class Main {


    public static void main(String[] args) {
        HMM hmm=new HMM();
        double[] a=HMMEvaluator.evaluate(1000,10,hmm);
        //Viterbi.test();

        double total=0.0;
        for(int i=0;i<a.length;i++){
            total+=a[i];
        }
        System.out.println(total/a.length);
    }
}
