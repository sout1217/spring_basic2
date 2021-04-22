// import meService from '@/services/me'

export const getMyData = function({ commit }) {
  // meService.getMyData().then(data => {
  //   commit('updateMyData', data)
  // })

  // 백엔드 통신 시 삭제 코드
  commit('updateMyData', {
    user: {
      name: 'something',
    },
    boards: [
      {
        id: 1,
        name: 'something board',
        description: 'something desc',
        teamId: 0,
      },
      {
        id: 2,
        name: 'something board 2',
        description: 'something desc',
        teamId: 1,
      },
      {
        id: 3,
        name: 'something board 3',
        description: 'something desc',
        teamId: 1,
      },
    ],
    teams: [
      {
        id: 1,
        name: 'Rose Class',
      },
    ],
  })
  // 백엔드 통신시 삭제  코드
}

export const addTeam = function({ commit }, team) {
  commit('addTeam', team)
}

export const addBoard = function({ commit }, board) {
  commit('addBoard', board)
}
