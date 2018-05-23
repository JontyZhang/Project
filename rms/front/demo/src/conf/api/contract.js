module.exports = {
    contract: {
        addContract: {
            path: '/contract/addContract',
            method: 'POST'
        },
        queryContractBatch: {
            path: '/contract/queryContractBatch',
            method: 'POST'
        }, 
        queryContractByRentingHouseId: {
            path: '/contract/queryContractByRentingHouseId',
            method: 'POST'
        },
        queryContractByContractId: {
            path: '/contract/queryContractByContractId',
            method: 'POST'
        },
        updateContract: {
            path: '/contract/updateContract：',
            method: 'POST'
        },
        deleteContractById: {
             path: '/contract/deleteContractById',
            method: 'POST'
        },
        createContactWord: {
            path: '/contract/createContactWord',
            method: 'POST'            
        }
   }
}