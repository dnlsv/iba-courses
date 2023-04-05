enum signs {
  "stone",
  "scissors",
  "paper",
}

function game(player1: signs, player2: signs): string {
  if (player1 === player2) return "draw";

  if (
    (player1 === signs.stone && player2 === signs.scissors) ||
    (player1 === signs.scissors && player2 === signs.paper) ||
    (player1 === signs.paper && player2 === signs.stone)
  )
    return "player1 win!";
  else return "player2 win!";
}

console.log(game(signs.stone, signs.stone));
