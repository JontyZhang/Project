module.exports = {
    MsgManager: {
        queryMsgBatch: {
           path: '/MsgManager/queryMsgBatch',
           method: 'POST'
       },
       queryMsgDetail: {
           path: '/MsgManager/queryMsgDetail',
           method: 'POST'
       },
       deleteMsgBatch: {
        path: '/MsgManager/deleteMsgBatch',
        method: 'POST'
       }
   }
}