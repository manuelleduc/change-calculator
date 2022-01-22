import Vue, {createApp} from 'vue'
import App from './App.vue'

if (process.env.NODE_ENV === 'development') {
  Vue.config.devtools = true
  Vue.config.performance = true
  Vue.config.productionTip = false
}

createApp(App).mount('#app')

