package BOJ;

import java.io.*;
import java.util.*;

public class BJ2910_빈도정렬 {

    private static class Code implements Comparable<Code>{
        int number;
        int cnt;
        int order;

        public Code(int number, int cnt, int order){
            this.number = number;
            this.cnt = cnt;
            this.order = order;
        }

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

        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> order = new ArrayList<>();

        for(int i : input){

            if(!order.contains(i)) order.add(i);

            int check = map.getOrDefault(i, 0);
            if(check == 0){
                map.put(i, 1);
            } else{
                map.put(i, map.get(i)+1);
            }
        }

        List<Code> codes = new ArrayList<>();

        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            Integer key = entry.getKey();
            Integer val = entry.getValue();

            int orderIdx = order.indexOf(key);
            codes.add(new Code(key, val, orderIdx));
        }

        Collections.sort(codes);

        StringBuilder sb = new StringBuilder();

        for(Code code : codes){
            for(int i=0; i<code.cnt; i++) sb.append(code.number).append(" ");
        }
        sb.append("\n");

        System.out.println(sb);
    }
}
