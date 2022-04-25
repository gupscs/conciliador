<template>
  <div class="auth-wrapper auth-v2">
    <b-row class="auth-inner m-0">
      <!-- Brand logo-->
      <b-link class="brand-logo">
        <vuexy-logo />
        <h2 class="brand-text text-primary ml-1">Vuexy</h2>
      </b-link>
      <!-- /Brand logo-->

      <!-- Left Text-->
      <b-col lg="8" class="d-none d-lg-flex align-items-center p-5">
        <div
          class="w-100 d-lg-flex align-items-center justify-content-center px-5"
        >
          <b-img fluid :src="imgUrl" alt="Login V2" />
        </div>
      </b-col>
      <!-- /Left Text-->

      <!-- Login-->
      <b-col lg="4" class="d-flex align-items-center auth-bg px-2 p-lg-5">
        <b-col sm="8" md="6" lg="12" class="px-xl-2 mx-auto">
          <b-card-title title-tag="h2" class="font-weight-bold mb-1">
            Welcome to Vuexy! 👋
          </b-card-title>
          <b-card-text class="mb-2">
            Please sign-in to your account and start the adventure
          </b-card-text>
           <!-- global messages -->
          <b-alert
            v-model="showDismissibleErrorAlert"
            variant="danger"
            dismissible
            class="mb-0"
          >
          <div class="alert-body">
            <feather-icon class="mr-25" icon="FrownIcon" />
            <span class="ml-25">Algo deu errado!! Já estamos trabalhando para resolver, tente novamente mais tarde</span
            >
          </div>
          </b-alert>
<!-- Bad Credentials messages -->
            <b-alert
            v-model="showBadCredentialsAlert"
            v-height-fade.appear
            variant="warning"
            dismissible
            class="mb-0"
          >
          <div class="alert-body">
            <feather-icon class="mr-25" icon="AlertTriangleIcon" />
            <span class="ml-25">Usuário/Senha inválidos!! Verifique e tente novamente</span
            >
          </div>
          </b-alert>

          <!-- form -->
          <validation-observer ref="loginValidation">
            <b-form class="auth-login-form mt-2" @submit.prevent>
              <!-- email -->
              <b-form-group label="Email" label-for="login-email">
                <validation-provider
                  #default="{ errors }"
                  name="Email"
                  rules="required|email"
                >
                  <b-form-input
                    id="login-email"
                    v-model="userEmail"
                    :state="errors.length > 0 ? false : null"
                    name="login-email"
                    placeholder="john@example.com"
                  />
                  <small class="text-danger">{{ errors[0] }}</small>
                </validation-provider>
              </b-form-group>

              <!-- forgot password -->
              <b-form-group>
                <div class="d-flex justify-content-between">
                  <label for="login-password">Password</label>
                  <b-link :to="{ name: 'auth-forgot-password-v2' }">
                    <small>Forgot Password?</small>
                  </b-link>
                </div>
                <validation-provider
                  #default="{ errors }"
                  name="Password"
                  rules="required"
                >
                  <b-input-group
                    class="input-group-merge"
                    :class="errors.length > 0 ? 'is-invalid' : null"
                  >
                    <b-form-input
                      id="login-password"
                      v-model="password"
                      :state="errors.length > 0 ? false : null"
                      class="form-control-merge"
                      :type="passwordFieldType"
                      name="login-password"
                      placeholder="············"
                    />
                    <b-input-group-append is-text>
                      <feather-icon
                        class="cursor-pointer"
                        :icon="passwordToggleIcon"
                        @click="togglePasswordVisibility"
                      />
                    </b-input-group-append>
                  </b-input-group>
                  <small class="text-danger">{{ errors[0] }}</small>
                </validation-provider>
              </b-form-group>

              <!-- checkbox -->
              <b-form-group>
                <b-form-checkbox
                  id="remember-me"
                  v-model="status"
                  name="checkbox-1"
                >
                  Remember Me
                </b-form-checkbox>
              </b-form-group>

              <!-- submit buttons -->
              <b-button
                type="submit"
                variant="primary"
                block
                @click="validationForm"
              >
                Sign in
              </b-button>
            </b-form>
          </validation-observer>

          <b-card-text class="text-center mt-2">
            <span>New on our platform? </span>
            <b-link :to="{ name: 'register' }">
              <span>&nbsp;Create an account</span>
            </b-link>
          </b-card-text>
        </b-col>
      </b-col>
      <!-- /Login-->
    </b-row>
  </div>
