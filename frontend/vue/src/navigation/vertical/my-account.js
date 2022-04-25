export default [
    {
        header: 'Minha Conta',
        resource: 'CUSTOMER',
        action: 'all',
    },
    {
        title: 'Meus Dados',
        route: 'my-info',
        icon: 'UserIcon',
        resource: 'CUSTOMER',
        action: 'all',
    },
    {
        title: 'Meus Marketplaces',
        //route: 'my-marketplaces',
        route: { name: 'my-marketplaces', params: { tabActived: 0} },
        icon: 'ShoppingCartIcon',
        resource: 'CUSTOMER',
        action: 'all',
    },
    {
        title: 'Custos Fixos',
        route: 'fixed-cost',
        icon: 'PercentIcon',
        resource: 'CUSTOMER',
        action: 'all',
    },
    {
        title: 'Custos dos Produtos',
        route: 'item-cost',
        icon: 'PackageIcon',
        resource: 'CUSTOMER',
        action: 'all',
    }
]