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
    path: '/my-account/myMarketplaces/:tabActived',
    name: 'my-marketplaces',
    component: () => import('@/views/my-marketplaces/MyMarketplaces.vue'),
    meta: {
      pageTitle: 'Meus Marketplaces',
      breadcrumb: [
        {
          text: 'Minha Conta',
        },
        {
          text: 'Meus Marketplaces',
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
  },
]
