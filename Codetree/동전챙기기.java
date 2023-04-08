package Codetree;

import java.util.*;
import java.io.*;

public class 동전챙기기 {
    private static int n;
    private static char[][] map;

    private static boolean visit[][][];

    private static class Node{
        int r, c, coinCnt, maxCoin, moveCnt, coinStatus;

        public Node(int r, int c, int coinCnt, int maxCoin, int moveCnt, int coinStatus){
            this.r = r;
            this.c = c;
            this.coinCnt = coinCnt;
            this.maxCoin = maxCoin;
            this.moveCnt = moveCnt;
            this.coinStatus = coinStatus;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new char[n][n];

        Queue<Node> q = new ArrayDeque();

        for(int i=0; i<n; i++){
            map[i] = br.readLine().toCharArray();
            for(int j=0; j<n; j++){
                if(map[i][j] == 'S'){
                    q.add(new Node(i, j, 0, 0, 0, 0));
                }
            }
        }
        visit = new boolean[n][n][1<<10];

        int answer = bfs(q);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(answer));
        bw.close();
    }

    private static int bfs(Queue<Node> q){
        int[] drow = new int[]{-1, 1, 0, 0};
        int[] dcol = new int[]{0, 0, -1, 1};

        while(!q.isEmpty()){
            Node cur = q.poll();

            if(map[cur.r][cur.c] == 'E' && cur.coinCnt >= 3){
                return cur.moveCnt;
            }

            for(int d=0; d<4; d++){
                int nr = cur.r + drow[d];
                int nc = cur.c + dcol[d];

                //범위 체크
                if(!isIn(nr, nc)) continue;

                //벽이면 못감
                if(map[nr][nc] == '#') continue;

                //동전이면
                if('1' <= map[nr][nc] && map[nr][nc] <='9'){
                    int coin = getCoinNum(map[nr][nc]);
                    int status = cur.coinStatus | 1<<coin;  //코인 주웠을 경우 상태

                    if(coin <= cur.maxCoin){   //현재 코인보다 작거나 같은 코인이면 줍지 않고 지나감
                        if(visit[nr][nc][cur.coinStatus]) continue;
                        q.add(new Node(nr, nc, cur.coinCnt, cur.maxCoin, cur.moveCnt+1, cur.coinStatus));
                        visit[nr][nc][cur.coinStatus] = true;
                    }
                    else{   //현재코인보다 큰 코인일 경우 줍고 지나간다
                        if(visit[nr][nc][status]) continue; //코인을 가지고 이미 방문한 상태면 가지 않음

                        //줍고 지나가는 경우
                        Node nodeWithCoin = new Node(nr, nc, cur.coinCnt+1, coin, cur.moveCnt+1, status);
                        q.add(nodeWithCoin);
                        visit[nr][nc][status] = true;

                        //안줍고 지나가는 경우
                        Node nodeWithoutCoin
                                = new Node(nr, nc, cur.coinCnt, cur.maxCoin, cur.moveCnt+1, cur.coinStatus);
                        q.add(nodeWithoutCoin);
                        visit[nr][nc][cur.coinStatus] = true;
                    }
                }
                //빈칸이면
                else{
                    if(visit[nr][nc][cur.coinStatus]) continue;
                    q.add(new Node(nr, nc, cur.coinCnt, cur.maxCoin, cur.moveCnt+1, cur.coinStatus));
                    visit[nr][nc][cur.coinStatus] = true;
                }
            }
        }
        return -1;
    }

    private static boolean isIn(int r, int c){
        if(r<0 || c<0 || r>=n || c>=n) return false;
        return true;
    }

    private static int getCoinNum(char c){
        return (int) c - 48;
    }
}