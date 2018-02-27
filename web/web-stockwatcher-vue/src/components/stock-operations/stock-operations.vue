<template>
  <div>
    <div class="header-anml">
    </div>
    <div class="logo"> </div>
    <div class="container">
      <h1>Stock Watcher</h1>
      <div>
        <button id="show-modal" @click="openAddStockModal">Add Stock</button>
        <!-- use the modal component, pass in the prop -->
        <stock-modal v-if="showModal" @close="showModal = false" :propOperation="operation" :propStockId="stockId">
        </stock-modal>
      </div>
      <div>
        <v-client-table :data="tableData" :columns="columns" :options="options">
          <button slot="edit" slot-scope="props" class="fa fa-edit" @click="edit(props.row.id)"> Edit </button>
        </v-client-table>
      </div>
    </div>
    <div class="footer-anml">
    </div>
  </div>
</template>
<script src="./stock-operations.js"></script>
<style src="./stock-operations.css" scoped></style>
