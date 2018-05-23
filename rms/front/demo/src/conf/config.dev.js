/**
 * 开发环境服务器配置
 */
module.exports = {
	// 后端服务器地址
	servers: {
		default: 'http://localhost:8080/rms'
	},
	// mockServer服务器地址，仅开发环境需要，测试和生产环境不需要
	mockServers: {
		default: 'http://localhost/rms'
	}
}
