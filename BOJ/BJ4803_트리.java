package BOJ;

import java.io.*;
import java.util.*;

public class BJ4803_트리 {
    private static List<List<Integer>> adjList;
    private static boolean[] visit;
    private static boolean isCycle = false;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        //인접리스트
        adjList = new ArrayList<>();
        //방문체크
        visit = new boolean[501];


        //출력 변수
        StringBuilder sb = new StringBuilder();
        int tc = 1;

        while(v != 0 || e != 0){

            for(int i=0; i<v+1; i++){
                adjList.add(new ArrayList<>());
            }

            //간선 입력받기
            for(int i=0; i<e; i++){
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());

                adjList.get(from).add(to);
                adjList.get(to).add(from);
            }

            //트리 개수 찾기
            int cnt = 0;
            for(int i=1; i<=v; i++){
                if(visit[i]) continue;
                makeTreeDfs(0, i);
                if(!isCycle) cnt++;
                isCycle = false;
            }

            sb.append("Case ").append(tc++).append(": ");

            if(cnt == 0){
                sb.append("No trees.");
            } else if(cnt == 1){
                sb.append("There is one tree.");
            } else if(cnt > 1){
                sb.append("A forest of ").append(cnt).append(" trees.");
            }

            sb.append("\n");

            //초기화 및 다음 입력 받기
            st = new StringTokenizer(br.readLine());
            v = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());
            adjList.clear();
            Arrays.fill(visit, false);

        }
        System.out.println(sb);
    }

    private static void makeTreeDfs(int from, int to){

        for(int v : adjList.get(to)){
            //직전 출발지로 재방문하는것 방지
            if(v == from) continue;
            if(visit[v]) {  //이외에 갔던곳을 재방문하면 사이클이다
                isCycle = true;
                return;
            }
            else{
                visit[v] = true;
                makeTreeDfs(to, v);
            }
        }
    }
}