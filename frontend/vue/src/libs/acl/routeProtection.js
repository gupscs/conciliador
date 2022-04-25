import ability from './ability'

export const canNavigate = to => to.matched.some(route => ability.can(route.meta.action, route.meta.resource))

export const _ = undefined
