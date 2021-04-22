export const user = function(state) {
  return state.user
}

export const hasBoards = state => state.boards.length > 0

export const personalBoards = state =>
  state.boards.filter(board => board.teamId === 0)

export const teamBoards = state => {
  const teams = []

  state.teams.forEach(team => {
    teams.push({
      id: team.id,
      name: team.name,
      boards: state.boards.filter(board => board.teamId === team.id),
    })
  })
  return teams
}
