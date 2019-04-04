import Vue from 'vue'
import StockOperations from '@/components/stock-operations/stock-operations.vue'
import StockService from '@/services/stock-service'
import sinon from 'sinon'
import SinonStubPromise from 'sinon-stub-promise'

describe('stock-operations.vue', () => {
  var mockResponse = {}
  beforeEach(function () {
    mockResponse.data = [
      {
        'id': 1,
        'name': 'Stock 1',
        'currentPrice': 100.5,
        'lastUpdate': '2018-02-25 16:00:00.000'
      },
      {
        'id': 2,
        'name': 'Stock 2',
        'currentPrice': 200,
        'lastUpdate': '2018-02-25 16:00:00.000'
      }
    ]
    SinonStubPromise(sinon)
  })

  it('should render correct contents', () => {
    const Constructor = Vue.extend(StockOperations)
    const vm = new Constructor().$mount()
    expect(vm.$el.querySelector('.container h1').textContent)
      .to.equal('Stock Watcher')
  })

  it('should get stocks correctly', () => {
    const Constructor = Vue.extend(StockOperations)
    const vm = new Constructor().$mount()
    var mockETS = sinon.stub(StockService, 'getStocks').returnsPromise()
    mockETS.resolves(mockResponse)
    vm.getStocks()
    // assert list contains new item
    expect(vm.tableData).to.equal(mockResponse.data)
  })
})