</template>

<script>
/* eslint-disable global-require */
/* eslint-disable global-require */
import { ValidationProvider, ValidationObserver } from "vee-validate";
import VuexyLogo from "@core/layouts/components/Logo.vue";
import {
  BRow,
  BCol,
  BLink,
  BFormGroup,
  BFormInput,
  BInputGroupAppend,
  BInputGroup,
  BFormCheckbox,
  BCardText,
  BCardTitle,
  BImg,
  BForm,
  BButton,
  BAlert,
  VBTooltip,
} from "bootstrap-vue";
import { required, email } from "@validations";
import { togglePasswordVisibility } from "@core/mixins/ui/forms";
import store from "@/store/index";
import { getHomeRouteForLoggedInUser } from "@/auth/utils";
import ToastificationContent from "@core/components/toastification/ToastificationContent.vue";
import api from "@api";
import qs from "qs";
import useJwt from "@/auth/jwt/useJwt";
import { heightFade } from '@core/directives/animations';

export default {
  components: {
    BRow,
    BCol,
    BLink,
    BFormGroup,
    BFormInput,
    BInputGroupAppend,
    BInputGroup,
    BFormCheckbox,
    BCardText,
    BCardTitle,
    BImg,
    BForm,
    BButton,
    VuexyLogo,
    ValidationProvider,
    ValidationObserver,
    BAlert,
  },
   directives: {
    'height-fade': heightFade,
  },
  mixins: [togglePasswordVisibility],
  data() {
    return {
      status: "",
      password: "",
      userEmail: "",
      sideImg: require("@/assets/images/pages/login-v2.svg"),
      // validation rulesimport store from '@/store/index'
      required,
      email,
      showDismissibleErrorAlert: false,
      showBadCredentialsAlert: false,
    };
  },
  
  computed: {
    passwordToggleIcon() {
      return this.passwordFieldType === "password" ? "EyeIcon" : "EyeOffIcon";
    },
    imgUrl() {
      if (store.state.appConfig.layout.skin === "dark") {
        // eslint-disable-next-line vue/no-side-effects-in-computed-properties
        this.sideImg = require("@/assets/images/pages/login-v2-dark.svg");
        return this.sideImg;
      }
      return this.sideImg;
    },
  },
  methods: {
    validationForm() {
      this.showDismissibleErrorAlert= false;
      this.showBadCredentialsAlert= false;
      this.$refs.loginValidation.validate().then((success) => {
        if (success) {
          useJwt.setToken("");
          var data = qs.stringify({
            username: this.userEmail,
            password: this.password,
            grant_type: "password",
          });
          var config = {
            method: "post",
            url: "/oauth/oauth/token",
            headers: {
              Authorization: "Basic bXlhcHA6MTIz",
              "Content-Type": "application/x-www-form-urlencoded",
            },
            data: data,
          };
          api(config)
            .then((response) => {
              useJwt.setToken(response.data.access_token);
              api
                .get(
                  `/sysadmin/sysadmin/findUserFrontModelByUsername/${this.userEmail}`
                )
                .then((response) => {
                  const userData = response.data;
                  localStorage.setItem("userData", JSON.stringify(userData));
                  localStorage.setItem("companyId", userData.companyId);
                  this.$ability.update(userData.ability)
                  this.$router.replace(getHomeRouteForLoggedInUser(userData.role))
                    .then(() => {
                      this.$toast({
                        component: ToastificationContent,
                        position: "top-right",
                        props: {
                          title: `Bem vindo ${
                            userData.fullName || userData.username
                          }`,
                          icon: "CoffeeIcon",
                          variant: "success",
                          text: `Você está logado, agora acompanhe seus lucros de perto!`,
                        },
                      });
                    });
                });
            })
            .catch((error) => {
              useJwt.setToken("");
              console.log(error.response);
              if(error.response.data.error == "invalid_grant"){
                this.showBadCredentialsAlert = true;
              }else{
                this.showDismissibleErrorAlert = true;
              }
            });
        }
      });
    },
  },
};
</script>

<style lang="scss">
@import "@core/scss/vue/pages/page-auth.scss";
</style>
