<template>
  <div id="reiseansichtcontainer">
    <div class="reiseansicht" id="reiseansicht">
      <h2 id="reisetitel">Reisename</h2>
      <ul id="reiseliste">
        <li class="reiseitem"><span class="reiseitemprefix">1</span>List Item One</li>
        <li class="reiseitem"><span class="reiseitemprefix">2</span>List Item Two</li>
        <li class="reiseitem"><span class="reiseitemprefix">3</span>List Item Three</li>
        <li class="reiseitem"><span class="reiseitemprefix">4</span>List Item Four</li>
        <li class="reiseitem"><span class="reiseitemprefix">5</span>List Item Five</li>
        <li class="reiseitem"><span class="reiseitemprefix">6</span>List Item Six</li>
        <li class="reiseitem"><span class="reiseitemprefix">1</span>List Item One</li>
        <li class="reiseitem"><span class="reiseitemprefix">2</span>List Item Two</li>
        <li class="reiseitem"><span class="reiseitemprefix">3</span>List Item Three</li>
        <li class="reiseitem"><span class="reiseitemprefix">4</span>List Item Four</li>
        <li class="reiseitem"><span class="reiseitemprefix">5</span>List Item Five</li>
        <li class="reiseitem"><span class="reiseitemprefix">6</span>List Item Six</li>
        <li class="reiseitem"><span class="reiseitemprefix">1</span>List Item One</li>
        <li class="reiseitem"><span class="reiseitemprefix">2</span>List Item Two</li>
        <li class="reiseitem"><span class="reiseitemprefix">3</span>List Item Three</li>
        <li class="reiseitem"><span class="reiseitemprefix">4</span>List Item Four</li>
        <li class="reiseitem"><span class="reiseitemprefix">5</span>List Item Five</li>
        <li class="reiseitem"><span class="reiseitemprefix">6</span>List Item Six</li>
        <li class="reiseitem"><span class="reiseitemprefix">1</span>List Item One</li>
        <li class="reiseitem"><span class="reiseitemprefix">2</span>List Item Two</li>
        <li class="reiseitem"><span class="reiseitemprefix">3</span>List Item Three</li>
        <li class="reiseitem"><span class="reiseitemprefix">4</span>List Item Four</li>
        <li class="reiseitem"><span class="reiseitemprefix">5</span>List Item Five</li>
        <li class="reiseitem"><span class="reiseitemprefix">6</span>List Item Six</li>
        <li class="reiseitem"><span class="reiseitemprefix">1</span>List Item One</li>
        <li class="reiseitem"><span class="reiseitemprefix">2</span>List Item Two</li>
        <li class="reiseitem"><span class="reiseitemprefix">3</span>List Item Three</li>
        <li class="reiseitem"><span class="reiseitemprefix">4</span>List Item Four</li>
        <li class="reiseitem"><span class="reiseitemprefix">5</span>List Item Five</li>
        <li class="reiseitem"><span class="reiseitemprefix">6</span>List Item Six</li>
      </ul>
    </div>
  </div>
</template>

<script>
export default {
  name: 'ReiseAnsicht'
}
</script>

<style scoped>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  z-index: 990;
}

#reiseansichtcontainer {
  position: absolute;
  right: 15%;
  top: 5vh;
}

#reiseansicht {
  width: 300px;
  height: 600px;
  position: fixed;
  border-bottom: 20px solid #000000AA;
  border-bottom-left-radius: 10px;
  border-bottom-right-radius: 10px;
  overflow: hidden;
  overflow-y: scroll;
}

#reisetitel {
  width: 300px;
  background: #000000AA;
  padding: 10px 20px;
  font-size: 20px;
  font-weight: 700;
  color: white;
  border-top-left-radius: 10px;
  border-top-right-radius: 10px;
}

#reiseliste {
  position: relative;
  background: #000000AA;
}

.reiseitem {
  list-style: none;
  padding: 10px;
  width: 100%;
  background: #00000011;
  box-shadow: 0 5px 25px rgba(0,0,0,.1);
  transition: transform .5s;
  color: white;
  text-align: left;
}

#reiseliste:hover reiseitem {
  opacity: 0.2;
}

.reiseitem:hover {
  transform: scale(1.1);
  z-index: 100;
  background: #FFFFFF04;
  box-shadow: 0 5px 25px rgba(0,0,0,.2);
  color: #fff;
  opacity: 1;
  font-weight: bold;
}

.reiseitemprefix {
  width: 20px;
  height: 20px;
  text-align: center;
  line-height: 20px;
  background: #FF9B71;
  color: #fff;
  display: inline-block;
  border-radius: 50%;
  margin-right: 10px;
  font-size: 12px;
  font-weight: 600;
  transform: translateY(-2px);
  float: left;
}

.reiseitem:hover reiseitemprefix {
  background: #fff;
  color: #FF9B71;
}
</style>
