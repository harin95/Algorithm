package BOJ;

import java.util.*;
import java.io.*;

public class BJ16918_봄버맨 {

    static int[][] map;
    static int r, c, n;
    static ArrayList<int[]> save = new ArrayList<>();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuffer sb = new StringBuffer();

        st = new StringTokenizer(br.readLine());
        // 1<= r, c, n <= 200
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());   //n초 후에 격자판 상태를 출력해야함

        String[] str = new String[c];
        map = new int[r][c];    //칸 안에 숫자는 폭탄이 설치된 이후 몇초가 흘렀는지, -1은 빈칸

        int time = 1;

        //초기상태 설정
        for(int i=0; i<r; i++) {
            str = br.readLine().split("");
            for (int j = 0; j < c; j++) {
                if (str[j].equals("O")) map[i][j] = 1;   //폭탄
                else map[i][j] = -1;    //빈칸
            }
        }

        while(time < n){   //n초가 될때까지
            time++;
            setBomb();
            bomb();
        }

        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                if(map[i][j] == -1) sb.append(".");
                else sb.append("O");
            }
            sb.append("\n");
        }
        sb.append("\n");
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.close();
    }

    //빈 공간에는 폭탄을 채우고, 폭탄이 있는 위치는 시간을 증가시킨다
    private static void setBomb(){
        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                if(map[i][j] == -1) map[i][j] = 0;   //빈칸에 폭탄 설치
                else {
                    if(++map[i][j] == 3) save.add(new int[]{i, j});
                }
            }
        }
    }

    //폭탄 터트리기
    private static void bomb(){
        int[] drow = new int[]{-1, 1, 0, 0};
        int[] dcol = new int[]{0, 0, -1, 1};

        for(int t=0; t<save.size(); t++){
            int[] arr = save.get(t);
            int i = arr[0];
            int j = arr[1];

            //폭탄 터트리고
            map[i][j] = -1;

            //상하좌우 터트리기
            for(int d=0; d<4; d++){
                int ni = i+drow[d];
                int nj = j+dcol[d];

                if(isIn(ni, nj)){
                    map[ni][nj] = -1;
                }
            }
        }
        //저장 초기화
        save.clear();
    }

    private static boolean isIn(int i, int j){
        if(i<0 || j<0 || i>=r || j>=c) return false;
        return true;
    }

    private static void print(){
        for(int[] arr : map) System.out.println(Arrays.toString(arr));
    }
}