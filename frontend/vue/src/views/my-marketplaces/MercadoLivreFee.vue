<template>
  <div>
    <b-row>
      <b-col cols="12">
        <mercado-livre-fee-repeater/>
      </b-col>
    </b-row>
  </div>
</template>

<script>
import { BRow, BCol } from 'bootstrap-vue'
import MercadoLivreFeeRepeater from './MercadoLivreFeeRepeater.vue'



export default {
  components: {
    BRow,
    BCol,
    MercadoLivreFeeRepeater,

  },
}
</script>