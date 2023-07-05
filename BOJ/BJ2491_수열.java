package BOJ;

import java.io.*;
import java.util.*;

public class BJ2491_수열 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int lenInc = 1;
        int lenDec = 1;
        int max = 1;

        for(int i=1; i<n; i++){
            if(input[i-1] < input[i]) lenInc++;
            else lenInc = 1;
            max = Math.max(max, lenInc);

            if(input[i-1] >= input[i]) lenDec++;
            else lenDec = 1;
            max = Math.max(max, lenDec);
        }
        System.out.println(max);
    }
}
