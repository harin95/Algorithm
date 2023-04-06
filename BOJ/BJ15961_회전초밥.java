package BOJ;

import java.io.*;
import java.util.*;

public class BJ15961_회전초밥 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());   //회전초밥 벨트에 있는 접시 수
        int d = Integer.parseInt(st.nextToken());   //초밥 종류
        int k = Integer.parseInt(st.nextToken());   //연속해서 먹는 접시 수
        int c = Integer.parseInt(st.nextToken());   //쿠폰 번호

        int[] belt = new int[n];

        for(int i=0; i<n; i++){
            belt[i] = Integer.parseInt(br.readLine());
        }
        //입력 끝

        int[] chobap = new int[d+1]; //현재 탐색 범위에 있는 초밥 종류 표시
        int cnt = 0;    //탐색 범위에 몇개의 종류가 있는지 카운트

        //처음 k개 탐색
        for(int i=0; i<k; i++){
            if(++chobap[belt[i]] == 1){
                cnt++;
            }
        }

        if(cnt == k && chobap[c] == 0){
            sb.append(cnt+1).append("\n");
        }

        else {

            int max = cnt;

            for (int i = 0; i <n; i++) {

                //맨 앞 초밥종류 제거
                if (--chobap[belt[i]] == 0)
                    cnt--;
                //뒤에 추가
                if (++chobap[belt[(i+k)%n]] == 1)
                    cnt++;

                //k개 안에 c가 들어있지 않으면 추가로 먹을 수 있다
                if(chobap[c] == 0){
                    max = Math.max(max, cnt+1);
                }
                else{
                    max = Math.max(max, cnt);
                }
            }
            sb.append(max).append("\n");
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.close();

    }
}
