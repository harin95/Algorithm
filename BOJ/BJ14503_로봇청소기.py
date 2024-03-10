N, M = map(int, input().split(" "))
pos = list(map(int, input().split(" ")))
dirs = [[-1, 0], [0, 1], [1, 0], [0, -1]]  # 전진 방향
opp_dirs = [[1, 0], [0, -1], [-1, 0], [0, 1]]  # 후진 방향
counter_clockwise = [3, 0, 1, 2]

room = []
cnt = 0

for i in range(N):
    temp_list = list(map(int, input().split()))
    room.append(temp_list)

r = pos[0]
c = pos[1]
d = pos[2]

while 1:
    # 현재 칸이 빈칸이고 청소하지 않았으면 청소
    if room[r][c] == 0:
        room[r][c] = 2
        cnt += 1

    # 반시계로 주변 탐색
    dirty_room = False

    for i in range(4):
        d = 3 if d - 1 < 0 else d - 1
        nr = r + dirs[d][0]
        nc = c + dirs[d][1]

        try:
            dirty_room = True if room[nr][nc] == 0 else False
            # 이동, 방향 전환
            if dirty_room:
                r = nr
                c = nc
                break
        except IndexError:
            continue

    # 청소가능 빈칸 없으면
    if not dirty_room:
        nr = r + opp_dirs[d][0]
        nc = c + opp_dirs[d][1]

        if room[nr][nc] != 1:  # 후진 가능, 한 칸 뒤로
            r = nr
            c = nc
        else:
            break  # 후진 불가, 종료

print(cnt)
