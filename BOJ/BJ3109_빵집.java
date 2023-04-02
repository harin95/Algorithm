package BOJ;

import java.io.*;
import java.util.*;

public class BJ3109_빵집 {

    static char[][] breadHouse;
    static int cnt = 0;
    static int r, c;
    static boolean rtrn = false;    //재귀 리턴 플래그

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        breadHouse = new char[r][];
        for(int i=0; i<r; i++) breadHouse[i] = br.readLine().toCharArray();
        //입력 끝

        //빵집 영역 점 r개를 시작점으로 가스관 경로 탐색
        for(int i=0; i<r; i++){
            stealGas(i, 0);
            rtrn = false;
        }
        System.out.println(cnt);
    }

    static void stealGas(int i, int j){
        if(breadHouse[i][j] == 'x' || breadHouse[i][j] == 'g' || rtrn) {    //집이거나 이미 가스관이 있으면 돌아옴
            return;
        }

        else if(j == c-1){  //원웅이네 빵집까지 도달
            cnt++;
            rtrn = true;    //경로 하나를 완성하면 재귀 스택 모두 리턴하도록 플래그 true로 만듦
            breadHouse[i][j] = 'g';
            return;
        }

        //현재칸이 빈칸이면 가스관 놓고 다음칸으로 이동
        //파이프를 최대로 놓기 위해서 위오른대각, 오른, 아래오른 순으로 탐색
        else if(breadHouse[i][j] == '.'){
            breadHouse[i][j] = 'g';
            if(i-1 >= 0) {
                stealGas(i - 1, j + 1);
            }

            if(j+1 < c) {
                stealGas(i, j + 1);
            }

            if(i+1 <r && j+1 <c) {
                stealGas(i + 1, j + 1);
            }
        }
    }
}