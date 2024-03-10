def solution(board, moves):
    answer = 0
    n = len(board)
    doll_loc = [-1 for _ in range(n)]   #행별 인형 위치
    basket = []    #뽑은 인형 담는 바구니

    for i in range(n):
        for j in range(n):
            if board[j][i] != 0:
                doll_loc[i] = j
                break

    for move in moves:
        move -= 1
        loc = doll_loc[move]
        if loc >= n:
            continue

        pick = board[loc][move]   #뽑은 인형
        board[loc][move] = 0  #뽑은 표시
        doll_loc[move] += 1     #인형 위치 변경

        answer += pop_doll(basket, pick)    #터뜨린 인형 개수

    return answer


#바구니에 연속해서 같은 인형이 쌓이면 터뜨리기
def pop_doll(basket, pick):
    cnt = 0

    if len(basket) > 0 and basket[-1] == pick:
        while basket and basket[-1] == pick:
            basket.pop()
            cnt += 1
    else:
        basket.append(pick)

    return cnt+1 if cnt > 0 else 0
  
