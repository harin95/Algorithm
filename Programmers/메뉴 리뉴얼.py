from itertools import combinations

def solution(orders, course):
    answer = []
    subsets = []
    course_cnt = {}

    for order in orders:
        for i in range(2, max(course)+1):
            arr = sorted(list(order))
            subsets.extend(list(combinations(arr, i)))

    subsets = sorted(subsets, key=lambda x : len(x))

    for k in course:
        course_cnt.clear()
        for subset in subsets:
            if len(subset) < k:
                continue
            elif len(subset) > k:
                break
            val = course_cnt.get(subset)
            course_cnt[subset] = val + 1 if val is not None else 1

        if len(course_cnt) == 0:
            continue
        sorted_list = sorted(course_cnt.items(), key=lambda x:x[1], reverse=True)

        max_val = sorted_list[0][1]
        if max_val < 2:
            continue
        for tup in sorted_list:
            if tup[1] == max_val:
                answer.append("".join(tup[0]))

    return sorted(answer)
  
