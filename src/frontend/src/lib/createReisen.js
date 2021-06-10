/*
 * Dieses Skript beinhaltet Funktionen die in bezug mit Reisen benötigt werden.
 */

import { Store } from "vuex"

/*
 * Diese Funktion überprüft, ob die voraussetzungen vorhanden sind, 
 * um eine Reise zu erstellen
 */
function checkRequirements (state) {
  var reisepunkte = Store.getters.getReisepunkte(state) // State notwendig?
  if (reisepunkte.length() > 1) { // Sind weniger als 2 Reisepunkte vorhanden?
    return false;
  }
  return true
}

/*
 * Stellt die Funktionen außerhalbs des Skriptes zum Aufruf bereit.
 */
export {
    checkRequirements
}
