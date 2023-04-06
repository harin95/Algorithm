package SWEA;

import java.io.*;
import java.util.*;

public class SWEA4014_활주로건설 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=T; tc++){
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            x--;    //해당 인덱스 포함 길이가 x이므로 -1 해둔다

            int[][] map = new int[n][n];
            for(int i=0; i<n; i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<n; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int cnt = 0;

            //행
            for(int i=0; i<n; i++){
                //경사로가 겹치는 것을 방지하기 위한 체크 배열
                boolean[] check = new boolean[n];
                col:
                for(int j=0; j<n; j++){

                    if(j == n-1){   //n-1까지 탐색했으면 활주로를 놓을 수 있음
                        cnt++;
                        break;
                    }

                    //j+1이 j보다 1 클 때 -> 왼쪽으로 경사로 필요
                    if(map[i][j] + 1 == map[i][j+1]){

                        //경사로의 길이가 인덱스의 범위를 초과하면 놓을 수 없음
                        if(j-x < 0) break;

                        //j부터 j-x까지 평지이고 경사로가 놓여져있지 않아야한다
                        int height = map[i][j];
                        for(int k=j; k>=j-x; k--){
                            if(height != map[i][k] || check[k]) break col;
                        }
                        //조건 만족하면 경사로 놓기
                        for(int k=j; k>=j-x; k--){
                            check[k]= true;
                        }
                    }

                    //j+1이 j보다 1 작을 때 -> 오른쪽으로 경사로 필요
                    if(map[i][j] == map[i][j+1] + 1){

                        //경사로의 길이가 인덱스의 범위를 초과하면 놓을 수 없음
                        if(j+1+x >= n) break;

                        //j+1부터 j+1+x까지 평지이고 경사로가 놓여져있지 않아야한다
                        int height = map[i][j+1];
                        for(int k=j+1; k<=j+1+x; k++){
                            if(height != map[i][k] || check[k]) break col;
                        }

                        //조건 만족하면 경사로 놓기
                        for(int k=j+1; k<=j+1+x; k++){
                            check[k] = true;
                        }
                    }

                    //앞뒤로 높이 차이가 2 이상일 때
                    if(Math.abs(map[i][j] - map[i][j+1]) > 1) break;

                    /*아래 경우는 활주로를 건설할 수 없음*/
                    //j와 j+1의 높이 차이가 2이상일 때
                    //경사로가 겹쳐져 놓아져야 할 때
                    //경사로의 길이가 인덱스 범위를 초과할때
                    //경사로의 길이만큼 평지가 없을 때
                }
            }

            //열
            for(int j=0; j<n; j++){
                //경사로가 겹치는 것을 방지하기 위한 체크 배열
                boolean[] check = new boolean[n];
                row:
                for(int i=0; i<n; i++){

                    if(i == n-1){   //n-1까지 탐색했으면 활주로를 놓을 수 있음
                        cnt++;
                        break;
                    }



                    //i+1이 i보다 1 클 때 -> 왼쪽으로 경사로 필요
                    if(map[i][j] + 1 == map[i+1][j]){

                        //경사로의 길이가 인덱스의 범위를 초과하면 놓을 수 없음
                        if(i-x < 0) break;

                        //i부터 i-x까지 평지이고 경사로가 놓여져있지 않아야한다
                        int height = map[i][j];
                        for(int k=i; k>=i-x; k--){
                            if(height != map[k][j] || check[k]) break row;
                        }
                        //조건 만족하면 경사로 놓기
                        for(int k=i; k>=i-x; k--){
                            check[k]= true;
                        }
                    }


                    //i+1이 i보다 1 작을 때 -> 오른쪽으로 경사로 필요
                    if(map[i][j] == map[i+1][j] + 1){

                        //경사로의 길이가 인덱스의 범위를 초과하면 놓을 수 없음
                        if(i+1+x >= n) break;

                        //j+1부터 j+1+x까지 평지이고 경사로가 놓여져있지 않아야한다
                        int height = map[i+1][j];
                        for(int k=i+1; k<=i+1+x; k++){
                            if(height != map[k][j] || check[k]) break row;
                        }

                        //조건 만족하면 경사로 놓기
                        for(int k=i+1; k<=i+1+x; k++){
                            check[k] = true;
                        }
                    }

                    //앞뒤로 높이 차이가 2 이상일 때
                    if(Math.abs(map[i][j] - map[i+1][j]) > 1) break;
                }
            }
            sb.append("#").append(tc).append(" ").append(cnt).append("\n");
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.close();
    }
}

