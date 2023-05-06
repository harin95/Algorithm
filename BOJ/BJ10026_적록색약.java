package BOJ;

import java.io.*;
import java.util.*;

public class BJ10026_적록색약 {

    private static class Node{
        int r, c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        char[][] grid1 = new char[n][];
        char[][] grid2 = new char[n][];

        boolean[][] visit1 = new boolean[n][n];
        boolean[][] visit2 = new boolean[n][n];

        for(int i=0; i<n; i++){
            String input = br.readLine();
            grid1[i] = input.toCharArray();
            grid2[i] = input.toCharArray();

            for(int j=0; j<n; j++){
                if(grid2[i][j] == 'G') grid2[i][j] = 'R';
            }
        }

        int cnt1=0, cnt2 = 0;

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(!visit1[i][j]){
                    cnt1++;
                    bfs(i, j, grid1, visit1);
                }
                if(!visit2[i][j]){
                    cnt2++;
                    bfs(i, j, grid2, visit2);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(cnt1).append(" ").append(cnt2).append("\n");

        System.out.println(sb.toString());
    }

    private static void bfs(int r, int c, char[][] grid, boolean[][] visit){
        int[] drow = new int[]{-1, 1, 0, 0};
        int[] dcol = new int[]{0, 0, -1, 1};

        char color = grid[r][c];
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(r, c));
        visit[r][c] = true;

        while(!q.isEmpty()){
            Node cur = q.poll();

            for(int d=0; d<4; d++){
                int nr = cur.r + drow[d];
                int nc = cur.c + dcol[d];

                try{
                    if(!visit[nr][nc] && grid[nr][nc] == color){
                        q.add(new Node(nr, nc));
                        visit[nr][nc] = true;
                    }
                } catch(Exception e ){ continue; }
            }
        }
    }
}
