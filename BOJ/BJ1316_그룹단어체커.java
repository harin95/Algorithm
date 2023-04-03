package BOJ;

import java.util.*;
import java.io.*;

public class BJ1316_그룹단어체커 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuffer sb = new StringBuffer();
		
		int n = Integer.parseInt(br.readLine());
		boolean[] visit = new boolean[27];
		
		int cnt = 0;
		
		for(int i=0; i<n; i++) {
			char[] input = br.readLine().toCharArray();
			
			boolean isGroupword = true;;
			
			for(int j=0; j<input.length-1; j++) {
				char current = input[j], next = input[j+1];
				if(current != next && visit[geti(current)]) {	
					//이미 나왔던 알파벳이 다른 구역에서 다시 나오면 그룹 단어가 아니다
					isGroupword = false;
					break;
				}
				else if(current != next && !visit[geti(current)]) {
					visit[geti(current)] = true;
				}
			}
			
			//마지막 알파벳 체크
			if(visit[geti(input[input.length-1])]) isGroupword = false;
			
			if(isGroupword) {
				cnt++;
			}
			Arrays.fill(visit, false);
		}
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(String.valueOf(cnt));
		bw.close();
		
	}
	
	private static int geti(char a) {
		return (int)a - 97;
	}
}
