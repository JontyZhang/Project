// see http://vuejs-templates.github.io/webpack for documentation.
var path = require('path')
var api = require('../src/conf/api')
var config = require('../src/conf/config.dev')

module.exports = {
  build: {
    env: require('./prod.env'),
    index: path.resolve(__dirname, '../dist/index.html'),
    assetsRoot: path.resolve(__dirname, '../dist'),
    assetsSubDirectory: 'static',
    assetsPublicPath: '/',
    productionSourceMap: true,
    // Gzip off by default as many popular static hosts such as
    // Surge or Netlify already gzip all static assets for you.
    // Before setting to `true`, make sure to:
    // npm install --save-dev compression-webpack-plugin
    productionGzip: false,
    productionGzipExtensions: ['js', 'css']
  },
  dev: {
    env: require('./dev.env'),
    port: 8000,
    assetsSubDirectory: 'static',
    assetsPublicPath: '/',
    proxyTable: createProxyTable(),
    // CSS Sourcemaps off by default because relative paths are "buggy"
    // with this option, according to the CSS-Loader README
    // (https://github.com/webpack/css-loader#sourcemaps)
    // In our experience, they generally work as expected,
    // just be aware of this issue when enabling this option.
    cssSourceMap: false
  }
}

/**
 * 配置代理
 */
function createProxyTable () {
	var proxyApi = []
	for(var i in api) {
		if(typeof(api[i]) === 'object') {
			Object.keys(api[i]).forEach(function(context) {
				var path = api[i][context]
				if(typeof(path) === 'object') {
					var serverName = path.server || 'default'
					if(path.mock === true) {
						proxyApi[path.path] = config.mockServers[serverName]
					} else {
						if (!config.servers[serverName]) {
							throw new Error('开发环境代理服务器配置错误' + serverName + '=' + config.servers[serverName])
						}
						proxyApi[path.path] = config.servers[serverName]
					}
				} else {
					proxyApi[path] = config.servers.default
				}
			})
		}
	}
	return proxyApi
}
