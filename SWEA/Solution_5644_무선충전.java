package SWEA;

import java.io.*;
import java.util.*;

public class Solution_5644_무선충전 {

    static int[] userA = new int[]{1, 1};
    static int[] userB = new int[]{10, 10};

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for(int test_case=1; test_case<=T; test_case++) {
            int[][] map = new int[11][11];

            String[] input = br.readLine().split(" ");
            int M = Integer.parseInt(input[0]);     //이동시간
            int A = Integer.parseInt(input[1]);     //bc의 개수

            input = br.readLine().split(" ");
            int[] routeA = Arrays.stream(input).mapToInt(Integer::parseInt).toArray();
            input = br.readLine().split(" ");
            int[] routeB = Arrays.stream(input).mapToInt(Integer::parseInt).toArray();

            int[][] bc = new int[A][];     //0: row, 1: col, 2: coverage, 3: performance

            for(int i=0; i<A; i++){
                input = br.readLine().split(" ");
                bc[i] = Arrays.stream(input).mapToInt(Integer::parseInt).toArray();
            }

            int[] totlaA = new int[M];
            int[] totalB = new int[M];
            //입력, 초기화 끝

            //매 시간 충전량 계산
            for(int time=0; time<M; time++){

                //현재 user의 위치에서 각 bc의 충전량을 계산
                int[] chargedA = new int[A];
                int[] chargedB = new int[A];

                //각 충전기 범위에 포함되는지 계산
                for(int idx=0; idx<A; idx++){
                    int[] charger = bc[idx];
                    int c = charger[2];
                    int p = charger[3];

                    int distA = dist(userA[0], userA[1], charger[0], charger[1]);
                    int distB = dist(userB[0], userB[1], charger[0], charger[1]);

                    //현재 위치에서 충전할 수 있는 경우의 수 중 둘의 합이 최대인 것을 저장

                }

                //현재 위치에서 두 유저가 최대로 충전할 수 있는 값을 저장

            }
        }

    }


    static int dist(int c1, int r1, int c2, int r2){
        return Math.abs(c1-c2) + Math.abs(r1-r2);
    }


    //user[0] : col, user[1] : row
    static void move(int[] user, int m){
        switch(m){
            case 0 :
                break;
            case 1 :    //상
                if(user[1]+1 <= 10) user[1]++;
                break;
            case 2 :    //우
                if(user[0]+1 <= 10) user[0]++;
                break;
            case 3 :    //하
                if(user[1]-1 >= 0) user[1]--;
                break;
            case 4 :    //좌
                if(user[0]-1 >= 10) user[0]--;
                break;
        }
    }
}
