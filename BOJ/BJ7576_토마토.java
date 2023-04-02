package BOJ;

import java.io.*;
import java.util.*;

public class BJ7576_토마토 {
    static int[] dj = new int[]{-1, 1, 0, 0};     //세로 이동
    static int[] dk = new int[]{0, 0, -1, 1};     //가로 이동

    static int m, n, h;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());   //가로
        n = Integer.parseInt(st.nextToken());   //세로

        int[][] tomato = new int[n][m];

        int zeroCnt = 0;

        Queue<int[]> q = new ArrayDeque<>();

        for (int j = 0; j < n; j++) {
            st = new StringTokenizer(br.readLine());
            for (int k = 0; k < m; k++) {
                tomato[j][k] = Integer.parseInt(st.nextToken());
                if(tomato[j][k] == 0) zeroCnt++;     //익지 않은 토마토의 개수를 센다
                else if(tomato[j][k] == 1){
                    q.add(new int[]{j, k, 0});   //0은 day 수
                }
            }
        }


        //시작할때부터 다 익어있다면 0 출력 후 종료
        if(zeroCnt == 0) {
            System.out.println(0);
            return;
        }

        int day = 0;

        while(!q.isEmpty()){
            int[] current = q.poll();
            int j = current[0], k = current[1];
            day = current[2];

            for (int d = 0; d < 4; d++) {
                int nj = j + dj[d];
                int nk = k + dk[d];
                if (isIn(nj, nk) && tomato[nj][nk] == 0) {  //익지 않은 토미토가 있으면
                    tomato[nj][nk] = 1;
                    q.add(new int[]{nj, nk, day+1});
                }
            }
        }

        //아직 익지 않은 토마토가 남아있으면
        for (int j = 0; j < n; j++) {
            for (int k = 0; k < m; k++) {
                if(tomato[j][k] == 0){
                    System.out.println(-1);
                    return;
                }
            }
        }


        System.out.println(day);
    }
    private static boolean isIn(int j, int k){
        if(j<0 || k<0|| j>=n || k>=m) return false;
        return true;
    }
}