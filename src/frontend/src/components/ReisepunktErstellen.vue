<template>
  <b-container id="punkterstellen" fluid>
    <b-card no-body>
      <!-- Tabs -->
      <b-tabs card>
        <b-tab title="Punkt" active>
          <!-- Textfield -->
          <div>
            <b-form @submit="onSubmit" @cancel="onCancel" v-if="show">
              <b-form-group label-align="left"
                id="input-group-1"
                label="Name"
                label-for="input-1"
              >
                <b-form-input
                  id="input-1"
                  v-model="form.name"
                  placeholder="Name eingeben"
                  required
                ></b-form-input>
              </b-form-group>
              <b-form-group id="input-group-4" v-slot="{ ariaDescribedby }">
                <b-form-checkbox-group
                  v-model="form.checked"
                  id="checkboxes-4"
                  :aria-describedby="ariaDescribedby"
                >
                </b-form-checkbox-group>
              </b-form-group>

              <b-button class="btn-gray" type="cancel" variant="danger">Abbrechen</b-button>
              <b-button class="btn-orange" type="submit" variant="primary">Erstellen</b-button>
            </b-form>
          </div>
          <!-- End Textfield -->
        </b-tab>
        <b-tab title="Sehenswürdigkeit">Sehenswürdigkeit</b-tab>
        <b-tab title="Attraktion">Attraktion</b-tab>
        <!-- End Tabs -->
      </b-tabs>
    </b-card>
  </b-container>
</template>

<script>
export default {
  name: 'ReisepunktErstellen',
  data () {
    return {
      form: {
        name: '',
        beschreibung: '',
        bilder: null
      },
      show: true
    }
  },
  methods: {
    onSubmit (event) {
      event.preventDefault()
      alert(JSON.stringify(this.form))
    },
    onCancel (event) {
      event.preventDefault()
      // Reset our form values
      this.form.name = ''
      this.form.beschreibung = null
      // Trick to reset/clear native browser form validation state
      this.show = false
      this.$nextTick(() => {
        this.show = true
      })
    }
  }
}
</script>

<style lang="scss" scoped>
$orange: orange;

#punkterstellen {
  position: relative;
  margin: 0 auto;
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
}
</style>
