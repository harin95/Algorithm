package SWEA;

import java.util.*;
import java.io.*;

public class Solution_1767_프로세서연결하기 {

    static int n;
    static int[][] cores;
    static int coreCnt;
    static int[][] processor;

    static int[] dr = new int[] {-1, 1, 0, 0};	//상 하 좌 우
    static int[] dc = new int[] {0, 0, -1, 1};

    static int min;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuffer sb = new StringBuffer();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        for(int tc=1; tc<=T; tc++) {

            n = Integer.parseInt(br.readLine());
            processor = new int[n][n];
            cores = new int[n][];
            coreCnt = 0;

            int idx = 0;
            for(int i=0; i<n; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<n; j++) {
                    processor[i][j] = Integer.parseInt(st.nextToken());
                    //가장자리는 이미 연결되어 있음
                    if((i==0 || i==n-1 || j==0 || j==n-1) && processor[i][j] == 1) {
                        continue;
                    }
                    else if(processor[i][j] == 1) {
                        cores[idx++] = new int[] {i, j};
                        coreCnt++;
                    }
                }
            }
            //입력 끝

            for(int i=coreCnt; i>0; i--) {
                min = Integer.MAX_VALUE;
                connect(0, 0, i);
                if(min != Integer.MAX_VALUE) break;
            }

            sb.append("#").append(tc).append(" ").append(min).append("\n");
        }
        bw.write(sb.toString());
        bw.close();
    }

    private static void connect(int corenum, int connectcnt, int target) {

        if(connectcnt == target) {
            min = Math.min(min, lineCount());
            return;
        }

        for(int i=corenum; i<coreCnt; i++) {
            int[]current = cores[i];

            int r = current[0], c = current[1];
            for(int d=0; d<4; d++) {	// 하나의 코어당 4가지 방향 탐색하기
                if(isAvailable(r, c, d)) {	// 장애물이 없으면
                    setLine(r, c, d, 9);	// 연결
                    // 다음 코어로 넘어간다
                    connect(i+1, connectcnt+1, target);
                    // 돌아오면 연결한것 되돌리기
                    setLine(r, c, d, 0);
                }
            }
        }
    }

    private static boolean isAvailable(int r, int c, int d) {

        if(d == 0) {	//상
            for(int i=0; i<r; i++) {
                if(processor[i][c] != 0) return false;
            }
        }
        else if(d == 2){	//좌
            for(int i=0; i<c; i++) {
                if(processor[r][i] != 0) return false;
            }
        }
        else if(d == 1) {	//하
            for(int i=r+1; i<n; i++) {
                if(processor[i][c] != 0) return false;
            }
        }
        else {
            for(int i=c+1; i<n; i++) {		//우
                if(processor[r][i] != 0) return false;
            }
        }
        return true;
    }

    private static void setLine(int r, int c, int d, int number) {

        if(d == 0) {
            for(int i=0; i<r; i++) {
                processor[i][c] = number;
            }
        }
        else if(d == 2){
            for(int i=0; i<c; i++) {
                processor[r][i] = number;
            }
        }
        else if(d == 1) {
            for(int i=r+1; i<n; i++) {
                processor[i][c] = number;
            }
        }
        else {
            for(int i=c+1; i<n; i++) {
                processor[r][i] = number;
            }
        }
    }

    private static int lineCount() {
        int cnt = 0;

        for(int[] arr : processor) {
            for(int i : arr) {
                if(i == 9) cnt++;
            }
        }
        return cnt;
    }

}
