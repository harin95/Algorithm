package SWEA;

import java.io.*;
import java.util.*;

public class SWEA1208_Flatten {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Scanner sc = new Scanner(System.in);
		
		for(int test_case=1; test_case<=10; test_case++) {
			
			int dump = sc.nextInt();

			int[] boxes = new int[100];
			
			for(int i=0; i<100; i++) boxes[i] = sc.nextInt();
			
			for(int i=0; i<dump; i++) {
				Arrays.sort(boxes);
				boxes[0]++;
				boxes[99]--;
			}
			Arrays.sort(boxes);
			System.out.println("#" + test_case + " " + (boxes[99]-boxes[0]));
		}
	}
}
