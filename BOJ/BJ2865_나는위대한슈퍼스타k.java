package BOJ;

import java.io.*;
import java.util.*;

public class BJ2865_나는위대한슈퍼스타k {

    static int n, m, k;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());   //참가자 수
        m = Integer.parseInt(st.nextToken());   //장르 수
        k = Integer.parseInt(st.nextToken());   //뽑을 사람 수

        //참가자별로 장르에 대한 능력을 배열에 담음
        double[][] superstar = new double[n+1][m];

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<n; j++){
                int idx = Integer.parseInt(st.nextToken());
                superstar[idx][i] = Double.parseDouble(st.nextToken());
            }
        }

        double[] best = new double[n+1];  //각 참가자들이 제일 잘하는 장르르 뽑는다
        for(int i=1; i<n+1; i++){
            best[i] = Arrays.stream(superstar[i]).max().getAsDouble();
        }

        Arrays.sort(best);

        double sum = 0;
        for(int i=n; i>n-k; i--){
            sum += best[i];
        }

        System.out.printf("%.1f", sum);
    }
}
