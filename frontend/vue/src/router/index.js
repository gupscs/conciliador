import Vue from 'vue'
import VueRouter from 'vue-router'

// Routes
import { canNavigate } from '@/libs/acl/routeProtection'
import { isUserLoggedIn, getUserData, getHomeRouteForLoggedInUser } from '@/auth/utils'
import common from './routes/common'
import examples from './routes/examples'
import myAccount from './routes/my-account'
import sales from './routes/sales'
import system from './routes/system'


Vue.use(VueRouter)

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  scrollBehavior() {
    return { x: 0, y: 0 }
  },
  routes: [
    { path: '/', redirect: { name: 'home' } },
    ...examples,
    ...common,
    ...myAccount,
    ...sales,
    ...system,
    {
      path: '*',
      redirect: 'error-404',
    },
  ],
})

router.beforeEach((to, from, next) => {
  const isLoggedIn = isUserLoggedIn()
  if( to.meta.resource === 'Auth'){
    next()
  }else if(!isLoggedIn){
    next('/login')
  }else  if(canNavigate(to) || to.meta.redirectIfLoggedIn ){
    next()
  }else if(!canNavigate(to)){
    next('/not-authorized')
  }else{
    console.log("!! Error !! - Error to generate route")
  }
})

// ? For splash screen
// Remove afterEach hook if you are not using splash screen
router.afterEach(() => {
  // Remove initial loading
  const appLoading = document.getElementById('loading-bg')
  if (appLoading) {
    appLoading.style.display = 'none'
  }
})

export default router
