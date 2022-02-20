<template>
  <div class="auth-wrapper auth-v2">
    <b-row class="auth-inner m-0">
      <!-- Brand logo-->
      <b-link class="brand-logo">
        <vuexy-logo />

        <h2 class="brand-text text-primary ml-1">
          Vuexy
        </h2>
      </b-link>
      <!-- /Brand logo-->

      <!-- Left Text-->
      <b-col
        lg="8"
        class="d-none d-lg-flex align-items-center p-5"
      >
        <div
          class="w-100 d-lg-flex align-items-center justify-content-center px-5"
        >
          <b-img
            fluid
            :src="imgUrl"
            alt="Register V2"
          />
        </div>
      </b-col>
      <!-- /Left Text-->

      <!-- Register-->
      <b-col
        lg="4"
        class="d-flex align-items-center auth-bg px-2 p-lg-5"
      >
        <b-col
          sm="8"
          md="6"
          lg="12"
          class="px-xl-2 mx-auto"
        >
          <b-card-title
            title-tag="h2"
            class="font-weight-bold mb-1"
          >
            Adventure starts here 🚀
          </b-card-title>
          <b-card-text class="mb-2">
            Make your app management easy and fun!
          </b-card-text>
          <!-- global messages -->
          <b-alert
            v-model="showDismissibleErrorAlert"
            v-height-fade.appear
            variant="danger"
            dismissible
            class="mb-0"
          >
            <div class="alert-body">
              <feather-icon
                class="mr-25"
                icon="FrownIcon"
              />
              <span
                class="ml-25"
              >Algo deu errado!! Já estamos trabalhando para resolver, tente
                novamente mais tarde</span>
            </div>
          </b-alert>

          <!-- form -->
          <validation-observer ref="registerForm">
            <b-form class="auth-register-form mt-2">
              <!-- CNPJ -->

              <b-form-group
                label="CNPJ"
                label-for="register-identificationNo"
              >
                <validation-provider
                  #default="{ errors }"
                  name="cnpj"
                  rules="required|integer|digits:14"
                >
                  <b-form-input
                    id="register-identificationNo"
                    v-model="register.identificationNo"
                    name="register-identificationNo"
                    placeholder="12.345.678/9012-34 (somente números)"
                    :state="
                      errors.length > 0 || !validationExistIdentificationNo
                        ? false
                        : null
                    "
                  />
                  <small class="text-danger">{{ errors[0] }}</small>
                </validation-provider>
                <b-form-invalid-feedback
                  :state="validationExistIdentificationNo"
                >
                  CNPJ já cadastrado
                </b-form-invalid-feedback>
              </b-form-group>
              <!-- Nome da Empresa -->
              <b-form-group
                label="Nome da Empresa"
                label-for="register-company-name"
              >
                <validation-provider
                  #default="{ errors }"
                  name="register-company-name"
                  rules="required"
                >
                  <b-form-input
                    id="register-companyName"
                    v-model="register.name"
                    name="register-company-name"
                    placeholder="Nome da sua Empresa"
                    :state="errors.length > 0 ? false : null"
                  />
                  <small class="text-danger">{{ errors[0] }}</small>
                </validation-provider>
              </b-form-group>
              <!-- Telefone -->
              <b-form-group
                label="Celular"
                label-for="register-phone1"
              >
                <validation-provider
                  #default="{ errors }"
                  name="register-phone1"
                  rules="required|min:11|integer"
                >
                  <b-form-input
                    id="register-phone1"
                    v-model="register.phone1"
                    name="register-phone1"
                    placeholder="Celular/Whatsapp (somente números)"
                    :state="errors.length > 0 ? false : null"
                  />
                  <small class="text-danger">{{ errors[0] }}</small>
                </validation-provider>
              </b-form-group>
              <!-- Nome do Usuário -->
              <b-form-group
                label="Nome do Usuário"
                label-for="register-name"
              >
                <validation-provider
                  #default="{ errors }"
                  name="register-name"
                  rules="required"
                >
                  <b-form-input
                    id="register-name"
                    v-model="register.userName"
                    name="register-name"
                    placeholder="Seu nome"
                    :state="errors.length > 0 ? false : null"
                  />
                  <small class="text-danger">{{ errors[0] }}</small>
                </validation-provider>
              </b-form-group>

              <!-- email -->
              <b-form-group
                label="Email"
                label-for="register-email"
              >
                <validation-provider
                  #default="{ errors }"
                  name="Email"
                  rules="required|email"
                >
                  <b-form-input
                    id="register-email"
                    v-model="register.email"
                    name="register-email"
                    :state="
                      errors.length > 0 || !validationExistUsername
                        ? false
                        : null
                    "
                    placeholder="john@example.com"
                  />
                  <small class="text-danger">{{ errors[0] }}</small>
                </validation-provider>
                <b-form-invalid-feedback :state="validationExistUsername">
                  Email já cadastrado
                </b-form-invalid-feedback>
              </b-form-group>
              <!-- password -->
              <b-form-group
                label-for="register-password"
                label="Password"
              >
                <validation-provider
                  #default="{ errors }"
                  name="Password"
                  vid="Password"
                  rules="required|password"
                >
                  <b-input-group
                    class="input-group-merge"
                    :class="errors.length > 0 ? 'is-invalid' : null"
                  >
                    <b-form-input
                      id="register-password"
                      v-model="register.password"
                      class="form-control-merge"
                      :type="passwordFieldType"
                      :state="errors.length > 0 ? false : null"
                      name="register-password"
                      placeholder="············"
                    />
                    <b-input-group-append is-text>
                      <feather-icon
                        :icon="passwordToggleIcon"
                        class="cursor-pointer"
                        @click="togglePasswordVisibility"
                      />
                    </b-input-group-append>
                  </b-input-group>
                  <small class="text-danger">{{ errors[0] }}</small>
                </validation-provider>
              </b-form-group>
              <!-- password confirm -->
              <b-form-group
                label-for="register-confimed-password"
                label="Confirm Password"
              >
                <validation-provider
                  #default="{ errors }"
                  name="Confirm Password"
                  rules="required|confirmed:Password"
                >
                  <b-input-group
                    class="input-group-merge"
                    :class="errors.length > 0 ? 'is-invalid' : null"
                  >
                    <b-form-input
                      id="register-confirm-password"
                      v-model="register.confirmedPassword"
                      class="form-control-merge"
                      :type="passwordFieldType"
                      :state="errors.length > 0 ? false : null"
                      name="register-confirm-password"
                      placeholder="············"
                    />
                    <b-input-group-append is-text>
                      <feather-icon
                        :icon="passwordToggleIcon"
                        class="cursor-pointer"
                        @click="togglePasswordVisibility"
                      />
                    </b-input-group-append>
                  </b-input-group>
                  <small class="text-danger">{{ errors[0] }}</small>
                </validation-provider>
              </b-form-group>

              <b-form-group>
                <validation-provider
                  #default="{ errors }"
                  name="termos"
                  rules="required"
                >
                  <b-form-checkbox
                    id="register-privacy-policy"
                    v-model="register.terms"
                    name="checkbox-1"
                  >
                    Aceito os
                    <b-link
                      v-ripple.400="'rgba(113, 102, 240, 0.15)'"
                      v-b-modal.modal-scrollable
                      variant="outline-primary"
                    >
                      Termos & Condições
                    </b-link>
                  </b-form-checkbox>
                  <small class="text-danger">{{ errors[0] }}</small>
                </validation-provider>
              </b-form-group>

              <b-button
                variant="primary"
                block
                type="submit"
                @click.prevent="validationForm"
              >
                Sign up
              </b-button>
            </b-form>
          </validation-observer>

          <p class="text-center mt-2">
            <span>Already have an account?</span>
            <b-link :to="{ name: 'login' }">
              <span>&nbsp;Sign in instead</span>
            </b-link>
          </p>
        </b-col>
      </b-col>
      <!-- /Register-->
    </b-row>
    <!-- modal Scrolling Content inside Modal-->
    <b-modal
      id="modal-scrollable"
      scrollable
      title="Termos & Condições"
      ok-only
      ok-title="OK"
      cancel-variant="outline-secondary"
    >
      <b-card-text
        v-for="(content, index) in scrollContent"
        :key="index"
      >
        {{ content }}
      </b-card-text>
    </b-modal>
  </div>
