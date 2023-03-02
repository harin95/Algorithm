package BOJ;

import org.example.Main;

import java.util.*;
import java.io.*;

public class Main_17135_캐슬디펜스 {
    static int n, m, d;

    static class Enemy implements Comparable<Enemy>{
        int r;
        int c;
        int dist;

        public Enemy(){};

        public Enemy(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public Enemy(Enemy e){
            this(e.r, e.c);
        }

        @Override
        public boolean equals(Object obj) {
            Enemy e = (Enemy) obj;
            if(this.r == e.r && this.c == e.c) return true;
            return false;
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }

        @Override
        public String toString() {
            return "Enemy{" + "[" + r + ", " + c + "]\t" +
                    "dist=" + dist +
                    '}';
        }

        @Override
        public int compareTo(Enemy o) {
            return this.dist == o.dist ? Integer.compare(this.c, o.c) : Integer.compare(this.dist, o.dist);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        ArrayList<Enemy> enemy = new ArrayList<>();

        //적들 저장
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++){
                if(Integer.parseInt(st.nextToken()) == 1){
                    enemy.add(new Enemy(i, j));
                }
            }
        }

        ArrayList<int[]> combination = new ArrayList<>();

        //최대 15개 칸에 3명의 궁수를 놓는 조합
        for(int i=0; i<m-2; i++){
            for(int j=i; j<m-1; j++){
                if(i==j) continue;
                for(int k=j; k<m; k++){
                    if(j==k) continue;
                    combination.add(new int[]{i, j, k});
                }
            }
        }

        int max = 0;
        for(int i=0; i<combination.size(); i++){
            //경우의 수마다 적 리스트 복사해서 사용
            ArrayList<Enemy> enemyCopy = new ArrayList<>();
            for(Enemy e : enemy){
                enemyCopy.add(new Enemy(e));
            }


            int[] army = combination.get(i); //궁수 조합 하나 뽑아서 적을 몇명 없앨 수 있는지 계산
            int cnt = 0;

            while(!enemyCopy.isEmpty()) {
                //현재 궁수의 위치에서 없앨 수 있는 적을 저장해둔다 (겹칠 수 있으므로)
                HashSet<Enemy> toDelete = new HashSet<>();

                choose(toDelete, enemyCopy, army[0]);
                choose(toDelete, enemyCopy, army[1]);
                choose(toDelete, enemyCopy, army[2]);

                //적 제거
                for(Enemy e : toDelete){
                    enemyCopy.remove(e);
                    cnt++;
                }
                toDelete.clear();
                //공격 한번이 끝나면 적들의 위치 이동
                move(enemyCopy);
            }

            //경우의 수 마다 최대값으로 갱신
            max = Math.max(max, cnt);
            cnt = 0;
        }
        System.out.println(max);
    }
    static int dist(int x1, int y1, int x2, int y2){
        return Math.abs(x1-x2) + Math.abs(y1-y2);
    }

    static void choose(HashSet<Enemy> toDelete, ArrayList<Enemy> enemyCopy, int armycol){

        for(Enemy e : enemyCopy){
            e.dist = dist(e.r, e.c, n, armycol);        //궁수 위치에서 적들 거리 계산
        }
        Collections.sort(enemyCopy);
        if(enemyCopy.get(0).dist <= d)
            toDelete.add(enemyCopy.get(0));
    }

    static void move(ArrayList<Enemy> enemyCopy){

        ArrayList<Enemy> temp = new ArrayList<>();

        for(Enemy e : enemyCopy){
            if(e.r+1 == n){     //성이 있는 칸으로 이동하면 게임에서 제외
                temp.add(e);
                continue;
            }
            e.r++;      //다른 적들은 한칸 성쪽으로 이동
        }

        //제외된 적들은 제거
        for(Enemy e : temp){
            enemyCopy.remove(e);
        }
    }
}
