// vue.config.js
// sets the port of our frontend development server on 3000
// sets a proxy called '/api' for 'http://localhost:8080' our backend server address
module.exports = {
    // https://cli.vuejs.org/config/#devserver-proxy
    devServer: {
        port: 3000,
        proxy: {
            '/api': {
                target: 'http://localhost:8080',
                ws: true,
                changeOrigin: true
            }
        }
    }

}