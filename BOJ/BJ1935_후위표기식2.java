package BOJ;

import java.io.*;
import java.util.*;

public class BJ1935_후위표기식2 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String input = br.readLine();

        char alphabet = 'A';
        Map<String, Double> numbers = new HashMap<>();

        for(int i=0; i<n; i++){
            Double num = Double.parseDouble(br.readLine());
            numbers.put(String.valueOf(alphabet), num);
            alphabet = (char) (alphabet + 1);
        }

        Stack<Double> calculate = new Stack<>();

        for(int i=0; i<input.length(); i++){
            char c = input.charAt(i);

            if(isOperator(c)){
                try{
                    Double num2 = calculate.pop();
                    Double num1 = calculate.pop();

                    if(c == '+'){
                        calculate.push(num1 + num2);
                    } else if(c == '-'){
                        calculate.push(num1 - num2);
                    } else if(c == '*'){
                        calculate.push(num1 * num2);
                    } else if(c == '/'){
                        calculate.push(num1 / num2);
                    }

                } catch(Exception ignored){}
            } else{
                Double num = numbers.get(String.valueOf(c));
                calculate.push(num);
            }
        }

        Double result = calculate.pop();

        System.out.printf("%.2f", result);
    }

    private static boolean isOperator(char val){
        return val == '+' || val == '-' || val == '*' || val == '/';
    }
}
