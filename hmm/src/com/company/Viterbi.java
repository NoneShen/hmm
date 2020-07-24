package com.company;


public class Viterbi {
    public static int[] solve(HMM hmm, double[] initialProb, int [] observations) {
        int T=observations.length;
        double[][] A=hmm.getTransitionProb();
        double[][] B=hmm.getEmissionProb();
        int[] states= hmm.getStates();
        int K=hmm.getNumStates();
        double[][] T1=new double[T][K];
        int[][] T2=new int[K][T];
        int[][] T2_temp=new int[K][T];
        double val,max_val;
        int max_val_index;
        // Initialization
        for (int y :states){
            T1[0][y]= initialProb[y] * B[y][observations[0]];
            T2[y][0]=y;
        }
        //Recursion
        for(int t=1;t<T;t++){
            for(int y:states){
                max_val=0.0;
                max_val_index=0;
                for(int i:states){
                    val=T1[t-1][i] * A[i][y] * B[y][observations[t]];
                    if(val>max_val){
                        max_val=val;
                        max_val_index=i;
                        T1[t][y]=max_val;
                        System.arraycopy(T2[max_val_index],0,T2_temp[y],0,t);
                        T2_temp[y][t]=y;
                    }
                }
            }
            T2=T2_temp;
        }
        max_val=-1;
        max_val_index=0;
        for(int y:states){
            if(T1[T-1][y] > max_val){
                max_val=T1[T-1][y];
                max_val_index=y;
            }
        }
        return T2[max_val_index];

        // Returns the MLE for the states.
    }

    static int[] observations=new int[]{0,1,2};

    public static void test() {
        HMM hmm=new HMM();
        double[] intial={0.33,0.33,0.33};
        int[] result=Viterbi.solve(hmm,intial,observations);
        for (int r : result)
        {
            System.out.print(HMM.Weather.values()[r] + " ");
        }
        System.out.println();
    }


}