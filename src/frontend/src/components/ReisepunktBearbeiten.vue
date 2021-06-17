<template>
  <b-container id="punkterstellen" fluid>
    <b-card no-body>
      <!-- Tabs -->
      <h1>{{ this.name }}</h1>
      <b-tabs card justified>
        <b-tab title="Punkt" :disabled="typ != 'punkt'">
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
                  <b-button class="btn-orange" type="submit">Speichern</b-button>
                </div>
              </div>
            </b-form>
          </div>
          <!-- End Textfield Punkt -->
        </b-tab>
        <b-tab title="Sehenswürdigkeit" :disabled="typ != 'sehenswuerdigkeit'">
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
                <b-button class="btn-orange" type="submit">Speichern</b-button>
              </div>
            </div>
          </b-form>
          <!-- End Textfield Sehenswürdigkeit -->
        </b-tab>
        <b-tab title="Attraktion" :disabled="typ != 'attraktion'">
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
                <label class="bislabel">bis</label>
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
                <b-button class="btn-orange" type="submit">Speichern</b-button>
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
export default {
  name: 'ReisepunktBearbeiten',
  data () {
    return {
      typ: 'attraktion', // attraktion
      nutzerEmail: '',
      name: '',
      beschreibung: 'hier sollte eigentlich eine beschreibung reinkommen',
      mo_von: '13:00',
      mo_bis: '16:00',
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
  methods: {
    loadMarkerData (point) {
      this.typ = point.typ
      this.nutzerEmail = point.nutzerEmail
      this.name = point.name
      this.beschreibung = point.beschreibung
      this.mo_von = point.mo_von
      this.mo_bis = point.mo_bis
      this.di_von = point.di_von
      this.di_bis = point.di_bis
      this.mi_von = point.mi_von
      this.mi_bis = point.mi_bis
      this.do_bis = point.do_bis
      this.do_von = point.do_von
      this.fr_von = point.fr_von
      this.fr_bis = point.fr_bis
      this.sa_von = point.sa_von
      this.sa_bis = point.sa_bis
      this.so_von = point.so_von
      this.so_bis = point.so_bis
      this.mo_ganztaegig = point.mo_ganztaegig
      this.di_ganztaegig = point.di_ganztaegig
      this.mi_ganztaegig = point.mi_ganztaegig
      this.do_ganztaegig = point.do_ganztaegig
      this.fr_ganztaegig = point.fr_ganztaegig
      this.sa_ganztaegig = point.sa_ganztaegig
      this.so_ganztaegig = point.so_ganztaegig
      this.mo_geschlossen = point.mo_geschlossen
      this.di_geschlossen = point.di_geschlossen
      this.mi_geschlossen = point.mi_geschlossen
      this.do_geschlossen = point.do_geschlossen
      this.fr_geschlossen = point.fr_geschlossen
      this.sa_geschlossen = point.sa_geschlossen
      this.so_geschlossen = point.so_geschlossen
      this.bilder = point.bilder
      console.log('HEYYYY ich bin fertig')
    },
    onSubmit (event) {
      // ToDo: In der Datenbank überschreiben
      console.log('Speichern')
    },
    onReset (event) {
      // ToDo: Schließen
      this.$emit('cancel')
      console.log('Änderungen nicht übernommen.')
    }
  },
  created () {
    this.$parent.$on('update', this.loadMarkerData)
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

  &-bar {
    overflow: hidden;
    clear: both;
  }
}

.right {
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
