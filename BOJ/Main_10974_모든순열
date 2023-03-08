import java.util.*;
import java.io.*;

public class Main {
	
	static int[] arr;
	static int n;
	static StringBuffer sb = new StringBuffer();
	static boolean[] visit;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		n = Integer.parseInt(br.readLine());
		arr = new int[n];
		visit = new boolean[n+1];
		permutation(0);
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(sb.toString());
		bw.close();
		
	}
	
	private static void permutation(int cnt) {
		if(cnt == n) {
			for(int i=0; i<n; i++) {
				sb.append(arr[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i=1; i<=n; i++) {
			if(visit[i]) continue;
			arr[cnt] = i;
			visit[i] = true;
			permutation(cnt+1);
			visit[i] = false;
		}
	}
}
