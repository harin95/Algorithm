package BOJ;

import java.io.*;
import java.util.*;

public class Main_1244_스위치켜고끄기 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		String[] input = br.readLine().split(" ");
		int[] switches = Arrays.stream(input).mapToInt(Integer::parseInt).toArray();
		
		int studNum = Integer.parseInt(br.readLine());
		
		for(int i=0; i<studNum; i++) {
			
			input = br.readLine().split(" ");
			int switchN = Integer.parseInt(input[1]);
			int[] toggleIdx = new int[2];
			
			if(input[0].equals("1")) {
				
				for(int idx=0; idx<switches.length; idx++) {
					if((idx+1)%switchN==0)
						toggle(switches, idx);
				}
			}
			else {
				for(int j=1; j<switches.length/2; j++) {
					if(switchN-j-1 >= 0 && switchN+j < switches.length) {
						int[] arr1 = Arrays.copyOfRange(switches, switchN-j-1, switchN+j);
						if(!symmetry(arr1)) break;
						toggleIdx[0] = switchN-j-1;
						toggleIdx[1] = switchN+j; 
						
					}
				}
			}
			
			if(toggleIdx.equals(new int[] {0, 0})) toggle(switches, switchN-1);
			
			for(int idx=toggleIdx[0]; idx<toggleIdx[1]; idx++) {
				toggle(switches, idx);
			}
		}
		
		for(int i=0; i<switches.length; i++) {
			System.out.print(switches[i] + " ");
			if((i+1)%20==0) System.out.println();
		}
	}
	
	public static void toggle(int[] arr, int i) {
		
		if(arr[i] == 0) arr[i] = 1;
		else arr[i] = 0;
	}
	
	public static boolean symmetry(int[] arr) {
		
		for(int i=0; i<arr.length; i++) {
			if(arr[i] == arr[arr.length-1-i]) continue;
			else return false;
		}
		return true;
	}

}
