import Vue from 'vue'

// axios
import axios from 'axios'

const axiosIns = axios.create({
  // You can add your headers here
  // ================================
  // baseURL: 'https://some-domain.com/api/',
  // timeout: 1000,
  // headers: {'X-Custom-Header': 'foobar'}
  baseURL: 'http://localhost:8090',
  //baseURL: 'https://api-gateway-dot-conciliador-339723.rj.r.appspot.com/',
  timeout: 100000,
  
})

Vue.prototype.$http = axiosIns

export default axiosIns
