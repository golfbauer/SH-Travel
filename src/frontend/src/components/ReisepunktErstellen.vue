<template>
  <div class = AttraktionErstellen>
    <div class = HintergrundPanel>

      <!-- Anfang der oberen Buttons -->
      <div id = ButtonAttraktion>
        <button id = Btn_Attraktion>
          <p class = Text align = Center> Attraktion</p>
        </button>
      </div>

      <div id = ButtonSehenswürdigkeit>
        <button id = Btn_Sehenswürdigkeit>
          <p class = Text align = Center> Sehenswürdigkeit</p>
        </button>
      </div>

      <div id = ButtonPunkt>
        <button id = Btn_Punkt>
          <p class = Text align = Center> Punkt</p>
        </button>
      </div>
      <!-- End obere Buttons -->

      <!-- Start of textfields -->
      <div id = Inputfield_Name>
        <label class = Text> Name</label>
        <input class = Inputfield align = left>
      </div>

      <div id = Inputfield_Öffnungszeiten>
        <label class = Text> Öffnungszeiten</label>
        <input class = Inputfield align = left>
      </div>

      <div id = Inputfield_Beschreibung>
        <label class = Text> Beschreibung</label>
        <input class = InputfieldLarge align = left>
      </div>
      <!-- end of textfields -->

      <!-- Start Bilder -->
      <div id = Bilder>
        <label class = Text> Bilder</label>

        <div id = ButtonUpload>
          <button id = BtnUpload>
            <p class = TextSmall> Hochladen</p>
          </button>
        </div>

        <div id = ButtonSearch>
          <button id = BtnSearch>
            <p class = TextSmall> Durchsuchen</p>
          </button>
        </div>

      </div>
      <!-- Ende Bilder -->

      <!-- Start Ende -->
      <div id = ButtonsEnde>
        <div id = ButtonGrau>
          <button id = BtnGrau>
            <p class = Text>Abbrechen</p>
          </button>
        </div>
        <div id = ButtonOrange>
          <button id = BtnOrange>
            <p class = Text>Erstellen</p>
          </button>
        </div>
      </div>
      <!-- Ende Ende -->
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

#ButtonAttraktion {

  position: absolute;
  left: 66.67%;
  right: 0%;
  top: 0%;
  bottom: 0%;

  mix-blend-mode: normal;
}

#Btn_Attraktion {

  position: absolute;
  left: 0%;
  right: 0%;
  top: 0%;
  bottom: 0%;
  height: 50px;
  width: 202px;

  background: #32B2A3;
  border-radius: 5px;

}
#Btn_Sehenswürdigkeit {
  position: absolute;
  left: 0%;
  right: 0%;
  top: 0%;
  bottom: 0%;
  height: 50px;
  width: 202px;

  background: #147D72;
  border-radius: 5px;
}

#ButtonSehenswürdigkeit {
  position: absolute;
  left: 33.33%;
  right: 33.33%;
  top: 0%;
  bottom: 0%;

  mix-blend-mode: normal;
}

#ButtonPunkt {
  position: absolute;
  left: 0%;
  right: 66.67%;
  top: 0%;
  bottom: 0%;

  mix-blend-mode: normal;
}
#Btn_Punkt {
  position: absolute;
  left: 0%;
  right: 0%;
  top: 0%;
  bottom: 0%;
  height: 50px;
  width: 202px;

  background: #147D72;
  border-radius: 5px;
}

.Text{

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
/* Start der textfelder*/

#Inputfield_Name {
  position: absolute;
  left: 4.95%;
  right: 5.28%;
  top: 10.14%;
  bottom: 80%;
  width: 544px;
  height: 70px;

}

#Inputfield_Öffnungszeiten {

  position: absolute;
  left: 5.12%;
  right: 5.12%;
  top: 21.69%;
  bottom: 68.45%;

}

#Inputfield_Beschreibung {

  position: absolute;
  left: 5.12%;
  right: 5.12%;
  top: 33.24%;
  bottom: 56.9%;

}

.InputfieldLarge {
  position: absolute;
  left: 0%;
  right: 0%;
  top: 50%;
  bottom: -100%;
  width: 544px;
  height: 105px;
  margin-top: 15px;

  background: #ECECEC;
}

.Inputfield {
  position: absolute;
  left: 0%;
  right: 0%;
  top: 50%;
  bottom: 0%;
  width: 544px;
  height: 35px;
  margin-top: 15px;

  background: #ECECEC;
}
/* Ende der textfelder*/

#Bilder {

  position: absolute;
  width: 535px;
  height: 232px;
  left: 30px;
  top: 300px;

}

#ButtonUpload {

  position: absolute;
  left: 30px;
  right: 68.15%;
  top: 59.15%;
  bottom: 37.04%;

  mix-blend-mode: normal;
  opacity: 0.9;

}
#BtnUpload {

  position: absolute;
  left: 0%;
  right: 0%;
  top: 0%;
  bottom: 0%;
  width: 163px;
  height: 27px;

  background: #4F4F4F;
  border-radius: 5px;
}
#BtnSearch {

  position: absolute;
  left: 0%;
  right: 0%;
  top: 0%;
  bottom: 0%;
  width: 163px;
  height: 27px;

  background: #4F4F4F;
  border-radius: 5px;
}

#ButtonSearch {

  position: absolute;
  left: 32.67%;
  right: 40.43%;
  top: 59.15%;
  bottom: 37.04%;
  margin-left: 15px;

  mix-blend-mode: normal;
  opacity: 0.9;
}

.TextSmall {

  position: absolute;
  left: 0%;
  right: 0%;
  top: 0%;
  bottom: 0%;

  font-family: Fira Sans;
  font-style: normal;
  font-weight: normal;
  font-size: 14px;
  line-height: 17px;
  display: flex;
  align-items: center;
  text-align: center;

  color: #ECECEC;
}

#Horizontal Scrollbar {

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

#ButtonsEnde {

  position: absolute;
  width: 361px;
  height: 45px;
  left: 204px;
  top: 600px;
}

#ButtonGrau {

  position: absolute;
  left: 33.66%;
  right: 37.95%;
  top: 90.7%;
  bottom: 2.96%;
  margin-right: 20px;

  mix-blend-mode: normal;
  opacity: 0.9;
}

#BtnGrau {
  position: absolute;
  left: 0%;
  right: 0%;
  top: 0%;
  bottom: 0%;
  height: 45px;
  width: 172px;
  margin-right: 20px;

  background: #4F4F4F;
  border-radius: 5px;
}

#ButtonOrange {

  position: absolute;
  left: 64.85%;
  right: 6.77%;
  top: 90.7%;
  bottom: 2.96%;

  mix-blend-mode: normal;
  opacity: 0.9;
}

#BtnOrange {

  position: absolute;
  left: 0%;
  right: 0%;
  top: 0%;
  bottom: 0%;
  height: 45px;
  width: 172px;

  background: #FF9B71;
  border-radius: 5px;
}

</style>
