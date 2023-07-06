package BOJ;

import java.util.*;

public class BJ2161_카드1 {
    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        Queue<Integer> q = new ArrayDeque<>();

        for(int i=1; i<=n; i++){
            q.add(i);
        }

        StringBuilder sb = new StringBuilder();

        while(q.size() > 1){
            sb.append(q.poll()).append(" ");
            q.add(q.poll());
        }

        sb.append(q.peek());
        System.out.println(sb);
    }
}
