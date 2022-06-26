<template>
  <b-card>
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
    <!-- Sessing messages -->
    <b-alert
      v-model="showSessionExpiredAlert"
      variant="warning"
      dismissible
      class="mb-0"
    >
      <div class="alert-body">
        <feather-icon class="mr-25" icon="AlertTriangleIcon" />
        <span class="ml-25"
          >Sua sessão deve ter expirado, saia e entre novamente no sistema. Caso
          problema persistir, entre em contato!!</span
        >
      </div>
    </b-alert>
    <validation-observer ref="myInfoForm">
      <b-form @submit.prevent>
        <b-row>
          <!-- CNPJ -->
          <b-col md="3">
            <b-form-group label="CNPJ" label-for="myinfo-identificationNo">
              <b-form-input
                id="myinfo-identificationNo"
                v-model="myinfo.identificationNo"
                name="myinfo-identificationNo"
                plaintext
              />
            </b-form-group>
          </b-col>

          <!-- Tax Cost -->
          <b-col md="3">
            <b-form-group label="Imposto (%)" label-for="tax-cost">
              <validation-provider
                #default="{ errors }"
                name="tax-cost"
                rules="required"
              >
                <b-form-input
                  id="tax-cost"
                  type="number"
                  placeholder="Valor do imposto mensal (10.50)"
                  v-model="myinfo.taxCost"
                  max="100"
                />
                <small class="text-danger">{{ errors[0] }}</small>
              </validation-provider>
            </b-form-group>
          </b-col>

          <!-- Update ID -->
          <b-col md="3">
            <b-form-group
              label="Atualizado por (id)"
              label-for="myinfo-updateId"
            >
              <b-form-input
                id="myinfo-updateId"
                v-model="myinfo.updateId"
                name="myinfo-updateId"
                plaintext
              />
            </b-form-group>
          </b-col>
          <!-- Last Update -->
          <b-col md="3">
            <b-form-group
              label="Ultima Atualização em:"
              label-for="myinfo-updateDate"
            >
              <flat-pickr
                id="myinfo-updateDate"
                v-model="myinfo.updateDate"
                name="myinfo-updateDate"
                :config="{ enableTime: true, dateFormat: 'Y-m-d H:i:S' }"
                class="form-control"
                plaintext
              />
            </b-form-group>
          </b-col>

          <!-- Nome da Empresa -->
          <b-col md="6">
            <b-form-group label="Nome da Empresa" label-for="myinfo-name">
              <validation-provider
                #default="{ errors }"
                name="myinfo-name"
                rules="required"
              >
                <b-form-input
                  id="myinfo-name"
                  v-model="myinfo.name"
                  name="myinfo-name"
                  placeholder="Nome da sua Empresa"
                  :state="errors.length > 0 ? false : null"
                />
                <small class="text-danger">{{ errors[0] }}</small>
              </validation-provider>
            </b-form-group>
          </b-col>
          <!-- Nome Abreviado -->
          <b-col md="6">
            <b-form-group label="Nome Abreviado" label-for="myinfo-shortName">
              <validation-provider
                #default="{ errors }"
                name="myinfo-shortName"
                rules="required"
              >
                <b-form-input
                  id="myinfo-shortName"
                  v-model="myinfo.shortName"
                  name="myinfo-shortName"
                  placeholder="Nome Abreviado ou Fantasia"
                  :state="errors.length > 0 ? false : null"
                />
                <small class="text-danger">{{ errors[0] }}</small>
              </validation-provider>
            </b-form-group>
          </b-col>

          <!-- Address -->
          <b-col md="6">
            <b-form-group label="Endereço" label-for="myinfo-address">
              <validation-provider
                #default="{ errors }"
                name="myinfo-address"
                rules="required"
              >
                <b-form-input
                  id="myinfo-address"
                  v-model="myinfo.address"
                  name="myinfo--address"
                  placeholder="Endereço"
                  :state="errors.length > 0 ? false : null"
                />
                <small class="text-danger">{{ errors[0] }}</small>
              </validation-provider>
            </b-form-group>
          </b-col>
          <!-- Address 2 -->
          <b-col md="6">
            <b-form-group label="Complemento" label-for="myinfo-address2">
              <b-form-input
                id="myinfo-address2"
                v-model="myinfo.address2"
                name="myinfo--address2"
                placeholder="Complemento"
              />
            </b-form-group>
          </b-col>
          <!-- Zipcode -->
          <b-col md="6">
            <b-form-group label="CEP" label-for="myinfo-zipcode">
              <validation-provider
                #default="{ errors }"
                name="myinfo-zipcode"
                rules="required|integer"
              >
                <b-form-input
                  id="myinfo-zipcode"
                  v-model="myinfo.zipcode"
                  name="myinfo-zipcode"
                  placeholder="CEP (somente números)"
                  :state="errors.length > 0 ? false : null"
                />
                <small class="text-danger">{{ errors[0] }}</small>
              </validation-provider>
            </b-form-group>
          </b-col>
          <!-- State -->
          <b-col md="6">
            <b-form-group label="Estado" label-for="myinfo-state">
              <validation-provider
                #default="{ errors }"
                name="myinfo-state"
                rules="required"
              >
                <b-form-input
                  id="myinfo-state"
                  v-model="myinfo.state"
                  name="myinfo-state"
                  placeholder="UF"
                  :state="errors.length > 0 ? false : null"
                  :formatter="formatterUpperCase"
                />
                <small class="text-danger">{{ errors[0] }}</small>
              </validation-provider>
            </b-form-group>
          </b-col>
          <!-- Cidade -->
          <b-col md="6">
            <b-form-group label="Cidade" label-for="myinfo-city">
              <validation-provider
                #default="{ errors }"
                name="myinfo-city"
                rules="required"
              >
                <b-form-input
                  id="myinfo-city"
                  v-model="myinfo.city"
                  name="myinfo-city"
                  placeholder="Cidade"
                  :state="errors.length > 0 ? false : null"
                  :formatter="formatterUpperCase"
                />
                <small class="text-danger">{{ errors[0] }}</small>
              </validation-provider>
            </b-form-group>
          </b-col>
          <!-- Phone1 -->
          <b-col md="6">
            <b-form-group label="Telefone Principal" label-for="myinfo-phone1">
              <validation-provider
                #default="{ errors }"
                name="myinfo-phone1"
                rules="required|integer|min:11"
              >
                <b-form-input
                  id="myinfo-phone1"
                  v-model="myinfo.phone1"
                  name="myinfo-phone1"
                  placeholder="Celular/Whastapp (somente números com DDD)"
                  :state="errors.length > 0 ? false : null"
                />
                <small class="text-danger">{{ errors[0] }}</small>
              </validation-provider>
            </b-form-group>
          </b-col>
          <!-- Phone2 -->
          <b-col md="6">
            <b-form-group label="Outros Telefones" label-for="myinfo-phone2">
              <b-form-input
                id="myinfo-phone2"
                v-model="myinfo.phone2"
                name="myinfo-phone2"
                placeholder="Outros telefones (somente números com DDD)"
              />
            </b-form-group>
          </b-col>
          <!-- email -->
          <b-col md="6">
            <b-form-group label="Email" label-for="myinfo-email">
              <validation-provider
                #default="{ errors }"
                name="Email"
                rules="required|email"
              >
                <b-form-input
                  id="myinfo-email"
                  v-model="myinfo.email"
                  name="myinfo-email"
                  :state="errors.length > 0 ? false : null"
                  placeholder="john@example.com"
                />
                <small class="text-danger">{{ errors[0] }}</small>
              </validation-provider>
            </b-form-group>
          </b-col>
          <!-- Salvar-->
          <b-col>
            <b-button
              v-ripple.400="'rgba(255, 255, 255, 0.15)'"
              type="submit"
              variant="primary"
              class="mr-1"
              @click.prevent="validationForm"
            >
              Salvar
            </b-button>
          </b-col>
        </b-row>
      </b-form>
    </validation-observer>
  </b-card>
