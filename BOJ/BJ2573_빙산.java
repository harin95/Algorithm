package BOJ;

import java.io.*;
import java.util.*;
public class BJ2573_빙산 {
    private static int[] drow = new int[]{-1, 1, 0, 0};
    private static int[] dcol = new int[]{0, 0, -1, 1};

    private static int n, m;
    private static boolean[][] visit;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int[][] map = new int[n][m];
        visit = new boolean[n][m];

        for (int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int year = 0;
        int[][] zeroCnt = new int[n][m];

        while (true) {
            year++;
            //주변에 0이 몇개있는지 세기
            searchZero(map, zeroCnt);
            //주변에 0만큼 높이가 녹는다
            int zeroNum = meltIce(map, zeroCnt);

            int regionNum = regionCount(map);
            if (regionNum >= 2) {
                System.out.println(year);
                return;
            }

            if (zeroNum == n * m) {
                System.out.println("0");
                return;
            }
        }
    }

    private static void searchZero(int[][] map, int[][] zeroCnt) {
        //zeroCnt 일단 초기화
        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                zeroCnt[i][j] = 0;
            }
        }

        for (int r=0; r<n; r++) {
            for (int c=0; c<m; c++) {
                if (map[r][c] == 0) continue;

                int cnt = 0;

                for (int d=0; d<4; d++) {
                    int nr = r + drow[d];
                    int nc = c + dcol[d];
                    try {
                        if (map[nr][nc] == 0) cnt++;
                    } catch (Exception ignored) {}
                }
                zeroCnt[r][c] = cnt;
            }
        }
    }

    private static int meltIce(int[][] map, int[][] zeroCnt) {// 다 녹아서 0만 남으면 true 아니면 false
        int cnt = 0;
        for(int r=0; r<n; r++){
            for (int c=0; c<m; c++){
                map[r][c] -= zeroCnt[r][c];
                if (map [r][c] < 0) map[r][c] = 0;
                if (map[r][c] == 0) cnt++;
            }
        }
        return cnt;
    }

    private static int regionCount(int[][] map) {// 덩어리 2개 이상으로 분리되면 true 아니면 false
        //visit 알단 초기화
        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                visit[i][j] = false;
            }
        }
        int cnt = 0;

        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                if (visit[i][j] || map[i][j] == 0)
                    continue;
                if (++cnt >= 2) {
                    return cnt;
                }
                Queue<int[]> q = new ArrayDeque<>();
                q.add(new int[]{i, j});
                //bfs
                while (!q.isEmpty()) {
                    int[] cur = q.poll();
                    for (int d=0; d<4; d++) {
                        int nr = cur[0] + drow[d];
                        int nc = cur[1] + dcol[d];
                        try {
                            if (visit[nr][nc] || map[nr][nc] == 0) continue;
                            visit[nr][nc] = true;
                            q.add(new int[]{nr, nc});
                        } catch (Exception ignored) {
                        }
                    }
                }
            }
        }
        return cnt;
    }
}