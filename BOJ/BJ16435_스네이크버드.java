package BOJ;

import java.io.*;
import java.util.*;

public class BJ16435_스네이크버드 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input = br.readLine().split(" ");
		
		int n = Integer.parseInt(input[0]);
		int l = Integer.parseInt(input[1]);
		
		input = br.readLine().split(" ");
		int[] fruit = Arrays.stream(input).mapToInt(Integer::parseInt).toArray();
		
		Arrays.sort(fruit);
		
		for(int i=0; i<n; i++) {
			if(fruit[i] <= l) {
				l++;
			}
		}
		System.out.println(l);
	}
}
