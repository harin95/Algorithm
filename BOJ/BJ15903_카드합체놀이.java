package BOJ;

import java.util.*;
import java.io.*;

public class BJ15903_카드합체놀이 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        Queue<Long> pq = new PriorityQueue<>();

        for(int i=0; i<n; i++){
            Long num = Long.parseLong(st.nextToken());
            pq.add(num);
        }

        for(int i=0; i<m; i++){
            Long num1 = pq.poll();
            Long num2 = pq.poll();

            for(int j=0; j<2; j++) pq.add(num1 + num2);
        }

        Long sum = 0L;

        for(Long i : pq){
            sum += i;
        }

        System.out.println(sum);
    }
}
