<template>
  <b-card>
    <b-tabs content-class="pt-1" fill>
      <b-tab active title="Contas">
        <mercado-livre-account />
      </b-tab>
      <b-tab title="Commissões">
        <mercado-livre-fee />
      </b-tab>
    </b-tabs>
  </b-card>
</template>

<script>
import { BTabs, BTab, BCard } from "bootstrap-vue";
import MercadoLivreAccount from "./MercadoLivreAccount.vue";
import MercadoLivreFee from "./MercadoLivreFee.vue";
import router from "@/router";

export default {
  components: {
    BTabs,
    BTab,
    BCard,
    MercadoLivreAccount,
    MercadoLivreFee,
  },
  data() {},
  created() {},
};
</script>