package SWEA;

import java.util.*;
import java.io.*;

public class Solution_3289_서로소집합 {

    static int[] groups;
    static int n;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(input[0]);

        for(int test_case=1; test_case<=T; test_case++) {
            sb.append("#").append(test_case).append(" ");
            input = br.readLine().split(" ");
            n = Integer.parseInt(input[0]);
            int m = Integer.parseInt(input[1]);

            makeSet();

            for (int i = 0; i < m; i++) {
                input = br.readLine().split(" ");
                int op = Integer.parseInt(input[0]);
                int a = Integer.parseInt(input[1]);
                int b = Integer.parseInt(input[2]);

                if (op == 0) {    //union
                    union(a, b);
                } else {   //같은 그룹인지 확인
                    int answer = 1;
                    if(findSet(a) != findSet(b)) answer = 0;
                    sb.append(answer);
                }
//                System.out.println(Arrays.toString(groups));
            }
            sb.append("\n");
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            bw.write(sb.toString());
            bw.flush();
            sb.delete(0, sb.length());
        }
    }
    static void makeSet(){
        groups = new int[n+1];
        for(int i=1; i<n+1; i++){
            groups[i] = i;
        }
    }

    static int findSet(int a){
        if(groups[a] == a) return a;
        return groups[a] = findSet(groups[a]);  //path compression
    }

    static boolean union(int a, int b){
        int rootA = findSet(a);
        int rootB = findSet(b);

        if(rootA == rootB) return false;

        groups[rootB] = rootA;
        return true;
    }

}
