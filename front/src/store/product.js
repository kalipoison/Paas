import Cookie from 'js-cookie'
export default {
  state: {
    product: {}
  },
  mutations: {
    //存放 product
    setProduct (state, val) {
      state.product = val
      Cookie.set('product', val)
    },
    //清空 product
    clearProduct (state) {
      state.token = ''
      Cookie.remove('token')
    },
    //获取 product
    getToken (state) {
      state.token = Cookie.get('product')
    }
  },
  actions: {}
}