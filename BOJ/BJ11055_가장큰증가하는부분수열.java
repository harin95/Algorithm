package BOJ;

import java.util.*;
import java.io.*;

public class BJ11055_가장큰증가하는부분수열 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        if(n == 1){
            System.out.println(input[0]);
            return;
        }

        int[] sumArr = Arrays.copyOf(input, n);

        int max = 0;

        for(int i=0; i<n; i++){
            for(int j=i-1; j>=0; j--){
                if(input[i] > input[j]){
                    sumArr[i] = Math.max(sumArr[i], sumArr[j] + input[i]);
                }
            }
            max = Math.max(max, sumArr[i]);
        }
        System.out.println(max);
    }
}
