package BOJ;

import java.io.*;
import java.util.*;

public class BJ2606_바이러스 {

    private static int n;
    private static int[][] adj;
    private static boolean[] visit;
    private static int cnt = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        adj = new int[n+1][n+1];
        visit = new boolean[n+1];
        visit[1] = true;

        for(int i=0; i<m; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            adj[from][to] = 1;
            adj[to][from] = 1;
        }

        dfs(1);

        System.out.println(cnt);
    }

    private static void dfs(int from){
        for(int to=1; to<=n; to++){
            if(adj[from][to] == 1 && !visit[to]){
                visit[to] = true;
                cnt++;
                dfs(to);
            }
        }
    }
}
