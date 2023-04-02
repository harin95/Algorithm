package BOJ;

import java.io.*;
import java.util.*;

public class BJ2567_색종이2 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	//표준입력
        StringTokenizer st = new StringTokenizer(br.readLine());	//토크나이저

        int len = 104;

        int[][] white = new int[len][len];		//흰색 스카프 영역, 인덱스에러 방지로 범위를 크게 받는다
        int[][] copy = new int[len][len];

        int n = Integer.parseInt(st.nextToken());	//검은색 스카프의 개수

        for(int i=0; i<n; i++) {	//검은색 스카프의 위치를 입력받기 위한 반복문
            st = new StringTokenizer(br.readLine());	//토크나이저
            int cstart = Integer.parseInt(st.nextToken());	//검은색 스카프의 가로 시작점
            int rstart = Integer.parseInt(st.nextToken());	//검은색 스카프의 세로 시작점

            for(int r=rstart+2; r<rstart+12; r++) {	//세로 길이만큼
                for(int c=cstart+2; c<cstart+12; c++) {	//가로 길이만큼
                    white[r][c] = 1;	//배열에서 검은색 스카프의 영역을 1로 표시 (좌표위치가 다르지만 둘레만 구하므로 상관없다)
                }
            }
        }

        //흰색스카프 배열 크기 100*100 = 10000
        //완전탐색으로 해도 시간초과 나지 않는다

        int cnt = 0; 	//둘레를 계산하기 위한 변수

        for(int i=1; i<len-1; i++) {
            for(int j=1; j<len-1; j++) {
                if(white[i][j] == 1 && white[i-1][j] == 0) cnt++;   //위 가로 둘레
                if(white[i][j] == 1 && white[i+1][j] == 0) cnt++;   //아래 가로 둘레
                if(white[i][j] == 1 && white[i][j-1] == 0) cnt++;   //왼쪽 세로 둘레
                if(white[i][j] == 1 && white[i][j+1] == 0) cnt++;   //오른쪽 세로 둘레
            }
        }
        System.out.println(cnt);	//결과 출력
    }
}
