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

      <b-sidebar id="sidebar-right" bg-variant="white" right backdrop shadow>
        <item-cost-add-new />
      </b-sidebar>
    </div>
  </b-card>
</template>

<script>
import { BButton, BSidebar, VBToggle, BCardText, BCard } from "bootstrap-vue";
import Ripple from "vue-ripple-directive";
import ItemCostAddNew from "./ItemCostAddNew.vue";

export default {
  components: {
    BCard,
    BButton,
    BSidebar,
    ItemCostAddNew,
    BCardText,
  },
  directives: {
    "b-toggle": VBToggle,
    Ripple,
  },
};
</script>
