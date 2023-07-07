package BOJ;

import java.io.*;

public class BJ4811_알약 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long[][] dp = new long[31][31];

        for (int i = 1; i <= 30; i++) {
            for (int j = 0; j <= 30; j++) {
                if (j == 0) dp[i][j] = 1;
                else if (i >= j) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }

        int n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while (n != 0) {
            sb.append(dp[n][n]).append("\n");
            n = Integer.parseInt(br.readLine());
        }
        System.out.println(sb);
    }
}
