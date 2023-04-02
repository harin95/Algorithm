package BOJ;

import java.util.*;
import java.io.*;

public class BJ7569_토마토 {

    static int[] di = new int[]{0, 0, 0, 0, -1, 1};     //위 아래 이동
    static int[] dj = new int[]{-1, 1, 0, 0, 0, 0};     //세로 이동
    static int[] dk = new int[]{0, 0, -1, 1, 0, 0};     //가로 이동

    static int m, n, h;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());   //가로
        n = Integer.parseInt(st.nextToken());   //세로
        h = Integer.parseInt(st.nextToken());   //높이

        int[][][] tomato = new int[h][n][m];

        int zeroCnt = 0;

        Queue<int[]> q = new ArrayDeque<>();

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < n; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < m; k++) {
                    tomato[i][j][k] = Integer.parseInt(st.nextToken());
                    if(tomato[i][j][k] == 0) zeroCnt++;     //익지 않은 토마토의 개수를 센다
                    else if(tomato[i][j][k] == 1){
                        q.add(new int[]{i, j, k, 0});   //0은 day 수
                    }
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
            int i = current[0], j = current[1], k = current[2];
            day = current[3];

            for (int d = 0; d < 6; d++) {
                int ni = i + di[d];
                int nj = j + dj[d];
                int nk = k + dk[d];
                if (isIn(ni, nj, nk) && tomato[ni][nj][nk] == 0) {  //익지 않은 토미토가 있으면
                    tomato[ni][nj][nk] = 1;
                    q.add(new int[]{ni, nj, nk, day+1});
                }
            }
        }

        //아직 익지 않은 토마토가 남아있으면
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < m; k++) {
                    if(tomato[i][j][k] == 0){
                        System.out.println(-1);
                        return;
                    }
                }
            }
        }

        System.out.println(day);
    }
    private static boolean isIn(int i, int j, int k){
        if(i<0 || j<0 || k<0 || i>=h || j>=n || k>=m) return false;
        return true;
    }
}
