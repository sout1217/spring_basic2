module.exports = {
  devServer: {
    port: 3000,
    proxy: {
      'api/v1/*': {
        target: 'http://localhost:8080'
      }
    }
  }
}
