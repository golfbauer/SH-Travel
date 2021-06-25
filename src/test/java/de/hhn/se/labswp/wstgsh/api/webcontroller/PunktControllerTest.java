package de.hhn.se.labswp.wstgsh.api.webcontroller;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import de.hhn.se.labswp.wstgsh.api.models.PunktRepository;

import de.hhn.se.labswp.wstgsh.api.models.NutzerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PunktControllerTest {

  @Mock
  private PunktRepository punktRepository;
  @Mock
  NutzerRepository nutzerRepository;
  private PunktController underTest;

  @BeforeEach
  void setUp() {
    underTest = new PunktController(punktRepository, nutzerRepository);
  }

  /*@Test
  void canGetAllPunkte() {
    //when
    underTest.all();
    //then
    verify(punktRepository).findAll();
  }

  @Test
  void canGetPunktWithId() {
    //given
    long id  = 10;
    given(punktRepository.findById(id)).willReturn(java.util.Optional.of(new Punkt()));
    //when
    underTest.one(id);
    //then
    verify(punktRepository).findById(id);
  }

  @Test
  void punktWithIdDoesntExist() {
    //given
    long id = 10;
    given(punktRepository.findById(id)).willReturn(Optional.empty());
    //when
    //then
    assertThatThrownBy(() -> underTest.one(id))
            .isInstanceOf(IllegalStateException.class)
            .hasMessageContaining("Id nicht gefunden.");
  }

  @Test
  void canAddNewPunkt() {
    //given
    List<Reise> reisen = new ArrayList<>();
    Nutzer nutzer = new Nutzer("Peter", "Lustig", "@mail", "pete",
            "password", REISENDER, false, true);
    Punkt punkt = new Punkt(9999L, 45.6F, 45.6F, "Ein Punkt",
            true, reisen, nutzer);
    //when
    underTest.newPunktWithNutzer(punkt);
    //then
    ArgumentCaptor<Punkt> punktArgumentCaptor = ArgumentCaptor.forClass(Punkt.class);
    verify(punktRepository).save(punktArgumentCaptor.capture());

    Punkt capturedPunkt = punktArgumentCaptor.getValue();

    assertThat(capturedPunkt).isEqualTo(punkt);
  }

  @Test
  void nameIsToLong() {
    //given
    List<Reise> reisen = new ArrayList<>();
    Nutzer nutzer = new Nutzer("Peter", "Lustig", "@mail", "pete",
            "password", REISENDER, false, true);
    Punkt punkt = new Punkt(9999L, 45.6F, 45.6F,
            "Ein Punktwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww",
            true, reisen, nutzer);
    //when
    assertThatThrownBy(() -> underTest.newPunktWithNutzer(punkt))
            .isInstanceOf(IllegalStateException.class)
            .hasMessageContaining("Name des Punkts ist zu lang.");
  }

  @Test
  void canEditaPunkt() {
    //given
    long id = 9;
    List<Reise> reisen = new ArrayList<>();
    Nutzer nutzer = new Nutzer("Peter", "Lustig", "@mail", "pete",
            "password", REISENDER, false, true);
    Punkt punkt = new Punkt(9L, 45.6F, 45.6F, "Ein Punkt",
            true, reisen, nutzer);
    //when
    underTest.replacePunkt(punkt, id);
    //then
    verify(punktRepository).deleteById(id);
    verify(punktRepository).save(punkt);
  }

  @Test
  void willThrowExceptionCauseOfId() {
    //given
    long id = 9;
    List<Reise> reisen = new ArrayList<>();
    Nutzer nutzer = new Nutzer("Peter", "Lustig", "@mail", "pete",
            "password", REISENDER, false, true);
    Punkt punkt = new Punkt(10L, 45.6F, 45.6F, "Ein Punkt",
            true, reisen, nutzer);
    //when //then
    assertThatThrownBy(() -> underTest.replacePunkt(punkt, id))
            .isInstanceOf(IllegalStateException.class)
            .hasMessageContaining("Neuer Punkt muss selbe id, wie der Alte haben.");

    verify(punktRepository, never()).deleteById(any());
    verify(punktRepository, never()).save(any());
  }

  @Test
  void canDeleteaPunkt() {
    //given
    long id = 10;
    //when
    underTest.deletePunkt(id);
    //then
    verify(punktRepository).deleteById(id);
  }*/
}