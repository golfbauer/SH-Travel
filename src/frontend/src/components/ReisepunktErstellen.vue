<template>
  <div id="reisepunkterstellen">
    <h1 class="center">Reisepunkt erstellen</h1>
    <div class="grid-container">
      <p class="grid-item-full">Bitte wählen Sie eine Kategorie:</p>
      <label class="grid-item-left" for="punkt">Punkt</label>
      <input
        class="grid-item-right"
        type="radio"
        id="punkt"
        name="rptype"
        value="punkt"
        v-model="reisepunkt.typ"
      />
      <label class="grid-item-left" for="sehenswuerdigkeit"
        >Sehenswürdigkeit</label
      >
      <input
        class="grid-item-right"
        type="radio"
        id="sehenswuerdigkeit"
        name="rptype"
        value="sehenswuerdigkeit"
        v-model="reisepunkt.typ"
      />
      <label class="grid-item-left" for="attraktion">Attraktion</label>
      <input
        class="grid-item-right"
        type="radio"
        id="attraktion"
        name="rptype"
        value="attraktion"
        v-model="reisepunkt.typ"
      />
      <div class="grid-item-full right">
        <button class="btn btn-gray" @click="cancle">Abbrechen</button>
        <button class="btn btn-orange" @click="next">Weiter</button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "ReisepunktErstellen",
  data() {
    return {
      reisepunkt: {
        typ: "",
      },
    };
  },
  methods: {
    next() {
      console.log("next(): " + this.reisepunkt.typ);
      // Close this component and open next component
    },
    cancle() {
      console.log("cancle(): " + this.reisepunkt.typ);
      // Close this component (Close component - click beside)
    },
  },
};
</script>

<style scoped>
#reisepunkterstellen {
  margin: auto;
  max-width: 25em;
  padding: 0.5em;
  background-color: rgba(0, 114, 89, 0.85);
  color: #fff;
}
.grid-container {
  display: grid;
  row-gap: 0.5em;
}
.grid-item-left {
  grid-column-start: 1;
  grid-column-end: 2;
  text-align: right;
}
.grid-item-right {
  grid-column-start: 2;
  grid-column-end: 3;
}
.grid-item-full {
  grid-column-start: 1;
  grid-column-end: 4;
  margin: 0;
  text-align: center;
}
.btn {
  box-sizing: border-box;
  margin: 0.5em;
  padding: 0.5em 0;
  width: 8em;
  border-radius: 5px;
  border: 0;
}
.btn-gray {
  background-color: gray;
  color: #fff;
}
.btn-orange {
  background-color: orange;
  color: #fff;
}
.center {
  text-align: center;
}
.right {
  text-align: right;
}
</style>