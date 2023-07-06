package BOJ;

import java.io.*;
import java.util.*;

public class BJ2910_빈도정렬 {

    private static class Code implements Comparable<Code>{
        int number;   
        int cnt;    //해당 숫자가 몇번 나타났는지 카운트
        int order;  //해당 숫자가 몇번째로 나왔는지 순서 저장

        public Code(int number, int cnt, int order){
            this.number = number;
            this.cnt = cnt;
            this.order = order;
        }

        //cnt → order 순으로 정렬
        @Override
        public int compareTo(Code c){
            return c.cnt == this.cnt ? Integer.compare(this.order, c.order) : Integer.compare(c.cnt, this.cnt);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Map<Integer, Integer> map = new HashMap<>();    //숫자가 몇번 등장했는지 hashmap으로 저장
        List<Integer> order = new ArrayList<>();        //숫자가 몇번째로 등장했는지 list로 저장

        for(int i : input){

            if(!order.contains(i)) order.add(i);    //등장한 숫자 순서대로 저장

            int check = map.getOrDefault(i, 0);    //초기화
            //카운트
            if(check == 0){
                map.put(i, 1);
            } else{
                map.put(i, map.get(i)+1);         
            }
        }

        //정렬을 위해 Code 인스턴스 생성해서 리스트에 담는다
        List<Code> codes = new ArrayList<>();    
        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            Integer key = entry.getKey();
            Integer val = entry.getValue();

            int orderIdx = order.indexOf(key);
            codes.add(new Code(key, val, orderIdx));
        }

        Collections.sort(codes);    //정렬

        //정답 출력
        StringBuilder sb = new StringBuilder();

        for(Code code : codes){
            for(int i=0; i<code.cnt; i++) sb.append(code.number).append(" ");
        }
        sb.append("\n");

        System.out.println(sb);
    }
}
