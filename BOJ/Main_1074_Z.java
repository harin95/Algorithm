package BOJ;

import java.util.*;
import java.io.*;

public class Main_1074_Z {
	static int n, r, c;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		divide(n, 0, 0, (int) Math.pow(2, n), 0, (int) Math.pow(2, n), 0);
	}

	public static void divide(int size, int base, int rstart, int rend, int cstart, int cend, int squareNum) {
		/*
		* size = n
		* base : 사각형의 안에 첫번째 칸 숫자
		* rstart, rend : 사각형의 시작 행, 끝 행
		* cstart, cend : 사각형의 시작 열, 끝 열
		* squareNum : 4개로 나눈 사각형 중 몇번째인지
		* */

		int rmid = (rstart + rend) / 2;
		int cmid = (cstart + cend) / 2;
		int square = squareNum;

		if (size == 0) {	//size가 0이 되면 가장 작은 네모 한칸
			System.out.println(base);
			return;
		}

		if (r < rmid && c < cmid) {		//사각형 1
			square = 1;
			int next_len = (int) Math.pow(2, size - 1);		//4개로 나눈 작은 사각형 길이
			int next_base = base + next_len * next_len * (square - 1);		//4개로 나눈 작은 사각형의 첫번째 숫자
			divide(size - 1, next_base, rstart, rmid, cstart, cmid, square);
		}
		else if (r < rmid && c >= cmid) {		//사각형 2
			square = 2;
			int next_len = (int) Math.pow(2, size - 1);
			int next_base = base + next_len * next_len * (square - 1);
			divide(size - 1, next_base, rstart, rmid, cmid, cend, square);
		}
		else if (r >= rmid && c < cmid) {		//사각형 3
			square = 3;
			int next_len = (int) Math.pow(2, size - 1);
			int next_base = base + next_len * next_len * (square - 1);
			divide(size - 1, next_base, rmid, rend, cstart, cmid, square);
		}
		else if (r >= rmid && c >= cmid) {	//사각형 4
			square = 4;
			int next_len = (int) Math.pow(2, size - 1);
			int next_base = base + next_len * next_len * (square - 1);
			divide(size - 1, next_base, rmid, rend, cmid, cend, square);
		}
	}
}