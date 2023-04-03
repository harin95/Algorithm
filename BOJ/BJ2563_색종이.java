package BOJ;

import java.util.*;
import java.io.*;

public class BJ2563_색종이 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuffer sb = new StringBuffer();
		
		int n = Integer.parseInt(br.readLine());
		int[][] white = new int[100][100];
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			int c = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			
			for(int j=r; j<r+10; j++) {
				for(int k=c; k<c+10; k++) {
					white[j][k] = 1;
				}
			}
		}
		
		int cnt = 0;
		
		for(int i=0; i<100; i++) {
			for(int j=0; j<100; j++) {
				if(white[i][j] == 1) cnt++;
			}
		}
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(String.valueOf(cnt));
		bw.close();
		
	}
}
