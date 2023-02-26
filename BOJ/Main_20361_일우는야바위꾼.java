package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_20361_일우는야바위꾼 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());   //컵 개수
        int x = Integer.parseInt(st.nextToken());   //공 위치
        int k = Integer.parseInt(st.nextToken());   //교환 회수

        for(int change=0; change<k; change++){
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());

            if(i == x) x =j;
            else if(j == x) x = i;
        }
        System.out.println(x);
    }
}
