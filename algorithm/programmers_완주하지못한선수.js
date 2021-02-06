function solution(participant, completion) {
    const participants = [...participant].sort();
    const completions = [...completion].sort();
    for (let i = 0; i < completions.length; i++) {
        if (participants[i] !== completions[i]) {
            return participants[i];
        }
    }
    return participants[participants.length - 1];
}
