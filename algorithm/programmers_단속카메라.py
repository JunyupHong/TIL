def solution(routes):
    routes.sort(key=lambda r: r[0])
    camera_coverage = routes[0][1]
    answer = 1
    
    for i, r in enumerate(routes):
        if camera_coverage >= r[1]: camera_coverage = r[1]
        else:
            answer += 1
            camera_coverage = routes[i+1][1]
    return answer
