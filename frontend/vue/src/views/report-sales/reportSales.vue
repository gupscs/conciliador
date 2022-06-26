<template>
  <b-card>
     <!-- SKU Tables -->
    <b-table
      responsive
      :fields="fields"
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
        { label: "ID", key: "id",sortable: true,  thClass: "text-center", tdClass: "text-center"},
        { label: "ID DA EMPRES", key: "companyId",sortable: true,  thClass: "text-center", tdClass: "text-center"},
        { label: "MKT PLACE", key: "mktPlace",sortable: true,  thClass: "text-center", tdClass: "text-center"},
        { label: "PEDIDO", key: "orderId",sortable: true,  thClass: "text-center", tdClass: "text-center"},
        { label: "DATA DE FECHAMENTO", key: "closedDate",sortable: true,formatter: "formatDate",  thClass: "text-center", tdClass: "text-center"},
        { label: "DATA DE CRIAÇÃO", key: "createdDate",sortable: true,formatter: "formatDate",  thClass: "text-center", tdClass: "text-center"},
        { label: "DATA DE EXPIRAÇÃO", key: "expirationDate",sortable: true,formatter: "formatDate",  thClass: "text-center", tdClass: "text-center"},
        { label: "ULTIMA ATUALIZAÇÃO", key: "lastUpdateDate",sortable: true,formatter: "formatDate",  thClass: "text-center", tdClass: "text-center"},
        { label: "STATUS DO PEDIDO", key: "orderStatus",sortable: true,  thClass: "text-center", tdClass: "text-center"},
        { label: "TIPO COMISSÃO DO MKT PLACE", key: "mktPlaceFeeType",sortable: true,  thClass: "text-center", tdClass: "text-center"},
        { label: "STATUS DO PEDIDO NO MKT PLACE", key: "mktPlaceStatus",sortable: true,  thClass: "text-center", tdClass: "text-center"},
        { label: "MOEDA", key: "currency",sortable: true,  thClass: "text-center", tdClass: "text-center"},
        { label: "VALOR TOTAL", key: " totalAmount",sortable: true,  thClass: "text-center", tdClass: "text-right"},
        { label: "CUSTO DO FRETE", key: "shippingCost",sortable: true,  thClass: "text-center", tdClass: "text-right"},
        { label: "ID DO FRETE", key: "shippingMethodId",sortable: true,  thClass: "text-center", tdClass: "text-center"},
        { label: "CEP DO RECEBEDOR", key: "receiverZipcode",sortable: true,  thClass: "text-center", tdClass: "text-center"},
        { label: "COMISSÃO DO MKT PLACE", key: "mktPlaceFeeCost",sortable: true,  thClass: "text-center", tdClass: "text-right"},
        { label: "ID DO CALCULO DOS CUSTOS", key: "orderCostCalculated.orderCostCalcuationId",sortable: true,  thClass: "text-center", tdClass: "text-center"},
        { label: "CALCULO DA COMISSÃO DO MKT PLACE", key: "orderCostCalculated.mktPlaceFee",sortable: true,  thClass: "text-center", tdClass: "text-right"},
        { label: "CALCULO DO CUSTO DO PRODUTO", key: "orderCostCalculated.itemTotalCost",sortable: true,  thClass: "text-center", tdClass: "text-right"},
        { label: "CALCULO IMPOSTO", key: "orderCostCalculated.tax",sortable: true,  thClass: "text-center", tdClass: "text-right"},
        { label: "CALCULO CUSTOS FIXOS", key: "orderCostCalculated.fixedTotalCost",sortable: true,  thClass: "text-center", tdClass: "text-right"},
        { label: "CALCULO DO FRETE", key: "orderCostCalculated.shippingCost",sortable: true,  thClass: "text-center", tdClass: "text-right"},
        { label: "CALCULO DO FRETE DE RETORNO", key: "orderCostCalculated.shippingReturnCost",sortable: true,  thClass: "text-center", tdClass: "text-right"},
        { label: "DATA DE CRIAÇÃO", key: "insertDate",sortable: true,formatter: "formatDate",  thClass: "text-center", tdClass: "text-center"},
        { label: "CRIADO POR", key: "insertId",sortable: true,  thClass: "text-center", tdClass: "text-center"},
        { label: "DATA DE ATUALIZAÇÃO", key: "updateDate",sortable: true,formatter: "formatDate",  thClass: "text-center", tdClass: "text-center"},
        { label: "ATUALIZADO POR", key: "updateId",sortable: true,  thClass: "text-center", tdClass: "text-center"},
      ],
      items: [],
      showDismissibleErrorAlert: false,
    };
  },
  created() {
    const userData = JSON.parse(localStorage.getItem("userData"));
    api
      .get(
        `/orders/getOrderByCompanyId/${userData.companyId}`
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
        return Moment(String(value)).format("DD/MM/YYYY hh:mm");
      } else {
        return value;
      }
    },
  },
};
</script>
