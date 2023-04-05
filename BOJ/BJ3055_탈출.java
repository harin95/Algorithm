package BOJ;

import java.io.*;
import java.util.*;

public class BJ3055_탈출 {
    private static int n, m;

    private static int[] drow = new int[]{-1, 1, 0, 0};
    private static int[] dcol = new int[]{0, 0, -1, 1};
    private static boolean visit[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        visit = new boolean[n][m];

        //홍수, 비버 이동 경로 큐
        Queue<int[]> flood = new ArrayDeque<>();
        Queue<int[]> docci = new ArrayDeque<>();

        //숲 지도
        char[][] map = new char[n][m];

        //해당 위치까지 이동하는데 걸린 시간 메모
        int[][] time = new int[n][m];
        //도착지점
        int[] beaver = new int[2];

        for(int i=0; i<n; i++){
            map[i] = br.readLine().toCharArray();
            for(int j=0; j<m; j++){
                if(map[i][j] == '*'){
                    //물 좌표 큐에 넣기
                    flood.add(new int[]{i, j, 0});  //0은 깊이
                }
                else if(map[i][j] == 'S'){
                    //고슴도치 좌표 큐에 넣기
                    docci.add(new int[]{i, j, 0});  //0은 깊이
                    visit[i][j] = true;             //방문처리
                }
                else if(map[i][j] == 'D'){      //도착 지점
                    beaver[0] = i;
                    beaver[1] = j;
                }
            }
        }

        int[] water = new int[]{};  //물 현재 위치
        int[] cur = new int[]{};    //고슴도치 현재 위치

        int t = 0;  //깊이가 t인 좌표들만 한 큐에 처리해 준다
        boolean escape = false;     //탈출 성공 여부

        while(!flood.isEmpty() || !docci.isEmpty()){

            //홍수가 퍼질 좌표는 비버가 이동하지 않아야 하기 때문에 홍수 먼저 처리
            while(!flood.isEmpty()){
                if(flood.peek()[2] != t) break;     //깊이가 t인 좌표들만 처리해줄것
                water = flood.poll();

                for(int d=0; d<4; d++){
                    int nr = water[0] + drow[d];
                    int nc = water[1] + dcol[d];
                    int depth = water[2];

                    if(isIn(nr, nc)){
                        //돌, 비버 소굴로는 못감
                        if(map[nr][nc] == 'X' || map[nr][nc] == 'D' || map[nr][nc] == '*') continue;
                        map[nr][nc] = '*';  //홍수 퍼트리기
                        flood.add(new int[]{nr, nc, depth+1});
                    }
                }
            }

            //비버 이동
            while(!docci.isEmpty()){
                if(docci.peek()[2] != t) break;
                cur = docci.poll();

                //비버네집 도착
                if(cur[0] == beaver[0] && cur[1] == beaver[1]){
                    sb.append(time[cur[0]][cur[1]]).append("\n");
                    escape = true;
                    break;
                }

                for(int d=0; d<4; d++){
                    int nr = cur[0] + drow[d];
                    int nc = cur[1] + dcol[d];
                    int depth = cur[2];

                    if(isIn(nr, nc)){
                        //홍수나 돌로는 못감
                        if(map[nr][nc] == '*' || map[nr][nc] == 'X' || visit[nr][nc]) continue;
                        time[nr][nc] = time[cur[0]][cur[1]] + 1;
                        visit[nr][nc] = true;
                        docci.add(new int[]{nr, nc, depth+1});
                    }
                }
            }
            t++;
            if(escape) break;
        }

        //탈출하지 못하는 경우
        if(!escape)
            sb.append("KAKTUS").append("\n");

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.close();
    }

    private static boolean isIn(int r, int c) {
        if (r < 0 || c < 0 || r >= n || c >= m) return false;
        return true;
    }
}
