package de.hhn.se.labswp.wstgsh.api.webcontroller;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import de.hhn.se.labswp.wstgsh.api.models.SehenswuerdigkeitRepository;

import de.hhn.se.labswp.wstgsh.api.models.NutzerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SehenswuerdigkeitControllerTest {

  @Mock
  private SehenswuerdigkeitRepository sehenswuerdigkeitRepository;
  @Mock
  private NutzerRepository nutzerRepository;
  private SehenswuerdigkeitController underTest;

  @BeforeEach
  void setUp() {
    underTest = new SehenswuerdigkeitController(sehenswuerdigkeitRepository, nutzerRepository);
  }

/*  *//**
   * Test if Sehenswuerdigkeit gets returned for specific id.
   *//*
  @Test
  void canGetAllSehenswuerdigkeiten() {
    //when
    underTest.getSehenswuerdigkeiten();
    //then
    verify(sehenswuerdigkeitRepository).findAll();
  }

  @Test
  void canGetSehenswuerdigkeitWithId() {
    //given
    long id  = 10;
    given(sehenswuerdigkeitRepository.findById(id))
            .willReturn(java.util.Optional.of(new Sehenswuerdigkeit()));
    //when
    underTest.getSehenswuerdigkeit(id);
    //then
    verify(sehenswuerdigkeitRepository).findById(id);
  }

  @Test
  void sehenswuerdigkeitWithIdDoesntExist() {
    //given
    long id = 10;
    given(sehenswuerdigkeitRepository.findById(id)).willReturn(Optional.empty());
    //when
    //then
    assertThatThrownBy(() -> underTest.getSehenswuerdigkeit(id))
            .isInstanceOf(IllegalStateException.class)
            .hasMessageContaining("ID nicht gefunden.");
  }

  @Test
  void newSehenswuerdigkeit() {
    //given
    List<Reise> reisen = new ArrayList<>();
    Nutzer nutzer = new Nutzer("Peter", "Lustig", "@mail", "pete",
            "password", REISENDER, false, true);
    Sehenswuerdigkeit temp = new Sehenswuerdigkeit(69L, 95.123f, 78.456f,
            "Sehenswürdig", "Eine Sehenswürdige Sehenswürdigkeit",
            true, reisen, nutzer);
    //when
    underTest.newSehenswuerdigkeit(temp);
    //then
    ArgumentCaptor<Sehenswuerdigkeit> sehenswuerdigkeitArgumentCaptor =
            ArgumentCaptor.forClass(Sehenswuerdigkeit.class);
    verify(sehenswuerdigkeitRepository).save(sehenswuerdigkeitArgumentCaptor.capture());

    Sehenswuerdigkeit capturedSehenswuerdigkeit = sehenswuerdigkeitArgumentCaptor.getValue();

    assertThat(capturedSehenswuerdigkeit).isEqualTo(temp);
  }

  @Test
  void nameIsToLong() {
    //given
    List<Reise> reisen = new ArrayList<>();
    Nutzer nutzer = new Nutzer("Peter", "Lustig", "@mail", "pete",
            "password", REISENDER, false, true);
    Sehenswuerdigkeit temp = new Sehenswuerdigkeit(69L, 95.123f, 78.456f,
            "Sehenswürdig mit einem Namen der zu Lang ist, \"\n"
                    + "            + \"als das wir diesen erlauben könnten\",\n"
                    + "            \"Eine Sehenswürdige Sehenswürdigkeit\"",
            "Eine Sehenswürdige Sehenswürdigkeit",
            true, reisen, nutzer);
    //when
    assertThatThrownBy(() -> underTest.newSehenswuerdigkeit(temp))
            .isInstanceOf(IllegalStateException.class)
            .hasMessageContaining("Name der Sehenswürdigkeit ist zu lang.");
  }

  @Test
 void editSehenswuerdigkeit() {
    //given
    Long id = 69L;
    List<Reise> reisen = new ArrayList<>();
    Nutzer nutzer = new Nutzer("Peter", "Lustig", "@mail", "pete",
            "password", REISENDER, false, true);
    Sehenswuerdigkeit temp = new Sehenswuerdigkeit(69L, 95.123f, 78.456f,
            "Sehenswürdig", "Eine Sehenswürdige Sehenswürdigkeit",
            true, reisen, nutzer);
    Sehenswuerdigkeit temp2 = new Sehenswuerdigkeit(id, 90.000f, 75.000f,
            "Sehenswürdig2", "Noch eine Sehenswürdige Sehenswürdigkeit",
            true, reisen, nutzer);
    underTest.newSehenswuerdigkeit(temp);
    //when
    underTest.editSehenswuerdigkeit(id, temp2);
    //then
    verify(sehenswuerdigkeitRepository).deleteById(id);
    verify(sehenswuerdigkeitRepository).save(temp2);
  }

  @Test
  void deleteSehenswuerdigkeit() {
    //given
    long id = 10;
    //when
    underTest.deleteSehenswuerdigkeit(id);
    //then
    verify(sehenswuerdigkeitRepository).deleteById(id);
  }*/
}