import * as axios from 'axios'

export default {
  getStocks: function () {
    return axios({
      method: 'GET',
      url: 'http://localhost:8010/api/app/stock-watcher/v1/stocks',
      responseType: 'application/json'
    })
  },
  getStockById: function (id) {
    return axios({
      method: 'GET',
      url: 'http://localhost:8010/api/app/stock-watcher/v1/stocks/' + id,
      responseType: 'application/json'
    })
  },
  addStocks: function (formData) {
    return axios.post('http://localhost:8010/api/app/stock-watcher/v1/stocks', formData)
  },
  updateStock: function (formData) {
    return axios.put('http://localhost:8010/api/app/stock-watcher/v1/stocks/' + formData.id, formData)
  }
}
