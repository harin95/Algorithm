package SWEA;

import java.util.*;
import java.io.*;

public class Solution_2115_벌꿀채취 {

    private static int n, m, c;
    private static int profit = 0;
    private static boolean[] isSelected;
    private static int[] worker;
    private static ArrayList<int[]> save;
    private static int[][] comb;
    private static boolean[] check;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        for(int tc=1; tc<=T; tc++) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            int[][] honey = new int[n][n];
            isSelected = new boolean[m];
            comb = new int[2][];

            for(int i=0; i<n; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<n; j++) {
                    honey[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            //입력 끝


            worker = new int[m];
            save = new ArrayList<>();

            for(int row=0; row<n; row++) {
                for(int col=0; col<=n-m; col++) {
                    //일꾼은 m개씩 꿀통을 고른다
                    worker = Arrays.copyOfRange(honey[row], col, col+m);
                    //고른 m개의 꿀통에서 허용 범위를 초과하지 않게 담을 수 있는 꿀의 경우를 부분집합으로 찾는다
                    powerset(0, row, col);
                }
            }

            //추출한 꿀 합 순으로 정렬 (내림차순)
            Collections.sort(save, (o1, o2) -> o2[0] - o1[0]);

            check = new boolean[save.size()];
            //부분집합의 조합으로 낼 수 있는 최대 이익을 찾는다
            combination(0, 0);
            sb.append("#").append(tc).append(" ").append(profit).append("\n");
            profit = 0; //static 변수 초기화
        }
        bw.write(sb.toString());
        bw.close();
    }

    private static void powerset(int cnt, int row, int col) {
        /* row, col : 일꾼이 선택한 연속된 가로 m개의 시작점
        *  두 일꾼의 선택지 겹치는지 아닌지 판단할때 필요*/

        if(cnt == m) {
            int sum = 0;
            for(int i=0; i<m; i++) {
                if(isSelected[i]) {
                    sum += worker[i];
                }
            }
            //이익과 시작점을 저장한다
            if(sum <= c) {
                int profit = 0;
                for(int i=0; i<m; i++) {
                    if(isSelected[i]) {
                        profit += Math.pow(worker[i], 2);
                    }
                }
                save.add(new int[] {profit, row, col});
            }
            return;
        }

        isSelected[cnt] = true;
        powerset(cnt+1, row, col);
        isSelected[cnt] = false;
        powerset(cnt+1, row, col);

    }

    private static void combination(int cnt, int start) {
        if(cnt == 2) {

            int r = comb[0][1]; int c = comb[0][2];
            int nr = comb[1][1]; int nc = comb[1][2];

            if(r == nr &&	//같은 행이고
                    ((c <= nc && nc <= c+m-1) ||	//시작점이 겹치거나
                            (c <= nc+m-1 && nc+m-1 <=c+m-1)	//끝점이 겹치면
                    )) {
                return;	//저장하지 않고 리턴
            }
            else {	//겹치지 않는 경우에만
                profit = Math.max(comb[0][0] + comb[1][0], profit);	//최대 이익 갱신
                return;
            }
        }

        for(int i=start; i<save.size(); i++) {
            if(check[i]) continue;
            comb[cnt] = save.get(i);
            check[i] = true;
            combination(cnt+1, i+1);
            check[i] = false;
        }
    }
}
