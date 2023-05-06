package BOJ;

import java.io.*;

public class BJ1003_피보나치함수 {
    private static class Fibo{
        int zero;
        int one;

        public Fibo(int zero, int one) {
            this.zero = zero;
            this.one = one;
        }

        public static Fibo add(Fibo f1, Fibo f2){
            int zero = f1.zero + f2.zero;
            int one = f1.one + f2.one;

            return new Fibo(zero, one);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        Fibo[] fiboArr = new Fibo[41];
        fiboArr[0] = new Fibo(1, 0);
        fiboArr[1] = new Fibo(0, 1);

        for(int i=2; i<41; i++){
            fiboArr[i] = Fibo.add(fiboArr[i-1], fiboArr[i-2]);
        }

        StringBuilder sb = new StringBuilder();
        for(int t=0; t<T; t++){
            int number = Integer.parseInt(br.readLine());
            sb.append(fiboArr[number].zero).append(" ").append(fiboArr[number].one).append("\n");
        }

        System.out.println(sb.toString());
    }
}
