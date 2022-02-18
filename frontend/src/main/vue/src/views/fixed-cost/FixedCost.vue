<template>
  <div>
    <b-row>
      <b-col cols="12">
        <fixed-cost-repeater/>
      </b-col>
    </b-row>
  </div>
</template>

<script>
import { BRow, BCol } from 'bootstrap-vue'

import FixedCostRepeater from './FixedCostRepeater.vue'

export default {
  components: {
    BRow,
    BCol,
    FixedCostRepeater,
  },
}
</script>