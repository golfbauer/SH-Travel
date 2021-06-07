package de.hhn.se.labswp.wstgsh.webcontroller;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import de.hhn.se.labswp.wstgsh.webapi.models.Sehenswuerdigkeit;
import de.hhn.se.labswp.wstgsh.webapi.models.SehenswuerdigkeitRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SehenswuerdigkeitControllerTest {

  @Mock
  private SehenswuerdigkeitRepository sehenswuerdigkeitRepository;
  private SehenswuerdigkeitController underTest;

  @BeforeEach
  void setUp() {
    underTest = new SehenswuerdigkeitController(sehenswuerdigkeitRepository);
  }

  /**
   * Test if Sehenswuerdigkeit gets returned for specific id.
   */
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
    Sehenswuerdigkeit temp = new Sehenswuerdigkeit(69L, 95.123f, 78.456f,
            "nutzerEmail@mail.com", "Sehenswürdig",
            "Eine Sehenswürdige Sehenswürdigkeit");
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
 void editSehenswuerdigkeit() {
    //given
    Long id = 69L;
    Sehenswuerdigkeit temp = new Sehenswuerdigkeit(id, 95.123f, 78.456f,
            "nutzerEmail@mail.com", "Sehenswürdig",
            "Eine Sehenswürdige Sehenswürdigkeit");
    Sehenswuerdigkeit temp2 = new Sehenswuerdigkeit(id, 90.000f, 75.000f,
            "nutzerEmail2@mail.com", "Sehenswürdig2",
            "Noch eine Sehenswürdige Sehenswürdigkeit");
    underTest.newSehenswuerdigkeit(temp);
    //when
    underTest.editSehenswuerdigkeit(id, temp2);
    //then
    verify(sehenswuerdigkeitRepository).deleteById(id);
    verify(sehenswuerdigkeitRepository).save(temp2);
  }

  @Test
  void editSehenswuerdigkeitThrowsExceptionIfIdMismatch() {
    //given
    Sehenswuerdigkeit temp = new Sehenswuerdigkeit(69L, 95.123f, 78.456f,
            "nutzerEmail@mail.com", "Sehenswürdig",
            "Eine Sehenswürdige Sehenswürdigkeit");
    Sehenswuerdigkeit temp2 = new Sehenswuerdigkeit(420L, 90.000f, 75.000f,
            "nutzerEmail2@mail.com", "Sehenswürdig2",
            "Noch eine Sehenswürdige Sehenswürdigkeit");
    underTest.newSehenswuerdigkeit(temp);
    //when
    //then
    assertThatThrownBy(() -> underTest.editSehenswuerdigkeit(69L, temp2))
            .isInstanceOf(IllegalStateException.class)
            .hasMessageContaining("Neue Sehenswuerdigkeit muss selbe id, wie die Alte haben.");
    verify(sehenswuerdigkeitRepository, never()).deleteById(any());
  }

  @Test
  void deleteSehenswuerdigkeit() {
    //given
    long id = 10;
    //when
    underTest.deleteSehenswuerdigkeit(id);
    //then
    verify(sehenswuerdigkeitRepository).deleteById(id);
  }
}