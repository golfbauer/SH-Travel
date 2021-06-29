package de.hhn.se.labswp.wstgsh.api.webcontroller;

import static org.mockito.Mockito.verify;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import de.hhn.se.labswp.wstgsh.api.models.*;
import de.hhn.se.labswp.wstgsh.api.models.NutzerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ReisekatalogControllerTest {
  @Mock
  private ReisekatalogRepository reisekatalogRepository;

  @Mock
  private ReiseRepository reiseRepository;

  @Mock
  private NutzerRepository nutzerRepository;

  private ReisekatalogController underTest;

  @BeforeEach
  void setup() {
    underTest = new ReisekatalogController(reisekatalogRepository, reiseRepository, nutzerRepository);
  }

/*  @Test
  void canGetAllReisekataloge() {
    //when
    underTest.all();
    //then
    verify(reisekatalogRepository).findAll();
  }

  @Test
  void canGetReisekatalogWithId() {
    //given
    long id = 10;
    given(reisekatalogRepository.findById(id)).willReturn(java.util.Optional.of(new Reisekatalog()));
    //when
    underTest.one(id);
    //then
    verify(reisekatalogRepository).findById(id);
  }

  @Test
  void reisekatalogWithIdDoesntExist() {
    //given
    long id = 10;
    given(reisekatalogRepository.findById(id)).willReturn(Optional.empty());
    //then
    assertThatThrownBy(() -> underTest.one(id))
            .isInstanceOf(IllegalStateException.class)
            .hasMessageContaining("Id nicht gefunden.");
  }

  @Test
  void canDeleteReisekatalog() {
    //given
    long id = 10;
    //when
    underTest.deleteReise(id);
    //then
    verify(reisekatalogRepository).deleteById(id);
  }

  @Test
  void canAddReiseToReisekatalog() {
    //given
    List<Reisepunkt> reisepunkte = new ArrayList<>();
    List<Reise> reisen = new ArrayList<>();
    Nutzer nutzer = new Nutzer("Peter", "Lustig", "@mail", "pete",
            "password", REISENDER, false, true);
    Reisepunkt reisepunkt = new Reisepunkt(12L, 4.1f, 32.32f,
            "newTestReisepunkt", true, reisen, nutzer);
    reisepunkte.add(reisepunkt);
    List<Reisekatalog> reisekataloge = new ArrayList<>();
    Reise reise = new Reise(4L, new Date(), "TestReise", true,
            reisepunkte, reisekataloge, nutzer);
    Long id = 1L;
    Reisekatalog reisekatalog = new Reisekatalog(id, reisen, nutzer);

    given(reisekatalogRepository.findById(id)).willReturn(Optional.of(reisekatalog));
    given(reisekatalogRepository.save(reisekatalog)).willReturn(reisekatalog);

    given(reiseRepository.findById(4L)).willReturn(Optional.of(reise));
    given(reiseRepository.save(reise)).willReturn(reise);

    //when
    underTest.addReise(reise.getId(), id);
    //then
    ArgumentCaptor<Reisekatalog> reisekatalogArgumentCaptor = ArgumentCaptor.forClass(Reisekatalog.class);
    verify(reisekatalogRepository).save(reisekatalogArgumentCaptor.capture());
    Reisekatalog capturedReisekatalog = reisekatalogArgumentCaptor.getValue();
    reisekatalog.addReise(reise);
    assertThat(capturedReisekatalog).isEqualTo(reisekatalog);
  }

  @Test
  void addReiseThrowsExceptionIfAlreayAdded() {
    //given
    Long id = 10L;
    List<Reisepunkt> reisepunkte = new ArrayList<>();
    List<Reise> reisenR = new ArrayList<>();
    Nutzer nutzer = new Nutzer("Peter", "Lustig", "@mail", "pete",
            "password", REISENDER, false, true);
    Reisepunkt reisepunkt = new Reisepunkt(12L, 4.1f, 32.32f,
            "newTestReisepunkt", true, reisenR, nutzer);
    reisepunkte.add(reisepunkt);
    List<Reisekatalog> reisekataloge = new ArrayList<>();
    Reise reise = new Reise(4L, new Date(), "TestReise", true,
            reisepunkte, reisekataloge, nutzer);
    List<Reise> reisen = new ArrayList<>();
    reisen.add(reise);


    Reisekatalog reisekatalog = new Reisekatalog(id, reisen, nutzer);

    given(reisekatalogRepository.findById(id)).willReturn(Optional.of(reisekatalog));
    given(reiseRepository.findById(4L)).willReturn(Optional.of(reise));

    //then
    assertThatThrownBy(() -> underTest.addReise(reise.getId(), id))
            .isInstanceOf(IllegalStateException.class)
            .hasMessageContaining("Reisekatalog enthält bereits Reise.");
  }*/
}
