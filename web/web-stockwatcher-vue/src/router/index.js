import Vue from 'vue'
import Router from 'vue-router'
import StockOperations from '@/components/stock-operations/stock-operations.vue'

Vue.use(Router)

export default new Router({

  routes: [
    {
      path: '/',
      name: 'stock-operations',
      component: StockOperations
    }
  ]
})
