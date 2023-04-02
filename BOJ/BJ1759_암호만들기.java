package BOJ;

import java.util.*;
import java.io.*;

public class BJ1759_암호만들기 {

    static boolean[] choose;
    static String[] input;
    static int l, c;
    static StringBuilder sb;

    /*최대 연산 15C15
    * 최소 1개의 모음과 최소 2개의 자음*/

    public static void main(String[] args) throws IOException{

        Scanner sc = new Scanner(System.in);
        l = sc.nextInt();   //암호길이
        c = sc.nextInt();   //가능성 알파벳 수
        sc.nextLine();

        input = sc.nextLine().split(" ");
        Arrays.sort(input);
        choose = new boolean[c];
        sb = new StringBuilder();

        for(int i=0; i<l; i++) sb.append("0");      //초기화
        combination(0, 0, 0, 0);

    }

    static void combination(int cnt, int start, int mo, int ja){
        /*mo : 현재까지 모음 개수, ja : 현재까지 자음 개수*/
        if(cnt == l){
            if(mo >=1 && ja >= 2) System.out.println(sb.toString());
            return;
        }

        for(int i=start; i<c; i++){
            if(choose[i]) continue;

            sb.setCharAt(cnt, input[i].charAt(0));
            choose[i] = true;
            if(check(input[i])){
                combination(cnt+1, i+1, mo+1, ja);
            }
            else{
                combination(cnt+1, i+1, mo, ja+1);
            }
            choose[i] = false;
        }
    }

    static boolean check(String s){     //모음이면 true 자음이면 false
        if(s.equals("a") || s.equals("e") || s.equals("i") ||
                s.equals("o") || s.equals("u"))
            return true;
        return false;
    }
}
