<template>
  <b-card>
     <!-- SKU Tables -->
    <b-table
      responsive
      :items="items"
      striped
      small
    />
  </b-card>
</template>

<script>
import { BButton, BSidebar, VBToggle, BCardText, BCard } from "bootstrap-vue";
import Ripple from "vue-ripple-directive";
import { BTable } from "bootstrap-vue";
import api from "@api";
import Moment from "moment";

export default {
  components: {
    BCard,
    BButton,
    BSidebar,
    BCardText,
    BTable,
  },
  directives: {
    "b-toggle": VBToggle,
    Ripple,
  },
   data() {
    return {
      fields: [
        {
          label: "SKU",
          key: "sku",
          thClass: "text-center",
          tdClass: "text-center",
          sortable: true,
        },
        {
          label: "Custo Médio",
          key: "averageCost",
          thClass: "text-center",
          tdClass: "text-right",
          sortable: true,
        },
        {
          label: "Válido a partir",
          key: "startDate",
          thClass: "text-center",
          tdClass: "text-center",
          sortable: true,
          formatter: "formatDate",
        },
      ],
      items: [],
      showDismissibleErrorAlert: false,
    };
  },
  created() {
    const userData = JSON.parse(localStorage.getItem("userData"));
    api
      .get(
        `/orders/orders/getOrderByCompanyId/${userData.companyId}`
      )
      .then((response) => {
        this.items = response.data;
      })
      .catch((error) => {
        this.showDismissibleErrorAlert = true;
        console.log(error);
      });
  },
  methods: {
    formatDate(value) {
      if (value) {
        return Moment(String(value)).format("DD/MM/YYYY");
      } else {
        return value;
      }
    },
  },
};
</script>
