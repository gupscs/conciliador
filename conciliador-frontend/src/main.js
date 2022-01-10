import Vue from 'vue'
import { ToastPlugin, ModalPlugin } from 'bootstrap-vue'
import VueCompositionAPI from '@vue/composition-api'

import router from './router'
import store from './store'
import App from './App.vue'

// Global Components
import './global-components'

// 3rd party plugins
import '@/libs/portal-vue'
import '@/libs/toastification'
import '@axios'
import '@/libs/clipboard'
import '@/libs/sweet-alerts'
import '@/libs/vue-select'
import '@/libs/tour'
import i18n from '@/libs/i18n'

// BSV Plugin Registration
Vue.use(ToastPlugin)
Vue.use(ModalPlugin)

require('@core/assets/fonts/feather/iconfont.css') // For form-wizard

// Composition API
Vue.use(VueCompositionAPI)

// import core styles
require('@core/scss/core.scss')

// import assets styles
require('@/assets/scss/style.scss')

Vue.config.productionTip = false

new Vue({
  router,
  store,
  i18n,
  render: h => h(App),
}).$mount('#app')
