<template>
  <div id="reise-bearbeiten">
    <b-card no-body v-bind:header="reise.id">
      <b-list-group flush>
        <b-list-group-item v-for="reisepunkt in this.reise.reisepunkte" :key="reisepunkt.id">{{reisepunkt}}</b-list-group-item>
      </b-list-group>

      <b-card-body class="btn-bar">
        <b-button class="btn-gray" type="reset" @click="this.log">Abbrechen</b-button>
        <b-button class="btn-orange" type="submit">Speichern</b-button>
      </b-card-body>
    </b-card>
  </div>
</template>

<script>
import { mapActions, mapGetters } from 'vuex'
import * as reiseService from '@/service/helper/reise'
export default {
  name: 'ReiseBearbeiten',
  data () {
    return {
      selId: 2357,
      reise: {}
    }
  },
  props: {
    reiseId: Number
  },
  methods: {
    log: function () {
      console.log(this.reisen)
    }
  },
  created () {
    reiseService.getReise(this.selId) // change selId to reiseId
    console.log('Reisebearbeiten var after fetching ' + this.reisen)
  }
}
</script>

<style lang="scss" scoped>
$orange: orange;

#reise-bearbeiten {
  position: relative;
  margin: auto;
  padding: 0;
  max-width: 20rem;
  z-index: 999;
}

.btn {
  margin: 0.5rem;

  &-gray {
    background-color: gray;
    color: #fff;
  }

  &-orange {
    background-color: $orange;
    color: #fff;
  }

  &-bar{
    overflow: hidden;
    clear: both;
  }
}

</style>
