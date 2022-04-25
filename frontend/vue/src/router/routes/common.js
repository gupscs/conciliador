export default [
  {
    path: '/login',
    name: 'login',
    component: () => import('@/views/login/Login.vue'),
    meta: {
      layout: 'full',
      resource: 'Auth',
    },
  },
  {
    path: '/register',
    name: 'register',
    component: () => import('@/views/register/Register.vue'),
    meta: {
      layout: 'full',
      resource: 'Auth',
    },
  },
  {
    path: '/error-404',
    name: 'error-404',
    component: () => import('@/views/error/Error404.vue'),
    meta: {
      layout: 'full',
      resource: 'Auth',
    },
  },
  {
    path: '/not-authorized',
    name: 'misc-not-authorized',
    component: () => import('@/views/error/NotAuthorized.vue'),
    meta: {
      layout: 'full',
      redirectIfLoggedIn: true
    },
  },
  
  {
    path: '*',
    redirect: 'error-404',
  }
]