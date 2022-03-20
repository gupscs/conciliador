<template>
  <b-card title="Novo Custo Médio do Produto">
    <div>
       <!-- Global messages -->
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
            >Sua sessão deve ter expirado, saia e entre novamente no sistema.
            Caso problema persistir, entre em contato!!</span
          >
        </div>
      </b-alert>
      <validation-observer ref="itemForm">
        <b-form @submit.prevent>
          <b-row>
            <!-- SKU -->
            <b-col md="12">
              <b-form-group label="SKU" label-for="itemInfo-sku">
                <validation-provider
                  #default="{ errors }"
                  name="sku"
                  rules="required"
                >
                  <b-form-input
                    id="itemInfo-sku"
                    v-model="itemInfo.sku"
                    name="itemInfo-sku"
                    placeholder="Código do Item"
                    :state="errors.length > 0 ? false : null"
                  />
                  <small class="text-danger">{{ errors[0] }}</small>
                </validation-provider>
              </b-form-group>
            </b-col>
            <!-- Válido a partir de -->
            <b-col md="12">
              <b-form-group
                label="Válido a partir"
                label-for="itemInfo-startDate"
              >
                <validation-provider
                  #default="{ errors }"
                  name="startDate"
                  rules="required"
                >
                  <b-form-datepicker
                    id="itemInfo-startDate"
                    v-model="itemInfo.startDate"
                    name="itemInfo-startDate"
                    placeholder="Data de início do custo"
                    :state="errors.length > 0 ? false : null"
                    class="form-control"
                    :date-format-options="{
                      day: 'numeric',
                      month: 'numeric',
                      year: 'numeric',
                    }"
                  />
                  <small class="text-danger">{{ errors[0] }}</small>
                </validation-provider>
              </b-form-group>
            </b-col>
            <!-- Custo -->
            <b-col md="12">
              <b-form-group
                label="Custo Médio"
                label-for="itemInfo-averageCost"
              >
                <validation-provider
                  #default="{ errors }"
                  name="averageCost"
                  rules="required"
                >
                  <b-form-input
                    id="itemInfo-averageCost"
                    v-model="itemInfo.averageCost"
                    name="itemInfo-averageCost"
                    placeholder="Custo Médio (Ex: 123.99)"
                    :state="errors.length > 0 ? false : null"
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
    </div>
  </b-card>
</template>

<script>
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
  BFormDatepicker,
} from "bootstrap-vue";
import { required } from "@validations";
import ToastificationContent from "@core/components/toastification/ToastificationContent.vue";
import api from "@api";
import { heightFade } from "@core/directives/animations";
import Ripple from "vue-ripple-directive";
import flatPickr from "vue-flatpickr-component";
import { BAvatar, BListGroup, BListGroupItem, BCardText } from "bootstrap-vue";
import { ValidationProvider, ValidationObserver } from "vee-validate";

export default {
  components: {
    BAvatar,
    BListGroup,
    BListGroupItem,
    BCardText,
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
    BFormDatepicker,
    ToastificationContent,
    api,
    heightFade,
    // validations
    ValidationProvider,
    ValidationObserver,
    required,
    flatPickr,
  },
  directives: {
    "height-fade": heightFade,
    Ripple,
  },
  data() {
    return {
      itemInfo: {
        id: "",
        companyId: "",
        sku: "",
        startDate: "",
        averageCost: "",
        insertId: "",
      },
      showDismissibleErrorAlert: false,
      showSessionExpiredAlert: false,
    };
  },
  methods: {
    validationForm() {
      this.$refs.itemForm.validate().then((success) => {
        if (success) {
          const userData = JSON.parse(localStorage.getItem("userData"));
          this.itemInfo.insertId = userData.username;
          this.itemInfo.companyId = userData.companyId;
          api
            .post(
              "/organizational/organizational/saveItemAverageCost",
              this.itemInfo
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
              this.$root.$emit('bv::toggle::collapse', 'sidebar-right');
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
