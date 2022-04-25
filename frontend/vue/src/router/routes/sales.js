export default [
    {
        path: '/report-sales/reportSales',
        name: 'report-sales',
        component: () => import('@/views/report-sales/reportSales.vue'),
        meta: {
          resource: 'CUSTOMER',
          action: 'all',
          pageTitle: 'Relatório',
          breadcrumb: [
            {
              text: 'Vendas',
            },
            {
              text: 'Relatório',
              active: true,
            },
          ],
        },
      }

]