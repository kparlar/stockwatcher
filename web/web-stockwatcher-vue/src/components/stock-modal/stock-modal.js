import StockService from '@/services/stock-service'
import EventBus from '../event-bus'

export default {
  name: 'stock-modal',
  props: {
    propOperation: '',
    propStockId: ''
  },
  data: function () {
    return {
      operationName: '',
      addOperation: false,
      updateOperation: false,
      displayOperation: false,
      stockName: '',
      stockCurrentPrice: '',
      stockLastUpdate: '',
      showDismissibleAlert: false,
      alertType: '',
      alertMessage: '',
      stockId: ''
    }
  },
  created () {
    if (this.propOperation === 'add') {
      this.operationName = 'Add Stock'
      this.addOperation = true
    } else if (this.propOperation === 'update') {
      this.operationName = 'Update Stock Price'
      this.updateOperation = true
      this.getStockById(this.propStockId)
    }
  },
  methods: {
    addStock: function () {
      let formData = {}
      formData.id = this.stockId
      formData.name = this.stockName
      formData.currentPrice = this.stockCurrentPrice
      let addStockPromise = StockService.addStocks(formData)
      let vm = this
      addStockPromise.then(response => {
        let message = 'Added Successfully'
        vm.showMessage(vm, 'success', message, true)
      }).catch(err => {
        let message = 'Error Occurred'
        console.log(err)
        vm.showMessage(vm, 'danger', message, true)
      })
    },
    updateStock: function () {
      let formData = {}
      formData.id = this.stockId
      formData.name = this.stockName
      formData.currentPrice = this.stockCurrentPrice
      let addStockPromise = StockService.updateStock(formData)
      let vm = this
      addStockPromise.then(response => {
        let message = 'Updated Successfully'
        vm.showMessage(vm, 'success', message, true)
      }).catch(err => {
        let message = 'Error Occurred'
        console.log(err)
        vm.showMessage(vm, 'danger', message, true)
      })
    },
    getStockById: function (id) {
      let getStockPromise = StockService.getStockById(id)
      let vm = this
      getStockPromise.then(response => {
        vm.stockId = response.data.id
        vm.stockName = response.data.name
        vm.stockCurrentPrice = response.data.currentPrice
        vm.stockLastUpdate = response.data.lastUpdate
      }).catch(err => {
        let message = 'Cant load Stock with id:' + id
        console.log(err)
        vm.showMessage(vm, 'danger', message, true)
      })
    },
    showMessage: function (vm, alertType, message, dismissAlert) {
      vm.alertType = alertType
      vm.showDismissibleAlert = dismissAlert
      vm.alertMessage = message
    },
    closeModal: function () {
      EventBus.$emit('updateTable')
      EventBus.$emit('closeModal')
    }
  },
  mounted () {

  },
  watch: {

  }
}
