<template>
  <b-card>
    <!-- global messages -->
    <b-alert
      v-model="showDismissibleErrorAlert"
      v-height-fade.appear
      variant="info"
      dismissible
      class="mb-0"
    >
      <div class="alert-body">
        <feather-icon class="mr-25" icon="SunriseIcon" />
        <span class="ml-25"
          >Implementando</span
        >
      </div>
    </b-alert>
    <!-- Sessing messages -->
    <b-alert
      v-model="showSessionExpiredAlert"
      v-height-fade.appear
      variant="warning"
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
      showDismissibleErrorAlert: true,
    };
  },
  created(){
    console.log(this.$route.query.code);
    console.log(this.$route.query.error);
    console.log(this.$route.query.error_description);
  },
  methods: {

  },
};
</script>