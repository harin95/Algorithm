from collections import defaultdict
import heapq


def solution(N, road, K):
    answer = 0

    adj_dict = defaultdict(list)

    for road_info in road:
        fr, to, cost = map(int, road_info)
        adj_dict[fr].append((cost, to))
        adj_dict[to].append((cost, fr))

    distance = [float("inf")] * (N + 1)
    distance[1] = 0

    pq = []
    heapq.heappush(pq, (0, 1))

    while pq:
        cur = heapq.heappop(pq)
        cur_cost = cur[0]
        cur_num = cur[1]

        for adj_info in adj_dict.get(cur_num):
            next_cost = adj_info[0] + cur_cost
            next_num = adj_info[1]

            if distance[next_num] > next_cost:
                distance[next_num] = next_cost
                heapq.heappush(pq, (next_cost, next_num))

    for res in distance:
        if res <= K:
            answer += 1

    return answer
