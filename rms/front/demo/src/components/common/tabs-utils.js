module.exports = {
    /**
     * 新增一个选项卡
     * @param {*} cmpTabs 
     * @param {*} options 
     */
    open (cmpTabs, options) {
        cmpTabs.open(options)
    },
    openTab (cmpTabs, options) {
        cmpTabs.openTab(options)
    },
     /**
     * 选中一个选项卡
     * @param {*} cmpTabs 
     * @param {*} id 
     */
    selectTab (cmpTabs, id) {
        cmpTabs.selectTab(id)
    }
}