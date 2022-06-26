<template>
  <div class="misc-wrapper">
    <b-link class="brand-logo">
      <vuexy-logo />
    </b-link>
    <b-overlay :show="showOverlay" rounded="sm">
      <div class="misc-inner p-2 p-sm-3">
        <div class="w-100 text-center">
          <h2 class="mb-1">Sua conta não foi adicionada 🔐</h2>
          <p class="mb-2">
            Algo deu errado na autenticação de sua conta, tente novamente. Caso
            problema persistir, entre em contato!!
          </p>
          <p style="color: red" class="mb-2">
            Código do Erro: {{ error_code }}
          </p>
          <p style="color: red" class="mb-2">
            Descrição do Erro: {{ error_description }}
          </p>
          <b-button
            variant="primary"
            class="mb-1 btn-sm-block"
            @click="backToMyMarketPlace"
            >Voltar</b-button
          >
          <br />
          <b-img fluid :src="imgUrl" alt="Erro na autenticação" />
        </div>
      </div>
    </b-overlay>
  </div>
</template>

<script>
/* eslint-disable global-require */
import VuexyLogo from "@core/layouts/components/Logo.vue";
import { BAlert, BRow, BCol, BButton, BCard, BLink, BImg } from "bootstrap-vue";
import ToastificationContent from "@core/components/toastification/ToastificationContent.vue";
import api from "@api";
import { heightFade } from "@core/directives/animations";
import Ripple from "vue-ripple-directive";
import store from "@/store/index";

export default {
  components: {
    VuexyLogo,
    BRow,
    BCol,
    BButton,
    BCard,
    BAlert,
    BLink,
    BImg,
    store,
  },
  directives: {
    "height-fade": heightFade,
    Ripple,
  },
  data() {
    return {
      error_code: "",
      error_description: "",
      codeSave: {
        authorizationCode: "",
        companyId: "",
        insertId: "",
      },
      downImg: require("@/assets/images/pages/not-authorized.svg"),
      showDismissibleErrorAlert: true,
      showSessionExpiredAlert: true,
      showOverlay: true,
    };
  },
  computed: {
    imgUrl() {
      if (store.state.appConfig.layout.skin === "dark") {
        // eslint-disable-next-line vue/no-side-effects-in-computed-properties
        this.downImg = require("@/assets/images/pages/not-authorized-dark.svg");
        return this.downImg;
      }
      return this.downImg;
    },
  },
  created() {
    this.error_code = this.$route.query.error;
    this.error_description = this.$route.query.error_description;
    const code = this.$route.query.code;
    if (code) {
      const userData = JSON.parse(localStorage.getItem("userData"));
      this.codeSave.authorizationCode = code;
      this.codeSave.companyId = userData.companyId;
      this.codeSave.insertId = userData.username;
      api
        .post(
          "/mktplace-integrator/createMktPlaceIntegrationConfigMercadoLivre",
          this.codeSave
        )
        .then((response) => {
          this.$router
            .push({ name: "my-marketplaces", params: { tabActived: 0 } })
            .then(() => {
              this.$toast({
                component: ToastificationContent,
                props: {
                  title: "Conta adicionada com sucesso",
                  icon: "UserPlusIcon",
                  variant: "success",
                },
              });
            });
        })
        .catch((error) => {
          if (error.response) {
            this.error_code = error.response.status;
          } else {
            this.error_code = error;
          }
          this.error_description = "Algo deu errado!! Já estamos trabalhando para resolver, tente novamente mais tarde";
          console.log(error);
        });
    }
    this.showOverlay = false;
  },
  methods: {
    backToMyMarketPlace() {
      this.$router.push({ name: "my-marketplaces", params: { tabActived: 0 } });
    },
  },
};
</script>