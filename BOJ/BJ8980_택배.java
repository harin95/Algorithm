package BOJ;

import java.io.*;
import java.util.*;
public class BJ8980_택배 {

    private static class Delivery{
        int from, to, post;

        public Delivery(int from, int to, int post) {
            this.from = from;
            this.to = to;
            this.post = post;
        }

        @Override
        public String toString() {
            return "Delivery{" +
                    "from=" + from +
                    ", to=" + to +
                    ", post=" + post +
                    '}';
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int m = Integer.parseInt(br.readLine());

        List<Delivery> deliveries = new ArrayList<>();

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int post = Integer.parseInt(st.nextToken());

            deliveries.add(new Delivery(from, to, post));
        }

        Collections.sort(deliveries, (o1, o2) -> o1.from - o2.from);

        for(Delivery del : deliveries){
            System.out.println(del);
        }
    }
}
