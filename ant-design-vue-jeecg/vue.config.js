const path = require('path')

function resolve(dir) {
  return path.join(__dirname, dir)
}

// vue.config.js
module.exports = {
  /*
    Vue-cli3:
    Crashed when using Webpack `import()` #2463
    https://github.com/vuejs/vue-cli/issues/2463
   */
  // 如果你不需要生产环境的 source map，可以将其设置为 false 以加速生产环境构建。
  productionSourceMap: false,

  //打包app时放开该配置
  //publicPath:'./',
  configureWebpack: config => {
    //生产环境取消 console.log
    if (process.env.NODE_ENV === 'production') {
      config.optimization.minimizer[0].options.terserOptions.compress.drop_console = true
    }
  },
  chainWebpack: (config) => {
    config.resolve.alias
      .set('@$', resolve('src'))
      .set('@api', resolve('src/api'))
      .set('@assets', resolve('src/assets'))
      .set('@comp', resolve('src/components'))
      .set('@views', resolve('src/views'))
      .set('@layout', resolve('src/layout'))
      .set('@static', resolve('src/static'))
      .set('@mobile', resolve('src/modules/mobile'))
  },

  css: {
    loaderOptions: {
      less: {
        modifyVars: {
          /* less 变量覆盖，用于自定义 ant design 主题 */

          /*
          'primary-color': '#F5222D',
          'link-color': '#F5222D',
          'border-radius-base': '4px',
          */
        },
        javascriptEnabled: true,
      }
    }
  },

  devServer: {
    port: 3000,
    proxy: {
     /* '/api': {
        target: 'https://mock.ihx.me/mock/5baf3052f7da7e07e04a5116/antd-pro', //mock API接口系统
        ws: false,
        changeOrigin: true,
        pathRewrite: {
          '/tice': ''  //默认所有请求都加了tice前缀，需要去掉
        }
      },*/
      '/tice': {
        target: 'http://localhost:8080/api', //请求api
        ws: false,
        changeOrigin: true,
        pathRewrite: {
          '/tice': ''  //默认所有请求都加了tice前缀，需要去掉
        }
      },
    }
  },

  lintOnSave: undefined
}