package de.hhn.se.labswp.wstgsh.api.webcontroller;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.verify;

import de.hhn.se.labswp.wstgsh.api.models.AttraktionRepository;

import de.hhn.se.labswp.wstgsh.api.models.NutzerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AttraktionControllerTest {

  @Mock
  private AttraktionRepository attraktionRepository;
  @Mock
  private NutzerRepository nutzerRepository;
  @Mock
  private ReisepunktController reisepunktController;
  private AttraktionController underTest;

  @BeforeEach
  void setUp() {
    underTest = new AttraktionController(attraktionRepository, nutzerRepository,
            reisepunktController);
  }

  /*@Test
  void canGetAllAttraktionen() {
    //when
    underTest.all();
    //then
    verify(attraktionRepository).findAll();
  }

  @Test
  void canGetAttraktionWithId() {
    long id  = 10;
    given(attraktionRepository.findById(id)).willReturn(java.util.Optional.of(new Attraktion()));
    //when
    underTest.one(id);
    //then
    verify(attraktionRepository).findById(id);
  }

  @Test
  void attraktionWithIdDoesntExist() {
    //given
    long id = 10;
    given(attraktionRepository.findById(id)).willReturn(Optional.empty());
    //when
    //then
    assertThatThrownBy(() -> underTest.one(id))
            .isInstanceOf(IllegalStateException.class)
            .hasMessageContaining("Id nicht gefunden");
  }

  @Test
  void canAddNewAttraktionWithTime() {
    //given
    List<Reise> reisen = new ArrayList<>();
    Nutzer nutzer = new Nutzer("Peter", "Lustig", "@mail", "pete",
            "password", REISENDER, false, true);
    Attraktion attraktion = new Attraktion(45.6F, 45.6F, "Eine Attraktion",
            "Dies ist ein test", true, reisen, nutzer);
    DayOfWeek dayOfWeek = DayOfWeek.of(5);
    attraktion.getAttraktionOeffnungszeiten().add(new AttraktionOeffnungszeit(
            dayOfWeek, LocalTime.of(8, 0), LocalTime.of(19, 0), attraktion
    ));
    //when
    underTest.newAttraktion(attraktion);
    //then
    ArgumentCaptor<Attraktion> attraktionArgumentCaptor = ArgumentCaptor.forClass(Attraktion.class);
    verify(attraktionRepository).save(attraktionArgumentCaptor.capture());

    Attraktion attraktionCaptured = attraktionArgumentCaptor.getValue();

    assertThat(attraktionCaptured).isEqualTo(attraktion);
  }

  @Test
  void canAddNewAttraktionWithGanztaegig() {
    //given
    List<Reise> reisen = new ArrayList<>();
    Nutzer nutzer = new Nutzer("Peter", "Lustig", "@mail", "pete",
            "password", REISENDER, false, true);
    Attraktion attraktion = new Attraktion(45.6F, 45.6F, "Eine Attraktion",
            "Dies ist ein test", true, reisen, nutzer);
    DayOfWeek dayOfWeek = DayOfWeek.of(5);
    attraktion.getAttraktionOeffnungszeiten().add(new AttraktionOeffnungszeit(
            dayOfWeek, true, attraktion
    ));
    given(underTest.findNutzer()).willReturn(Optional.of(nutzer));
    given(SecurityContextHolder.getContext().getAuthentication().getName()).willReturn("@mail");
    given(nutzerRepository.findByEmail("@mail")).willReturn(Optional.of(nutzer));
    //when
    underTest.newAttraktion(attraktion);
    //then
    ArgumentCaptor<Attraktion> attraktionArgumentCaptor = ArgumentCaptor.forClass(Attraktion.class);
    verify(attraktionRepository).save(attraktionArgumentCaptor.capture());

    Attraktion attraktionCaptured = attraktionArgumentCaptor.getValue();

    assertThat(attraktionCaptured).isEqualTo(attraktion);
  }

  @Test
  void canAddNewOeffnungszeitToAttraktion() {
    //given
    Long id = 10L;
    List<Reise> reisen = new ArrayList<>();
    Nutzer nutzer = new Nutzer("Peter", "Lustig", "@mail", "pete",
            "password", REISENDER, false, true);
    Attraktion attraktion = new Attraktion(45.6F, 45.6F, "Eine Attraktion",
            "Dies ist ein test", true, reisen, nutzer);
    AttraktionOeffnungszeit oeffnungszeit = new AttraktionOeffnungszeit(
            DayOfWeek.of(5), LocalTime.of(8, 0), LocalTime.of(19, 0),
            attraktion
    );

    given(attraktionRepository.findById(id)).willReturn(Optional.of(attraktion));
    given(attraktionRepository.save(attraktion)).willReturn(attraktion);
    //when
    underTest.addOeffnungszeit(oeffnungszeit, id);
    //then
    ArgumentCaptor<Attraktion> attraktionArgumentCaptor = ArgumentCaptor.forClass(Attraktion.class);
    verify(attraktionRepository).save(attraktionArgumentCaptor.capture());

    Attraktion attraktionCaptured = attraktionArgumentCaptor.getValue();
    attraktion.getAttraktionOeffnungszeiten().add(oeffnungszeit);
    assertThat(attraktionCaptured).isEqualTo(attraktion);
  }

  @Test
  void canEditaAttraktion() {
    //given
    Long id = 10L;
    List<Reise> reisen = new ArrayList<>();
    Nutzer nutzer = new Nutzer("Peter", "Lustig", "@mail", "pete",
            "password", REISENDER, false, true);
    Attraktion attraktion = new Attraktion(45.6F, 45.6F, "Eine Attraktion",
            "Dies ist ein test", true, reisen, nutzer);
    Attraktion newAttraktion = new Attraktion(45.6F, 45.6F,
            "Replaced", "Dies ist ein test der ersetzt wurde", true,
            reisen, nutzer);

    given(attraktionRepository.findById(id)).willReturn(Optional.of(attraktion));
    given(attraktionRepository.save(attraktion)).willReturn(attraktion);
    //when
    underTest.replaceAttraktion(newAttraktion, id);
    //then
    ArgumentCaptor<Attraktion> attraktionArgumentCaptor = ArgumentCaptor.forClass(Attraktion.class);
    verify(attraktionRepository).save(attraktionArgumentCaptor.capture());

    Attraktion attraktionCaptured = attraktionArgumentCaptor.getValue();
    attraktion.setName("Replaced");
    attraktion.setBeschreibung("Dies ist ein test der ersetzt wurde");
    assertThat(attraktionCaptured).isEqualTo(attraktion);
  }

  @Test
  void canDeleteaAttraktion() {
    //given
    long id = 10;
    //when
    underTest.deleteAttraktion(id);
    //then
    verify(attraktionRepository).deleteById(id);
  }*/
}