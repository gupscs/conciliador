export default [
    {
        path: '/',
        name: 'home',
        component: () => import('@/views/Home.vue'),
        meta: {
          pageTitle: 'Home',
          breadcrumb: [
            {
              text: 'Home',
              active: true,
            },
          ],
        },
      },
      {
        path: '/second-page',
        name: 'second-page',
        component: () => import('@/views/SecondPage.vue'),
        meta: {
          pageTitle: 'Second Page',
          breadcrumb: [
            {
              text: 'Second Page',
              active: true,
            },
          ],
        },
      }
]