package BOJ;

import java.io.*;
import java.util.*;

public class BJ1260_DFS와BFS {
    private static int n;
    private static ArrayList<Integer>[] adj;
    private static boolean[] visit;

    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(st.nextToken());

        adj = new ArrayList[n+1];
        for(int i=1; i<n+1; i++){
            adj[i] = new ArrayList<Integer>();
        }

        visit = new boolean[n+1];

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            adj[from].add(to);
            adj[to].add(from);
        }

        dfs(start);
        Arrays.fill(visit, false);
        sb.append("\n");
        bfs(start);
        sb.append("\n");

        System.out.println(sb.toString());
    }

    private static void dfs(int from){
        visit[from] = true;
        sb.append(from).append(" ");

        for(int to=1; to<=n; to++){
            if(!visit[to] && adj[from].contains(to)){
                dfs(to);
            }
        }
    }

    private static void bfs(int start){

        Queue<Integer> q = new ArrayDeque<>();
        q.add(start);
        visit[start] = true;

        while(!q.isEmpty()){
            int from = q.poll();
            sb.append(from).append(" ");

            for(int to=1; to<=n; to++){
                if(!visit[to] && adj[from].contains(to)){
                    q.add(to);
                    visit[to] = true;
                }
            }
        }
    }

}
