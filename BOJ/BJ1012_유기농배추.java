package BOJ;

import java.io.*;
import java.util.*;

public class BJ1012_유기농배추 {

    private static class Node{
        int r, c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int t=0; t<T; t++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            int[][] baechu = new int[n][m];
            boolean[][] visit = new boolean[n][m];

            for(int i=0; i<k; i++){
                st = new StringTokenizer(br.readLine());
                int c = Integer.parseInt(st.nextToken());
                int r = Integer.parseInt(st.nextToken());
                baechu[r][c] = 1;
            }

            int cnt = 0;
            for(int i=0; i<n; i++){
                for(int j=0; j<m; j++){
                    if(!visit[i][j] && baechu[i][j] == 1){
                        cnt++;
                        bfs(i, j, baechu, visit);
                    }
                }
            }

            sb.append(cnt).append("\n");
        }
        System.out.println(sb.toString());
    }

    private static void bfs(int r, int c, int[][] baechu, boolean[][] visit){

        int[] drow = new int[]{-1, 1, 0, 0};
        int[] dcol = new int[]{0, 0, -1, 1};

        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(r, c));
        visit[r][c] = true;

        while(!q.isEmpty()){
            Node cur = q.poll();

            for(int d=0; d<4; d++){
                int nr = cur.r + drow[d];
                int nc = cur.c + dcol[d];

                try{
                    if(!visit[nr][nc] && baechu[nr][nc] == 1){
                        q.add(new Node(nr, nc));
                        visit[nr][nc] = true;
                    }
                } catch(Exception e){ continue; }
            }
        }
    }
}