</template>

<script>
/* eslint-disable global-require */
import { ValidationProvider, ValidationObserver } from 'vee-validate'
import VuexyLogo from '@core/layouts/components/Logo.vue'
import {
  BRow,
  BCol,
  BLink,
  BButton,
  BForm,
  BFormCheckbox,
  BFormGroup,
  BFormInput,
  BInputGroup,
  BInputGroupAppend,
  BImg,
  BCardTitle,
  BCardText,
  BAlert,
  BFormInvalidFeedback,
} from 'bootstrap-vue'
import {
  required, email, password, confirmed,
} from '@validations'
import { togglePasswordVisibility } from '@core/mixins/ui/forms'
import ToastificationContent from '@core/components/toastification/ToastificationContent.vue'
import api from '@api'
import { heightFade } from '@core/directives/animations'
import Ripple from 'vue-ripple-directive'
import useJwt from '@/auth/jwt/useJwt'
import store from '@/store/index'

export default {
  components: {
    VuexyLogo,
    BRow,
    BImg,
    BCol,
    BLink,
    BButton,
    BForm,
    BCardText,
    BCardTitle,
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
  },
  directives: {
    'height-fade': heightFade,
    Ripple,
  },
  mixins: [togglePasswordVisibility],
  data() {
    return {
      register: {
        userName: '',
        email: '',
        password: '',
        confirmedPassword: '',
        identificationNo: '',
        name: '',
        phone1: '',
        terms: '',
      },
      checkRegister: {
        existUsername: false,
        existIdentificationNo: false,
      },

      sideImg: require('@/assets/images/pages/register-v2.svg'),
      // validation
      required,
      email,
      password,
      confirmed,
      scrollContent: [
        'implementar os termos e condições puxando de um serviço',
      ],
      showDismissibleErrorAlert: false,
    }
  },
  computed: {
    passwordToggleIcon() {
      return this.passwordFieldType === 'password' ? 'EyeIcon' : 'EyeOffIcon'
    },
    imgUrl() {
      if (store.state.appConfig.layout.skin === 'dark') {
        // eslint-disable-next-line vue/no-side-effects-in-computed-properties
        this.sideImg = require('@/assets/images/pages/register-v2-dark.svg')
        return this.sideImg
      }
      return this.sideImg
    },
    validationExistIdentificationNo() {
      return !this.checkRegister.existIdentificationNo
    },
    validationExistUsername() {
      return !this.checkRegister.existUsername
    },
  },
  methods: {
    validationForm() {
      this.showDismissibleErrorAlert = false
      this.$refs.registerForm.validate().then(success => {
        if (success) {
          // TODO: VALIDAR O CNPJ CORRETAMENTE E TAMBÉM CPF
          api
            .get(
              `/organizational/organizational/registerCheck/${this.register.identificationNo}/${this.register.email}`,
            )
            .then(response => {
              this.checkRegister = response.data
              console.log(response.data)
              if (
                !this.checkRegister.existUsername
                && !this.checkRegister.existIdentificationNo
              ) {
                useJwt.setToken('')
                api
                  .post(
                    '/organizational/organizational/register',
                    this.register,
                  )
                  .then(response => {
                    this.$router.replace('/login').then(() => {
                      this.$toast({
                        component: ToastificationContent,
                        props: {
                          title: 'Registardo com Sucesso',
                          icon: 'EditIcon',
                          variant: 'success',
                        },
                      })
                    })
                  })
                  .catch(error => {
                    console.log(error)
                    this.showDismissibleErrorAlert = true
                  })
              }
            })
            .catch(error => {
              console.log(error)
              this.showDismissibleErrorAlert = true
            })
        }
      })
    },
  },
}
/* eslint-disable global-require */
</script>

<style lang="scss">
@import "@core/scss/vue/pages/page-auth.scss";
</style>
