module.exports = {
    role: {
        queryRoleById: {
           path: '/role/queryRoleById',
           method: 'POST'
       },
       addRole: {
           path: '/role/addRole',
           method: 'POST'
       },
       queryRoleBatch: {
           path: '/role/queryRoleBatch',
           method: 'POST'
       },
       deleteRole: {
           path: '/role/deleteRole',
           method: 'POST'
       },
       updateRole: {
           path: '/role/updateRole',
           method: 'POST'
       }
   }
}