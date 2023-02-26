package BOJ;

import java.io.*;
import java.util.*;

public class Main_13023_ABCDE {

    static int n, m;
    static ArrayList[] friends;
    static boolean visit[];
    static int fn = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());   //사람 수
        m = Integer.parseInt(st.nextToken());   //관계 수

        if (m < 4) {    //관계가 4개보다 적게 주어지면 5명이상 친구가 될 수 없음
            System.out.println(0);
            return;
        }

        visit = new boolean[n];
        friends = new ArrayList[n];     //연결 정점들을 리스트로 저장

        //초기화
        for(int i=0; i<n; i++){
            friends[i] = new ArrayList<Integer>();
        }

        //입력
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            friends[from].add(to);
            friends[to].add(from);
        }

        //0번부터 5명이상 연결되는 친구가 있는지 탐색
        for(int i=0; i<n; i++) {
            visit[i] = true;
            dfs(i, 0);
            visit[i] = false;
            if(fn == 4) return;     //5명 발견하면 더이상 탐색할 필요 없음
        }
        System.out.println(0);
    }

    static void dfs(int start, int depth){
        if(fn == 4) return;     //5명까지 찾으면 더 이상 찾을 필요 없음

        if(depth == 4){
            fn = 4;
            System.out.println(1);
            return;
        }

        ArrayList<Integer> temp = friends[start];   //현재 정점에 연결된 정점 리스트

        for(int j=0; j<temp.size(); j++){
            int nextv = temp.get(j);
            if(visit[nextv]) continue;
            visit[nextv] = true;
            dfs(nextv, depth+1);
            visit[nextv] = false;
        }
    }
}
