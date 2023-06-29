package BOJ;

import java.io.*;

public class BJ25479_기술연계마스터임스 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] input = br.readLine().split("");
        int[] pre = new int[2];
        int cnt = 0;

        for(int i=0; i<n; i++) {
            String s = input[i];

            try {
                int num = Integer.parseInt(s);
                cnt++;
            } catch (NumberFormatException ignored) {
            }

            if (s.equals("L")){
                pre[0]++;
            } else if(s.equals("S")) {
                pre[1]++;
            } else if (s.equals("R")) {
                if(pre[0]-- > 0) {
                    cnt++;
                }
                else{
                    System.out.println(cnt);
                    return;
                }
            } else if (s.equals("K")) {
                if(pre[1]-- > 0){
                    cnt++;
                }
                else{
                    System.out.println(cnt);
                    return;
                }
            }
        }
        System.out.println(cnt);
    }
}
