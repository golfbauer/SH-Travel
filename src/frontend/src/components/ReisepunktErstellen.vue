<template>
  <div>
    <h1>Reisepunkt erstellen</h1>
    <form>
      <label for="rpname">Reisepunktname:</label>
      <input type="text" id="rpname" name="rpname"><br />
      <label for="rpdescription">Beschreibung:</label>
      <input type="text" id="rpdescription" name="rpdescription"><br />
      <h3>Typ</h3>
      <input type="radio" id="rppunkt" name="rptype" value="punkt">
      <label for="rppunkt">Attraktion</label><br />
      <input type="radio" id="rpattraktion" name="rptype" value="attraktion">
      <label for="rpattraktion">Attraktion</label><br />
      <input type="radio" id="rpsehenswuerdigkeit" name="rptype" value="sehenswuerdigkeit">
      <label for="rpsehenswuerdigkeit">Sehenswürdigkeit</label>
    </form>
  </div>
</template>

<script>
export default {
  name: "ReisepunktErstellen"
}
</script>

<style scoped>

</style>