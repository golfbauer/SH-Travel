// vue.config.js
// sets the port of our frontend development server on 3000
// sets a proxy called '/api' for 'http://localhost:8080' our backend server address
module.exports = {

  publicPath: process.env.NODE_ENV === 'production' ? '/SHTravel' : '',
  devServer: {
    port: '3000',
    proxy: {
      '^/SHTravel': {
        target: 'http://localhost:8080',
        changeOrigin: true,
        pathRewrite: { '^/SHTravel' : '/' }
      }
    }
  }
}
