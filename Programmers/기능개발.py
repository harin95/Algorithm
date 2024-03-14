def solution(progresses, speeds):
    answer = []
    n = len(progresses)

    front = 0

    while front < n:
        for idx, speed in enumerate(speeds):
            progresses[idx] += speed

        if progresses[front] >= 100:
            done_num = 0
            for idx in range(front, n):
                if progresses[idx] >= 100:
                    done_num += 1
                else:
                    break

            front += done_num
            answer.append(done_num)

    return answer
