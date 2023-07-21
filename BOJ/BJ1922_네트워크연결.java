package BOJ;

import java.io.*;
import java.util.*;

public class BJ1922_네트워크연결 {
    private static int[] parents;
    public static class Edge implements Comparable<Edge>{
        int from;
        int to;
        int weight;

        public Edge(int from, int to, int weight){
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge e){
            return Integer.compare(this.weight, e.weight);
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "from=" + from +
                    ", to=" + to +
                    ", weight=" + weight +
                    '}';
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<Edge> edges = new ArrayList<>();

        int n = Integer.parseInt(br.readLine());    //컴퓨터의 수
        int m = Integer.parseInt(br.readLine());    //연결가능한 선의 개수

        for(int i=0; i<m; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            if(from <= to){
                edges.add(new Edge(from ,to, weight));
            } else{
                edges.add(new Edge(to, from, weight));
            }

        }

        parents = new int[n+1];
        for(int i=1; i<n+1; i++){
            parents[i] = i;
        }

        Collections.sort(edges);

        long sum = 0;
        int cnt = 0;

        for(Edge e : edges){
            if(find(e.from) != find(e.to)){
                sum += e.weight;
                union(e.from, e.to);
                if(++cnt == n-1) break;
            }
        }
        System.out.println(sum);
    }

    private static int find(int a){
        if(parents[a] == a) return a;
        return parents[a] = find(parents[a]);
    }

    private static void union(int a, int b){
        a = find(a);
        b = find(b);

        if(a != b) {
            parents[a] = b;
        }
    }
}
