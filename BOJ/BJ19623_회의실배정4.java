package BOJ;

import java.util.*;
import java.io.*;

public class BJ19623_회의실배정4 {

    private static class Meeting implements Comparable<Meeting> {
        int start, end, people;

        public Meeting(int start, int end, int people) {
            this.start = start;
            this.end = end;
            this.people = people;
        }

        @Override
        public int compareTo(Meeting o) {
            return Integer.compare(this.start, o.start);
        }   //시작시간 기준 정렬
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        List<Meeting> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int people = Integer.parseInt(st.nextToken());
            list.add(new Meeting(start, end, people));
        }

        Collections.sort(list);

        int[] dp = new int[n];
        dp[n-1] = list.get(n-1).people;

        /*
        * dp배열 : i ~ n-1 회의의 최대 인원
        * 회의 i를 선택했을 때 : 회의 i의 인원 + 회의 i의 끝나는 시간보다 큰 시간 중 가장 작은 시간 회의의 인원
        * 회의 i를 선택하지 않았을 때 : i+1 ~ n-1 회의의 최대 인원*/

        for(int i=n-2; i>=0; i--){
            int key = list.get(i).end;
            int j = binarySerach(list, key);
            int cmp = j == n ? list.get(i).people : dp[j] + list.get(i).people;
            dp[i] = Math.max(dp[i+1], cmp);
        }

        sb.append(dp[0]).append("\n");
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.close();
    }

    private static int binarySerach(List<Meeting> list, int key) {
        /*key보다 큰 값 중 가장 작은 값의 인덱스를 반환한다*/
        int low = 0;
        int high = list.size();

        while (low < high) {
            int mid = (low + high) / 2;

            if (list.get(mid).start > key) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return high;
    }
}