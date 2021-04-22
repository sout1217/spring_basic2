import axios from 'axios'

export default {
  /**
   * 현재 사용자의 이름, 보드, 팀 조회
   */
  getMyData() {
    return new Promise((resolve, reject) => {
      axios
        .post('/me')
        .then(({ data }) => {
          resolve(data)
        })
        .catch(error => {
          reject(error)
        })
    })
  },
}
