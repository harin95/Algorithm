package BOJ;

import java.io.*;
import java.util.*;

public class BJ2579_계단오르기 {

    private static class Jump implements Comparable{
        int cnt;    //1칸 점프 몇번했는지
        int score;

        public Jump(int cnt, int score) {
            this.cnt = cnt;
            this.score = score;
        }

        @Override
        public String toString() {
            return "Jump{" +
                    "cnt=" + cnt +
                    ", score=" + score +
                    '}';
        }

        @Override
        public int compareTo(Object o) {
            Jump jump = (Jump) o;
            return Integer.compare(jump.score, this.score);
        }
    }

    public static void main(String[] args) throws IOException {
        /*
        * 연속된 3개의 계단을 밟으면 안된다
        * i-1계단을 밟고 i계단을 밟는 경우
        * i-2계단을 밟고 i계단을 밟는 경우
        * */

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] stairs = new int[n];

        for(int i=0; i<n; i++){
            stairs[i] = Integer.parseInt(br.readLine());
        }

        int[] scores = new int[301];
        scores[0] = stairs[0];
        if(n == 1){
            System.out.println(scores[0]);
            return;
        }
        scores[1] = stairs[0] + stairs[1];
        if(n==2){
            System.out.println(scores[1]);
            return;
        }
        scores[2] = Math.max(stairs[0] + stairs[2], stairs[1] + stairs[2]);
        if(n==3){
            System.out.println(scores[2]);
            return;
        }

        for(int i=3; i<n; i++){
            scores[i] = Math.max(stairs[i] + stairs[i-1] + scores[i-3], stairs[i] + scores[i-2]);
        }

        System.out.println(scores[n-1]);
    }
}
