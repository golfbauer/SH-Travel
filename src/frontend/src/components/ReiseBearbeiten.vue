<template>
  <div id="reise-bearbeiten">
    <b-card no-body header="Reise bearbeiten">
      <div style="padding: 1em;">
        <b-form-group
          label-align="left"
          id="input-group-1"
          label="Reisename"
          label-for="input-1"
        >
          <!--  + this.laengengrad -->
          <b-form-input
            id="input-1"
            v-model="reise.name"
            placeholder="Reisename eingeben"
            required
            maxlength="30"
          ></b-form-input>
        </b-form-group>
        Reisepunkte:
      </div>
      <b-list-group flush>
        <b-list-group-item v-for="reisepunkt in this.reise.reisepunkte" :key="reisepunkt.id">
          {{reisepunkt.name}}
          <b-button @click="toggleAlert(reisepunkt.id)" variant="outline-danger" size="sm">Löschen</b-button>
          </b-list-group-item>
          <b-alert
            v-model="showAlert"
            class="position-fixed fixed-bottom m-0 rounded-0"
            style="z-index: 2000;"
            variant="warning"
            dismissible
          >
            Wollen Sie den Reisepunkt "{{ getReiseName(reisepunktId) }}" wirklich aus der Reise "{{ this.reise.name }}" löschen?
            <b-button @click="deleteReisepunkt(reisepunktId)" variant="outline-success"> Ja </b-button>
            <b-button @click="toggleAlert(0)" variant="outline-danger"> Nein </b-button>
          </b-alert>
      </b-list-group>

      <b-card-body class="btn-bar">
        <b-button class="btn-gray" type="reset">Abbrechen</b-button>
        <b-button class="btn-orange" type="submit">Speichern</b-button>
      </b-card-body>
    </b-card>
  </div>
</template>

<script>
import * as reiseService from '@/service/helper/reise'
export default {
  name: 'ReiseBearbeiten',
  data () {
    return {
      selId: 2298,
      reise: {},
      showAlert: false,
      reisepunktId: 0
    }
  },
  props: {
    reiseId: Number
  },
  methods: {
    deleteReisepunkt (reisepunktId) {
      this.reise = reiseService.delReisepunkt(this.reise, reisepunktId)
      this.toggleAlert(0)
    },
    toggleAlert (reisepunktId) {
      this.reisepunktId = reisepunktId
      if (this.showAlert) {
        this.showAlert = false
      } else {
        this.showAlert = true
      }
    },
    getReiseName (reisepunktId) {
      if (this.reise === undefined || this.reise.reisepunkte === undefined) {
        return ''
      }
      const length = this.reise.reisepunkte.length
      for (let i = 0; i < length; i++) {
        if (this.reise.reisepunkte[i].id === reisepunktId) {
          return this.reise.reisepunkte[i].name
        }
      }
      return undefined
    }
  },
  created () {
    const test = reiseService.getReise(this.selId) // change selId to reiseId
    test.then((msg) => {
      this.reise = msg
    })
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
