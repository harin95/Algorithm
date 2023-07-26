package BOJ;

import java.io.*;
import java.util.*;

public class BJ16493_최대페이지수 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] days = new int[m+1];
        int[] pages = new int[m+1];

        for(int i=1; i<m+1; i++){
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());

            days[i] = d;
            pages[i] = p;
        }

        int[][] dp = new int[m+1][n+1];

        for(int i=1; i<=m; i++){
            for(int j=1; j<=n; j++){
                if(j - days[i] >= 0){
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-days[i]] + pages[i]);
                } else{
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        System.out.println(dp[m][n]);

    }
}
