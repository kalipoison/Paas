import Cookie from 'js-cookie'
export default {
  state: {
    product: {},
    orderNum : '',
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
      Cookie.remove('product')
    },
    //获取 product
    getProduct (state) {
      state.product = Cookie.get('product')
    },
    //存放 orderNum
    setOrderNum (state, val) {
      state.orderNum = val
      Cookie.set('orderNum', val)
    },
    //清空 orderNum
    clearOrderNum (state) {
      state.orderNum = ''
      Cookie.remove('orderNum')
    },
    //获取 orderNum
    getOrderNum (state) {
      state.orderNum = Cookie.get('orderNum')
    }
  },
  actions: {}
}