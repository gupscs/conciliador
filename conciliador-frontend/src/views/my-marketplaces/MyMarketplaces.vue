<template>
  <b-tabs
    v-model="tabIndex"
    vertical
    content-class="col-12 col-md-9 mt-1 mt-md-0"
    pills
    nav-wrapper-class="col-md-3 col-12"
    nav-class="nav-left"
  >

    <!-- Mercado Livre tab -->
    <b-tab>

      <template #title>
        <span class="font-weight-bold">Mercado Livre</span>
      </template>

      <mercado-livre />
    </b-tab>

    <!-- Shopee tab -->
    <b-tab>

      <template #title>
        <span class="font-weight-bold">Shopee</span>
      </template>

      <shopee />
    </b-tab>

  </b-tabs>
</template>

<script>
import { BTabs, BTab } from 'bootstrap-vue'
import MercadoLivre from './MercadoLivre.vue'
import Shopee from './Shopee.vue'
import router from '@/router'

export default {
  components: {
    BTabs,
    BTab,
    MercadoLivre,
    Shopee,
  },
  data() {
    return {
      tabIndex: parseInt(router.currentRoute.params.tabActived),
    }
  },
  created() {
  },
}
</script>
