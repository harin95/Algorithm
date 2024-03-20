from collections import deque

def solution(maps):
    answer = 0
    user = [0, 0, 0]

    N = len(maps)
    M = len(maps[0])

    for i in range(0, N):
        for j in range(0, M):
            if maps[i][j] == "S":
                user[0] = i
                user[1] = j

    # 레버가 있는 곳까지 최소이동거리 탐색
    toLever = bfs(user, maps, N, M, 0)
    if toLever == -1:
        return -1
    
    # 레버 위치에서부터 출구까지 최소이동거리 탐색
    answer += toLever[2]
    toLever[2] = 0
    toExit = bfs(toLever, maps, N, M, 1)
    if toExit == -1:
        return -1

    return answer + toExit[2]


def bfs(user, maps, N, M, ver):
    dr = [-1, 1, 0, 0]
    dc = [0, 0, -1, 1]

    q = deque()
    q.append(user)
    cnt = 0

    visit = [[False for _ in range(M)] for _ in range(N)]
    visit[user[0]][user[1]] = True

    while len(q) > 0:
        cur = q.popleft()

        for d in range(4):
            nr = cur[0] + dr[d]
            nc = cur[1] + dc[d]
            
            if nr<0 or nc<0 or nr>=N or nc>=M or maps[nr][nc] == "X":
                continue
            elif ver == 0 and maps[nr][nc] == "L":
                return [nr, nc, cur[2]+1]            
            elif ver == 1 and maps[nr][nc] == "E":
                return [nr, nc, cur[2]+1]
            elif not visit[nr][nc]
                q.append([nr, nc, cur[2]+1])
                visit[nr][nc] = True
            
    return -1
