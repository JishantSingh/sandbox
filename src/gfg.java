/*package whatever //do not write package name here */

import java.util.*;
import java.lang.*;
import java.io.*;

class GFG {
    public static final int INF = 1000000000;
    public static void fw(int [][] adj, int [][]d, int n){
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                d[i][j] = adj[i][j];
            }
        }
        for(int k = 0;k<n;k++){
            for(int i =0; i<n;i++){
                for(int j=0;j<n;j++){
                    d[i][j] = Math.min(d[i][j], d[i][k] + d[k][j]);
                }
            }
        }
    }
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-- > 0 ){
            int n = sc.nextInt();
            int [][] adj = new int[n][n];
            int [][] d = new int[n][n];
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    String s = sc.next();
                    if(!s.equals("INF")){
                        adj[i][j] = Integer.parseInt(s);
                    }
                    else {
                        adj[i][j] = INF;
                    }
                }
            }
            fw(adj, d, n);
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    if(d[i][j] < INF){
                        System.out.print(d[i][j]+" ");
                    }
                    else {
                        System.out.print("INF ");
                    }
                }
                System.out.println();
            }
        }
    }
}