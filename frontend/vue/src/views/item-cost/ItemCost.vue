<template>
  <b-card>
    <div class="custom-search d-flex justify-content-end">
      <b-button
        v-ripple.400="'rgba(255, 255, 255, 0.15)'"
        v-b-toggle.sidebar-right
        variant="primary"
      >
        <feather-icon icon="PlusIcon" class="mr-25" />
        <span>Adicionar Custo</span>
      </b-button>

      <b-sidebar ref="itemCostAddNewSidebar" id="sidebar-right" bg-variant="white" right backdrop shadow>
        <item-cost-add-new />
      </b-sidebar>
    </div>
    <br />
     <!-- SKU Tables -->
    <b-table
      responsive="sm"
      :fields="fields"
      :items="items"
      striped
      small
      bordered
    />
  </b-card>
</template>

<script>
import { BButton, BSidebar, VBToggle, BCardText, BCard } from "bootstrap-vue";
import Ripple from "vue-ripple-directive";
import ItemCostAddNew from "./ItemCostAddNew.vue";
import { BTable } from "bootstrap-vue";
import api from "@api";
import Moment from "moment";

export default {
  components: {
    BCard,
    BButton,
    BSidebar,
    ItemCostAddNew,
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
        `/organizational/getItemAverageCost/${userData.companyId}`
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
