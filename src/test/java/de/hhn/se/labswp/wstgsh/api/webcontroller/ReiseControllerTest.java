package de.hhn.se.labswp.wstgsh.api.webcontroller;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.verify;

import de.hhn.se.labswp.wstgsh.api.models.*;

import de.hhn.se.labswp.wstgsh.api.models.NutzerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class ReiseControllerTest {

  @Mock
  private ReiseRepository reiseRepository;
  @Mock
  private ReisepunktRepository reisepunktRepository;
  @Mock
  private NutzerRepository nutzerRepository;

  private ReiseController underTest;

  @BeforeEach
  void setUp() {
    underTest = new ReiseController(reiseRepository, reisepunktRepository, nutzerRepository);
  }

/*  @Test
  void canGetAllReisen() {
    //when
    underTest.all();
    //then
    verify(reiseRepository).findAll();
  }

  @Test
  void canGetReiseWithId() {
    //given
    long id  = 10;
    given(reiseRepository.findById(id)).willReturn(java.util.Optional.of(new Reise()));
    //when
    underTest.one(id);
    //then
    verify(reiseRepository).findById(id);
  }

  @Test
  void reiseWithIdDoesntExist() {
    //given
    long id = 10;
    given(reiseRepository.findById(id)).willReturn(Optional.empty());
    //when
    //then
    assertThatThrownBy(() -> underTest.one(id))
            .isInstanceOf(IllegalStateException.class)
            .hasMessageContaining("Id nicht gefunden.");
  }

  @Test
  void canAddNewPunkt() {
    //given
    Nutzer nutzer = new Nutzer("Peter", "Lustig", "@mail", "pete",
            "password", REISENDER, false, true);
    List<Reisepunkt> reisepunkte = new ArrayList<>();
    List<Reisekatalog> reisekatalogs = new ArrayList<>();
    Reise reise = new Reise(new Date(), "TestReise", true,
            reisepunkte, reisekatalogs, nutzer);
    //when
    underTest.newReise(reise);
    //then
    ArgumentCaptor<Reise> reiseArgumentCaptor = ArgumentCaptor.forClass(Reise.class);
    verify(reiseRepository).save(reiseArgumentCaptor.capture());

    Reise capturedReise = reiseArgumentCaptor.getValue();

    assertThat(capturedReise).isEqualTo(reise);
  }

  @Test
  void canReplaceaReise() {
    //given
    Nutzer nutzer = new Nutzer("Peter", "Lustig", "@mail", "pete",
            "password", REISENDER, false, true);
    List<Reisepunkt> reisepunkte = new ArrayList<>();
    List<Reisekatalog> reisekatalogs = new ArrayList<>();

    Reise reise = new Reise(new Date(), "TestReise", true,
            reisepunkte, reisekatalogs, nutzer);
    Reise newReise = new Reise(new Date(), "NewTestReise", false,
            reisepunkte, reisekatalogs, nutzer);

    Long id = 1L;

    given(reiseRepository.findById(id)).willReturn(java.util.Optional.of(reise));
    given(reiseRepository.save(reise)).willReturn(reise);

    //when
    underTest.replaceReise(newReise, id);

    //then
    ArgumentCaptor<Reise> reiseArgumentCaptor = ArgumentCaptor.forClass(Reise.class);
    verify(reiseRepository).save(reiseArgumentCaptor.capture());
    Reise capturedReise = reiseArgumentCaptor.getValue();
    reise.setName("TestReise");
    reise.setOeffentlich(false);
    assertThat(capturedReise).isEqualTo(reise);
  }

  @Test
  void canDeleteaReise() {
    //given
    long id = 10;
    //when
    underTest.deleteReise(id);
    //then
    verify(reiseRepository).deleteById(id);
  }


  @Test
  void canAddaReisepunktToReise() {
    //given
    List<Reisepunkt> reisepunkte = new ArrayList<>();
    List<Reise> reisen = new ArrayList<>();
    Nutzer nutzer = new Nutzer("Peter", "Lustig", "@mail", "pete",
            "password", REISENDER, false, true);
    reisepunkte.add(new Reisepunkt(34L, 4.1f, 32.32f,
             "newTestReisepunkt", true,  reisen, nutzer));
    List<Reisekatalog> reisekatalogs = new ArrayList<>();
    Reise reise = new Reise(new Date(), "TestReise", true,
            reisepunkte, reisekatalogs, nutzer);
    reise.getReisepunkte().get(0).addReise(reise);

    Reisepunkt reisepunkt = new Reisepunkt(12L, 4.1f, 32.32f,
            "newTestReisepunkt", true, reisen, nutzer);

    long idReise = 1;
    long idReisepunkt = 12;

    given(reiseRepository.findById(idReise)).willReturn(java.util.Optional.of(reise));
    given(reiseRepository.save(reise)).willReturn(reise);

    given(reisepunktRepository.findById(idReisepunkt))
           .willReturn(java.util.Optional.of(reisepunkt));
    given(reisepunktRepository.save(reisepunkt)).willReturn(reisepunkt);

    //when
    underTest.addReisepunkt(idReisepunkt, idReise);

    //then
    ArgumentCaptor<Reise> reiseArgumentCaptor = ArgumentCaptor.forClass(Reise.class);
    verify(reiseRepository).save(reiseArgumentCaptor.capture());
    Reise capturedReise = reiseArgumentCaptor.getValue();
    reise.addReisepunkt(reisepunkt);
    assertThat(capturedReise).isEqualTo(reise);
  }

  @Test
  void reisepunktIsAlreadyAddedToReise() {
    //given
    List<Reise> reisen = new ArrayList<>();
    Nutzer nutzer = new Nutzer("Peter", "Lustig", "@mail", "pete",
            "password", REISENDER, false, true);
    Reisepunkt reisepunkt = new Reisepunkt(12L, 4.1f, 32.32f,
            "newTestReisepunkt", true, reisen, nutzer);

    List<Reisepunkt> reisepunkte = new ArrayList<>();
    reisepunkte.add(new Reisepunkt(34L, 4.1f, 32.32f,
            "newTestReisepunkt", true,  reisen, nutzer));
    reisepunkte.add(reisepunkt);
    List<Reisekatalog> reisekatalogs = new ArrayList<>();
    Reise reise = new Reise(new Date(), "TestReise", true,
            reisepunkte, reisekatalogs, nutzer);

    long idReise = 1;
    long idReisepunkt = 12;

    given(reiseRepository.findById(idReise)).willReturn(java.util.Optional.of(reise));

    given(reisepunktRepository.findById(idReisepunkt))
            .willReturn(java.util.Optional.of(reisepunkt));

    //when, then
    assertThatThrownBy(() -> underTest.addReisepunkt(idReisepunkt, idReise))
            .isInstanceOf(IllegalStateException.class)
            .hasMessageContaining("Reisepunkt ist bereits in Reise vorhanden.");
  }

  @Test
  void canChangeThePrivacySettingOfReise() {
    //given
    List<Reisepunkt> reisepunkte = new ArrayList<>();
    List<Reisekatalog> reisekatalogs = new ArrayList<>();
    Nutzer nutzer = new Nutzer("Peter", "Lustig", "@mail", "pete",
            "password", REISENDER, false, true);
    Reise reise = new Reise(new Date(), "TestReise", false,
            reisepunkte, reisekatalogs, nutzer);

    Long id = 1L;

    given(reiseRepository.findById(id)).willReturn(java.util.Optional.of(reise));
    given(reiseRepository.save(reise)).willReturn(reise);

    //when
    underTest.changePrivacySetting(true, id);

    //then
    ArgumentCaptor<Reise> reiseArgumentCaptor = ArgumentCaptor.forClass(Reise.class);
    verify(reiseRepository).save(reiseArgumentCaptor.capture());
    Reise capturedReise = reiseArgumentCaptor.getValue();
    reise.setOeffentlich(true);
    assertThat(capturedReise).isEqualTo(reise);
  }*/
}
