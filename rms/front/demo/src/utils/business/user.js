import store from '../../store'
import api from 'conf/api'
import backend from 'utils/backend'

export default {
    logout () {
        store.dispatch('global.setUser', null)
        backend.request(api.loginController.loginOut)
    }
}