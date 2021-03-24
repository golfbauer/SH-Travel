<template>
  <div class="center" id="reisepunkterstellen">
    <h1>Reisepunkt erstellen</h1>

    <div class="grid-container">
      <p class="grid-item-heading">Bitte wählen Sie eine Kategorie:</p>
      <label class="grid-item-left" for="rppunkt">Punkt</label>
      <input
        class="grid-item-right"
        type="radio"
        id="rppunkt"
        name="rptype"
        value="punkt"
      />
      <label class="grid-item-left" for="rpsehenswuerdigkeit"
        >Sehenswürdigkeit</label
      >
      <input
        class="grid-item-right"
        type="radio"
        id="rpsehenswuerdigkeit"
        name="rptype"
        value="sehenswuerdigkeit"
      />
      <label class="grid-item-left" for="rpattraktion">Attraktion</label>
      <input
        class="grid-item-right"
        type="radio"
        id="rpattraktion"
        name="rptype"
        value="attraktion"
      />
    </div>
    <div class="right">
      <button class="sh-button btn-gray">Abbrechen</button>
      <button class="sh-button btn-orange">Weiter</button>
    </div>
  </div>
</template>

<script>
export default {
  name: "ReisepunktErstellen",
};
</script>

<style scoped>
#reisepunkterstellen {
  margin: auto;
  width: 25em;
  padding: 0.5em;
  background-color: rgba(0, 114, 89, 0.85);
  color: #fff;
}
.grid-container {
  display: grid;
}
.grid-item-left {
  grid-column-start: 1;
  grid-column-end: 2;
}
.grid-item-right {
  grid-column-start: 2;
  grid-column-end: 3;
}
.grid-item-heading {
  grid-column-start: 1;
  grid-column-end: 3;
  text-align: center;
}
.sh-button {
  box-sizing: border-box;
  margin: 0.5em;
  padding: 0.5em 0;
  width: 8em;
  border-radius: 5px;
  border: 0;
}
.center {
  text-align: center;
}
.right {
  text-align: right;
}
.btn-gray {
  background-color: gray;
  color: #fff;
}
.btn-orange {
  background-color: orange;
  color: #fff;
}
</style>