import math

inf = 100_000


def distance(r1, c1, r2, c2):
    return math.pow((r1 - r2), 2) + math.pow((c1 - c2), 2)


# 루돌프와 각 산타 거리 계산 후 저장
def calculate_distance():
    global dist
    dist.clear()
    for idx, pos in enumerate(santa_position):
        sr = pos[0]
        sc = pos[1]
        if sr == -1 and sc == -1:
            d = inf
        else:
            d = distance(rudolph_r, rudolph_c, sr, sc)
        dist.append([d, sr, sc])
    dist.sort(key=lambda x: (x[0], -x[1], -x[2]))  # 거리 오름차순 / r,c 내림차순 정렬


def get_direction(r1, c1, r2, c2):
    # r1, c1 기준
    # 0: 상, 1: 우, 2: 하, 3: 좌, 4: 좌상, 5: 우상, 6: 좌하, 7: 우하
    if r1 == r2:
        if c1 > c2:
            return 3
        elif c1 < c2:
            return 1
    elif c1 == c2:
        if r1 > r2:
            return 0
        elif r1 < r2:
            return 2
    elif r1 > r2 and c1 > c2:
        return 4
    elif r1 > r2 and c1 < c2:
        return 5
    elif r1 < r2 and c1 > c2:
        return 6
    elif r1 < r2 and c1 < c2:
        return 7


def is_in(r, c):
    if 1 <= r <= N and 1 <= c <= N:
        return True
    return False


# 산타 위치 변경
def change_santa_map_position(santa_num, d, sr, sc, nr, nc):
    # 연쇄 반응
    if is_in(nr, nc) and game_map[nr][nc] > 0:
        nnr = nr + dr[d]
        nnc = nc + dc[d]
        change_santa_map_position(game_map[nr][nc], d, nr, nc, nnr, nnc)

    game_map[sr][sc] = 0

    if is_in(nr, nc):
        game_map[nr][nc] = santa_num
        santa_position[santa_num - 1] = [nr, nc]
    else:
        santa_position[santa_num - 1] = [-1, -1]    # 탈락 표시


N, M, P, C, D = map(int, input().split(" "))

dr = [-1, 0, 1, 0, -1, -1, 1, 1]
dc = [0, 1, 0, -1, -1, 1, -1, 1]
opp_dir = [2, 3, 0, 1]

rudolph_r, rudolph_c = map(int, input().split(" "))
santa_score = [0] * (P + 1) # 점수
santa_kijul = [0] * (P + 1) # 몇초까지 기절하는지
dist = []   # 루돌프와 산타들 거리
santa_position = [[] for _ in range(P)] # 산타 위치 배열로 저장
game_map = [[0 for _ in range(N + 1)] for _ in range(N + 1)]    # 산타 위치 맵으로 저장

for _ in range(P):
    num, r, c = map(int, input().split(" "))
    santa_position[num-1] = [r, c]
    game_map[r][c] = num

for k in range(1, M+1):
    # 1. 루돌프가 가장 가까운 산타를 선택
    calculate_distance()
    near_santa = dist[0]
    santa_r = near_santa[1]
    santa_c = near_santa[2]
    # 모든 산타가 탈락한 경우
    if santa_r == -1 and santa_c == -1:
        break
    # 2. 방향구하기
    d = get_direction(rudolph_r, rudolph_c, santa_r, santa_c)
    # 방향으로 한칸 이동
    rudolph_r += dr[d]
    rudolph_c += dc[d]

    # 3. 옮긴 칸에 산타가 있을 경우
    if game_map[rudolph_r][rudolph_c] > 0:
        santa_num = game_map[rudolph_r][rudolph_c]
        # 산타 기절 표시
        santa_kijul[santa_num] = k + 1
        # 산타 루돌프 돌진 방향으로 C칸 밀려남
        nr = rudolph_r + dr[d] * C
        nc = rudolph_c + dc[d] * C
        # 산타 점수 + C
        santa_score[santa_num] += C
        # 맵,배열에서 산타 위치 변경
        change_santa_map_position(santa_num, d, rudolph_r, rudolph_c, nr, nc)

    # 4. 1~P번 산타 움직임
    for idx, santa_pos in enumerate(santa_position):
        santa_num = idx + 1
        if santa_kijul[santa_num] >= k or santa_pos == [-1, -1]:  # 기절이나 탈락이면 패스
            continue

        santa_r, santa_c = santa_pos
        original_dist = distance(rudolph_r, rudolph_c, santa_r, santa_c)    # 루돌프와 원래 거리
        santa_rudolph_dist = []
        # 상우하좌 방향으로 움직였을 때 거리 계산 / 거리 가까운 순 & 방향 우선순위 순으로 정렬
        for d in [0, 1, 2, 3]:
            nr = santa_r + dr[d]
            nc = santa_c + dc[d]
            if not is_in(nr, nc):
                continue
            new_dist = distance(rudolph_r, rudolph_c, nr, nc)
            santa_rudolph_dist.append([new_dist, d, nr, nc])
            santa_rudolph_dist.sort(key=lambda x: (x[0], x[1]))

        # 루돌프랑 가장 가까워지는 방향(상우하좌)으로 1칸 이동
        for info in santa_rudolph_dist:
            new_dist = info[0]
            d = info[1]
            nr = info[2]
            nc = info[3]

            if new_dist < original_dist and game_map[nr][nc] == 0:  # 루돌프랑 가까워지지 않거나 칸에 산타가 있다면 이동하지 않음
                santa_position[santa_num-1] = [nr, nc]
                # 맵, 배열에서 산타 위치 변경
                change_santa_map_position(santa_num, d, santa_r, santa_c, nr, nc)
                # 옮긴 칸에 루돌프가 있을 경우
                if nr == rudolph_r and nc == rudolph_c:
                    # 산타 기절 표시
                    santa_kijul[santa_num] = k + 1
                    # 산타 이동 반대 방향으로 D만큼 밀려남
                    nr += dr[opp_dir[d]] * D
                    nc += dc[opp_dir[d]] * D
                    # 산타 점수 +D
                    santa_score[santa_num] += D
                    change_santa_map_position(santa_num, opp_dir[d], rudolph_r, rudolph_c, nr, nc)
                break

    # 탈락하지 않은 산타는 1점씩 획득
    for i in range(P):
        if santa_position[i] == [-1, -1]:
            continue
        santa_score[i+1] += 1

for score in santa_score[1:]:
    print(score, end=" ")
