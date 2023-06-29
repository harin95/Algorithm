package BOJ;

import java.util.Scanner;

public class BJ13699_점화식 {

    private static long[] numbers;
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int input = sc.nextInt();

        numbers = new long[36];
        numbers[0] = 1;

        for(int i=1; i<=35; i++){

            long num = 0;

            for(int j=0; j<i; j++){
                num += numbers[j]*numbers[i-j-1];
            }

            numbers[i] = num;
        }

        System.out.println(numbers[input]);
    }
}
