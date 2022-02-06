const webpack = require('webpack');

module.exports = {
  chainWebpack: config => {
    config.resolve.alias.set('vue', '@vue/compat')

    config.plugin('feature-flags').tap(args => {
      args[0].__VUE_PROD_DEVTOOLS__ = JSON.stringify(true)
      return args
    })

    config.module
      .rule('vue')
      .use('vue-loader')
      .tap(options => {
        return {
          ...options, compilerOptions: {
            compatConfig: {
              MODE: 2
            }
          }
        }
      })
  }
}