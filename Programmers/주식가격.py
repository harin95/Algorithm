def solution(prices):
    n = len(prices)
    answer = [0 for _ in range(n)]
    stack = [0]

    for i in range(1, n):
        while len(stack) > 0 and prices[stack[-1]] > prices[i]:
            top = stack.pop()
            answer[top] = i - top

        stack.append(i)

    for i in range(len(stack)-1):
        answer[stack[i]] = n-1-stack[i]

    return answer
  
