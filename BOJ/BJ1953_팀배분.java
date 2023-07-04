package BOJ;

import java.io.*;
import java.util.*;

public class BJ1953_팀배분 {

    private static boolean[] visit;
    private static int n;
    private static int[][] adjMatrix;
    private static int[] divideTeam;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        adjMatrix = new int[n+1][n+1];
        divideTeam = new int[n+1];

        for(int i=1; i<=n; i++){
            st = new StringTokenizer(br.readLine());
            int hateNum = Integer.parseInt(st.nextToken());

            for(int j=0; j<hateNum; j++){
                int hateWho = Integer.parseInt(st.nextToken());
                adjMatrix[i][hateWho] = 1;
                adjMatrix[hateWho][i] = 1;
            }
        }

        visit = new boolean[n+1];

        for(int i=1; i<=n; i++){
            if(!visit[i]){
//                bfs(i, 1);
            dfs(i, 1);
            }
        }

        StringBuilder blue = new StringBuilder();
        StringBuilder white = new StringBuilder();
        int blueCnt = 0;

        for(int i=1; i<=n; i++){
            if(divideTeam[i] == 1) {
                blue.append(i).append(" ");
                blueCnt++;
            }
            else white.append(i).append(" ");
        }

        StringBuilder sb = new StringBuilder();
        sb.append(blueCnt).append("\n").append(blue).append("\n")
                .append(n-blueCnt).append("\n").append(white).append("\n");

        System.out.println(sb);
    }

    /*
    * 이분그래프: 인접한 정점끼리 서로 다른 색으로 칠해서 모든 정점을 두 가지 색으로만 칠할 수 있는 그래프
    * 두 그룹으로 나눌때는 dfs를 쓰자*/
    private static void bfs(int start, int team){
        Queue<Integer> q = new ArrayDeque<>();
        //시작 정점 넣고 방문 체크
        q.add(start);
        divideTeam[start] = team;
        visit[start] = true;

        //연결된 정점 탐색하면 청팀 백팀으로 나눈다
        while(!q.isEmpty()){
            int vertex = q.poll();

            for(int i=1; i<=n; i++){
                if(adjMatrix[vertex][i] != 0){
                    if(!visit[i]){
                        divideTeam[i] = divideTeam[vertex] * -1;
                        visit[i] = true;
                    }
                }
            }
        }
    }
    /*
     * bfs 예외
     * 4
     * 1 3
     * 1 4
     * 1 4
     * 2 2 3*/
    private static void dfs(int vertex, int team){
        divideTeam[vertex] = team;
        visit[vertex] = true;

        for(int i=1; i<=n; i++){
            if(adjMatrix[vertex][i] != 0){
                if(!visit[i]){
                    divideTeam[i] = team*-1;
                    visit[i] = true;
                    dfs(i, team*-1);
                }
            }
        }
    }
}
