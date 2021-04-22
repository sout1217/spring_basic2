import axios from 'axios'

export default {
  /**
   * 새로운 보드 생성하기
   * @detail - 보드의 세부사항
   * */
  create(detail) {
    return new Promise((resolve, reject) => {
      axios
        .post('/boards', detail)
        .then(({ data }) => {
          resolve(data)
        })
        .catch(error => {
          reject(error)
        })
    })
  },
}
