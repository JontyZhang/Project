module.exports = {
    RentingHouseMsgController: {
        queryRentingHousesBatchFront: {
           path: '/RentingHouseMsgController/queryRentingHousesBatchFront',
           method: 'POST'
       },
       queryMsgDetail: {
           path: '/RentingHouseMsgController/queryMsgDetail',
           method: 'POST'
       },
       setMsg: {
        path: '/RentingHouseMsgController/setMsg',
        method: 'POST'
    } 
   }
}