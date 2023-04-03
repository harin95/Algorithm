package SWEA;

import java.io.*;
import java.util.*;

public class SWEA1263_사람네트워크2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for(int t=1 ; t<T; t++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());   //사람 수

            //인접행렬
            int[][] adjMatrix = new int[n][n];
            for(int i=0; i<n; i++){
                for(int j=0; j<n; j++){
                    adjMatrix[i][j] = Integer.parseInt(st.nextToken());
                }
            }
        }
    }
}
