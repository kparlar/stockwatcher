<template>
  <transition name="add-stock-modal">
    <div class="modal-mask">
      <div class="modal-wrapper">
        <div class="modal-container">
          <div class="modal-header">
            <slot name="header">
              {{operationName}}
            </slot>
          </div>

          <div class="modal-body">
            <slot name="alert">
              <b-alert v-bind:variant="alertType" dismissible
                       :show="showDismissibleAlert"
                       @dismissed="showDismissibleAlert=false">{{alertMessage}}</b-alert>
            </slot>
            <slot name="body" v-if="addOperation">
                <div class="form-group">
                  <label>ID</label>
                  <input type="text" v-model="stockId" class="form-control" placeholder="Stock Id">
                </div>
                <div class="form-group">
                  <label>Name</label>
                  <input type="text" v-model="stockName" class="form-control" placeholder="Stock Name">
                </div>
                <div class="form-group">
                  <label>Price</label>
                  <input type="text" v-model="stockCurrentPrice" class="form-control" placeholder="Current Stock Price">
                </div>
                <button type="submit" class="btn btn-primary" @click="addStock">Submit</button>
                <button class="btn btn-primary" @click="closeModal">Close</button>
            </slot>
            <slot name="body" v-if="updateOperation">
              <div class="form-group">
                <label>ID</label>
                <label class="form-control">{{stockId}}</label>
              </div>
              <div class="form-group">
                <label>Name</label>
                <label class="form-control">{{stockName}}</label>
              </div>
              <div class="form-group">
                <label>Price</label>
                <input type="text" v-model="stockCurrentPrice" class="form-control" placeholder="Current Stock Price">
              </div>
              <div class="form-group">
                <label>Last Update</label>
                <label class="form-control">{{stockLastUpdate}}</label>
              </div>
              <button type="submit" class="btn btn-primary" @click="updateStock">Submit</button>
              <button class="btn btn-primary" @click="closeModal">Close</button>
            </slot>
          </div>
          <div class="modal-footer">
            <slot name="footer">
            </slot>
          </div>
        </div>
      </div>
    </div>
  </transition>
</template>
<script src="./stock-modal.js"></script>
<style src="./stock-modal.css" scoped></style>
