export default [
    {
        path: '/my-account/myInfo',
        name: 'my-info',
        component: () => import('@/views/my-info/MyInfo.vue'),
        meta: {
          pageTitle: 'Meus Dados',
          breadcrumb: [
            {
              text: 'Minha Conta',
            },
            {
              text: 'Meus Dados',
              active: true,
            },
          ],
        },
      },
      {
        path: '/fixed-cost/fixedCost',
        name: 'fixed-cost',
        component: () => import('@/views/fixed-cost/FixedCost.vue'),
        meta: {
          pageTitle: 'Custos Fixos',
          breadcrumb: [
            {
              text: 'Minha Conta',
            },
            {
              text: 'Custos Fixos',
              active: true,
            },
          ],
        },
      }
]