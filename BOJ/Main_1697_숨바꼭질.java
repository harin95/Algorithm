package BOJ;

import java.util.*;

public class Main_1697_숨바꼭질 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        boolean[] check = new boolean[100001];

        if(n==k) {
            System.out.println(0);
            return;
        }

        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{n, 0});

        while(!q.isEmpty()){
            int[] next = q.poll();
            int num = next[0];
            int time = next[1];

            if(num == k){
                System.out.println(time);
                return;
            }

            else{
                //다음 계산으로 넘어감
                if(num-1 >= 0 && !check[num-1]) {
                    q.add(new int[]{num - 1, time + 1});
                    check[num-1] = true;
                }
                if(num+1 <= 100000 && !check[num+1]){
                    q.add(new int[]{num+1, time+1});
                    check[num+1] = true;
                }
                if(num*2 <= 100000 && !check[num*2]) {
                    q.add(new int[]{num * 2, time + 1});
                    check[num*2] = true;
                }
            }
        }
    }
}
