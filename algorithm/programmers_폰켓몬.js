function solution(nums) {
    const s = new Set(nums);
    return s.size > nums.length / 2 ? nums.length / 2 : s.size;
}
