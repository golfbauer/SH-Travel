package de.hhn.se.labswp.wstgsh.api.Configuration;

import de.hhn.se.labswp.wstgsh.exceptions.ReisepunktNotFoundAdvice;
import de.hhn.se.labswp.wstgsh.api.models.*;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for configuring Attraktions.
 */
@Configuration
public class AttraktionConfig {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(ReisepunktNotFoundAdvice.class);

  /**
   * With the help of this method you can make new entries into the database.
   * Importent: Comment out the entries you already ran.
   * If you wont do that the program will crash, since the entries already have been made.
   *
   * @param attraktionRepository Translates created objects into the database tables.
   * @return Arguments which will be implemented into the database.
   */
  @Bean
  CommandLineRunner commandLineRunnerAttraktion(AttraktionRepository attraktionRepository,
                                                AttraktionOeffnungszeitRepository
                                                        attraktionOeffnungszeitRepository) {
    return args -> {
     /* List<Attraktion> list = new ArrayList<>();

      Attraktion one = new Attraktion(
              10.67966025840837f, 53.8662401645461f, "Museum Holstentor",
              "Das Lübecker Holstentor ist wohl das bekannteste Stadttor in Deutschland. Es wurde"
                      + " zwischen 1464 und 1478 von dem Lübecker Ratsbaumeister Hinrich Helmstede "
                      + "bei einer Modernisierung der Befestigungsanlagen an der Traveseite " +
                      "erbaut.", true
      );
      one.getAttraktionOeffnungszeiten().add(new AttraktionOeffnungszeit(
              DayOfWeek.of(1), LocalTime.of(11, 0), LocalTime.of(17, 0), one
      ));
      one.getAttraktionOeffnungszeiten().add(new AttraktionOeffnungszeit(
              DayOfWeek.of(2), LocalTime.of(11, 0), LocalTime.of(17, 0), one
      ));
      one.getAttraktionOeffnungszeiten().add(new AttraktionOeffnungszeit(
              DayOfWeek.of(3), LocalTime.of(11, 0), LocalTime.of(17, 0), one
      ));
      one.getAttraktionOeffnungszeiten().add(new AttraktionOeffnungszeit(
              DayOfWeek.of(4), LocalTime.of(11, 0), LocalTime.of(17, 0), one
      ));
      one.getAttraktionOeffnungszeiten().add(new AttraktionOeffnungszeit(
              DayOfWeek.of(5), LocalTime.of(11, 0), LocalTime.of(17, 0), one
      ));
      one.getAttraktionOeffnungszeiten().add(new AttraktionOeffnungszeit(
              DayOfWeek.of(6), LocalTime.of(11, 0), LocalTime.of(17, 0), one
      ));
      one.getAttraktionOeffnungszeiten().add(new AttraktionOeffnungszeit(
              DayOfWeek.of(7), LocalTime.of(11, 0), LocalTime.of(17, 0), one
      ));
      list.add(one);
      Attraktion two = new Attraktion(
              10.230732061508025f, 54.41217026940215f, "Marine-Ehrenmal"
              + " Laboe", "Das Lübecker Holstentor ist wohl das bekannteste Stadttor in "
              + "Deutschland. Es wurde zwischen 1464 und 1478 von dem Lübecker Ratsbaumeister "
              + "Hinrich Helmstede bei einer Modernisierung der Befestigungsanlagen an der "
              + "Traveseite erbaut.", true
      );
      one.getAttraktionOeffnungszeiten().add(new AttraktionOeffnungszeit(
              DayOfWeek.of(1), LocalTime.of(9, 30), LocalTime.of(18, 0), two
      ));
      one.getAttraktionOeffnungszeiten().add(new AttraktionOeffnungszeit(
              DayOfWeek.of(2), LocalTime.of(9, 30), LocalTime.of(18, 0), two
      ));
      one.getAttraktionOeffnungszeiten().add(new AttraktionOeffnungszeit(
              DayOfWeek.of(3), LocalTime.of(9, 30), LocalTime.of(18, 0), two
      ));
      one.getAttraktionOeffnungszeiten().add(new AttraktionOeffnungszeit(
              DayOfWeek.of(4), LocalTime.of(9, 30), LocalTime.of(18, 0), two
      ));
      one.getAttraktionOeffnungszeiten().add(new AttraktionOeffnungszeit(
              DayOfWeek.of(5), LocalTime.of(9, 30), LocalTime.of(18, 0), two
      ));
      one.getAttraktionOeffnungszeiten().add(new AttraktionOeffnungszeit(
              DayOfWeek.of(6), LocalTime.of(9, 30), LocalTime.of(18, 0), two
      ));
      one.getAttraktionOeffnungszeiten().add(new AttraktionOeffnungszeit(
              DayOfWeek.of(7), LocalTime.of(9, 30), LocalTime.of(18, 0), two
      ));
      list.add(two);
      Attraktion three = new Attraktion(
              8.941200474376009f, 54.31506447239934f, "Nationalpark"
              + "-Zentrum Multimar Wattforum", "Das Multimar Wattforum in Tönning ist das größte "
              + "Informationszentrum für den Nationalpark Schleswig-Holsteinisches Wattenmeer.",
              true
      );
      one.getAttraktionOeffnungszeiten().add(new AttraktionOeffnungszeit(
              DayOfWeek.of(1), LocalTime.of(10, 0), LocalTime.of(17, 0), three
      ));
      one.getAttraktionOeffnungszeiten().add(new AttraktionOeffnungszeit(
              DayOfWeek.of(2), LocalTime.of(10, 0), LocalTime.of(17, 0), three
      ));
      one.getAttraktionOeffnungszeiten().add(new AttraktionOeffnungszeit(
              DayOfWeek.of(3), LocalTime.of(10, 0), LocalTime.of(17, 0), three
      ));
      one.getAttraktionOeffnungszeiten().add(new AttraktionOeffnungszeit(
              DayOfWeek.of(4), LocalTime.of(10, 0), LocalTime.of(17, 0), three
      ));
      one.getAttraktionOeffnungszeiten().add(new AttraktionOeffnungszeit(
              DayOfWeek.of(5), LocalTime.of(10, 0), LocalTime.of(17, 0), three
      ));
      one.getAttraktionOeffnungszeiten().add(new AttraktionOeffnungszeit(
              DayOfWeek.of(6), LocalTime.of(10, 0), LocalTime.of(17, 0), three
      ));
      one.getAttraktionOeffnungszeiten().add(new AttraktionOeffnungszeit(
              DayOfWeek.of(7), LocalTime.of(10, 0), LocalTime.of(17, 0), three
      ));
      list.add(three);
      Attraktion four = new Attraktion(
              10.61980292192798f, 54.137690339032844f, "Schloss Eutin",
              "Das Eutiner Schloss in Eutin in Ostholstein bildet den kulturellen Mittelpunkt "
                      + "und die Keimzelle der Stadt und gehört neben dem Gottorfer und dem "
                      + "Glücksburger Schloss zu den bedeutendsten höfischen Profanbauten "
                      + "Schleswig-Holsteins.", true
      );
      one.getAttraktionOeffnungszeiten().add(new AttraktionOeffnungszeit(
              DayOfWeek.of(1), false, one
      ));
      one.getAttraktionOeffnungszeiten().add(new AttraktionOeffnungszeit(
              DayOfWeek.of(2), LocalTime.of(11, 0), LocalTime.of(17, 0), four
      ));
      one.getAttraktionOeffnungszeiten().add(new AttraktionOeffnungszeit(
              DayOfWeek.of(3), LocalTime.of(11, 0), LocalTime.of(17, 0), four
      ));
      one.getAttraktionOeffnungszeiten().add(new AttraktionOeffnungszeit(
              DayOfWeek.of(4), LocalTime.of(11, 0), LocalTime.of(17, 0), four
      ));
      one.getAttraktionOeffnungszeiten().add(new AttraktionOeffnungszeit(
              DayOfWeek.of(5), LocalTime.of(11, 0), LocalTime.of(17, 0), four
      ));
      one.getAttraktionOeffnungszeiten().add(new AttraktionOeffnungszeit(
              DayOfWeek.of(6), LocalTime.of(11, 0), LocalTime.of(17, 0), four
      ));
      one.getAttraktionOeffnungszeiten().add(new AttraktionOeffnungszeit(
              DayOfWeek.of(7), LocalTime.of(11, 0), LocalTime.of(17, 0), four
      ));
      list.add(four);
      Attraktion five = new Attraktion(
              11.180696086047897f, 54.445266306761326f, "Meereszentrum "
              + "Fehmarn", "Das Meereszentrum Fehmarn ist ein Schauaquarium in Burg auf der "
              + "Ostseeinsel Fehmarn.", true
      );
      one.getAttraktionOeffnungszeiten().add(new AttraktionOeffnungszeit(
              DayOfWeek.of(1), LocalTime.of(10, 0), LocalTime.of(18, 0), five
      ));
      one.getAttraktionOeffnungszeiten().add(new AttraktionOeffnungszeit(
              DayOfWeek.of(2), LocalTime.of(10, 0), LocalTime.of(18, 0), five
      ));
      one.getAttraktionOeffnungszeiten().add(new AttraktionOeffnungszeit(
              DayOfWeek.of(3), LocalTime.of(10, 0), LocalTime.of(18, 0), five
      ));
      one.getAttraktionOeffnungszeiten().add(new AttraktionOeffnungszeit(
              DayOfWeek.of(4), LocalTime.of(10, 0), LocalTime.of(18, 0), five
      ));
      one.getAttraktionOeffnungszeiten().add(new AttraktionOeffnungszeit(
              DayOfWeek.of(5), LocalTime.of(10, 0), LocalTime.of(18, 0), five
      ));
      one.getAttraktionOeffnungszeiten().add(new AttraktionOeffnungszeit(
              DayOfWeek.of(6), LocalTime.of(10, 0), LocalTime.of(18, 0), five
      ));
      one.getAttraktionOeffnungszeiten().add(new AttraktionOeffnungszeit(
              DayOfWeek.of(7), LocalTime.of(10, 0), LocalTime.of(18, 0), five
      ));
      list.add(five);
      Attraktion six = new Attraktion(
              9.049576854519923f, 54.479807115477f, "Schloss vor Husum",
              "Das Schloss vor Husum ist der einzige erhaltene "
                      + "Schlossbau an der schleswig-holsteinischen Westküste. Es dient heute als"
                      + "Schlossmuseum.", true
      );
      one.getAttraktionOeffnungszeiten().add(new AttraktionOeffnungszeit(
              DayOfWeek.of(1), false, one
      ));
      one.getAttraktionOeffnungszeiten().add(new AttraktionOeffnungszeit(
              DayOfWeek.of(2), LocalTime.of(11, 0), LocalTime.of(17, 0), six
      ));
      one.getAttraktionOeffnungszeiten().add(new AttraktionOeffnungszeit(
              DayOfWeek.of(3), LocalTime.of(11, 0), LocalTime.of(17, 0), six
      ));
      one.getAttraktionOeffnungszeiten().add(new AttraktionOeffnungszeit(
              DayOfWeek.of(4), LocalTime.of(11, 0), LocalTime.of(17, 0), six
      ));
      one.getAttraktionOeffnungszeiten().add(new AttraktionOeffnungszeit(
              DayOfWeek.of(5), LocalTime.of(11, 0), LocalTime.of(17, 0), six
      ));
      one.getAttraktionOeffnungszeiten().add(new AttraktionOeffnungszeit(
              DayOfWeek.of(6), LocalTime.of(11, 0), LocalTime.of(17, 0), six
      ));
      one.getAttraktionOeffnungszeiten().add(new AttraktionOeffnungszeit(
              DayOfWeek.of(7), LocalTime.of(11, 0), LocalTime.of(17, 0), six
      ));
      list.add(six);
      Attraktion seven = new Attraktion(
              8.876653340384884f, 54.00096497337268f, "Seehundstation "
              + "Friedrichskoog", "Erleben Sie die in Deutschland einmalige gemischte Haltung von"
              + " Seehunden und Kegelrobben! Wir freuen uns auf Ihren Besuch!", true
      );
      one.getAttraktionOeffnungszeiten().add(new AttraktionOeffnungszeit(
              DayOfWeek.of(1), LocalTime.of(10, 0), LocalTime.of(18, 0), seven
      ));
      one.getAttraktionOeffnungszeiten().add(new AttraktionOeffnungszeit(
              DayOfWeek.of(2), LocalTime.of(10, 0), LocalTime.of(18, 0), seven
      ));
      one.getAttraktionOeffnungszeiten().add(new AttraktionOeffnungszeit(
              DayOfWeek.of(3), LocalTime.of(10, 0), LocalTime.of(18, 0), seven
      ));
      one.getAttraktionOeffnungszeiten().add(new AttraktionOeffnungszeit(
              DayOfWeek.of(4), LocalTime.of(10, 0), LocalTime.of(18, 0), seven
      ));
      one.getAttraktionOeffnungszeiten().add(new AttraktionOeffnungszeit(
              DayOfWeek.of(5), LocalTime.of(10, 0), LocalTime.of(18, 0), seven
      ));
      one.getAttraktionOeffnungszeiten().add(new AttraktionOeffnungszeit(
              DayOfWeek.of(6), LocalTime.of(10, 0), LocalTime.of(18, 0), seven
      ));
      one.getAttraktionOeffnungszeiten().add(new AttraktionOeffnungszeit(
              DayOfWeek.of(7), LocalTime.of(10, 0), LocalTime.of(18, 0), seven
      ));
      list.add(seven);
      Attraktion eight = new Attraktion(
              9.569216743829838f, 54.49705190997663f, "Wikinger Museum "
              + "Haithabu", "Das Wikinger Museum Haithabu ist eines der bedeutendsten "
              + "archäologischen Museen Deutschlands.", true
      );
      one.getAttraktionOeffnungszeiten().add(new AttraktionOeffnungszeit(
              DayOfWeek.of(1), LocalTime.of(9, 0), LocalTime.of(17, 0), eight
      ));
      one.getAttraktionOeffnungszeiten().add(new AttraktionOeffnungszeit(
              DayOfWeek.of(2), LocalTime.of(9, 0), LocalTime.of(17, 0), eight
      ));
      one.getAttraktionOeffnungszeiten().add(new AttraktionOeffnungszeit(
              DayOfWeek.of(3), LocalTime.of(9, 0), LocalTime.of(17, 0), eight
      ));
      one.getAttraktionOeffnungszeiten().add(new AttraktionOeffnungszeit(
              DayOfWeek.of(4), LocalTime.of(9, 0), LocalTime.of(17, 0), eight
      ));
      one.getAttraktionOeffnungszeiten().add(new AttraktionOeffnungszeit(
              DayOfWeek.of(5), LocalTime.of(9, 0), LocalTime.of(17, 0), eight
      ));
      one.getAttraktionOeffnungszeiten().add(new AttraktionOeffnungszeit(
              DayOfWeek.of(6), LocalTime.of(9, 0), LocalTime.of(17, 0), eight
      ));
      one.getAttraktionOeffnungszeiten().add(new AttraktionOeffnungszeit(
              DayOfWeek.of(7), LocalTime.of(9, 0), LocalTime.of(17, 0), eight
      ));
      list.add(eight);
      Attraktion nine = new Attraktion(
              8.297606365267928f, 54.90098700930191f, "Sylt-Aquarium",
              "Das Sylt Aquarium im Süden der Stadt Westerland auf Sylt ist ein "
                      + "zoologischer Garten, der sich auf Fische aus der Nordsee sowie tropische"
                      + " Fische aus Korallenriffen spezialisiert hat.", true
      );
      one.getAttraktionOeffnungszeiten().add(new AttraktionOeffnungszeit(
              DayOfWeek.of(1), LocalTime.of(10, 0), LocalTime.of(18, 0), nine
      ));
      one.getAttraktionOeffnungszeiten().add(new AttraktionOeffnungszeit(
              DayOfWeek.of(2), LocalTime.of(10, 0), LocalTime.of(18, 0), nine
      ));
      one.getAttraktionOeffnungszeiten().add(new AttraktionOeffnungszeit(
              DayOfWeek.of(3), LocalTime.of(10, 0), LocalTime.of(18, 0), nine
      ));
      one.getAttraktionOeffnungszeiten().add(new AttraktionOeffnungszeit(
              DayOfWeek.of(4), LocalTime.of(10, 0), LocalTime.of(18, 0), nine
      ));
      one.getAttraktionOeffnungszeiten().add(new AttraktionOeffnungszeit(
              DayOfWeek.of(5), LocalTime.of(10, 0), LocalTime.of(18, 0), nine
      ));
      one.getAttraktionOeffnungszeiten().add(new AttraktionOeffnungszeit(
              DayOfWeek.of(6), LocalTime.of(10, 0), LocalTime.of(18, 0), nine
      ));
      one.getAttraktionOeffnungszeiten().add(new AttraktionOeffnungszeit(
              DayOfWeek.of(7), LocalTime.of(10, 0), LocalTime.of(18, 0), nine
      ));
      list.add(nine);
      Attraktion ten = new Attraktion(
              10.780061975161248f, 54.07501846712684f,
              "HANSA-PARK Freizeit- und Familienpark", "Der "
              + "Hansa-Park ist ein saisonaler Freizeitpark in "
              + "Sierksdorf an der Ostsee. Der Park umfasst heute 460.000 m² mit mehr als 125 "
              + "Attraktionen.", true
      );
      one.getAttraktionOeffnungszeiten().add(new AttraktionOeffnungszeit(
              DayOfWeek.of(1), false, ten
      ));
      one.getAttraktionOeffnungszeiten().add(new AttraktionOeffnungszeit(
              DayOfWeek.of(2), false, ten
      ));
      one.getAttraktionOeffnungszeiten().add(new AttraktionOeffnungszeit(
              DayOfWeek.of(3), false, ten
      ));
      one.getAttraktionOeffnungszeiten().add(new AttraktionOeffnungszeit(
              DayOfWeek.of(4), false, ten
      ));
      one.getAttraktionOeffnungszeiten().add(new AttraktionOeffnungszeit(
              DayOfWeek.of(5), false, ten
      ));
      one.getAttraktionOeffnungszeiten().add(new AttraktionOeffnungszeit(
              DayOfWeek.of(6), false, ten
      ));
      one.getAttraktionOeffnungszeiten().add(new AttraktionOeffnungszeit(
              DayOfWeek.of(7), false, ten
      ));
      list.add(ten);
      Attraktion eleven = new Attraktion(
              10.769004821777346f, 54.01261077755602f,
              "Ostseetherme", "Erlebnisbad mit Saunalandschaft, Dampfbädern und Strandzugang. " +
              "Zwei große Wasserrutschen, ein Strömungskreisel und Wasserspritzen begeistern "
              + "Kinder. ", true
      );
      one.getAttraktionOeffnungszeiten().add(new AttraktionOeffnungszeit(
              DayOfWeek.of(1), LocalTime.of(10, 0), LocalTime.of(22, 0), eleven
      ));
      one.getAttraktionOeffnungszeiten().add(new AttraktionOeffnungszeit(
              DayOfWeek.of(2), LocalTime.of(10, 0), LocalTime.of(22, 0), eleven
      ));
      one.getAttraktionOeffnungszeiten().add(new AttraktionOeffnungszeit(
              DayOfWeek.of(3), LocalTime.of(10, 0), LocalTime.of(22, 0), eleven
      ));
      one.getAttraktionOeffnungszeiten().add(new AttraktionOeffnungszeit(
              DayOfWeek.of(4), LocalTime.of(10, 0), LocalTime.of(22, 0), eleven
      ));
      one.getAttraktionOeffnungszeiten().add(new AttraktionOeffnungszeit(
              DayOfWeek.of(5), LocalTime.of(10, 0), LocalTime.of(22, 0), eleven
      ));
      one.getAttraktionOeffnungszeiten().add(new AttraktionOeffnungszeit(
              DayOfWeek.of(6), LocalTime.of(10, 0), LocalTime.of(22, 0), eleven
      ));
      one.getAttraktionOeffnungszeiten().add(new AttraktionOeffnungszeit(
              DayOfWeek.of(7), LocalTime.of(10, 0), LocalTime.of(22, 0), eleven
      ));
      list.add(eleven);
      Attraktion twelve = new Attraktion(
              10.755615234375002f, 54.02703260975642f,
              "Park-golf", "Ein schöner Minigolfplatz mitten im Kurpark", true
      );
      one.getAttraktionOeffnungszeiten().add(new AttraktionOeffnungszeit(
              DayOfWeek.of(1), LocalTime.of(8, 30), LocalTime.of(12, 30), twelve
      ));
      one.getAttraktionOeffnungszeiten().add(new AttraktionOeffnungszeit(
              DayOfWeek.of(2), LocalTime.of(8, 30), LocalTime.of(12, 30), twelve
      ));
      one.getAttraktionOeffnungszeiten().add(new AttraktionOeffnungszeit(
              DayOfWeek.of(3), LocalTime.of(8, 30), LocalTime.of(12, 30), twelve
      ));
      one.getAttraktionOeffnungszeiten().add(new AttraktionOeffnungszeit(
              DayOfWeek.of(4), LocalTime.of(8, 30), LocalTime.of(18, 00), twelve
      ));
      one.getAttraktionOeffnungszeiten().add(new AttraktionOeffnungszeit(
              DayOfWeek.of(5), false, one
      ));
      one.getAttraktionOeffnungszeiten().add(new AttraktionOeffnungszeit(
              DayOfWeek.of(6), LocalTime.of(8, 30), LocalTime.of(12, 30), twelve
      ));
      one.getAttraktionOeffnungszeiten().add(new AttraktionOeffnungszeit(
              DayOfWeek.of(7), LocalTime.of(8, 30), LocalTime.of(12, 30), twelve
      ));
      list.add(twelve);
      attraktionRepository.saveAll(list);*/
    };
  }
}
