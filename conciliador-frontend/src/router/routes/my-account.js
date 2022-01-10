export default [
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