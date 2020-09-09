let answers = [];
function solution(tickets) {
    recursion(tickets, ['ICN']);
    answers.sort();
    return answers[0];
}

function recursion(remainTickets, route) {
    const departure = route[route.length - 1] || 'ICN';
    if (remainTickets.length === 0) {
        answers.push(route);
        return;
    }
    const usefulTickets = remainTickets.filter(t => t[0] === departure);
    if (usefulTickets.length === 0 ) return;
    
    usefulTickets.forEach(t => {
        const addRoute = [...route];
        addRoute.push(t[1]);

        const removeIdx = remainTickets.findIndex(rT => rT[0] === t[0] && rT[1] === t[1]);
        const newArr = remainTickets.slice();
        newArr.splice(removeIdx, 1);
        recursion(newArr, addRoute);
    });
    
}
