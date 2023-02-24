package SWEA;

import java.io.*;
import java.util.*;

public class Solution_5644_무선충전 {
    static int[][] map;
    static int[] userA;
    static int[] userB;

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for(int test_case=1; test_case<=T; test_case++) {
            map = new int[11][11];
            userA = new int[]{1, 1};
            userB = new int[]{10, 10};

            String[] input = br.readLine().split(" ");
            int M = Integer.parseInt(input[0]);     //이동시간
            int A = Integer.parseInt(input[1]);     //bc의 개수

            input = br.readLine().split(" ");
            int[] routeA = Arrays.stream(input).mapToInt(Integer::parseInt).toArray();
            routeA = Arrays.copyOf(routeA, routeA.length+1);
            input = br.readLine().split(" ");
            int[] routeB = Arrays.stream(input).mapToInt(Integer::parseInt).toArray();
            routeB = Arrays.copyOf(routeB, routeB.length+1);

            int[][] bc = new int[A][];     //0: row, 1: col, 2: coverage, 3: performance

            for(int i=0; i<A; i++){
                input = br.readLine().split(" ");
                bc[i] = Arrays.stream(input).mapToInt(Integer::parseInt).toArray();
            }

            for (int i = 0; i < A; i++) {
                map[bc[i][1]][bc[i][0]] = i;
            }

            int answer = 0;
            //입력, 초기화 끝

            //매 시간 충전량 계산
            for(int time=0; time<=M; time++){

                //userA, B가 범위에 들어가는 무선충전기 번호 저장
                ArrayList<Integer> candidateA = new ArrayList<>();
                ArrayList<Integer> candidateB = new ArrayList<>();

                //경우의 수에 따른 둘 위치에서의 최대 충전량
                ArrayList<Integer> candidateTotal = new ArrayList<>();

                //각 충전기 범위에 포함되는지 계산
                for(int idx=0; idx<A; idx++) {
                    int[] charger = bc[idx];
                    int c = charger[2];

                    int distA = dist(userA[0], userA[1], charger[0], charger[1]);
                    int distB = dist(userB[0], userB[1], charger[0], charger[1]);

                    if (distA <= c) candidateA.add(idx);
                    if (distB <= c) candidateB.add(idx);
                }

                //A만 포함될 때
                if(!candidateA.isEmpty() && candidateB.isEmpty()){
                    for(int i=0; i<candidateA.size(); i++){
                        int idxA = candidateA.get(i);
                        candidateTotal.add(bc[idxA][3]);
                    }
                }//B만 포함될 때
                else if(!candidateB.isEmpty() && candidateA.isEmpty()){
                    for(int j=0; j<candidateB.size(); j++){
                        int idxB = candidateB.get(j);
                        candidateTotal.add(bc[idxB][3]);
                    }
                }
                else if(!candidateA.isEmpty() && !candidateB.isEmpty()) {    //둘 다 포함
                    //충전기 조합 경우의 수
                    for (int i = 0; i < candidateA.size(); i++) {
                        for (int j = 0; j < candidateB.size(); j++) {

                            int idxA = candidateA.get(i);
                            int idxB = candidateB.get(j);
                            int chargeA, chargeB = 0;

                            if (idxA == idxB) {
                                //같으면 bc 범위에 있으면 균등분배
                                candidateTotal.add(bc[idxA][3]);
                            } else {
                                //다른 bc에 있을 경우
                                candidateTotal.add(bc[idxA][3] + bc[idxB][3]);
                            }
                        }
                    }
                }
                else{   //둘 다 포함되지 않으면 이동만
                    move(userA, routeA[time]);
                    move(userB, routeB[time]);
                    continue;
                }
                int nowMax = candidateTotal.stream().mapToInt(x->x).max().getAsInt();
                answer += nowMax;

                //위치 이동
                move(userA, routeA[time]);
                move(userB, routeB[time]);
            }
            System.out.println("#" + test_case + " " + answer);
        }
    }

    private static int dist(int c1, int r1, int c2, int r2){
        return Math.abs(c1-c2) + Math.abs(r1-r2);
    }

    //user[0] : col, user[1] : row
    private static void move(int[] user, int m){
        switch(m){
            case 0 :
                break;
            case 1 :    //상
                if(user[1]-1 >= 1) user[1]--;
                break;
            case 2 :    //우
                if(user[0]+1 <= 10) user[0]++;
                break;
            case 3 :    //하
                if(user[1]+1 <= 10) user[1]++;
                break;
            case 4 :    //좌
                if(user[0]-1 >= 1) user[0]--;
                break;
        }
    }
}
