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
    <br />
    <div>
      <div class="custom-search d-flex justify-content-end">
        <b-button
          v-ripple.400="'rgba(255, 255, 255, 0.15)'"
          variant="outline-primary"
          @click="repeateAgain"
        >
          <feather-icon icon="PlusIcon" class="mr-25" />
          <span>Adicionar Comissão</span>
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
          :itemId="item.itemId"
          :key="item.itemId"
          ref="row"
        >
          <b-col cols="12">
            <hr />
          </b-col>
          <!-- Tipo da Comissão-->
          <b-col md="5">
            <b-form-group label="Tipo de Comissão" label-for="item-feeType">
              <b-form-input
                id="item-feeType"
                type="text"
                placeholder="Tipo da comissão do ML"
                :formatter="formatterUpperCase"
                v-model="item.feeType"
              />
            </b-form-group>
          </b-col>

          <!-- Custo -->
          <b-col md="4">
            <b-form-group label="Valor" label-for="item-fee">
              <b-form-input
                id="item-fee"
                type="number"
                placeholder="Valor da comissão mensal (155.38)"
                v-model="item.fee"
              />
            </b-form-group>
          </b-col>

          <!-- Button -->
          <b-col md="3">  
              <b-button
                v-ripple.400="'rgba(234, 84, 85, 0.15)'"
                variant="outline-primary"
                class="mt-0 mt-md-2" style="margin-right: 10px"
                @click="saveItem(index)"
              >
                <feather-icon icon="XIcon" class="mr-25" />
                <span>Salvar</span>
              </b-button>
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
  BAlert,
} from "bootstrap-vue";
import { heightTransition } from "@core/mixins/ui/transition";
import Ripple from "vue-ripple-directive";
import api from "@api";
import ToastificationContent from "@core/components/toastification/ToastificationContent.vue";

export default {
  components: {
    BForm,
    BRow,
    BCol,
    BButton,
    BFormGroup,
    BFormInput,
    BCard,
    BAlert,
  },
  directives: {
    Ripple,
  },
  mixins: [heightTransition],
  data() {
    return {
      items: [],
      nextTodoId: 2,
      showDismissibleErrorAlert: false,
      username: "",
      companyId: "",
    };
  },
  mounted() {
    this.initTrHeight();
  },
  created() {
    window.addEventListener("resize", this.initTrHeight);
    const userData = JSON.parse(localStorage.getItem("userData"));
    this.username = userData.username;
    this.companyId = userData.companyId;
    api
      .get(
        `/organizational/organizational/getMktPlaceFee/${userData.companyId}/MERCADO_LIVRE`
      )
      .then((response) => {
        const listGetItem = response.data;
        if (listGetItem) {
          var count = 1;
          for (let item of response.data) {
            this.items.push({
              itmeId: count,
              id: item.id,
              feeType: item.feeType,
              fee: item.fee,
              marketPlace: item.marketPlace,
              insertId: item.insertId,
              insertDate: item.insertDate,
              updateId: this.username,
              updateDate: item.updateDate,
              companyId: item.companyId,
            });
            this.$nextTick(() => {
              this.trAddHeight(this.$refs.row[0].offsetHeight);
            });
            count++;
          }
        } else {
          this.items.push({
            itmeId: 1,
            id: null,
            feeType: "",
            fee: null,
            marketPlace: "MERCADO_LIVRE",
            insertId: this.username,
            companyId: this.companyId,
          });
        }
      })
      .catch((error) => {
        this.showDismissibleErrorAlert = true;
        console.error(error);
      });
  },
  destroyed() {
    window.removeEventListener("resize", this.initTrHeight);
  },
  methods: {
    formatterUpperCase(value) {
      return value.toUpperCase();
    },
    repeateAgain() {
      this.items.push({
        itemId: (this.nextTodoId += this.nextTodoId),
        feeType: "",
        fee: null,
        marketPlace: "MERCADO_LIVRE",
        insertId: this.username,
        companyId: this.companyId,
      });

      this.$nextTick(() => {
        this.trAddHeight(this.$refs.row[0].offsetHeight);
      });
    },
    removeItem(index) {
      this.$bvModal
        .msgBoxConfirm("A Comissão será deletado, Ok?", {
          cancelVariant: "outline-secondary",
          centered: true,
          title: "Por favor, confirmar",
        })
        .then((value) => {
          if (value) {
            if (this.items[index].id) {
              api
                .delete(
                  `/organizational/organizational/deleteMktPlaceFee/${this.items[index].id}`
                )
                .then((response) => {
                  this.items.splice(index, 1);
                  this.trTrimHeight(this.$refs.row[0].offsetHeight);
                })
                .catch((error) => {
                  this.showDismissibleErrorAlert = true;
                  console.error(error);
                });
            } else {
              this.items.splice(index, 1);
              this.trTrimHeight(this.$refs.row[0].offsetHeight);
            }
          }
        });
    },
    saveItem(index) {
      this.items[index].updateId = this.username;
      api
        .post("/organizational/organizational/saveMktPlaceFee", this.items[index])
        .then((response) => {
          this.items[index].id = response.data.id;
          this.items[index].insertDate = response.data.insertDate;
          this.$toast({
            component: ToastificationContent,
            props: {
              title: "Salvo com sucesso",
              icon: "EditIcon",
              variant: "success",
            },
          });
        })
        .catch((error) => {
          this.showDismissibleErrorAlert = true;
          console.error(error);
        });
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
