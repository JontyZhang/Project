const user = require('./user')
const rentingHouse = require('./rentingHouse')
const cityManager = require('./cityManager')
const role = require('./role')
const dictionaryManager = require('./dictionaryManager')
const loginController = require('./loginController')
const RentingHouseMsgController = require('./RentingHouseMsgController')
const contract = require('./contract')
const tenant = require('./tenant')
const deposit = require('./deposit')
const rent = require('./rent')
const MsgManager = require('./MsgManager')
const api = {}

Object.assign(
    api,
    user,
    rentingHouse,
    cityManager,
    role,
    dictionaryManager,
    loginController,
    RentingHouseMsgController,
    contract,
    tenant,
    deposit,
    rent,
    MsgManager
)
module.exports = api