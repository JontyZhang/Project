module.exports = {
    cityManager: {
        queryCityByPid: {
           path: '/cityManager/queryCityByPid',
           method: 'POST'
       },
       queryAllCity: {
           path: '/cityManager/queryAllCity',
           method: 'POST'
       }, 
       queryAllCityForFront: {
        path: '/cityManager/queryAllCityForFront',
        method: 'POST'
    }
   }
}