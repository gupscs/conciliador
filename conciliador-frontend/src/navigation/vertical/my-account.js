export default [
    {
        header: 'Minha Conta',
    },
    {
        title: 'Meus Dados',
        route: 'my-info',
        icon: 'UserIcon',
    },
    {
        title: 'Meus Marketplaces',
        //route: 'my-marketplaces',
        route: { name: 'my-marketplaces', params: { tabActived: 0} },
        icon: 'ShoppingCartIcon',
    },
    {
        title: 'Custos Fixos',
        route: 'fixed-cost',
        icon: 'PercentIcon',
    },
    {
        title: 'Custos dos Produtos',
        route: 'item-cost',
        icon: 'PackageIcon',
    }
    ,
    {
        title: 'Commissões',
        route: 'fee',
        icon: 'PieChartIcon',
    },
    {
        title: 'Impostos',
        route: 'tax',
        icon: 'DollarSignIcon',
    }
]