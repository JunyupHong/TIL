let answer = 0;
function solution(numbers, target) {
    recursion(numbers, target, numbers[0], 1);
    recursion(numbers, target, -numbers[0], 1);
    return answer;
}

function recursion(numbers, target, prevSum, index) {
    const addSum = prevSum + numbers[index];
    const subSum = prevSum - numbers[index];
    
    if(index === numbers.length - 1) {
        if(addSum === target || subSum === target) answer++;
        return;
    } else {
        index++;
        recursion(numbers, target, addSum, index);
        recursion(numbers, target, subSum, index);
    }
}
