package com.company;

import java.util.Random;

public class HMMEvaluator {


    public static int RandomByWeight(double[] weights) {
        Random random = new Random();
        int weightSum = 0;
        for (int i = 0; i < weights.length; i++) {
            weightSum += weights[i]*10000;//注意这一步至关重要 10000是精度 这个值越大精度越高 这里取10的倍数 因为比例不变
        }
        //总的权重中生成一个随机数
        int number_rand = random.nextInt(weightSum);
        int sum_temp = 0;
        //循环数组 判断生成的随机数是否小于等于当前权重是就返回序号 否则加上当前的权重继续循环
        for (int i = 0; i < weights.length; i++) {
            sum_temp += weights[i]*10000;
            if (number_rand <= sum_temp) {
                return i;
            }
        }
        return -1;
    }

    public static double[] evaluate(int time,int states_length,HMM hmm){
        double accuracy[]=new double[time];

        double[] initialProb={0.33,0.33,0.33};
        double[][] A=hmm.getTransitionProb();
        double[][] B=hmm.getEmissionProb();
        int[] states_sample=new int[states_length];
        int[] observations=new int[states_length];
        int[] result=new int[states_length];
        for(int i=0;i<time;i++){
            int first_state=RandomByWeight(initialProb);
            states_sample[0]=first_state;
            int errors=0;
            for(int j=1;j<states_length;j++){
                states_sample[j]=RandomByWeight(A[states_sample[j-1]]);
            }
            for(int k=0;k<states_length;k++){
                observations[k]=RandomByWeight(B[states_sample[k]]);
            }

            result= Viterbi.solve(hmm,initialProb,observations);

            for(int m=0;m<states_length;m++){
                if(result[m]!=states_sample[m]){
                    errors++;
                }
            }
            accuracy[i]=(double)(states_length-errors)/states_length;

        }


        return accuracy;
    }





}
