<template>
  <div id="reisepunkterstellen">
    <h1 class="center">Reisepunkt erstellen</h1>
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
  max-width: 25em;
  padding: 0.5em;
  background-color: rgba(0, 114, 89, 0.85);
  color: #fff;
}
#reisepunkterstellen .grid-container {
  display: grid;
  row-gap: 0.5em;
  margin-bottom: 1em;
}
#reisepunkterstellen .grid-item-left {
  grid-column-start: 1;
  grid-column-end: 2;
  text-align: right;
}
#reisepunkterstellen .grid-item-right {
  grid-column-start: 2;
  grid-column-end: 3;
}
#reisepunkterstellen .grid-item-heading {
  grid-column-start: 1;
  grid-column-end: 4;
  margin: 0;
  text-align: center;
}
#reisepunkterstellen .sh-button {
  box-sizing: border-box;
  margin: 0.5em;
  padding: 0.5em 0;
  width: 8em;
  border-radius: 5px;
  border: 0;
}
#reisepunkterstellen .center {
  text-align: center;
}
#reisepunkterstellen .right {
  text-align: right;
}
#reisepunkterstellen .btn-gray {
  background-color: gray;
  color: #fff;
}
#reisepunkterstellen .btn-orange {
  background-color: orange;
  color: #fff;
}
</style>