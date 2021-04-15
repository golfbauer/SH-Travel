<template>
  <div class = AttraktionErstellen>
    <div class = HintergrundPanel>

      <button class="Auswahl_Punkt"> Punkt </button>
      <button class="Auswahl_Sehenswürdigkeit"> Sehenswürdigkeit </button>
      <button class="Auswahl_Attraktion"> Attraktion </button>

      <label class ="Label_Inputfield_Name"> Name</label>
      <input type = "text" class = Inputfield>
    </div>
  </div>

</template>

<script>
export default {
  name: 'ReisepunktErstellen'
}
</script>

<style lang="scss" scoped>

.AttraktionErstellen{
  position: absolute;
  width: 606px;
  height: 710px;
  z-index: 998;
}

.HintergrundPanel {
  position: absolute;
  left: 0%;
  right: 0%;
  top: 0%;
  bottom: 0%;

  background: #1B998B;
  border-radius: 5px;
}

.Auswahl_Punkt {
  position: absolute;
  left: 0%;
  right: 0%;
  top: 0%;
  bottom: 92.96%;
}

.Auswahl_Sehenswürdigkeit {
  position: absolute;
  left: 10%;
  right: 0%;
  top: 0%;
  bottom: 92.96%;
}

.Auswahl_Attraktion {
  position: absolute;
  left: 35%;
  right: 0%;
  top: 0%;
  bottom: 92.96%;
}

.Label_Inputfield_Öffnungszeiten {
  position: absolute;
  left: 4.95%;
  right: 5.28%;
  top: 10.14%;
  bottom: 80%;
}
.Inputfield {
  position: absolute;
  height: 30px;
  width: 200px;
  left: 0%;
  right: 0%;
  top: 50%;
  bottom: 0%;
  background: #ECECEC;
}

.Name {
  position: absolute;
  left: 0%;
  right: 0%;
  top: 0%;
  bottom: 50%;

  font-family: Fira Sans;
  font-style: normal;
  font-weight: normal;
  font-size: 24px;
  line-height: 29px;
  display: flex;
  align-items: center;

  color: #ECECEC;
}

.Label_Inputfield_Name {

  position: absolute;
  left: 5.12%;
  right: 5.12%;
  top: 21.69%;
  bottom: 68.45%;
}

.Label_Inputfield_Large {

  position: absolute;
  left: 5.12%;
  right: 5.12%;
  top: 33.24%;
  bottom: 56.9%;
}

.Beschreibung {
  position: absolute;
  left: 0%;
  right: 0%;
  top: 0%;
  bottom: 50%;

  font-family: Fira Sans;
  font-style: normal;
  font-weight: normal;
  font-size: 24px;
  line-height: 29px;
  display: flex;
  align-items: center;

  color: #ECECEC;
}

.Buttons {
  position: absolute;
  width: 361px;
  height: 45px;
  left: 204px;
  top: 644px;
}

.Button_Grau {

  position: absolute;
  left: 33.66%;
  right: 37.95%;
  top: 90.7%;
  bottom: 2.96%;

  mix-blend-mode: normal;
  opacity: 0.9;
}

.Reisen {
  position: absolute;
  left: 0%;
  right: 0%;
  top: 0%;
  bottom: 0%;

  font-family: Fira Sans;
  font-style: normal;
  font-weight: normal;
  font-size: 24px;
  line-height: 29px;
  display: flex;
  align-items: center;
  text-align: center;

  color: #ECECEC;
}

.Button_Orange {
  position: absolute;
  left: 64.85%;
  right: 6.77%;
  top: 90.7%;
  bottom: 2.96%;

  mix-blend-mode: normal;
  opacity: 0.9;
}

.Button {
  position: absolute;
  left: 0%;
  right: 66.67%;
  top: 0%;
  bottom: 0%;

  mix-blend-mode: normal;
}

.Button {
  position: absolute;
  left: 33.33%;
  right: 33.33%;
  top: 0%;
  bottom: 0%;

  mix-blend-mode: normal;
}

.Button_Small {
  position: absolute;
  left: 4.95%;
  right: 68.15%;
  top: 59.15%;
  bottom: 37.04%;

  mix-blend-mode: normal;
  opacity: 0.9;
}

.Horizontal Scrollbar {
  position: absolute;
  left: 5.12%;
  right: 8.25%;
  top: 85.07%;
  bottom: 13.52%;
}
.Background {
  position: absolute;
  left: 0%;
  right: 0%;
  top: 0%;
  bottom: 0%;

  background: #ECECEC;
}
.Bar {
  position: absolute;
  left: 0%;
  right: 9.33%;
  top: 0%;
  bottom: 0%;

  background: #4C5756;
}
</style>
