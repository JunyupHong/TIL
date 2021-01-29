function solution(skill, skill_trees) {
    let answer = 0;
    skill_trees.forEach(skillTree => {
        const order = skillTree.split('').filter(s => skill.includes(s));
        const origin = skill.split('').map(s => order.indexOf(s) + 1 || 100);
        if (JSON.stringify(origin) === JSON.stringify(origin.sort((a, b) => a - b))) answer++;
    });
    return answer;
}
