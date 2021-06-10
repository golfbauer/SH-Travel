package de.hhn.se.labswp.wstgsh.webapi.Configuration;

import de.hhn.se.labswp.wstgsh.exceptions.ReisepunktNotFoundAdvice;
import de.hhn.se.labswp.wstgsh.webapi.models.Sehenswuerdigkeit;
import de.hhn.se.labswp.wstgsh.webapi.models.SehenswuerdigkeitRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class SehenswuerdigkeitConfig {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(ReisepunktNotFoundAdvice.class);

  /**
   * With the help of this method you can make new entries into the database.
   * Importent: Comment out the entries you already ran.
   * If you wont do that the program will crash, since the entries already have been made.
   * @param sehenswuerdigkeitRepository Translates created objects into the database tables.
   * @return Arguments which will be implemented into the database.
   */
  @Bean
  CommandLineRunner commandLineRunnerSehenswuerdigkeit(SehenswuerdigkeitRepository
                                                               sehenswuerdigkeitRepository) {
    return args -> {
      /*List<Sehenswuerdigkeit> list = new ArrayList<>();
      Sehenswuerdigkeit foehr = new Sehenswuerdigkeit(
              568L, 8.498268127441408f, 54.71708518710868f,
              "Henri@gmx.de", "Föhr",
              "Die Insel Föhr gehört zu den Nordfriesischen Inseln und zum Kreis "
                      + "Nordfriesland in Schleswig-Holstein. Föhr ist die größte und "
                      + "bevölkerungsreichste deutsche Insel ohne Landverbindung."
      );
      Sehenswuerdigkeit schiesgebiet = new Sehenswuerdigkeit(
              568L, 6.707153320312501f, 54.438102809740165f,
              "Henri@gmx.de", "Schießgebiet in der Nordsee",
              "Schießgebiet, welches von der Kriegsmarine zu Testzwecken verwendet wird."
      );
      Sehenswuerdigkeit wildesMoor = new Sehenswuerdigkeit(
              568L, 9.249114990234377f, 54.41533406218809f,
              "Dennis@gmx.de", "Wildes Moor bei Schwabstedt",
              "Das Wilde Moor bei Schwabstedt ist ein großräumiges Feuchtgebiet mit "
                      + "Hochmoor- und Niedermoorflächen, wechselfeuchtem Grünland und offenen "
                      + "Wasserflächen."
      );
      Sehenswuerdigkeit kaserne = new Sehenswuerdigkeit(
              568L, 8.498268127441408f, 54.71708518710868f,
              "Dennis@gmx.de", "Kaserne Nord Eckernförde",
              "Die militärische Tradition reicht bis 1912 zurück, als Eckernförde "
                      + "erstmalig Garnisonsstadt und Marinestützpunkt wurde. Auch zukünftig wird "
                      + "mit dem 1.Ubootgeschwader, Seebataillion, Kommando Spezialkräfte der "
                      + "Marine und weiteren Dienststellen die Marine in Eckernförde beheimatet "
                      + " sein."
      );
      Sehenswuerdigkeit halligenInsel = new Sehenswuerdigkeit(
              568L, 8.6011334579367f, 54.63848717848078f,
              "Brian@gmx.de", "Halligen Insel in Schleswig-Holstein",
              "Die Halligen sind kleine, nicht oder nur wenig geschützte Marschinseln "
                      + "vor den Küsten, die bei Sturmfluten überschwemmt werden können."
      );
      Sehenswuerdigkeit segberger = new Sehenswuerdigkeit(
              568L, 10.09471893310547f, 53.934161372242194f,
              "Brian@gmx.de", "Segeberger Staatsforst",
              "Der Segeberger Staatsforst – früher Segeberger Heide – liegt im Kreis "
                      + "Segeberg, hat eine Fläche von über 4.000 ha und ist damit hinter dem "
                      + "Sachsenwald das zweitgrößte zusammenhängende Waldgebiet in "
                      + "Schleswig-Holstein."
      );
      Sehenswuerdigkeit kuechensee = new Sehenswuerdigkeit(
              568L, 10.761795043945312f, 53.68989646326055f,
              "Ben@gmx.de", "Küchensee",
              "Der Küchensee, auch Großer Küchensee genannt, ist ein See im Kreis "
                      + "Herzogtum Lauenburg im deutschen Bundesland Schleswig-Holstein."
      );
      Sehenswuerdigkeit travenmunderStrand = new Sehenswuerdigkeit(
              568L, 10.8946f, 53.9568f,
              "Henri@gmx.de", "Travemünder Strand",
              "Der Travemünder Strand ist sehr breit und feinsandig, das Wasser wird"
                      + " nur langsam flach– ein perfekter Ort für gemeinsame Strandtage mit der "
                      + "Familie."
      );
      Sehenswuerdigkeit bucht = new Sehenswuerdigkeit(
              568L, 9.968032836914064f, 54.765691084839936f,
              "Henri@gmx.de", "Gettinger Bucht",
              "Die Geltinger Bucht ist eine Bucht der Ostsee an der nordöstlichen Küste "
                      + "von der Region Angeln am Ausgang der Flensburger Förde bei Gelting. Somit "
                      + "erstreckt sich die Bucht von Habernis im Nordwesten über das südlich "
                      + "gelegene Wackerballig zum östlich gelegenen Naturschutzgebiet Geltinger "
                      + "Birk."
      );
      Sehenswuerdigkeit leuchtturm = new Sehenswuerdigkeit(
              568L, 11.017671854493297f, 54.44096335508482f,
              "Henri@gmx.de", "Leuchtturm Fluegge",
              "Der Leuchtturm Flügge steht im Südwesten der Insel Fehmarn, "
                      + "wenige Kilometer westlich der Fehmarnsundbrücke."
      );
      list.add(foehr);
      list.add(schiesgebiet);
      list.add(wildesMoor);
      list.add(kaserne);
      list.add(halligenInsel);
      list.add(segberger);
      list.add(kuechensee);
      list.add(travenmunderStrand);
      list.add(bucht);
      list.add(leuchtturm);
      sehenswuerdigkeitRepository.saveAll(list);*/
    };
  }
}
