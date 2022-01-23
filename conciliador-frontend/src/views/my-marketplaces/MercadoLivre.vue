<template>
  <b-card title="Contas Adicionadas do Mercado Livre"> 
    <!-- global messages -->
    <b-alert
      v-model="showDismissibleErrorAlert"
      variant="danger"
      dismissible
      class="mb-0"
    >
      <div class="alert-body">
        <feather-icon class="mr-25" icon="FrownIcon" />
        <span class="ml-25"
          >Algo deu errado!! Já estamos trabalhando para resolver, tente
          novamente mais tarde</span
        >
      </div>
    </b-alert>

    <div class="custom-search d-flex justify-content-end">
      <!-- Add new account button -->
      <b-button
        v-ripple.400="'rgba(113, 102, 240, 0.15)'"
        variant="outline-primary"
        @click="confirmText"
      >
        Adicionar Nova Conta
      </b-button>
    </div>
    <!-- Users Tables -->
    <vue-good-table :columns="columns" :rows="rows" :line-numbers="true" 
    styleClass="vgt-table condensed striped !important" />
  </b-card>
</template>

<script>
/* eslint-disable global-require */
import {
  BRow,
  BCol,
  BButton,
  BAlert,
  BFormInvalidFeedback,
  BCard,
} from "bootstrap-vue";
import ToastificationContent from "@core/components/toastification/ToastificationContent.vue";
import api from "@api";
import { VueGoodTable } from "vue-good-table";
import Ripple from "vue-ripple-directive";


export default {
  components: {
    BRow,
    BCol,
    BButton,
    BAlert,
    BFormInvalidFeedback,
    BCard,
    VueGoodTable,
  },
  directives: {
    Ripple,
  },
  data() {
    return {
      columns: [
        {
          label: "Usuário",
          field: "mktPlaceUsername",
        },
        {
          label: "Id",
          field: "mktPlaceUserId",
        },
        {
          label: "Inserido Por",
          field: "insertId",
        },
        {
          label: "Inserido Em",
          field: "insertDate",
        },
        {
          label: "Atualizado Por",
          field: "updateId",
        },
        {
          label: "Atualizado Em",
          field: "updateDate",
        },
        {
          label: "Ativo/Desativado",
          field: "enable",
        },
      ],
      rows: [],
      showDismissibleErrorAlert: false,
    };
  },
  created() {
    //console.log(this.$route.query.test);
    const userData = JSON.parse(localStorage.getItem("userData"));
    api
      .get(
        `/mktplace-integrator/mktplace-integrator/getMktPlaceIntegrationConfigUsers/${userData.companyId}/MERCADO_LIVRE`
      )
      .then((response) => {
        console.log(response);
        this.rows = response.data;
      })
      .catch((error) => {
        this.showDismissibleErrorAlert = true;
        console.log(error);
      });
  },
  methods: {
    confirmText() {
      this.$swal({
        title: "Confirme para continuar",
        text: "Você será redirecionado para o site do Mercado Livre para autenticação da conta.",
        icon: "warning",
        showCancelButton: true,
        confirmButtonText: "Sim, pode me redirecionar",
        customClass: {
          confirmButton: "btn btn-primary",
          cancelButton: "btn btn-outline-danger ml-1",
        },
        buttonsStyling: false,
      }).then((result) => {
        if (result.value) {
          /*
          //ler os parametros da aplicacao id (criar novo webservice)
          //concatenar os parametros na url do mercado livre
          */
          window.location.href = "https://google.com/contact";
        }
      });
    },
  },
};
</script>