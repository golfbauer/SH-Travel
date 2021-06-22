<template>
  <b-container id="punkterstellen" fluid>
    <b-card no-body>
      <!-- Tabs -->
      <b-tabs card justified>
        <b-tab title="Punkt" @click="typ = 'punkt'" active>
          <!-- Textfield Punkt -->
          <div>
            <b-form @submit="onSubmit" @reset="onReset">
              <b-form-group
                label-align="left"
                id="input-group-1"
                label="Name"
                label-for="input-1"
              >
                <!--  + this.laengengrad -->
                <b-form-input
                  id="input-1"
                  v-model="name"
                  placeholder="Name eingeben"
                  required
                  maxlength="30"
                ></b-form-input>
              </b-form-group>

            <div class="btn-bar">
              <div class="right">
                <b-button class="btn-gray" type="reset">Abbrechen</b-button>
                <b-button class="btn-orange" type="submit">Erstellen</b-button>
              </div>
            </div>
            </b-form>
          </div>
          <!-- End Textfield Punkt -->
        </b-tab>
        <b-tab title="Sehenswürdigkeit" @click="typ = 'sehenswuerdigkeit'">
        <!-- Textfield Sehenswürdigkeit -->
          <b-form @submit="onSubmit" @reset="onReset">
            <b-form-group
              label-align="left"
              id="input-group-1"
              label="Name"
              label-for="input-1"
            >
              <b-form-input
                id="input-1"
                v-model="name"
                placeholder="Name eingeben"
                required
                maxlength="30"
              >
              </b-form-input>
            </b-form-group>

            <b-form-group
              label-align="left"
              id="input-group-2"
              label="Beschreibung"
              label-for="input-2"
            >
              <b-form-textarea
                id="input-2"
                v-model="beschreibung"
                placeholder="Beschreibung eingeben"
                rows="2"
                max-rows="6"
              >
              </b-form-textarea>
            </b-form-group>
            <b-form-group
             label-align="left"
              id="input-group-3"
              label="Bilder"
              label-for="input-3"
            >
              <b-form-file
                id="input-3"
                v-model="bilder"
                accept="image/*"
                class="mt-1"
                placeholder="Wähle Bilder aus oder lege sie hier ab"
                multiple
              >
              </b-form-file>
              <b-button @click="bilder = []" class="mr-2">Reset Auswahl</b-button>
            </b-form-group>
            <div class="btn-bar">
              <div class="right">
                <b-button class="btn-gray" type="reset">Abbrechen</b-button>
                <b-button class="btn-orange" type="submit">Erstellen</b-button>
              </div>
            </div>
          </b-form>
          <!-- End Textfield Sehenswürdigkeit -->
        </b-tab>
        <b-tab title="Attraktion" @click="typ = 'attraktion'">
        <!-- Textfield Attraktion -->
          <b-form @submit="onSubmit" @reset="onReset">
            <b-form-group
              label-align="left"
              id="input-group-1"
              label="Name"
              label-for="input-1"
            >
              <b-form-input
                id="input-1"
                v-model="name"
                placeholder="Name eingeben"
                required
                maxlength="30"
              >
              </b-form-input>
            </b-form-group>
            <b-form-group
              label-align="left"
              id="input-group-4"
              label="Öffnungszeiten"
              label-for="input-4"
              description="Zeitangabe: 14:00, 15:30,... | Geschlossen - Felder leer lassen"
            >
              <b-input-group>
                <label class="oeffnungszeitlabel">Montag</label>
                <b-input class="inpvonzeit" v-model="mo_von" :disabled="mo_ganztaegig === true"></b-input>
                <label class="bislabel" >bis</label>
                <b-input class="inpbiszeit" v-model="mo_bis" :disabled="mo_ganztaegig === true"></b-input>

                <b-checkbox-group v-model="mo_ganztaegig">
                  <label class="ganztaegiglabel">Ganztägig</label>
                  <b-checkbox class="cbganztaegig"></b-checkbox>
                </b-checkbox-group>
              </b-input-group>

              <b-input-group>
                <label class="oeffnungszeitlabel">Dienstag</label>
                <b-input class="inpvonzeit" v-model="di_von" :disabled="di_ganztaegig === true"></b-input>
                <label class="bislabel">bis</label>
                <b-input class="inpbiszeit" v-model="di_bis" :disabled="di_ganztaegig === true"></b-input>

                <b-checkbox-group v-model="di_ganztaegig">
                  <label class="ganztaegiglabel">Ganztägig</label>
                  <b-checkbox class="cbganztaegig"></b-checkbox>
                </b-checkbox-group>
              </b-input-group>

              <b-input-group>
                <label class="oeffnungszeitlabel">Mittwoch</label>
                <b-input class="inpvonzeit" v-model="mi_von" :disabled="mi_ganztaegig === true"></b-input>
                <label class="bislabel">bis</label>
                <b-input class="inpbiszeit" v-model="mi_bis" :disabled="mi_ganztaegig === true"></b-input>

                <b-checkbox-group v-model="mi_ganztaegig">
                  <label class="ganztaegiglabel">Ganztägig</label>
                  <b-checkbox class="cbganztaegig"></b-checkbox>
                </b-checkbox-group>
              </b-input-group>

              <b-input-group>
                <label class="oeffnungszeitlabel">Donnerstag</label>
                <b-input class="inpvonzeit" v-model="do_von" :disabled="do_ganztaegig === true"></b-input>
                <label class="bislabel">bis</label>
                <b-input class="inpbiszeit" v-model="do_bis" :disabled="do_ganztaegig === true"></b-input>

                <b-checkbox-group v-model="do_ganztaegig">
                  <label class="ganztaegiglabel">Ganztägig</label>
                  <b-checkbox class="cbganztaegig"></b-checkbox>
                </b-checkbox-group>
              </b-input-group>

              <b-input-group>
                <label class="oeffnungszeitlabel">Freitag</label>
                <b-input class="inpvonzeit" v-model="fr_von" :disabled="fr_ganztaegig === true"></b-input>
                <label class="bislabel">bis</label>
                <b-input class="inpbiszeit" v-model="fr_bis" :disabled="fr_ganztaegig === true"></b-input>

                <b-checkbox-group v-model="fr_ganztaegig">
                  <label class="ganztaegiglabel">Ganztägig</label>
                  <b-checkbox class="cbganztaegig"></b-checkbox>
                </b-checkbox-group>
              </b-input-group>

              <b-input-group>
                <label class="oeffnungszeitlabel">Samstag</label>
                <b-input class="inpvonzeit" v-model="sa_von" :disabled="sa_ganztaegig === true"></b-input>
                <label class="bislabel">bis</label>
                <b-input class="inpbiszeit" v-model="sa_bis" :disabled="sa_ganztaegig === true"></b-input>

                <b-checkbox-group v-model="sa_ganztaegig">
                  <label class="ganztaegiglabel">Ganztägig</label>
                  <b-checkbox class="cbganztaegig"></b-checkbox>
                </b-checkbox-group>
              </b-input-group>

              <b-input-group>
                <label class="oeffnungszeitlabel">Sonntag</label>
                <b-input class="inpvonzeit" v-model="so_von" :disabled="so_ganztaegig === true"></b-input>
                <label class="bislabel">bis</label>
                <b-input class="inpbiszeit" v-model="so_bis" :disabled="so_ganztaegig === true"></b-input>

                <b-checkbox-group v-model="so_ganztaegig">
                  <label class="ganztaegiglabel">Ganztägig</label>
                  <b-checkbox class="cbganztaegig"></b-checkbox>
                </b-checkbox-group>
              </b-input-group>

            </b-form-group>
            <b-form-group
              label-align="left"
              id="input-group-2"
              label="Beschreibung"
              label-for="input-2"
            >
              <b-form-textarea
                id="input-2"
                v-model="beschreibung"
                placeholder="Beschreibung eingeben"
                rows="2"
                max-rows="6"
              >
              </b-form-textarea>
            </b-form-group>
            <b-form-group
             label-align="left"
              id="input-group-3"
              label="Bilder"
              label-for="input-3"
            >
              <b-form-file
                id="input-3"
                v-model="bilder"
                accept="image/*"
                class="mt-1"
                placeholder="Wähle Bilder aus oder lege sie hier ab"
                multiple
              >
              </b-form-file>
              <b-button @click="bilder = []" class="mr-2">Reset Auswahl</b-button>
            </b-form-group>
            <div class="btn-bar">
              <div class="right">
                <b-button class="btn-gray" type="reset">Abbrechen</b-button>
                <b-button class="btn-orange" type="submit">Erstellen</b-button>
              </div>
            </div>
          </b-form>
          <!-- End Textfield Attraktion -->
        </b-tab>
        <!-- End Tabs -->
      </b-tabs>
    </b-card>
  </b-container>
