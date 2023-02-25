package BOJ;

import org.example.Main;

import java.util.*;
import java.io.*;

public class Main_1987_알파벳 {

    static char[][] board;
    static int R, C;
    static boolean[] check;

    static int[] dr = new int[]{-1, 1, 0, 0};
    static int[] dc = new int[]{0, 0, -1, 1};
    static int max = 0;

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        R = Integer.parseInt(input[0]);
        C = Integer.parseInt(input[1]);

        board = new char[R][];
        for(int i=0; i<R; i++) board[i] = br.readLine().toCharArray();

        check = new boolean[27];

        check[geti(board[0][0])] = true;    //시작점 방문 표시
        dfs(0, 0, 1);
        System.out.println(max);
    }

    static void dfs(int r, int c, int depth){
        if(noMoreRoute(r, c)){   //더 이상 갈 곳이 없으면 되돌아감
            max = Math.max(max, depth);
            return;
        }

        for(int i=0; i<4; i++){
            int nextr = r + dr[i];
            int nextc = c + dc[i];

            if(nextr < 0 || nextr >= R || nextc < 0 || nextc >= C) continue;

            //다음 이동할 알파벳
            int idx = geti(board[nextr][nextc]);

            if(check[idx]) continue;      //이미 선택한 곳이면 가지 않음
            check[idx] = true;
            dfs(nextr, nextc, depth+1);
            check[idx] = false;
        }
    }

    static boolean noMoreRoute(int r, int c){
        for(int i=0; i<4; i++){
            int nextr = r + dr[i];
            int nextc = c + dc[i];

            if(nextr < 0 || nextr >= R || nextc < 0 || nextc >= C) continue;

            //하나라도 나아갈 길이 있으면 false 반환
            int idx = geti(board[nextr][nextc]);
            if(!check[idx]) return false;
        }
        return true;
    }

    static int geti(char c){
        return (int)c - 65;      // A = 0
    }
}

