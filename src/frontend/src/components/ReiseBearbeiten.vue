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
      </div>
      <div class="reisetag">
        Reisepunkte:
      </div>
      <b-list-group flush v-if="renderComponent">
        <b-list-group-item v-for="reisepunkt in this.reise.reisepunkte" :key="reisepunkt.id">
          <div class="flex-container">
            <b-button-group vertical>
              <b-button @click="moveUp(reisepunkt.id)" size="sm">Hoch</b-button>
              <b-button @click="moveDown(reisepunkt.id)" size="sm">Runter</b-button>
            </b-button-group>
              <b-col>
              <p>
                {{reisepunkt.name}}
              </p>
              <b-button @click="toggleAlert(reisepunkt.id)" variant="outline-danger" size="sm">Löschen</b-button>
            </b-col>
          </div>
        </b-list-group-item>
        <b-alert
          v-model="showAlert"
          class="valert position-fixed fixed-bottom m-0 rounded-0"
          variant="warning"
          dismissible
        >
          Wollen Sie den Reisepunkt "{{ getReiseName(reisepunktId) }}" wirklich aus der Reise "{{ this.reise.name }}" löschen?
          <div class="vbtn-bar">
            <b-button class="vbtn-alert" @click="deleteReisepunkt(reisepunktId)" variant="outline-success"> Ja </b-button>
            <b-button class="vbtn-alert" @click="toggleAlert(0)" variant="outline-danger"> Nein </b-button>
          </div>
        </b-alert>
      </b-list-group>

      <b-card-body class="vbtn-bar">
        <b-button class="vbtn-gray" type="reset" @click="closeShow(true)">Abbrechen</b-button>
        <b-button class="vbtn-orange" type="submit" @click="onSave()">Speichern</b-button>
      </b-card-body>
    </b-card>
  </div>
</template>

<script>
import * as reiseService from '@/service/helper/reise'
import * as reiseApi from '@/service/api/reise'
export default {
  name: 'ReiseBearbeiten',
  data () {
    return {
      reise: {},
      showAlert: false,
      reisepunktId: 0,
      renderComponent: true
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
    },
    moveUp (reisepunktId) {
      this.reise = reiseService.mvReisepunkt(this.reise, reisepunktId, true)
      this.forceRerender()
    },
    moveDown (reisepunktId) {
      this.reise = reiseService.mvReisepunkt(this.reise, reisepunktId, false)
      this.forceRerender()
    },
    forceRerender () {
      this.renderComponent = false
      this.$nextTick(() => {
        this.renderComponent = true
      })
    },
    onSave () {
      if (this.reise === {}) {
        return
      }
      const re = reiseApi.setReise(this.reise)
      if (re) {
        this.$emit('makeToast', ['success', 'Reise bearbeiten', 'Reise "' + this.reise.name + '" erfolgreich gespeichert'])
      } else {
        this.$emit('makeToast', ['danger', 'Reise bearbeiten', 'Reise "' + this.reise.name + '" konnte nicht gespeichert werden'])
      }
      this.closeShow(re)
    },
    closeShow (status) {
      this.$emit('updateShow', status)
    }
  },
  created () {
    const test = reiseService.getReise(this.reiseId)
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
  max-width: 25em;
  z-index: 999;
}

.vbtn {
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
    margin: 0 auto;
    display: flex;
    gap: 1em;
  }

  &-alert {
    margin-top: 1em;
    width: 5em;
  }
}

.valert {
  margin: 0 auto !important;
  width: 100%;
  max-width: 25em;

  & div {
    width: min-content;
  }
}

.flex-container {
  display: flex;
  gap: 0.5em
}

.reisetag {
  text-align: left;
  margin-left: 1em;
}
</style>
