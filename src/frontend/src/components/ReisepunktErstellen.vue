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
                ></b-form-input>
              </b-form-group>

              <b-form-group
                label-align="left"
                id="input-group-laengengrad"
                label="Längengrad"
                label-for="laengengrad"
              >
                <!--  + this.laengengrad -->
                <b-form-input
                  id="laengengrad"
                  v-model="laengengrad"
                  placeholder="Name eingeben"
                  required
                ></b-form-input>
              </b-form-group>

              <b-form-group
                label-align="left"
                id="input-group-breitengrad"
                label="Breitengrad"
                label-for="breitengrad"
              >
                <!--  + this.laengengrad -->
                <b-form-input
                  id="breitengrad"
                  v-model="breitengrad"
                  placeholder="Name eingeben"
                  required
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
              >
              </b-form-input>
            </b-form-group>

              <b-form-group
                label-align="left"
                id="input-group-laengengrad"
                label="Längengrad"
                label-for="laengengrad"
              >
                <!--  + this.laengengrad -->
                <b-form-input
                  id="laengengrad"
                  v-model="laengengrad"
                  placeholder="Name eingeben"
                  required
                ></b-form-input>
              </b-form-group>

              <b-form-group
                label-align="left"
                id="input-group-breitengrad"
                label="Breitengrad"
                label-for="breitengrad"
              >
                <!--  + this.laengengrad -->
                <b-form-input
                  id="breitengrad"
                  v-model="breitengrad"
                  placeholder="Name eingeben"
                  required
                ></b-form-input>
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
              >
              </b-form-input>
            </b-form-group>

              <b-form-group
                label-align="left"
                id="input-group-laengengrad"
                label="Längengrad"
                label-for="laengengrad"
              >
                <!--  + this.laengengrad -->
                <b-form-input
                  id="laengengrad"
                  v-model="laengengrad"
                  placeholder="Name eingeben"
                  required
                ></b-form-input>
              </b-form-group>

              <b-form-group
                label-align="left"
                id="input-group-breitengrad"
                label="Breitengrad"
                label-for="breitengrad"
              >
                <!--  + this.laengengrad -->
                <b-form-input
                  id="breitengrad"
                  v-model="breitengrad"
                  placeholder="Name eingeben"
                  required
                ></b-form-input>
              </b-form-group>
            <!-- Öffnungszeiten -->
            <b-form-group
              label-align="left"
              id="input-group-4"
              label="Öffnungszeiten"
              label-for="input-4"
              description="z.B. Montag 10:00 - 15:30, Mittwoch 9:00 - 14:00, Freitag 9:00 - 12:00"
            >
              <b-input-group>
                <label class="oeffnungszeitlabel">Montag</label>
                <b-input class="inpvonzeit" v-model="mo_von"></b-input>
                <label class="bislabel" >bis</label>
                <b-input class="inpbiszeit" v-model="mo_bis"></b-input>

                <b-checkbox-group>
                  <label class="ganztaegiglabel">Ganztägig</label>
                  <b-checkbox class="cbganztaegig" v-model="mo_ganztaegig"></b-checkbox>
                </b-checkbox-group>
              </b-input-group>

              <b-input-group>
                <label class="oeffnungszeitlabel">Dienstag</label>
                <b-input class="inpvonzeit" v-model="di_von"></b-input>
                <label class="bislabel">bis</label>
                <b-input class="inpbiszeit" v-model="di_bis"></b-input>

                <b-checkbox-group>
                  <label class="ganztaegiglabel">Ganztägig</label>
                  <b-checkbox class="cbganztaegig" v-model="di_ganztaegig"></b-checkbox>
                </b-checkbox-group>
              </b-input-group>

              <b-input-group>
                <label class="oeffnungszeitlabel">Mittwoch</label>
                <b-input class="inpvonzeit" v-model="mi_von"></b-input>
                <label class="bislabel">bis</label>
                <b-input class="inpbiszeit" v-model="mi_bis"></b-input>

                <b-checkbox-group>
                  <label class="ganztaegiglabel">Ganztägig</label>
                  <b-checkbox class="cbganztaegig" v-model="mi_ganztaegig"></b-checkbox>
                </b-checkbox-group>
              </b-input-group>

              <b-input-group>
                <label class="oeffnungszeitlabel">Donnerstag</label>
                <b-input class="inpvonzeit" v-model="do_von"></b-input>
                <label class="bislabel">bis</label>
                <b-input class="inpbiszeit" v-model="do_bis"></b-input>

                <b-checkbox-group>
                  <label class="ganztaegiglabel">Ganztägig</label>
                  <b-checkbox class="cbganztaegig" v-model="do_ganztaegig"></b-checkbox>
                </b-checkbox-group>
              </b-input-group>

              <b-input-group>
                <label class="oeffnungszeitlabel">Freitag</label>
                <b-input class="inpvonzeit" v-model="fr_von"></b-input>
                <label class="bislabel">bis</label>
                <b-input class="inpbiszeit" v-model="fr_bis"></b-input>

                <b-checkbox-group>
                  <label class="ganztaegiglabel">Ganztägig</label>
                  <b-checkbox class="cbganztaegig" v-model="fr_ganztaegig"></b-checkbox>
                </b-checkbox-group>
              </b-input-group>

              <b-input-group>
                <label class="oeffnungszeitlabel">Samstag</label>
                <b-input class="inpvonzeit" v-model="sa_von"></b-input>
                <label class="bislabel">bis</label>
                <b-input class="inpbiszeit" v-model="sa_bis"></b-input>

                <b-checkbox-group>
                  <label class="ganztaegiglabel">Ganztägig</label>
                  <b-checkbox class="cbganztaegig" v-model="sa_ganztaegig"></b-checkbox>
                </b-checkbox-group>
              </b-input-group>

              <b-input-group>
                <label class="oeffnungszeitlabel">Sonntag</label>
                <b-input class="inpvonzeit" v-model="so_von"></b-input>
                <label class="bislabel">bis</label>
                <b-input class="inpbiszeit" v-model="so_bis"></b-input>

                <b-checkbox-group>
                  <label class="ganztaegiglabel">Ganztägig</label>
                  <b-checkbox class="cbganztaegig" v-model="so_ganztaegig"></b-checkbox>
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
import { toggleDragging } from '@/lib/mapWrapper'

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
      mo_ganztaegig: false,
      di_ganztaegig: false,
      mi_ganztaegig: false,
      do_ganztaegig: false,
      fr_ganztaegig: false,
      sa_ganztaegig: false,
      so_ganztaegig: false,
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
        const res = axios.post('/SHTravel/punkt', {
          laengengrad: this.laengengrad,
          breitengrad: this.breitengrad,
          nutzerEmail: this.nutzerEmail,
          name: this.name
        })
          .then(response => {
            console.log(response)
            this.makeToast('success', 'Punkt', this.name + ' erfolgreich erstellt')
            this.disableThisShow()
          })
          .catch(error => {
            this.makeToast('danger', 'Punkt', this.name + ' konnte nicht erstellt werden')
            console.error(error)
          })
      } else if (this.typ === 'sehenswuerdigkeit') {
        axios.post('/SHTravel/sehenswuerdigkeit', {
          laengengrad: this.laengengrad,
          breitengrad: this.breitengrad,
          nutzerEmail: this.nutzerEmail,
          name: this.name,
          beschreibung: this.beschreibung,
          bilder: this.bilder
        })
          .then(response => {
            console.log(response)
            this.makeToast('success', 'Sehenswuerdigkeit', this.name + ' erfolgreich erstellt')
            this.disableThisShow()
          })
          .catch(error => {
            this.makeToast('danger', 'Sehenswuerdigkeit', this.name + ' konnte nicht erstellt werden')
            console.error(error)
          })
      } else if (this.typ === 'attraktion') {
        axios.post('/SHTravel/attraktion', {
          laengengrad: this.laengengrad,
          breitengrad: this.breitengrad,
          nutzerEmail: this.nutzerEmail,
          name: this.name,
          attraktionOeffnungszeiten: [
            {
              tagDerWoche: 'MONDAY',
              oeffnetUm: this.mo_von,
              schliestUm: this.mo_bis,
              ganztaegig: this.mo_ganztaegig[0],
              geschlossen: this.checkIfGeschlossen(this.mo_ganztaegig, this.mo_von, this.mo_bis)
            },
            {
              tagDerWoche: 'TUESDAY',
              oeffnetUm: this.di_von,
              schliestUm: this.di_bis,
              ganztaegig: this.di_ganztaegig[0],
              geschlossen: this.checkIfGeschlossen(this.di_ganztaegig, this.di_von, this.di_bis)
            },
            {
              tagDerWoche: 'WEDNESDAY',
              oeffnetUm: this.mi_von,
              schliestUm: this.mi_bis,
              ganztaegig: this.mi_ganztaegig[0],
              geschlossen: this.checkIfGeschlossen(this.mi_ganztaegig, this.mi_von, this.mi_bis)
            },
            {
              tagDerWoche: 'THURSDAY',
              oeffnetUm: this.do_von,
              schliestUm: this.do_bis,
              ganztaegig: this.do_ganztaegig[0],
              geschlossen: this.checkIfGeschlossen(this.do_ganztaegig, this.do_von, this.do_bis)
            },
            {
              tagDerWoche: 'FRIDAY',
              oeffnetUm: this.fr_von,
              schliestUm: this.fr_bis,
              ganztaegig: this.fr_ganztaegig[0],
              geschlossen: this.checkIfGeschlossen(this.fr_ganztaegig, this.fr_von, this.fr_bis)
            },
            {
              tagDerWoche: 'SATURDAY',
              oeffnetUm: this.sa_von,
              schliestUm: this.sa_bis,
              ganztaegig: this.sa_ganztaegig[0],
              geschlossen: this.checkIfGeschlossen(this.sa_ganztaegig, this.sa_von, this.sa_bis)
            },
            {
              tagDerWoche: 'SUNDAY',
              oeffnetUm: this.so_von,
              schliestUm: this.so_bis,
              ganztaegig: this.so_ganztaegig[0],
              geschlossen: this.checkIfGeschlossen(this.so_ganztaegig, this.so_von, this.so_bis)
            }
          ],
          beschreibung: this.beschreibung,
          bilder: this.bilder
        })
          .then(response => {
            console.log(response)
            this.makeToast('success', 'Attraktion', this.name + ' erfolgreich erstellt')
            this.disableThisShow()
          })
          .catch(error => {
            this.makeToast('danger', 'Attraktion', this.name + ' konnte nicht erstellt werden')
            console.error(error)
          })
      }

      toggleDragging(true)
    },
    onReset (event) {
      event.preventDefault()
      this.disableThisShow()

      toggleDragging(true)
    },
    makeToast (variant = null, title = null, body = null) {
      this.$emit('makeToast', [variant, title, body])
    },
    disableThisShow: function () {
      this.$emit('updateShow', 'false')
    },
    formatOeffnungszeit (inputZeit) {
      var time = new Date()
      time.setHours(inputZeit.split(':')[0])
      time.setMinutes(inputZeit.split(':')[1])

      var ret = time.toLocaleTimeString('it-IT') // 01:50:10
      ret = ret.substring(0, ret.length - 3)
      return ret
    },
    checkIfGeschlossen (ganztaegig, von, bis) {
      return !ganztaegig && von === '' && bis === ''
    }
  },
  created () {
    const coords = this.getCoords
    this.laengengrad = coords.laengengrad
    this.breitengrad = coords.breitengrad
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
