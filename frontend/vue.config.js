const apiTarget = process.env.VUE_APP_API_PROXY || 'http://localhost:8080'

module.exports = {
  devServer: {
    host: '0.0.0.0',
    port: 8081,
    proxy: {
      '/api': {
        target: apiTarget,
        changeOrigin: true
      }
    }
  }
}
