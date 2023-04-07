package BOJ;


import java.io.*;
import java.util.*;

public class BJ2042_구간합구하기 {

    private static long[] arr, fenwick;
    private static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());   //수 변경 횟수
        int k = Integer.parseInt(st.nextToken());   //구간합 구하는 횟수


        arr = new long[n+1];

        for(int i=1; i<n+1; i++){
            arr[i] = Long.parseLong(br.readLine());
        }
        //입력 끝

        //펜윅트리 초기화
        fenwick = new long[n+1];
        for(int i=1; i<n+1; i++){
            update(i, arr[i]);
        }

        for(int i=0; i<m+k; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            if(a == 1){ //수 변경
                update(b, c-arr[b]);    //차이만큼 더해주기
                arr[b] = c;
            }
            else{   //구간합 구하기
                sb.append(getSum((int)c)-getSum(b-1)).append("\n");
            }
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.close();
    }

    private static void update(int idx, long number){
        while(idx < n+1){
            fenwick[idx] += number;
            idx += idx & -idx;
        }
    }

    private static long getSum(int idx){
        long sum = 0;

        while(idx > 0){
            sum += fenwick[idx];
            idx -= idx & -idx;
        }
        return sum;
    }
}
