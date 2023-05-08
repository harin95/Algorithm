package BOJ;

import java.io.*;
import java.util.*;

public class BJ1938_통나무옮기기 {
	
	private static Block block;
	private static Block goal;
	private static String[][] map;
	private static int n;
	private static int answer = 0;
	
	private static class Block{
		int r, c;	//시작점 위치
		int status;	//0: 가로, 1: 세로
		int cnt;	//움직임 카운트
		
		public Block(int r, int c, int status, int cnt) {
			this.r = r;
			this.c = c;
			this.status = status;
			this.cnt = cnt;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		//입력받기
		n = Integer.parseInt(br.readLine());
		map = new String[n][n];
		
		for(int i=0; i<n; i++) {
			map[i] = br.readLine().split("");
		}
		
		//기둥 위치 찾기
		findB();
		findE();
		
		boolean[][][] visit = new boolean[n][n][2];	//가로, 세로 상태별로 방문 체크하기 위해 3차원 배열 사용
		
		Queue<Block> q = new ArrayDeque<>();
		q.add(block);
		visit[block.r][block.c][block.status] = true;	//시작점 큐에 넣고 방문 체크
		
		while(!q.isEmpty()) {
			Block cur = q.poll();			
			
			//기둥 옮기기 완료
			if(cur.r == goal.r && cur.c == goal.c && cur.status == goal.status) {
				answer = cur.cnt;
				break;
			}
			
			for(int d=0; d<5; d++) {	//상, 하, 좌, 우, 회전 동작 수행하며 가능한 경우 큐에 넣어 탐색 진행				
				Block newBlock = move(cur, d);
				
				if(newBlock == null) continue;	//인덱스 벗어나거나 장애물이 있는 경우
				if(visit[newBlock.r][newBlock.c][newBlock.status]) continue;	//이미 방문한 경우
				
				q.add(newBlock);
				visit[newBlock.r][newBlock.c][newBlock.status] = true;
			}
			
		}
		
		System.out.println(answer);
	}
	
	//시작점 찾는 함수
	private static void findB() {
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {

				if(map[i][j].equals("B")) {
					if(j+1 < n && map[i][j+1].equals("B")) {	//가로로 배치되어 있음
						block = new Block(i, j, 0, 0);
						return;
					}
					else if(i+1 < n && map[i+1][j].equals("B")) {	//세로로 배치되어 있음
						block = new Block(i, j, 1, 0);
						return;
					}
				}

			}
		}
	}
	
	//도착점 찾는 함수
	private static void findE() {
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(map[i][j].equals("E")) {
					if(j+1 < n && map[i][j+1].equals("E")) {	//가로로 배치되어 있음
						goal = new Block(i, j, 0, 0);
						return;
					}
					else if(i+1 < n && map[i+1][j].equals("E")) {	//세로로 배치되어 있음
						goal = new Block(i, j, 1, 0);
						return;
					}
				}
			}
		}
	}
	
	//기둥 움직이기
	private static Block move(Block block, int command) {
	
		//up
		if(command == 0) {
			//인덱스 체크
			if(block.r-1 < 0) return null;
			
			//가로일 경우 장애물 체크
			if(block.status == 0) {	
				for(int i=0; i<3; i++) {
					if(map[block.r-1][block.c+i].equals("1")) return null;
				}
				
				//장애물 없으면 옮기기
				return new Block(block.r-1, block.c, block.status, block.cnt+1);
			}
			
			//세로일 경우 장애물 체크
			else if(block.status == 1) { 
				if(map[block.r-1][block.c].equals("1")) return null;
				
				//장애물 없으면 옮기기
				return new Block(block.r-1, block.c, block.status, block.cnt+1);
			}
		}
		
		//down
		else if(command == 1) {			
			//가로일 경우
			if(block.status == 0) {	
				//인덱스 체크
				if(block.r+1 >= n) return null;
				
				//장애물 체크
				for(int i=0; i<3; i++) {
					if(map[block.r+1][block.c+i].equals("1")) return null;
				}
				
				//장애물 없으면 옮기기
				return new Block(block.r+1, block.c, block.status, block.cnt+1);
			}
			
			//세로일 경우
			else if(block.status == 1) { 
				//인덱스 체크
				if(block.r+3 >= n) return null;
				
				//장애물 체크
				if(map[block.r+3][block.c].equals("1")) return null;
				
				//장애물 없으면 옮기기
				return new Block(block.r+1, block.c, block.status, block.cnt+1);
			}
		}
		
		//left
		else if(command == 2) {
			//인덱스 체크
			if(block.c-1 < 0) return null;
			
			//가로일 경우 장애물 체크
			if(block.status == 0) {
				if(map[block.r][block.c-1].equals("1")) return null;
				
				//장애물 없으면 옮기기
				return new Block(block.r, block.c-1, block.status, block.cnt+1);
			}
			
			//세로일 경우 장애물 체크
			if(block.status == 1) {
				for(int i=0; i<3; i++) {
					if(map[block.r+i][block.c-1].equals("1")) return null;
				}
				
				//장애물 없으면 옮기기
				return new Block(block.r, block.c-1, block.status, block.cnt+1);
			}
		}
		
		//right
		else if(command == 3) {

			//가로일 경우
			if(block.status == 0) {
				//인덱스 체크
				if(block.c+3 >= n) return null;
				
				//장애물 체크
				if(map[block.r][block.c+3].equals("1")) return null;
				
				//장애물 없으면 옮기기
				return new Block(block.r, block.c+1, block.status, block.cnt+1);
			}
			
			//세로일 경우
			if(block.status == 1) {
				//인덱스 체크
				if(block.c+1 >= n) return null;
				
				//장애물 체크
				for(int i=0; i<3; i++) {
					if(map[block.r+i][block.c+1].equals("1")) return null;
				}
				
				//장애물 없으면 옮기기
				return new Block(block.r, block.c+1, block.status, block.cnt+1);
			}
		}
		
		//rotate
		else if(command == 4) {
			//가로인 경우
			if(block.status == 0) {
				//첫번째 행, 마지막 행에서는 회전 불가
				if(block.r == 0 || block.r == n-1) return null;
				
				//주변에 장애물이 있으면 회전 불가
				for(int i=0; i<3; i++) {
					if(map[block.r-1][block.c+i].equals("1")
							|| map[block.r+1][block.c+i].equals("1")) return null;
				}
				
				//가능하면 회전
				return new Block(block.r-1, block.c+1, Math.abs(block.status-1), block.cnt+1);
			}
			
			//세로인 경우
			if(block.status == 1) {
				//첫번째 열, 마지막 열에서는 회전 불가
				if(block.c == 0 || block.c == n-1) return null;
				
				//주변에 장애물이 있으면 회전 불가
				for(int i=0; i<3; i++) {
					if(map[block.r+i][block.c-1].equals("1")
							|| map[block.r+i][block.c+1].equals("1")) return null;
				}
				
				//가능하면 회전 
				return new Block(block.r+1, block.c-1, Math.abs(block.status-1), block.cnt+1);
			}
		}
		
		return null;
	}
}
