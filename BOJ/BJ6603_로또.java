package BOJ;

import java.util.*;
import java.io.*;

public class BJ6603_로또 {

    private static int[] lotto = new int[6];
    private static StringBuilder sb;
    private static boolean[] visit;
    public static void main(String[] argsz) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        String input = br.readLine();

        while(!input.equals("0")) {

            int[] numbers = Arrays.stream(input.split(" ")).mapToInt(Integer::parseInt).toArray();
            visit = new boolean[numbers[0]];
            combination(0, numbers, 1);
            sb.append("\n");

            input = br.readLine();
        }
        System.out.println(sb.toString());
    }

    private static void combination(int cnt, int[] numbers, int start){

        if(cnt == 6){
            String output = Arrays.toString(lotto);
            output = output.replace("[", "");
            output = output.replace(",", "");
            output = output.replace("]", "");

            sb.append(output).append("\n");
            return;
        }

        for(int i=start; i<numbers[0]+1; i++){
            if(visit[i-1]) continue;
            lotto[cnt] = numbers[i];
            visit[i-1] = true;
            combination(cnt+1, numbers, i+1);
            visit[i-1] = false;
        }

    }
}
