export default {
  updateMyData(state, data) {
    state.user.name = data.user.name
    state.teams = data.teams
    state.boards = data.boards
  },
  // 추가 하는 도중에 순서가 바뀐경우는 ?
  addTeam(state, team) {
    state.teams.push(team)
  },
  addBoard(state, board) {
    state.boards.push(board)
  },
}
