package BOJ;

import java.io.*;
import java.util.*;

public class BJ1967_트리의지름 {

    private static int max = 0;
    private static int furthest = 0;
    private static boolean[] visit;
    private static int n;
    private static List<List<Node>> weight;

    private static class Node{
        int vertex;
        int weight;

        public Node(int vertex, int weight){
            this.vertex = vertex;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        visit = new boolean[10001];

        weight = new ArrayList<>();
        for(int i=0; i<10001; i++){
            weight.add(new ArrayList<>());
        }


        for(int i=0; i<n-1; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            weight.get(from).add(new Node(to, w));
            weight.get(to).add(new Node(from, w));
        }

        visit[1] = true;
        dfs(1, 0);

        Arrays.fill(visit, false);

        max = 0;

        visit[furthest] = true;
        dfs(furthest, max);
        System.out.println(max);
    }

    public static void dfs(int start, int sum){
        max = Math.max(max, sum);
        if(max == sum) furthest = start;

        for(Node node : weight.get(start)){
            int v = node.vertex;
            if(!visit[v]){
                visit[v] = true;
                dfs(v, sum + node.weight);
                visit[v] = false;
            }
        }
    }
}
