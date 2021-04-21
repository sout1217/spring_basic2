module.exports = {
  devServer: {
    overlay: false,
    port: 3000,
    proxy: {
      'api/v1/*': {
        target: 'http://localhost:8080',
      },
    },
  },
  configureWebpack: {
    entry: {
      app: './src/main.js',
      style: ['bootstrap/dist/css/bootstrap.min.css'],
    },
  },
}
