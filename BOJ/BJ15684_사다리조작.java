package BOJ;

import java.util.*;
import java.io.*;

public class BJ15684_사다리조작 {
    private static Node[][] ladder;
    private static int C, m, R;
    private static boolean[][] visit;
    private static List<int[]> coordinates;
    private static int[][] selected;
    private static boolean[] visitCoordinates;
    private static boolean success = false;

    private static class Node{
        int r;
        int c;
        Node left;
        Node right;

        public Node(){}

        public Node(int r, int c){
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        C = Integer.parseInt(st.nextToken());   //세로선 개수 (열)
        m = Integer.parseInt(st.nextToken());   //주어지는 가로선 개수
        R = Integer.parseInt(st.nextToken());   //가로선 개수 (행)

        ladder = new Node[R+2][C+1];   //사다리
        visit = new boolean[R+2][C+1];  //방문체크
        selected = new int[3][2];   //조합 저장

        for(int i=1; i<=R; i++){
            for(int j=1; j<=C; j++){
                ladder[i][j] = new Node(i, j);
            }
        }

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());   //a번 가로선 (행)
            int b = Integer.parseInt(st.nextToken());   //b번과 b+1세로선 (열)

            //가로선 연결지점 저장
            Node from = ladder[a][b];
            Node to = ladder[a][b+1];
            from.right = to;
            to.left = from;
        }

        if(ladderGame()) {
            System.out.println("0");
            return;
        }


        //좌표 생성
        coordinates = new ArrayList<>();
        for(int i=1; i<=R; i++){
            for(int j=1; j<=C-1; j++){   //가로선을 왼 → 오 방향으로 만들거기 때문에 h-1까지만 좌표 생성
                coordinates.add(new int[]{i, j});
            }
        }
        visitCoordinates = new boolean[coordinates.size()];

        //가로선을 한개 놓아서 되는 경우 찾기
        for(int[] point : coordinates){
            int r = point[0];
            int c = point[1];

            //가로선 만들기
            if(ladder[r][c].right != null || ladder[r][c].left != null) continue;
            makeLine(r, c);

            //사다리타서 확인
            if(ladderGame()){
                System.out.println("1");
                return;
            }

            //가로선 제거
            removeLine(r, c);
        }

        //두개
        combination(0, 2, 0);
        if(success){
            System.out.println("2");
            return;
        }

        //세개
        Arrays.fill(visitCoordinates, false);
        combination(0, 3, 0);
        if(success){
            System.out.println("3");
            return;
        }

        System.out.println("-1");
    }

    //모든 i번째 사다리에서 출발해서 i번으로 돌아오면 true
    private static boolean ladderGame(){
        for(int i=1; i<=C; i++){
            //visit 배열 초기화
            for(int v=0; v<=R; v++) Arrays.fill(visit[v], false);

            visit[1][i] = true;
            int result = dfs(1, i);
            if(i != result) return false;
        }
        return true;
    }

    private static int dfs(int r, int c){
        if(r == R+1) return c;

        Node node = ladder[r][c];
        Node left = node.left;
        Node right = node.right;

        if(node.left != null && node.right!= null) return -1;   //양쪽 다 존재하면 연속된 가로선이다(허용되지 않음)

        //가로선이 있고 방문하지 않았다면 이동
        if(left != null && !visit[left.r][left.c]){ //왼쪽
            visit[left.r][left.c] = true;
            return dfs(left.r, left.c);
        } else if(right != null && !visit[right.r][right.c]){   //또는 오른쪽
            visit[right.r][right.c] = true;
            return dfs(right.r, right.c);
        } else {    //가로선이 없으면 아래로 내려가기
            visit[node.r+1][node.c] = true;
            return dfs(node.r+1, node.c);
        }
    }

    private static void combination(int cnt, int selectNum, int start){
        if(success) return;

        if(cnt == selectNum){   //다 골랐으면 가로선 만들고 사다리 게임 시작

            for(int i=0; i<selectNum; i++) {
                int r = selected[i][0];
                int c = selected[i][1];

                //이미 연결된 지점이 포함되어 있으면 만든 가로선 되돌리고 재귀나가기
                if(ladder[r][c].right != null || ladder[r][c].left != null){
                    for(int j=0; j<i; j++){
                        int jr = selected[j][0];
                        int jc = selected[j][1];
                        removeLine(jr, jc);
                    }
                    return;
                }

                //아니라면 가로선 연결하기
                makeLine(r, c);
            }

            //사다리타기 고고
            if(ladderGame()){
                success = true;
                return;
            }

            //가로선 제거
            for(int i=0; i<selectNum; i++) {
                int r = selected[i][0];
                int c = selected[i][1];

                removeLine(r, c);
            }
            return;
        }

        for(int i=start; i<coordinates.size(); i++){
            if(visitCoordinates[i]) continue;

            visitCoordinates[i] = true;
            selected[cnt] = coordinates.get(i);
            combination(cnt+1, selectNum, i);
            visitCoordinates[i] = false;
        }
    }

    private static void makeLine(int r, int c){
        ladder[r][c].right = ladder[r][c+1];
        ladder[r][c+1].left = ladder[r][c];
    }

    private static void removeLine(int r, int c){
        ladder[r][c].right = null;
        ladder[r][c+1].left = null;
    }
}
