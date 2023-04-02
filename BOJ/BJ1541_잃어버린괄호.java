package BOJ;

import java.io.*;

public class BJ1541_잃어버린괄호 {

    private static String plus = "+";

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int answer = 0;
        String str = br.readLine();

        /*값을 최소로 만들기 위해서는 더하기 연산을 모두 괄호로 묶고 빼기 연산을 수행하면 된다*/

        if(str.contains("-")){
            String[] input = str.split("-");    // 빼기가 있는 식이라면 빼기기준으로 split 한다 -> 더하기 연산 끼리는 하나로 묶임

            for(int i=0; i<input.length; i++){
                int number = 0;
                if(input[i].contains("+")){
                    String[] cal = input[i].split("\\+");
                    for(String s : cal) number += Integer.parseInt(s);  // 더하기 연산을 먼저 수행하고 빼준다
                }
                else{
                    number = Integer.parseInt(input[i]);
                }
                answer = (i==0) ? answer+number : answer-number;    // 첫번째 숫자는 빼지 않는다
            }
        }
        else {  //더하기 연산만 있다면 분리해서 그냥 더해준다
            String[] input = str.split("\\+");
            for(String s : input) answer += Integer.parseInt(s);
        }
        System.out.println(answer);
    }
}
