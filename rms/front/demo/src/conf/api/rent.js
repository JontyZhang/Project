module.exports = {
    Rent: {
        queryRentBatch: {
           path: '/Rent/queryRentBatch',
           method: 'POST'
       },
       queryRentById: {
           path: '/Rent/queryRentById',
           method: 'POST'
       },
       deleteRents: {
           path: '/Rent/deleteRents',
           method: 'POST'
       },
       updateRents: {
           path: '/Rent/updateRents',
           method: 'POST'
       }
   }
}