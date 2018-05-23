let NS = 'button.'

export default {
	state: {
		button: null
	},
	getters: {
		[NS + 'button']: state => state.button
	},
	mutations: {
		[NS + 'button'] (state, button) {
			state.button = button
		}
	},
	actions: {		
		[NS + 'setButton'] (context, button) {
			context.commit(NS + 'button', button)
		}
	}
}