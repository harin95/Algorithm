def solution(want, number, discount):
    answer = 0

    idx_dict = {}
    for idx, item in enumerate(want):
        idx_dict[item] = idx

    possible = [0] * len(want)

    for item in discount[:10]:
        val = idx_dict.get(item)
        if val is not None:
            possible[int(idx_dict.get(item))] += 1

    if can_buy_all(number, possible):
        answer += 1

    for i in range(len(discount)-10):
        sub = idx_dict.get(discount[i])
        add = idx_dict.get(discount[i+10])

        if sub is not None:
            possible[sub] -= 1
        if add is not None:
            possible[add] += 1

        if can_buy_all(number, possible):
            answer += 1

    return answer


def can_buy_all(number, possible):
    for idx, val in enumerate(number):
        if possible[idx] < val:
            return False
    return True
  
