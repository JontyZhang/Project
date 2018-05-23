module.exports = {
  root: true,
  parserOptions: {
    sourceType: 'module'
  },
  // https://github.com/feross/standard/blob/master/RULES.md#javascript-standard-style
  extends: 'standard',
  // required to lint *.vue files
  plugins: [
    'html'
  ],
  // add your custom rules here
  'rules': {
    // allow paren-less arrow functions
    'arrow-parens': 0,
    // allow debugger during development
    'no-debugger': process.env.NODE_ENV === 'production' ? 2 : 0,
	'no-mixed-spaces-and-tabs': 0,
	'eol-last': 0,
	'no-trailing-spaces': 0,
	'no-irregular-whitespace': 0,
	'no-unused-vars': [0, {'vars': 'all', 'args': 'after-used'}],
	'no-multi-spaces': 0,
	'no-trailing-spaces': 0,
	'indent': [0, 2],
	'no-alert': 1,
	'no-tabs': 'off',
	'no-new': 'off'
  }
}