package BOJ;

import java.util.*;
import java.io.*;

public class BJ1469_숌사이수열 {
    private static int[] input;
    private static int n;
    private static int[] answer;
    private static boolean[] visit;
    private static boolean end = false;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Arrays.sort(input);     //사전순으로 가장 빠른 것 출력하기 위해 정렬 사용
        visit = new boolean[n];
        answer = new int[2*n];
        Arrays.fill(answer, -1);

        dfs(0, 0);

        //정답 출력
        StringBuilder sb = new StringBuilder();

        for(int num : answer){
            if(num == -1){
                System.out.println(num);
                return;
            }
            sb.append(num).append(" ");
        }

        sb.append("\n");

        System.out.println(sb);
    }

    private static void dfs(int cnt, int len){

        //정답 배열을 다 채우면 더 이상 탐색하지 않고 리턴
        if(len == 2*n){
            end = true;
            return;
        }

        for(int i=0; i<n; i++){
            if(visit[i] || cnt+input[i]+1 >= 2*n) continue; //이미 방문했거나 인덱스가 범위를 벗어나는 경우

            /*
            * cnt 인덱스 자리와 input[i] 숫자만큼 후 인덱스 자리(cnt+input[i]+1)가 비어있을 경우엔 input[i]의 숫자로 채운다
            * 예시) cnt = 0 이고 input[i] = 3 인 경우
            * -1 ㅁ ㅁ ㅁ -1 ㅁ  와 같이 비어있으면 3을 해당 자리에 넣을 수 있다
            * */

            if(answer[cnt] == -1 && answer[cnt+input[i]+1] == -1){
                answer[cnt] = answer[cnt+input[i]+1] = input[i];    //해당 숫자 넣고 방문 체크
                visit[i] = true;

                dfs(cnt+1, len+2);  //다음 인덱스로 넘어간다

                if(end) return;     //이미 정답 배열을 채웠을 경우 탐색 중단

                visit[i] = false;   //백트래킹을 위해 방문 체크와 숫자 초기화 해준다
                answer[cnt] = answer[cnt+input[i]+1] = -1;
            } else{
                //해당 자리가 이미 다른 숫자로 채워져 있을 경우 -> cnt 인덱스만 늘려서 다른 빈 자리 찾기
                dfs(cnt+1, len);
            }
        }
    }
}
