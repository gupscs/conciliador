<template>
  <b-card title="Custos">
    <div>
      <div class="custom-search d-flex justify-content-end">
        <b-button
          v-ripple.400="'rgba(255, 255, 255, 0.15)'"
          variant="primary"
          @click="repeateAgain"
        >
          <feather-icon icon="PlusIcon" class="mr-25" />
          <span>Adicionar Custo</span>
        </b-button>
      </div>
      <b-form
        ref="form"
        :style="{ height: trHeight }"
        class="repeater-form"
        @submit.prevent="repeateAgain"
      >
        <!-- Row Loop -->
        <b-row
          v-for="(item, index) in items"
          :id="item.id"
          :key="item.id"
          ref="row"
        >
          <b-col cols="12">
            <hr />
          </b-col>
          <!-- Cost Name -->
          <b-col md="6">
            <b-form-group label="Custo" label-for="item-costName">
              <b-form-input
                id="item-costName"
                type="text"
                placeholder="Nome do Custo"
                :formatter="formatterUpperCase"
                v-model="item.costName"
              />
            </b-form-group>
          </b-col>

          <!-- Cost -->
          <b-col md="4">
            <b-form-group label="Valor" label-for="item-cost">
              <b-form-input
                id="item-cost"
                type="number"
                placeholder="Valor do custo mensal (155.38)"
                v-model="item.cost"
              />
            </b-form-group>
          </b-col>

          <!-- Remove Button -->
          <b-col lg="2" md="2" class="mb-50 justify-content-end">
            <b-button
              v-ripple.400="'rgba(234, 84, 85, 0.15)'"
              variant="outline-primary"
              class="mt-0 mt-md-2"
              @click="saveItem(index)"
            >
              <feather-icon icon="XIcon" class="mr-25" />
              <span>Salvar</span>
            </b-button>
            &nbsp
            <b-button
              v-ripple.400="'rgba(234, 84, 85, 0.15)'"
              variant="outline-danger"
              class="mt-0 mt-md-2"
              @click="removeItem(index)"
            >
              <feather-icon icon="XIcon" class="mr-25" />
              <span>Delete</span>
            </b-button>
          </b-col>
        </b-row>
      </b-form>
    </div>
    <template #code>
      {{ codeBasic }}
    </template>
  </b-card>
</template>

<script>
import {
  BForm,
  BFormGroup,
  BFormInput,
  BRow,
  BCol,
  BButton,
  BCard,
} from "bootstrap-vue";
import { heightTransition } from "@core/mixins/ui/transition";
import Ripple from "vue-ripple-directive";
import { codeBasic } from "./code";

export default {
  components: {
    BForm,
    BRow,
    BCol,
    BButton,
    BFormGroup,
    BFormInput,
    BCard,
  },
  directives: {
    Ripple,
  },
  mixins: [heightTransition],
  data() {
    return {
      items: [
        {
          id: 1,
          selected: "male",
          selected1: "designer",
          prevHeight: 0,
        },
      ],
      nextTodoId: 2,
      codeBasic,
    };
  },
  mounted() {
    this.initTrHeight();
  },
  created() {
    window.addEventListener("resize", this.initTrHeight);
  },
  destroyed() {
    window.removeEventListener("resize", this.initTrHeight);
  },
  methods: {
    formatterUpperCase(value) {
      return value.toUpperCase()
    },
    repeateAgain() {
      this.items.push({
        id: (this.nextTodoId += this.nextTodoId),
        costName: "",
        cost: null,
      });

      this.$nextTick(() => {
        this.trAddHeight(this.$refs.row[0].offsetHeight);
      });
    },
    removeItem(index) {
      this.$bvModal
        .msgBoxConfirm('O Custo será deleta, Ok?', {
          cancelVariant: 'outline-secondary',
          centered: true,
          title: 'Por favor, confirmar',
        })
        .then(value => {
          if(value){
             this.items.splice(index, 1);
            this.trTrimHeight(this.$refs.row[0].offsetHeight);
          }
        })
      
      
     
    },
    saveItem(index) {
      console.log(this.items[index]);
    },
    initTrHeight() {
      this.trSetHeight(null);
      this.$nextTick(() => {
        this.trSetHeight(this.$refs.form.scrollHeight);
      });
    },
  },
};
</script>

<style lang="scss" scoped>
.repeater-form {
  overflow: hidden;
  transition: 0.35s height;
}
</style>
