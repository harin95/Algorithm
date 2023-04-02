package Algorithm.BOJ;

import java.util.*;
import java.io.*;

public class BJ19623_회의실배정4 {

    private static class Meeting implements Comparable<Meeting>{
        int start, end, people;

        public Meeting(int start, int end, int people) {
            this.start = start;
            this.end = end;
            this.people = people;
        }

        @Override
        public int compareTo(Meeting o) {
            return Integer.compare(this.end, o.end);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        List<Meeting> list = new ArrayList<>();

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            list.add(new Meeting(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())));

            Collections.sort(list);
        }

        int[] dp = new int[n];
        dp[0] = list.get(0).people;
        int max = dp[0];

        for(int i=1; i<n; i++){
            int start = list.get(i).start;
            //이분탐색
            //끝나는 시간 중에서 start보다 작은 가장 큰 값의 인덱스
            int j = binarySerach(list, start);

            if(j != -1){
                dp[i] = dp[j] + list.get(i).people;  //있으면 이전 인원수에 더함
            }
            else{
                dp[i] = list.get(i).people;     //없으면 자신 그대로
            }
            max = Math.max(max, dp[i]);     //최대값 갱신
        }
        sb.append(max).append("\n");
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.close();
    }

    private static int binarySerach(List<Meeting> list, int key){
        int low = 0;
        int high = list.size();

        while(low < high){
            int mid = (low+high)/2;

            if(list.get(mid).end >= key){
                high = mid;
            }
            else{
                low = mid+1;
            }
        }
        return high-1;
    }
}