</template>

<script>
/* eslint-disable global-require */
import { ValidationProvider, ValidationObserver } from "vee-validate";
import VuexyLogo from "@core/layouts/components/Logo.vue";
import {
  BRow,
  BCol,
  BButton,
  BForm,
  BFormCheckbox,
  BFormGroup,
  BFormInput,
  BInputGroup,
  BInputGroupAppend,
  BAlert,
  BFormInvalidFeedback,
  BCard,
} from "bootstrap-vue";
import { required, email } from "@validations";
import ToastificationContent from "@core/components/toastification/ToastificationContent.vue";
import api from "@api";
import { heightFade } from "@core/directives/animations";
import Ripple from "vue-ripple-directive";
import flatPickr from "vue-flatpickr-component";

export default {
  components: {
    VuexyLogo,
    BRow,
    BCol,
    BButton,
    BForm,
    BFormCheckbox,
    BFormGroup,
    BFormInput,
    BInputGroup,
    BInputGroupAppend,
    // validations
    ValidationProvider,
    ValidationObserver,
    BAlert,
    BFormInvalidFeedback,
    BCard,
    flatPickr,
  },
  directives: {
    "height-fade": heightFade,
    Ripple,
  },
  data() {
    return {
      myinfo: {
        id: "",
        identificationNo: "",
        name: "",
        shortName: "",
        address: "",
        address2: "",
        zipcode: "",
        district: "",
        city: "",
        state: "",
        country: "",
        phone1: "",
        phone2: "",
        email: "",
        taxCost: "",
        updateDate: "",
        updateId: "",
        username: "",
      },
      // validation
      required,
      email,
      showDismissibleErrorAlert: false,
      showSessionExpiredAlert: false,
    };
  },
  created() {
    this.loadPageData();
  },
  methods: {
    formatterUpperCase(value) {
      return value.toUpperCase();
    },
    loadPageData() {
      const userData = JSON.parse(localStorage.getItem("userData"));
      api
        .get(
          `/organizational/getCompanyInfo/${userData.companyId}`
        )
        .then((response) => {
          if (response.status == 204) {
            this.showSessionExpiredAlert = true;
          } else {
            this.myinfo = response.data;
            this.myinfo.username = userData.username;
          }
        })
        .catch((error) => {
          console.log(error);
          console.log(error.response);
          this.showDismissibleErrorAlert = true;
        });
    },
    validationForm() {
      this.showDismissibleErrorAlert = false;
      this.showSessionExpiredAlert = false;
      this.$refs.myInfoForm.validate().then((success) => {
        if (success) {
          this.myinfo.updateId = this.myinfo.username;
          this.myinfo.updateDate = "";
          api
            .post(
              "/organizational/organizational/updateCompanyInfo",
              this.myinfo
            )
            .then((response) => {
              this.$toast({
                component: ToastificationContent,
                props: {
                  title: "Salvo com sucesso",
                  icon: "EditIcon",
                  variant: "success",
                },
              });
              this.loadPageData();
            })
            .catch((error) => {
              if (error.response.status == 400) {
                this.showSessionExpiredAlert = true;
              } else {
                this.showDismissibleErrorAlert = true;
              }
            });
        }
      });
    },
  },
};
</script>