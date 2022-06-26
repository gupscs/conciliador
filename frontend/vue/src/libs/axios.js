import Vue from 'vue'

// axios
import axios from 'axios'

const axiosIns = axios.create({
  // You can add your headers here
  // ================================
  // baseURL: 'https://some-domain.com/api/',
  // timeout: 1000,
  // headers: {'X-Custom-Header': 'foobar'}
  //baseURL: 'http://localhost:8090',
  baseURL: 'https://vps39840.publiccloud.com.br',
  timeout: 100000,
  
})

Vue.prototype.$http = axiosIns

export default axiosIns