</template>

<script>
import { mapGetters } from 'vuex'
import * as mapService from '@/service/helper/map'
import { createPunkt } from '@/service/api/punkt'
import { createSehenswuerdigkeit } from '@/service/api/sehenswuerdigkeit'
import { createAttraktion } from '@/service/api/attraktion'

export default {
  name: 'ReisepunktErstellen',
  data () {
    return {
      typ: 'punkt',
      nutzerEmail: '',
      name: '',
      beschreibung: '',
      mo_von: '',
      mo_bis: '',
      di_von: '',
      di_bis: '',
      mi_von: '',
      mi_bis: '',
      do_von: '',
      do_bis: '',
      fr_von: '',
      fr_bis: '',
      sa_von: '',
      sa_bis: '',
      so_von: '',
      so_bis: '',
      mo_ganztaegig: [],
      di_ganztaegig: [],
      mi_ganztaegig: [],
      do_ganztaegig: [],
      fr_ganztaegig: [],
      sa_ganztaegig: [],
      so_ganztaegig: [],
      mo_geschlossen: false,
      di_geschlossen: false,
      mi_geschlossen: false,
      do_geschlossen: false,
      fr_geschlossen: false,
      sa_geschlossen: false,
      so_geschlossen: false,
      bilder: [],
      laengengrad: '',
      breitengrad: ''
    }
  },
  computed: mapGetters(['getCoords']),
  methods: {
    onSubmit (event) {
      event.preventDefault()
      const axios = require('axios')
      console.log(this.laengengrad + ' ' + this.breitengrad)
      if (this.typ === 'punkt') {
        createPunkt({
          name: this.name,
          laengengrad: this.laengengrad,
          breitengrad: this.breitengrad,
          nutzerEmail: this.nutzerEmail
        }).then(response => {
          console.log(response)
          this.makeToast('success', 'Punkt', this.name + ' erfolgreich erstellt')
          this.disableThisShow()
        }).catch(error => {
          this.makeToast('danger', 'Punkt', this.name + ' konnte nicht erstellt werden')
          console.error(error)
        })
        // const res = axios.post('/SHTravel/punkt', {
        //   laengengrad: this.laengengrad,
        //   breitengrad: this.breitengrad,
        //   nutzerEmail: this.nutzerEmail,
        //   name: this.name
        // })
        //   .then(response => {
        //     console.log(response)
        //     this.makeToast('success', 'Punkt', this.name + ' erfolgreich erstellt')
        //     this.disableThisShow()
        //   })
        //   .catch(error => {
        //     this.makeToast('danger', 'Punkt', this.name + ' konnte nicht erstellt werden')
        //     console.error(error)
        //   })
      } else if (this.typ === 'sehenswuerdigkeit') {
        createSehenswuerdigkeit({
          name: this.name,
          laengengrad: this.laengengrad,
          breitengrad: this.breitengrad,
          nutzerEmail: this.nutzerEmail,
          beschreibung: this.beschreibung,
          bilder: this.bilder
        }).then(response => {
          console.log(response)
          this.makeToast('success', 'Sehenswuerdigkeit', this.name + ' erfolgreich erstellt')
          this.disableThisShow()
        })
          .catch(error => {
            this.makeToast('danger', 'Sehenswuerdigkeit', this.name + ' konnte nicht erstellt werden')
            console.error(error)
          })
        // axios.post('/SHTravel/sehenswuerdigkeit', {
        //   laengengrad: this.laengengrad,
        //   breitengrad: this.breitengrad,
        //   nutzerEmail: this.nutzerEmail,
        //   name: this.name,
        //   beschreibung: this.beschreibung,
        //   bilder: this.bilder
        // })
        //   .then(response => {
        //     console.log(response)
        //     this.makeToast('success', 'Sehenswuerdigkeit', this.name + ' erfolgreich erstellt')
        //     this.disableThisShow()
        //   })
        //   .catch(error => {
        //     this.makeToast('danger', 'Sehenswuerdigkeit', this.name + ' konnte nicht erstellt werden')
        //     console.error(error)
        //   })
      } else if (this.typ === 'attraktion') {
        createAttraktion({
          name: this.name,
          laengengrad: this.laengengrad,
          breitengrad: this.breitengrad,
          nutzerEmail: this.nutzerEmail,
          beschreibung: this.beschreibung,
          bilder: this.bilder,
          attraktionOeffnungszeiten: [
            {
              tagDerWoche: 'MONDAY',
              oeffnetUm: this.clearIfGanztaegig(this.mo_ganztaegig, this.mo_von),
              schliestUm: this.clearIfGanztaegig(this.mo_ganztaegig, this.mo_bis),
              ganztaegig: this.mo_ganztaegig[0],
              geschlossen: this.checkIfGeschlossen(this.mo_ganztaegig, this.mo_von, this.mo_bis)
            },
            {
              tagDerWoche: 'TUESDAY',
              oeffnetUm: this.clearIfGanztaegig(this.di_ganztaegig, this.di_von),
              schliestUm: this.clearIfGanztaegig(this.di_ganztaegig, this.di_bis),
              ganztaegig: this.di_ganztaegig[0],
              geschlossen: this.checkIfGeschlossen(this.di_ganztaegig, this.di_von, this.di_bis)
            },
            {
              tagDerWoche: 'WEDNESDAY',
              oeffnetUm: this.clearIfGanztaegig(this.mi_ganztaegig, this.mi_von),
              schliestUm: this.clearIfGanztaegig(this.mi_ganztaegig, this.mi_bis),
              ganztaegig: this.mi_ganztaegig[0],
              geschlossen: this.checkIfGeschlossen(this.mi_ganztaegig, this.mi_von, this.mi_bis)
            },
            {
              tagDerWoche: 'THURSDAY',
              oeffnetUm: this.clearIfGanztaegig(this.do_ganztaegig, this.do_von),
              schliestUm: this.clearIfGanztaegig(this.do_ganztaegig, this.do_bis),
              ganztaegig: this.do_ganztaegig[0],
              geschlossen: this.checkIfGeschlossen(this.do_ganztaegig, this.do_von, this.do_bis)
            },
            {
              tagDerWoche: 'FRIDAY',
              oeffnetUm: this.clearIfGanztaegig(this.fr_ganztaegig, this.fr_von),
              schliestUm: this.clearIfGanztaegig(this.fr_ganztaegig, this.fr_bis),
              ganztaegig: this.fr_ganztaegig[0],
              geschlossen: this.checkIfGeschlossen(this.fr_ganztaegig, this.fr_von, this.fr_bis)
            },
            {
              tagDerWoche: 'SATURDAY',
              oeffnetUm: this.clearIfGanztaegig(this.sa_ganztaegig, this.sa_von),
              schliestUm: this.clearIfGanztaegig(this.sa_ganztaegig, this.sa_bis),
              ganztaegig: this.sa_ganztaegig[0],
              geschlossen: this.checkIfGeschlossen(this.sa_ganztaegig, this.sa_von, this.sa_bis)
            },
            {
              tagDerWoche: 'SUNDAY',
              oeffnetUm: this.clearIfGanztaegig(this.so_ganztaegig, this.so_von),
              schliestUm: this.clearIfGanztaegig(this.so_ganztaegig, this.so_bis),
              ganztaegig: this.so_ganztaegig[0],
              geschlossen: this.checkIfGeschlossen(this.so_ganztaegig, this.so_von, this.so_bis)
            }
          ]
        }).then(response => {
          console.log(response)
          this.makeToast('success', 'Attraktion', this.name + ' erfolgreich erstellt')
          this.disableThisShow()
        })
          .catch(error => {
            this.makeToast('danger', 'Attraktion', this.name + ' konnte nicht erstellt werden')
            console.error(error)
          })
        // axios.post('/SHTravel/attraktion', {
        //   laengengrad: this.laengengrad,
        //   breitengrad: this.breitengrad,
        //   nutzerEmail: this.nutzerEmail,
        //   name: this.name,
        //   attraktionOeffnungszeiten: [
        //     {
        //       tagDerWoche: 'MONDAY',
        //       oeffnetUm: this.clearIfGanztaegig(this.mo_ganztaegig, this.mo_von),
        //       schliestUm: this.clearIfGanztaegig(this.mo_ganztaegig, this.mo_bis),
        //       ganztaegig: this.mo_ganztaegig[0],
        //       geschlossen: this.checkIfGeschlossen(this.mo_ganztaegig, this.mo_von, this.mo_bis)
        //     },
        //     {
        //       tagDerWoche: 'TUESDAY',
        //       oeffnetUm: this.clearIfGanztaegig(this.di_ganztaegig, this.di_von),
        //       schliestUm: this.clearIfGanztaegig(this.di_ganztaegig, this.di_bis),
        //       ganztaegig: this.di_ganztaegig[0],
        //       geschlossen: this.checkIfGeschlossen(this.di_ganztaegig, this.di_von, this.di_bis)
        //     },
        //     {
        //       tagDerWoche: 'WEDNESDAY',
        //       oeffnetUm: this.clearIfGanztaegig(this.mi_ganztaegig, this.mi_von),
        //       schliestUm: this.clearIfGanztaegig(this.mi_ganztaegig, this.mi_bis),
        //       ganztaegig: this.mi_ganztaegig[0],
        //       geschlossen: this.checkIfGeschlossen(this.mi_ganztaegig, this.mi_von, this.mi_bis)
        //     },
        //     {
        //       tagDerWoche: 'THURSDAY',
        //       oeffnetUm: this.clearIfGanztaegig(this.do_ganztaegig, this.do_von),
        //       schliestUm: this.clearIfGanztaegig(this.do_ganztaegig, this.do_bis),
        //       ganztaegig: this.do_ganztaegig[0],
        //       geschlossen: this.checkIfGeschlossen(this.do_ganztaegig, this.do_von, this.do_bis)
        //     },
        //     {
        //       tagDerWoche: 'FRIDAY',
        //       oeffnetUm: this.clearIfGanztaegig(this.fr_ganztaegig, this.fr_von),
        //       schliestUm: this.clearIfGanztaegig(this.fr_ganztaegig, this.fr_bis),
        //       ganztaegig: this.fr_ganztaegig[0],
        //       geschlossen: this.checkIfGeschlossen(this.fr_ganztaegig, this.fr_von, this.fr_bis)
        //     },
        //     {
        //       tagDerWoche: 'SATURDAY',
        //       oeffnetUm: this.clearIfGanztaegig(this.sa_ganztaegig, this.sa_von),
        //       schliestUm: this.clearIfGanztaegig(this.sa_ganztaegig, this.sa_bis),
        //       ganztaegig: this.sa_ganztaegig[0],
        //       geschlossen: this.checkIfGeschlossen(this.sa_ganztaegig, this.sa_von, this.sa_bis)
        //     },
        //     {
        //       tagDerWoche: 'SUNDAY',
        //       oeffnetUm: this.clearIfGanztaegig(this.so_ganztaegig, this.so_von),
        //       schliestUm: this.clearIfGanztaegig(this.so_ganztaegig, this.so_bis),
        //       ganztaegig: this.so_ganztaegig[0],
        //       geschlossen: this.checkIfGeschlossen(this.so_ganztaegig, this.so_von, this.so_bis)
        //     }
        //   ],
        //   beschreibung: this.beschreibung,
        //   bilder: this.bilder
        // })
        //   .then(response => {
        //     console.log(response)
        //     this.makeToast('success', 'Attraktion', this.name + ' erfolgreich erstellt')
        //     this.disableThisShow()
        //   })
        //   .catch(error => {
        //     this.makeToast('danger', 'Attraktion', this.name + ' konnte nicht erstellt werden')
        //     console.error(error)
        //   })
      }
    },
    onReset (event) {
      event.preventDefault()
      this.disableThisShow()
    },
    makeToast (variant = null, title = null, body = null) {
      this.$emit('makeToast', [variant, title, body])
    },
    disableThisShow: function () {
      mapService.toggleMapIO(true)
      this.$emit('updateShow', 'false')
    },
    formatOeffnungszeit (inputZeit) { // ToDo entfernen?
      // ToDo: Ist Leer/Null Check

      var time = new Date()
      time.setHours(inputZeit.split(':')[0])
      time.setMinutes(inputZeit.split(':')[1])

      var ret = time.toLocaleTimeString('it-IT') // 01:50:10
      ret = ret.substring(0, ret.length - 3)
      console.log(ret)
      return ret
    },
    checkIfGeschlossen (ganztaegig, von, bis) {
      return !ganztaegig[ganztaegig.length - 1] && von === '' && bis === ''
    },
    clearIfGanztaegig (ganztaegig, zeit) {
      return ganztaegig[ganztaegig.length - 1] === true ? null : zeit
    },
    moToggle () { // ToDo entfernen?
      this.moEnabled = !this.moEnabled
      console.log(this.moEnabled)
    }
  },
  created () {
    const coords = this.getCoords
    this.laengengrad = coords.laengengrad
    this.breitengrad = coords.breitengrad
    mapService.toggleMapIO(false)
  }
}
</script>

<style lang="scss" scoped>
$orange: orange;

#punkterstellen {
  position: relative;
  padding: 0;
  max-width: 45rem;
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

.right{
  float: right;
}

.oeffnungszeitlabel {
  font-size: 16px;
  transform: translateY(+5px);
  display: inline-block;
  width: 100px;
  text-align: right;
  padding-right: 10px;
  padding-bottom: 10px;
}

.bislabel {
  font-size: 16px;
  transform: translateY(+5px);
  display: inline-block;
  width: 50px;
  text-align: center;
  padding-right: 10px;
  padding-left: 10px;
}

.inpbiszeit {
  max-width: 250px;
  min-width: 100px;
  align-self: flex-end;
}

.inpvonzeit {
  max-width: 250px;
  min-width: 100px;
  align-self: flex-start;
}

.ganztaegiglabel {
  font-size: 16px;
  transform: translateY(+5px);
  display: inline-block;
  width: 100px;
  text-align: center;
  padding-right: 5px;
  padding-left: 10px;
}

.cbganztaegig {
  size: auto;
  transform: translateY(+11px);
}
</style>
