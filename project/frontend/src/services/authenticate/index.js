import axios from 'axios'

export default {
  authenticate (form) {
    return new Promise((resolve, reject) => {
      axios
        .post('/authentications', form)
        .then(({ data }) => {
          resolve(data)
        })
        .catch(error => {
          reject(error)
        })
    })
  }
}
