package BOJ;

import java.math.BigInteger;
import java.util.*;
import java.io.*;
public class BJ1786_찾기 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String T = br.readLine();
        String P = br.readLine();

        int n = P.length();
        //부분일치테이블 만들기
        int[] skip = new int[n];

        int j = 0;
        for(int i=1; i<n; i++){
            if(P.charAt(i) == P.charAt(j)){
                skip[i] = j+1;
                j++;
            }
            else if(P.charAt(i) != P.charAt(j)){
                if(j == 0) continue;
                //자리 옮기고 다시 비교
                j = skip[j-1];
                i--;
            }
        }

        //패턴 찾기
        j=0;
        int cnt = 0;
        List<Integer> saveIndex = new ArrayList<>();

        for(int i=0; i<T.length(); i++){
            if(T.charAt(i) == P.charAt(j) && j == n-1){     //문자열 매칭
                cnt++;
                saveIndex.add(i);
                j = skip[j];
            }
            else if(T.charAt(i) == P.charAt(j)){    //글자 한개 매칭
                j++;
            }
            else if(T.charAt(i) != P.charAt(j)){
                if(j == 0) continue;
                j = skip[j-1];
                i--;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(cnt).append("\n");
        for(int i=0; i<saveIndex.size(); i++){
            sb.append(saveIndex.get(i)-n+2).append("\n");
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.close();
    }





}