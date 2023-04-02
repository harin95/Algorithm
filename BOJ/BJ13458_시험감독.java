import java.util.*;
import java.io.*;

public class BJ13458_시험감독 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] room = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<n; i++) room[i] = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int main = Integer.parseInt(st.nextToken());
		int sub = Integer.parseInt(st.nextToken());
		Long sum = 0L;
		
		sum += n;
		
		for(int student : room) {
			student -= main;
			
			if(student > 0) {
				Long extra = Long.valueOf(student%sub == 0 ? student/sub : student/sub + 1);
				sum += extra;
			}
		}
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(String.valueOf(sum));
		bw.close();
	}
}
