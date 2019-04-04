import Vue from 'vue'
import StockModal from '@/components/stock-modal/stock-modal.vue'
import StockService from '@/services/stock-service'
import sinon from 'sinon'
import SinonStubPromise from 'sinon-stub-promise'
describe('stock-modal.vue', () => {
  var mockResponse = {}
  beforeEach(function () {
    mockResponse.data = [
      {
        'id': 1,
        'name': 'Stock 1',
        'currentPrice': 100.5,
        'lastUpdate': '2018-02-25 16:00:00.000'
      }
    ]
    SinonStubPromise(sinon)
  })

  it('should get stocks correctly', () => {
    const Constructor = Vue.extend(StockModal)
    const vm = new Constructor().$mount()
    vm.stockId = 10
    vm.stockName = 'Temp Stock'
    vm.stockCurrentPrice = 10.50
    var mockETS = sinon.stub(StockService, 'addStocks').returnsPromise()
    mockETS.resolves(mockResponse)
    vm.addStock()
    // assert list contains new item
    expect(vm.alertMessage).to.equal('Added Successfully')
  })
})
