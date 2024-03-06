def solution(N, stages):
    n = len(stages)
    N += 2

    #각 스테이지에 몇 명의 플레이어가 있는지 배열로 저장
    playerNum = [0] * N

    for stage in stages:
        playerNum[stage] += 1

    # 누적합 구하기
    sumArr = [0] * N
    sumArr[1] = playerNum[1]
    
    for i in range(1, N):
        sumArr[i] = sumArr[i-1] + playerNum[i]


    #실패율

    failRate = [0] * N
    
    for i in range(1, N):
        try:
            failRate[i] = (playerNum[i]) / (sumArr[N-1]- sumArr[i-1])
        except:
            failRate[i] = 0

    failRate[0] = failRate[N-1] = -1

    # 실패율 높은 순으로 스테이지 번호 내림차순 정렬  
    answer = sorted(range(len(failRate)), key=lambda k: failRate[k],reverse=True)

    return answer[:-2]
