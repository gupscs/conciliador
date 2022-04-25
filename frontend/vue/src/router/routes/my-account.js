export default [
    {
        path: '/my-account/myInfo',
        name: 'my-info',
        component: () => import('@/views/my-info/MyInfo.vue'),
        meta: {
          resource: 'CUSTOMER',
          action: 'all',
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
          resource: 'CUSTOMER',
          action: 'all',
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
          resource: 'CUSTOMER',
          action: 'all',
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
      {
        path: '/item-cost/itemCost',
        name: 'item-cost',
        component: () => import('@/views/item-cost/ItemCost.vue'),
        meta: {
          resource: 'CUSTOMER',
          action: 'all',
          pageTitle: 'Custos dos Produtos',
          breadcrumb: [
            {
              text: 'Minha Conta',
            },
            {
              text: 'Custos dos Produtos',
              active: true,
            },
          ],
        },
      },
      {
        path: '/mercado-livre/authorizationRedirect',
        name: 'mercado-livre-authorization-redirect',
        component: () => import('@/views/my-marketplaces/mercado-livre/AuthorizationRedirect.vue'),
        meta: {
          layout: 'full',
          resource: 'CUSTOMER',
          action: 'all',
        },
      }
]

