package BOJ;

import java.util.*;
import java.io.*;

public class BJ20056_마법사상어와파이어볼 {

    private static int[][] direction = new int[][]{{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};
    private static HashMap<Integer, ArrayList<Integer>> map;
    private static class FireBall{
        int r, c, m, s, d;

        public FireBall(int r, int c, int m, int s, int d) {
            this.r = r;
            this.c = c;
            this.m = m;
            this.s = s;
            this.d = d;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        List<FireBall> list = new ArrayList<>();
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int mi = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            list.add(new FireBall(r, c, mi, s, d));
        }

        map = new HashMap<>();

        for(int i=0; i<k; i++){     //k번 이동 (1000)
            for(int j=0; j<m; j++) { //m개의 구슬 (2500)

            }
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.close();
    }
}
