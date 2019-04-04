import StockService from '@/services/stock-service'
import Vue from 'vue'
import {ServerTable, ClientTable, Event} from 'vue-tables-2'
import 'bootstrap/dist/css/bootstrap.css'
import BootstrapVue from 'bootstrap-vue'
import StockModal from '../stock-modal/stock-modal.vue'
import EventBus from '../event-bus'

Vue.use(ClientTable)
Vue.use(ServerTable)
Vue.use(Event)
Vue.use(BootstrapVue)

export default {
  name: 'stock-operations',
  components: {
    StockModal
  },
  data () {
    return {
      msg: 'Welcome to Your Vue.js App',
      searchQuery: '',
      stockColumns: ['Id', 'Name', 'Current-Price', 'Last-Update'],
      stockList: [{
        id: '',
        name: '',
        currentPrice: '',
        lastUpdate: ''
      }],
      columns: ['id', 'name', 'currentPrice', 'lastUpdate', 'edit'],
      tableData: [],
      options: {
        template: 'default'
      },
      showModal: false,
      stockId: ''
    }
  },
  methods: {
    getStocks: function () {
      let getStocksPromise = StockService.getStocks()
      getStocksPromise
        .then(response => { this.tableData = response.data })
        .catch(error => console.log(error))
    },
    openAddStockModal: function () {
      this.operation = 'add'
      this.showModal = true
    },
    openUpdateStockModal: function (id) {
      this.operation = 'update'
      this.stockId = id
      this.showModal = true
    },
    closeModal: function () {
      this.showModal = false
    },
    edit: function (id) {
      this.openUpdateStockModal(id)
    }

  },
  mounted () {

  },
  watch: {

  },
  created () {
    this.getStocks()
    EventBus.$on('updateTable', () => { this.getStocks() })
    EventBus.$on('closeModal', () => { this.closeModal() })
  }
}
