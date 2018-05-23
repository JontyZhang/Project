module.exports = {
    rentingHouse: {
        queryRentingHouse: {
           path: '/rentingHouse/queryRentingHouse',
           method: 'POST'
       },
       addRentingHouse: {
           path: '/rentingHouse/addRentingHouse',
           method: 'POST'
       },
       deleteRentingHouse: {
        path: '/rentingHouse/deleteRentingHouse',
        method: 'POST'
       },
       queryRentingHousesBatch: {
        path: '/rentingHouse/queryRentingHousesBatch',
        method: 'POST'
       },
       updateRentingHouse: {
        path: '/rentingHouse/updateRentingHouse',
        method: 'POST'
       },
       uploadURL: {
        path: '/upload/addFile',
        method: 'POST'
       }
   }
}