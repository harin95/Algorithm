def solution(id_list, report, k):
    report = set(report)
    reported_num = [0] * len(id_list)
    whom_i_report = {}
    answer = [0] * len(id_list)

    for id in range(len(id_list)):
        whom_i_report[id] = []

    for i in report:
        content = i.split(' ')  #content[0]: 신고한 사람 content[1]: 신고당한 사람
        reported_num[id_list.index(content[1])] += 1    #신고당한 횟수 증가
        whom_i_report[id_list.index(content[0])].append(id_list.index(content[1]))

    for i in range(len(id_list)):
        for j in range(len(whom_i_report[i])):
            if reported_num[whom_i_report[i][j]] >= k:
                answer[i] += 1

    return answer
