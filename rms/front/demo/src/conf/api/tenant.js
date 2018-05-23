module.exports = {
    tenant: {
        queryTenantBatch: {
            path: '/tenant/queryTenantBatch',
            method: 'POST'
        },
        addTenant: {
            path: '/tenant/addTenant',
            method: 'POST'
        }, 
        deleteTenant: {
            path: '/tenant/deleteTenant',
            method: 'POST'
        },
        queryTenant: {
            path: '/tenant/queryTenant',
            method: 'POST'
        },
        updateTenant: {
            path: '/tenant/updateTenant',
            method: 'POST'
        }
   }
}