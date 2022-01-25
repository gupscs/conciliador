export default [
  {
    path: '/login',
    name: 'login',
    component: () => import('@/views/login/Login.vue'),
    meta: {
      layout: 'full',
    },
  },
  {
    path: '/register',
    name: 'register',
    component: () => import('@/views/register/Register.vue'),
    meta: {
      layout: 'full',
    },
  },
  {
    path: '/error-404',
    name: 'error-404',
    component: () => import('@/views/error/Error404.vue'),
    meta: {
      layout: 'full',
    },
  },
  {
    path: '/mercado-livre/authorizationRedirect',
    name: 'error-404',
    component: () => import('@/views/my-marketplaces/mercado-livre/AuthorizationRedirect.vue'),
    meta: {
      layout: 'full',
    },
  },
  {
    path: '*',
    redirect: 'error-404',
  }
]