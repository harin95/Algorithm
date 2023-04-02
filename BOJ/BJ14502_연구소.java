package Algorithm.BOJ;

import java.io.*;
import java.util.*;

public class BJ14502_연구소 {
    private static int n, m;
    private static int[][] map;
    private static boolean[] visit;     //for combination
    private static int[][] select;     //for combination

    private static List<int[]> virusList;
    private static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];


        List<int[]> emptyList = new ArrayList<>();
        virusList = new ArrayList<>();

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++){
                int num = Integer.parseInt(st.nextToken());
                if(num == 0)
                    //빈칸 리스트에 좌표 추가
                    emptyList.add(new int[]{i, j});
                else if(num == 2)
                    //바이러스 위치 저장
                    virusList.add(new int[]{i, j});

                map[i][j] = num;
            }
        }

        visit = new boolean[emptyList.size()];
        select = new int[3][2];

        combination(emptyList, 0, 0);

        sb.append(max).append("\n");
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.close();
    }

    private static void combination(List<int[]> list, int cnt, int start){

        if(cnt == 3){
            max = Math.max(max, virus());
            return;
        }

        //빈칸리스트에서 벽 3개 놓을 곳 조합 뽑기
        for(int i=start; i<list.size(); i++){
            if(visit[i]) continue;
            select[cnt] = list.get(i);
            visit[i] = true;
            combination(list, cnt+1, i);
            visit[i] = false;
        }

    }

    private static int virus(){
        int[][] mapCopy = new int[n][m];

        for(int i=0; i<n; i++){
            mapCopy[i] = Arrays.copyOf(map[i], m);  //맵 복사해서 사용
        }

        for(int i=0; i<select.length; i++){
            int r = select[i][0];
            int c = select[i][1];
            //벽으로 표시
            mapCopy[r][c] = 1;
        }

        int[] drow = new int[]{-1, 1, 0, 0};
        int[] dcol = new int[]{0, 0, -1, 1};

        //바이러스가 퍼진다
        for(int i=0; i<virusList.size(); i++){

            Queue<int[]> q = new ArrayDeque<>();
            q.add(new int[]{virusList.get(i)[0], virusList.get(i)[1]});

            while(!q.isEmpty()) {
                int[] cur = q.poll();
                int r = cur[0];
                int c = cur[1];

                for (int d = 0; d < 4; d++) {
                    int nr = r + drow[d];
                    int nc = c + dcol[d];

                    if (!isIn(nr, nc) || mapCopy[nr][nc] == 1 || mapCopy[nr][nc] == 2) continue;
                    mapCopy[nr][nc] = 2;    //바이러스 전파
                    q.add(new int[]{nr, nc});
                }
            }
        }

        //안전 구역
        int cnt = 0;
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(mapCopy[i][j] == 0) cnt++;
            }
        }
        return cnt;
    }

    private static boolean isIn(int r, int c){
        if(r<0 || c<0 || r>=n || c>=m) return false;
        return true;
    }

}
