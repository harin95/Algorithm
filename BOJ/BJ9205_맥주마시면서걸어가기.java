package BOJ;

import java.io.*;
import java.util.*;

public class BJ9205_맥주마시면서걸어가기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int t=0; t<T; t++){

            int beer = 20;

            int n = Integer.parseInt(br.readLine());    //편의점 개수

            //상근 집
            StringTokenizer st = new StringTokenizer(br.readLine());
            int rs = Integer.parseInt(st.nextToken());
            int cs = Integer.parseInt(st.nextToken());

            //편의점들
            List<int[]> markets = new ArrayList<>();
            for(int i=0; i<n; i++){
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                markets.add(new int[]{x, y});
            }

            //페스티벌
            st = new StringTokenizer(br.readLine());
            int re = Integer.parseInt(st.nextToken());
            int ce = Integer.parseInt(st.nextToken());

        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.close();
    }
}
