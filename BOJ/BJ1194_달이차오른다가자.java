package Algorithm.BOJ;

import java.util.*;
import java.io.*;

public class BJ1194_달이차오른다가자 {

    private static int n, m;
    private static char[][] map;
    private static boolean[][][] visit;
    private static int[] keys = new int[6];

    private static class Node{
        int r, c, cnt, keyStatus;

        public Node(int r, int c, int cnt, int keyStatus) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
            this.keyStatus = keyStatus;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new char[n][m];
        visit = new boolean[n][m][64];

        int sr = 0, sc = 0;

        for(int i=0; i<n; i++){
            String str = br.readLine();
            for(int j=0; j<m; j++){
                char c = str.charAt(j);
                if(c == '0'){
                    sr = i; sc = j;
                }
                map[i][j] = c;
            }
        }

        int answer = bfs(sr, sc);
        sb.append(answer).append("\n");

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.close();
    }

    private static int bfs(int sr, int sc) {

        int[] drow = new int[]{-1, 1, 0, 0};
        int[] dcol = new int[]{0, 0, -1, 1};
        String[] strarr = new String[]{"위", "아래", "좌", "우"};

        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(sr, sc, 0, 000000));

        while(!q.isEmpty()){
            Node cur = q.poll();

            //출구 도착
            if(map[cur.r][cur.c] == '1') return cur.cnt;

            for(int d=0; d<4; d++){
                int nr = cur.r + drow[d];
                int nc = cur.c + dcol[d];

                if(isIn(nr, nc)){
                    int type = getType(map[nr][nc]);

                    if(type == 0){  //열쇠
                        int keyStatus = (cur.keyStatus | 1 << getKeyNumber(map[nr][nc]));    //획득한 열쇠를 or연산으로 추가
                        if(!visit[nr][nc][keyStatus]){
                            q.add(new Node(nr, nc, cur.cnt+1, keyStatus));
                            visit[nr][nc][keyStatus] = true;
                        }
                    }
                    else if(type == 1){ //문
                        //문은 키가 있을때만 지나갈 수 있다
                        //현재 키 상태와 내가 마주한 문을 & 연산해서 키 소유 여부 판단
                        int doorNum = 1 << getDoorNumber(map[nr][nc]);
                        if((cur.keyStatus & doorNum) == doorNum && !visit[nr][nc][cur.keyStatus]){   //키 있음
                            q.add(new Node(nr, nc, cur.cnt+1, cur.keyStatus));
                            visit[nr][nc][cur.keyStatus] = true;
                        }
                    }
                    else if(type == 2) {  //벽
                        continue;
                    }
                    else{   //빈칸 또는 출발점
                        if(!visit[nr][nc][cur.keyStatus]){
                            q.add(new Node(nr, nc, cur.cnt+1, cur.keyStatus));
                            visit[nr][nc][cur.keyStatus] = true;
                        }
                    }
                }
            }
        }
        return -1;
    }

    private static boolean isIn(int nr, int nc) {
        if(nr<0 || nc<0 || nr>=n || nc >=m) return false;
        return true;
    }

    private static int getType(char c){
        int i = (int)c;

        if(97<=i && i<=102) return 0;   //keys
        else if(65<=i && i<=70) return 1;   //doors
        else if(i == 35) return 2;  //wall

        return 3;   //empty
    }

    private static int getKeyNumber(char c){
        return (int)c - 97;
    }

    private static int getDoorNumber(char c){
        return (int)c - 65;
    }
}
